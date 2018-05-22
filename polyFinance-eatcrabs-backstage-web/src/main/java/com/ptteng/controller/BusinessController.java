package com.ptteng.controller;

import com.alibaba.fastjson.JSON;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.Debt;
import com.ptteng.domain.business.Invest;
import com.ptteng.domain.business.InvestDebt;
import com.ptteng.domain.business.Product;
import com.ptteng.dubbo.BackstageConsumer;
import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.backstage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 有关产品，债权的一些功能
 */
@Controller
public class BusinessController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private BackstageConsumer consumer;

    /**
     * 查看产品列表
     */
    @RequestMapping(value = "/a/product/list", method = RequestMethod.GET)
    public String getProductList(ProductsQuery query, Model model) {
        PageHandleBO<Product> pageHandleBO = consumer.getBusinessService().findProductsByQuery(query);
        model.addAttribute("products", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "productList";
    }

    /**
     * 查看单个产品
     */
    @ResponseBody
    @RequestMapping(value = "/a/product/{id}", method = RequestMethod.GET)
    public String querySingleProduct(@PathVariable Long id) {
        return JSON.toJSONString(consumer.getBusinessService().findSingleProduct(id));
    }

    /**
     * 新增产品
     */
    @RequestMapping(value = "/a/product/new", method = RequestMethod.POST)
    public String addSingleProduct(@RequestBody Product product, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(product);
        if (errorJson != null) {
            logger.info("新增产品接口接收的非法参数：{}", product.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("新增产品接口接收参数：{}", product.toString());
        Long id = consumer.getBusinessService().addNewProduct(product).getId();
        logger.warn("成功新增一个产品，产品id：{}", id);
        return "success";
    }

    /**
     * 产品上下架
     */
    @RequestMapping(value = "/a/product/onSale", method = RequestMethod.POST)
    public String onSaleProduct(@RequestBody OnSaleVO onSaleVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(onSaleVO);
        if (errorJson != null) {
            logger.info("上架（下架）接口接收的非法参数：{}", onSaleVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("上架（下架）接口接收参数：{}", onSaleVO.toString());
        if (consumer.getBusinessService().onSaleProduct(onSaleVO)) {
            logger.warn("上架（下架）产品成功，产品id：{}", onSaleVO.getId());
            return "success";
        }
        logger.warn("错误的上架（下架）操作，产品id：{}", onSaleVO.getId());
        model.addAttribute("code", -1);
        model.addAttribute("message", "产品不存在");
        return "exception";
    }

    /**
     * 查看债权列表
     */
    @RequestMapping(value = "/a/debt/list", method = RequestMethod.GET)
    public String getBannerList(DebtsQuery query, Model model) {
        //借款状态 -1：未使用 0：全部 1:使用中 2：已过期
        Integer type = query.getUsed();
        Long current = System.currentTimeMillis();
        if (type == null) {

        } else if (type == -1) {
            query.setMinStart(current);
        } else if (type == 1) {
            query.setMaxStart(current);
            query.setMinFinish(current);
        } else if (type == 2) {
            query.setMaxFinish(current);
        }
        PageHandleBO<Debt> pageHandleBO = consumer.getBusinessService().findDebtsByQuery(query);
        model.addAttribute("current", System.currentTimeMillis());
        model.addAttribute("debts", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "debtList";
    }

    /**
     * 查看单个债权
     */
    @RequestMapping(value = "/a/debt/{id}", method = RequestMethod.GET)
    public String getSingleDebt(@PathVariable Long id, Model model) {
        Debt debt = consumer.getBusinessService().findSingleDebt(id);
        if (debt == null) {
            model.addAttribute("code", -1);
            model.addAttribute("message", "该债务不存在");
            return "exception";
        }
        model.addAttribute("debt", consumer.getBusinessService().findSingleDebt(id));
        return "debt";
    }

    /**
     * 修改单个债权
     */
    @RequestMapping(value = "/a/debt/edit/{id}", method = RequestMethod.POST)
    public String editSingleDebt(@PathVariable Long id, @RequestBody Debt debt, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(debt);
        if (errorJson != null) {
            logger.info("修改债权接口接收的非法参数：{}", debt);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("修改债权接口接收参数：{}，id：{}", debt.toString(), id);
        if (consumer.getBusinessService().editSingleDebt(id, debt)) {
            logger.warn("成功修改债务，id：{}", debt.getId());
            return "success";
        }
        logger.warn("修改债务失败，id：{}", debt.getId());
        model.addAttribute("code", -1);
        model.addAttribute("message", "该债务不存在");
        return "exception";
    }

    /**
     * 新增单个债权
     */
    @RequestMapping(value = "/a/debt/new", method = RequestMethod.POST)
    public String addSingleDebt(@RequestBody Debt debt, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(debt);
        if (errorJson != null) {
            logger.info("新增债权接口接收的非法参数：{}", debt.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("新增债权接口接收参数：{}", debt.toString());
        Long id = consumer.getBusinessService().addNewDebt(debt).getId();
        logger.warn("成功新增一个债权，债权id：{}", id);
        return "success";
    }

    /**
     * 某个债务下已匹配投资列表
     */
    @RequestMapping(value = "/a/debt/matched", method = RequestMethod.GET)
    public String matchedInvestList(MatchesQuery query, Model model) {
        PageHandleBO<Invest> pageHandleBO = consumer.getBusinessService().findMatchedInvestByQuery(query);
        model.addAttribute("invests", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "investList";
    }

    /**
     * 推荐投资列表（债权匹配中）
     */
    @RequestMapping(value = "/a/debt/match/{id}", method = RequestMethod.GET)
    public String matchResult(@PathVariable Long id, RecommendQuery query, Model model) {
        PageHandleBO<Invest> pageHandleBO = consumer.getBusinessService().getRecommendation(id, query);
        model.addAttribute("invests", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "matchResult";
    }

    /**
     * 新增匹配
     */
    @RequestMapping(value = "/a/debt/match", method = RequestMethod.POST)
    public String matchNewInvest(@RequestBody InvestDebt investDebt, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(investDebt);
        if (errorJson != null) {
            logger.info("新增债权匹配接口接收的非法参数：{}", investDebt.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("新增债权匹配接口接收参数：{}", investDebt.toString());
        if (consumer.getBusinessService().matchNewInvest(investDebt)) {
            logger.warn("债权匹配成功，债务id：{}，投资id：{}", investDebt.getDebtId(), investDebt.getInvestId());
            return "success";
        }
        logger.warn("错误的债权匹配，债务id：{}，投资id：{}", investDebt.getDebtId(), investDebt.getInvestId());
        model.addAttribute("code", -1);
        model.addAttribute("message", "匹配失败");
        return "exception";
    }

}
