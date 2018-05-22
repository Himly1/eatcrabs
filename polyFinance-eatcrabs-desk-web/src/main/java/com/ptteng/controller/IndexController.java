package com.ptteng.controller;

import com.alibaba.fastjson.JSON;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.Banner;
import com.ptteng.domain.business.Product;
import com.ptteng.dubbo.DeskConsumer;
import com.ptteng.vo.backstage.ProductsQuery;
import com.ptteng.vo.common.BannersQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页
 */
@Controller
public class IndexController {
    public static Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    DeskConsumer consumer;

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/b/u/index", method = RequestMethod.GET)
    public String index(Model model) {
        //首页展示banner
        BannersQuery bannersQuery = new BannersQuery();
        bannersQuery.setOnline(1);
        PageHandleBO<Banner> banners = consumer.getOperationService().findBannersByQuery(bannersQuery);
        //首页精选产品
        ProductsQuery productsQuery = new ProductsQuery();
        productsQuery.setType(3);
        PageHandleBO<Product> products = consumer.getBusinessService().findProductsByQuery(productsQuery);

        model.addAttribute("banners", banners.getObjects());
        model.addAttribute("products", products.getObjects());
        return "index";
    }

    /**
     * banner详情
     * @param bannerId
     * @return
     */
    @ResponseBody
    @RequestMapping("/b/u/banners/{bannerId}/detail")
    public String bannerDetail(@PathVariable Long bannerId){
        Banner banner = consumer.getOperationService().getSingleBanner(bannerId);
        Map<String, Object> result = new HashMap<>();
        result.put("name", banner.getName());
        result.put("content", banner.getContent());
        result.put("img", banner.getImg());
        return JSON.toJSONString(result);
    }
}
