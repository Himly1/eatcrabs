package com.ptteng;

import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.Banner;
import com.ptteng.domain.business.Product;
import com.ptteng.dubbo.DeskConsumer;
import com.ptteng.vo.backstage.ProductsQuery;
import com.ptteng.vo.common.BannersQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DeskTest {
    @Autowired
    DeskConsumer deskConsumer;

    @Test
    public void indexTest(){
        BannersQuery bannersQuery = new BannersQuery();
        bannersQuery.setOnline(1);
        PageHandleBO<Banner> banners = deskConsumer.getOperationService().findBannersByQuery(bannersQuery);
        //首页精选产品
        ProductsQuery productsQuery = new ProductsQuery();
        productsQuery.setType(3);
        PageHandleBO<Product> products = deskConsumer.getBusinessService().findProductsByQuery(productsQuery);
    }
}
