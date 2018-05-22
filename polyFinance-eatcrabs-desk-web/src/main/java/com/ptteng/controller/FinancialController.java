package com.ptteng.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.DealRecord;
import com.ptteng.domain.business.Product;
import com.ptteng.domain.business.TempOrder;
import com.ptteng.domain.business.User;
import com.ptteng.dubbo.DeskConsumer;
import com.ptteng.vo.backstage.DealsQuery;
import com.ptteng.vo.backstage.ProductsQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 理财
 */
@Controller
public class FinancialController {
    public static Logger logger = LoggerFactory.getLogger(FinancialController.class);
    @Autowired
    private DeskConsumer consumer;

    /**
     * 产品列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/u/products", method = RequestMethod.GET)
    public String productList(Model model) {
        ProductsQuery query = new ProductsQuery();
        query.setOnSale(1);
        query.setSize(30);
        PageHandleBO<Product> products = consumer.getBusinessService().findProductsByQuery(query);
        model.addAttribute("products", products.getObjects());
        return "productList";
    }

    /**
     * 产品详情
     *
     * @param productId
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/products/{productId}/detail", method = RequestMethod.POST)
    public String productDetail(@PathVariable Long productId, Model model) {
        logger.info("产品详情接口接收参数 id：{}", productId);
        Product product = consumer.getBusinessService().findSingleProduct(productId);
        if (null == product || -1 == product.getOnsale()) {
            logger.warn("产品详情接口接收非法参数：{}", productId);
            model.addAttribute("code", -1);
            model.addAttribute("message", "非法的参数请求");
            return "exception";
        }

        DealsQuery query = new DealsQuery();
        query.setProductId(productId);
        query.setSuccess(1);
        String account = (String) SecurityUtils.getSubject().getPrincipal();
        logger.info("{} 用户 {} 产品的交易记录", account, productId);
        query.setUserMobile(account);
        PageHandleBO<DealRecord> dealRecords = consumer.getCustomerService().findDealsByQuery(query);
        logger.info(dealRecords.toString());
        model.addAttribute("product", product);
        model.addAttribute("dealRecords", dealRecords.getObjects());
        return "productDetail";
    }

    /**
     * 立即投资
     *
     * @param productId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/b/products/{productId}/invest", method = RequestMethod.POST)
    public String productInvest(@PathVariable Long productId, Model model) {
        logger.info("立即投资接口接收参数 id：{}", productId);
        //查看产品状态
        Product product = consumer.getBusinessService().findSingleProduct(productId);
        if (null == product || -1 == product.getOnsale()) {
            logger.warn("产品详情接口接收非法参数：{}", productId);
            model.addAttribute("code", -1);
            model.addAttribute("message", "非法的参数请求");
            return "exception";
        }
        //查看该用户认证情况
        String account = (String) SecurityUtils.getSubject().getPrincipal();
        User user = consumer.getCustomerService().getUserLoginToken(account);
        Integer userStatus = consumer.getCustomerService().userStatus(user.getId());
        logger.info(user.toString());
        //新增一个临时订单
        consumer.getBusinessService().addNewTempOrder(user.getId(), productId);
        //根据用户认证情况进行页面跳转
        model.addAttribute("code", userStatus);
        model.addAttribute("message", "请求成功");
        if (1 == userStatus || 4 == userStatus) {
            return model.toString();
        }
        model.addAttribute("userName", consumer.getCustomerService().getSingleUserInfo(user.getId()).getIdentity().getName());
        if (3 == userStatus) {
            return model.toString();
        }
        if (2 == userStatus) {
            model.addAttribute("idCard", consumer.getCustomerService().getSingleUserInfo(user.getId()).getIdentity().getIdCard());
        }
        return model.toString();
    }
}
