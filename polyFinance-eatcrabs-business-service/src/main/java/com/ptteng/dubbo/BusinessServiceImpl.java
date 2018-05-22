package com.ptteng.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.*;
import com.ptteng.repository.*;
import com.ptteng.utlis.SerialNumberUtil;
import com.ptteng.utlis.jpa.CopyUtil;
import com.ptteng.utlis.jpa.MySpecification;
import com.ptteng.utlis.jpa.PageableUtil;
import com.ptteng.service.BusinessService;
import com.ptteng.vo.backstage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.xml.transform.Templates;
import java.util.LinkedList;
import java.util.List;

/**
 * 债务、产品相关service实现类
 */
@Component
@Service(version = "1.0.0", interfaceName = "com.ptteng.service.BusinessService")
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private DebtDAO debtDAO;
    @Autowired
    private InvestDAO investDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private InvestDebtDAO investDebtDAO;
    @Autowired
    private TempOrderDAO tempOrderDAO;

    /**
     * 查询债务信息列表
     */
    @Override
    public PageHandleBO<Debt> findDebtsByQuery(DebtsQuery query) {
        PageHandleBO<Debt> pageHandleBO = new PageHandleBO<>();
        Page<Debt> debtPage = debtDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(debtPage.getTotalElements());
        pageHandleBO.setObjects(debtPage.getContent());
        return pageHandleBO;
    }

    /**
     * 查询已匹配的债务
     */
    @Override
    public PageHandleBO<Invest> findMatchedInvestByQuery(MatchesQuery query) {
        PageHandleBO<Invest> pageHandleBO = new PageHandleBO<>();
        Page<Invest> investPage = investDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(investPage.getTotalElements());
        pageHandleBO.setObjects(investPage.getContent());
        return pageHandleBO;
    }

    /**
     * 查询产品列表
     */
    @Override
    public PageHandleBO<Product> findProductsByQuery(ProductsQuery query) {
        PageHandleBO<Product> pageHandleBO = new PageHandleBO<>();
        Page<Product> productPage = productDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(productPage.getTotalElements());
        pageHandleBO.setObjects(productPage.getContent());
        return pageHandleBO;
    }

    /**
     * 查询一个产品
     */
    @Override
    public Product findSingleProduct(Long id) {
        Product product = productDAO.findOne(id);
        return product == null ? new Product() : product;
    }

    /**
     * 添加一个产品
     */
    @Override
    public Product addNewProduct(Product product) {
        // 注意金额都是以分为单位
        product.setLeast(500000);
        product.setStep(100000);
        product.setBegin("T(自然日)+1");
        // 限额 除了新手计划是5W，其他都是-1（无限制）
        if (product.getType() == 1) {
            product.setQuota(5000000);
        } else {
            product.setQuota(-1);
        }
        product.setOnsale(-1);
        product.setCreateAt(System.currentTimeMillis());
        product.setUpdateAt(System.currentTimeMillis());
        return productDAO.save(product);
    }

    /**
     * 上下架一个产品
     */
    @Override
    public boolean onSaleProduct(OnSaleVO onSaleVO) {
        Product user = productDAO.findOne(onSaleVO.getId());
        if (user == null) {
            return false;
        }
        user.setOnsale(-onSaleVO.getOnSale());
        productDAO.save(user);
        return true;
    }

    /**
     * 查看单个债务
     */
    @Override
    public Debt findSingleDebt(Long id) {
        return debtDAO.findOne(id);
    }

    /**
     * 修改单个债务
     */
    @Override
    public boolean editSingleDebt(Long id, Debt vo) {
        // 先find再判断最后save，避免出错而且不用重复fetch数据库
        Debt po = debtDAO.findOne(id);
        if (po == null) {
            return false;
        }
        CopyUtil.copyValue(po, vo);
        po.setId(id);
        po.setUpdateAt(System.currentTimeMillis());
        debtDAO.save(po);
        return true;
    }

    /**
     * 新增单个债权
     */
    @Override
    public Debt addNewDebt(Debt debt) {
        debt.setMatch(0L);
        debt.setTerm(debt.getFinish() - debt.getStart());
        debt.setCreateAt(System.currentTimeMillis());
        debt.setUpdateAt(System.currentTimeMillis());
        return debtDAO.save(debt);
    }

    /**
     * 查询推荐匹配的投资列表
     */
    @Override
    public PageHandleBO<Invest> getRecommendation(Long id, RecommendQuery query) {
        PageHandleBO<Invest> pageHandle = new PageHandleBO<>();
        Debt debt = debtDAO.findOne(id);
        if (debt == null) {
            List<Invest> invests = new LinkedList<>();
            pageHandle.setCount(0L);
            pageHandle.setObjects(invests);
            return pageHandle;
        }
        query.setIsMatched(-1L);
        query.setMaxAmount(debt.getAmount() - debt.getMatch());
        query.setMaxTime(debt.getFinish());
        Page<Invest> investPage = investDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandle.setCount(investPage.getTotalElements());
        pageHandle.setObjects(investPage.getContent());
        return pageHandle;
    }

    /**
     * 新增债权匹配
     */
    @Override
    public boolean matchNewInvest(InvestDebt investDebt) {
        Debt debt = debtDAO.findOne(investDebt.getDebtId());
        Invest invest = investDAO.findOne(investDebt.getInvestId());
        //如果没有匹配，该条invest字段的debt_id应该是-1
        if (debt == null || invest == null || invest.getDebtId() != -1L) {
            return false;
        }
        long current = System.currentTimeMillis();
        investDebt.setCreateAt(current);
        investDebtDAO.save(investDebt);
        invest.setDebtId(debt.getId());
        invest.setUpdateAt(current);
        investDAO.save(invest);
        debt.setMatch(debt.getMatch() + invest.getAmount());
        debt.setUpdateAt(current);
        debtDAO.save(debt);
        return true;
    }

    /**
     * 新增临时订单
     */
    @Override
    public boolean addNewTempOrder(Long userId, Long productId) {
        TempOrder tempOrder = tempOrderDAO.findByUserId(userId);
        System.out.println(tempOrder.toString());
        if(null != tempOrder){
            tempOrderDAO.delete(tempOrder);
        }
        tempOrder.setUserId(userId);
        tempOrder.setDealNumber(SerialNumberUtil.getInstance().getNumber());
        tempOrder.setProductId(productId);
        tempOrder.setAmount(0);
        tempOrder.setCreateAt(System.currentTimeMillis());
        tempOrderDAO.save(tempOrder);
        return true;
    }
}
