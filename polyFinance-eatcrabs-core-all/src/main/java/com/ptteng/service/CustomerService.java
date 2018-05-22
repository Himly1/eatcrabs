package com.ptteng.service;

import com.ptteng.bo.*;
import com.ptteng.domain.business.DealRecord;
import com.ptteng.domain.business.User;
import com.ptteng.domain.business.UserCard;
import com.ptteng.vo.backstage.*;

import java.util.List;

public interface CustomerService {

    /**
     * 查询用户列表
     */
    PageHandleBO<CustomerBO> findCustomersByQuery(CustomersQuery query);

    /**
     * 查询用户交易记录
     */
    PageHandleBO<DealRecord> findDealsByQuery(DealsQuery query);

    /**
     * 查询用户投资记录
     */
    PageHandleBO<UserInvestBO> findInvestsByQuery(InvestsQuery query);

    /**
     * 冻结（解冻）单个账户
     */
    boolean freezeSingleUser(FreezeVO freezeVO);

    /**
     * 获取用户的登陆密码
     */
    User getUserLoginToken(String account);

    /**
     * 个人详情页个人消息
     */
    UserInfoBO getSingleUserInfo(Long id);

    /**
     * 更改用户手机号
     */
    boolean editSingleUserMobile(MobileVO mobileVO);

    /**
     * 个人详情页账户信息
     */
    DepositorInfoBO getSingleDepositorInfo(Long id);

    /**
     * 个人详情页绑卡信息
     */
    UserCard getSingleUserCard(Long id);

    /**
     * 解绑银行卡
     */
    boolean untieUserCard(Long id);

    /**
     * 产品下拉框接口
     */
    List<ProductSelectBO> getProductSelects();

    /**
     * 用户认证情况  1.实名认证 2. 设置交易密码 3. 绑定银行卡 4.完成
     */
    Integer userStatus(Long id);

    /**
     * 昨日收益
     */
    Long ydayEarn(String account);
}
