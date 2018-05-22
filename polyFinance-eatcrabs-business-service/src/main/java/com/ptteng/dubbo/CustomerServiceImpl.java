package com.ptteng.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.ptteng.bo.*;
import com.ptteng.domain.business.*;
import com.ptteng.repository.*;
import com.ptteng.utlis.MoneyUtil;
import com.ptteng.utlis.jpa.MySpecification;
import com.ptteng.utlis.jpa.PageableUtil;
import com.ptteng.service.CustomerService;
import com.ptteng.vo.backstage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 用户相关service实现类
 */
@Component
@Service(version = "1.0.0", interfaceName = "com.ptteng.service.CustomerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private DealRecordDAO dealRecordDAO;
    @Autowired
    private InvestDAO investDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private DepositorDAO depositorDAO;
    @Autowired
    private IdentityDAO identityDAO;
    @Autowired
    private UserCardDAO userCardDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private InterestDAO interestDAO;

    /**
     * 查询用户列表
     */
    @Override
    public PageHandleBO<CustomerBO> findCustomersByQuery(CustomersQuery query) {
        PageHandleBO<CustomerBO> pageHandleBO = new PageHandleBO<>();
        List<CustomerBO> list = new LinkedList<>();
        Page<User> userPage = userDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(userPage.getTotalElements());
        for (User user : userPage.getContent()) {
            CustomerBO customerBO = new CustomerBO();
            customerBO.setUser(user);
            Identity identity = identityDAO.findByUserId(user.getId());
            if (identity != null) {
                customerBO.setName(identity.getName());
            }
            customerBO.setDepositorInfoBO(getSingleDepositorInfo(user.getId()));
            list.add(customerBO);
        }
        pageHandleBO.setObjects(list);
        return pageHandleBO;
    }

    /**
     * 查询用户交易记录
     */
    @Override
    public PageHandleBO<DealRecord> findDealsByQuery(DealsQuery query) {
        PageHandleBO<DealRecord> pageHandleBO = new PageHandleBO<>();
        Page<DealRecord> dealRecords = dealRecordDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(dealRecords.getTotalElements());
        pageHandleBO.setObjects(dealRecords.getContent());
        return pageHandleBO;
    }

    /**
     * 查询用户投资记录
     */
    @Override
    public PageHandleBO<UserInvestBO> findInvestsByQuery(InvestsQuery query) {
        PageHandleBO<UserInvestBO> pageHandleBO = new PageHandleBO<>();
        Page<Invest> investPage = investDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(investPage.getTotalElements());
        List<Invest> invests = investPage.getContent();
        List<UserInvestBO> list = new LinkedList<>();
        for (Invest invest : invests) {
            UserInvestBO userInvestBO = new UserInvestBO();
            userInvestBO.setAllocationBO(MoneyUtil.getInvestAllo(invest));
            userInvestBO.setInvest(invest);
            list.add(userInvestBO);
        }
        pageHandleBO.setObjects(list);
        return pageHandleBO;
    }

    /**
     * 冻结（解冻）单个账户
     */
    @Override
    public boolean freezeSingleUser(FreezeVO freezeVO) {
        User user = userDAO.findOne(freezeVO.getId());
        if (user == null) {
            return false;
        }
        user.setFreeze(-freezeVO.getFreeze());
        userDAO.save(user);
        return true;
    }

    @Override
    public User getUserLoginToken(String account) {
        return userDAO.findByAccount(account);
    }

    /**
     * 个人详情页个人消息
     */
    @Override
    public UserInfoBO getSingleUserInfo(Long id) {
        UserInfoBO info = new UserInfoBO();
        User user = userDAO.findOne(id);
        if (user == null) {
            return null;
        }
        info.setUser(user);
        Identity identity = identityDAO.findOne(id);
        info.setIdentity(identity == null ? new Identity() : identity);
        return info;
    }

    /**
     * 更改用户手机号
     */
    @Override
    public boolean editSingleUserMobile(MobileVO mobileVO) {
        User user = userDAO.findOne(mobileVO.getId());
        if (user == null) {
            return false;
        }
        user.setAccount(mobileVO.getMobile());
        userDAO.save(user);
        return true;
    }

    /**
     * 个人详情页账户信息
     */
    @Override
    public DepositorInfoBO getSingleDepositorInfo(Long id) {
        DepositorInfoBO depositorInfoBO = new DepositorInfoBO();
        Depositor depositor = depositorDAO.findByUserId(id);
        List<Interest> interests = interestDAO.findInterestsByUserId(id);
        AllocationBO allocation = MoneyUtil.getInterestsAllo(interests);
        if (depositor == null) {
            return new DepositorInfoBO();
        }
        depositorInfoBO.setTotal(depositor.getTotal() + allocation.getAssigned());
        depositorInfoBO.setIncome(depositor.getRefund() + allocation.getUnassigned());
        return depositorInfoBO;
    }

    /**
     * 个人详情页绑卡信息
     */
    @Override
    public UserCard getSingleUserCard(Long id) {
        UserCard userCard = userCardDAO.findOne(id);
        return userCard == null ? new UserCard() : userCard;
    }

    /**
     * 解绑银行卡
     */
    @Override
    public boolean untieUserCard(Long id) {
        Depositor depositor = depositorDAO.findByUserId(id);
        if (depositor == null || depositor.getTotal() != 0L) {
            return false;
        }
        UserCard userCard = userCardDAO.findOne(id);
        if (userCard == null) {
            return false;
        }
        userCardDAO.delete(id);
        return true;
    }

    /**
     * 产品下拉框接口
     */
    @Override
    public List<ProductSelectBO> getProductSelects() {
        List<Product> products = productDAO.findAll();
        List<ProductSelectBO> list = new LinkedList<>();
        for (Product product : products) {
            ProductSelectBO productSelectBO = new ProductSelectBO();
            productSelectBO.setProductId(product.getId());
            productSelectBO.setProductName(product.getName());
            list.add(productSelectBO);
        }
        return list;
    }

    /**
     * 用户认证情况  1.实名认证 2. 设置交易密码 3. 绑定银行卡 4.完成
     */
    @Override
    public Integer userStatus(Long id) {
        if (null != userCardDAO.findOne(id)) {
            return 4;
        }
        if (null != depositorDAO.findByUserId(id)) {
            return 3;
        }
        if (null != identityDAO.findByUserId(id)) {
            return 2;
        }
        return 1;
    }

    /**
     * 昨日收益
     */
    @Override
    public Long ydayEarn(String account) {
        //昨日收益
        Long earn = 0L;
        long yesterday = System.currentTimeMillis() - 24 * 60 * 60 * 1000;//昨天的这一时间的毫秒数
        InvestsQuery query = new InvestsQuery();
        query.setUserMobile(account);
        PageHandleBO<UserInvestBO> pageHandle = findInvestsByQuery(query);
        List<UserInvestBO> investList = pageHandle.getObjects();
        for (UserInvestBO userInvest : investList) {
            Invest invest = userInvest.getInvest();
            if (yesterday >= invest.getStart() && yesterday <= invest.getFinish()){
                earn += MoneyUtil.dayEarn(invest);
            }
        }
        return earn;
    }

}
