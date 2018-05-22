package com.ptteng.service;

import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.*;
import com.ptteng.vo.backstage.*;

import java.util.List;

public interface BusinessService {

    /**
     * 查询债务信息列表
     */
    PageHandleBO<Debt> findDebtsByQuery(DebtsQuery query);

    /**
     * 查询已匹配的债务
     */
    PageHandleBO<Invest> findMatchedInvestByQuery(MatchesQuery query);

    /**
     * 查询产品列表
     */
    PageHandleBO<Product> findProductsByQuery(ProductsQuery query);

    /**
     * 查询一个产品
     */
    Product findSingleProduct(Long id);

    /**
     * 添加一个产品
     */
    Product addNewProduct(Product product);

    /**
     * 上下架一个产品
     */
    boolean onSaleProduct(OnSaleVO onSaleVO);

    /**
     * 查看单个债务
     */
    Debt findSingleDebt(Long id);

    /**
     * 修改单个债务
     */
    boolean editSingleDebt(Long id, Debt debt);

    /**
     * 新增单个债权
     */
    Debt addNewDebt(Debt debt);

    /**
     * 查询推荐匹配的投资列表
     */
    PageHandleBO<Invest> getRecommendation(Long id, RecommendQuery query);

    /**
     * 新增债权匹配
     */
    boolean matchNewInvest(InvestDebt investDebt);

    /**
     * 新增临时订单
     */
    boolean addNewTempOrder(Long userId, Long productId);
}
