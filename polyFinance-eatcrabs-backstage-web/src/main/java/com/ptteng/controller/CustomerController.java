package com.ptteng.controller;

import com.ptteng.bo.*;
import com.ptteng.domain.business.DealRecord;
import com.ptteng.dubbo.BackstageConsumer;
import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.backstage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台有关用户的部分
 */
@Controller
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private BackstageConsumer consumer;

    /**
     * 查看用户列表（主要信息）
     */
    @RequestMapping(value = "/a/customer/list", method = RequestMethod.GET)
    public String getCustomerList(CustomersQuery query, Model model) {
        PageHandleBO<CustomerBO> pageHandleBO = consumer.getCustomerService().findCustomersByQuery(query);
        model.addAttribute("customers", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "customerList";
    }

    /**
     * 冻结(解冻)某一个用户
     */
    @RequestMapping(value = "/a/customer/freeze", method = RequestMethod.POST)
    public String freezeSingleUser(@RequestBody FreezeVO freezeVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(freezeVO);
        if (errorJson != null) {
            logger.info("解冻（冻结）接口接收的非法参数：{}", freezeVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("解冻（冻结）接口接收参数：{}", freezeVO.toString());
        if (consumer.getCustomerService().freezeSingleUser(freezeVO)) {
            logger.warn("成功解冻（冻结），用户id：{}", freezeVO.getId());
            return "success";
        }
        logger.warn("错误的解冻（冻结）操作，用户id：{}", freezeVO.getId());
        model.addAttribute("code", -1);
        model.addAttribute("message", "用户不存在");
        return "exception";
    }

    /**
     * 用户详情（个人信息）
     */
    @RequestMapping(value = "/a/customer/person/{id}", method = RequestMethod.GET)
    public String getUserInfoBOList(@PathVariable Long id, Model model) {
        model.addAttribute("userInfo", consumer.getCustomerService().getSingleUserInfo(id));
        return "userInfo";
    }

    /**
     * 更改用户手机号
     */
    @RequestMapping(value = "/a/customer/mobile", method = RequestMethod.POST)
    public String editUserMobile(@RequestBody MobileVO mobileVO, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(mobileVO);
        if (errorJson != null) {
            logger.info("更改用户手机号接口接收的非法参数：{}", mobileVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("更改用户手机号接口接收参数：{}", mobileVO.toString());
        if (consumer.getCustomerService().editSingleUserMobile(mobileVO)) {
            logger.warn("更换手机号成功，用户id：{}", mobileVO.getId());
            return "success";
        }
        logger.warn("错误的更换手机号操作，用户id：{}", mobileVO.getId());
        model.addAttribute("code", -1);
        model.addAttribute("message", "用户不存在");
        return "exception";
    }

    /**
     * 用户账户
     */
    @RequestMapping(value = "/a/customer/account/{id}", method = RequestMethod.GET)
    public String getUserDepositorInfo(@PathVariable Long id, Model model) {
        model.addAttribute("depositorInfoBO", consumer.getCustomerService().getSingleDepositorInfo(id));
        return "depositorInfo";
    }

    /**
     * 用户绑卡信息
     */
    @RequestMapping(value = "/a/customer/bank/{id}", method = RequestMethod.GET)
    public String getUserCardInfo(@PathVariable Long id, Model model) {
        model.addAttribute("userCard", consumer.getCustomerService().getSingleUserCard(id));
        return "userCardInfo";
    }

    /**
     * 用户解绑银行卡
     */
    @RequestMapping(value = "/a/customer/bank/untie/{id}", method = RequestMethod.POST)
    public String untieUserBankCard(@PathVariable Long id, Model model) {
        logger.info("解绑银行卡,用户id：{}", id);
        if (consumer.getCustomerService().untieUserCard(id)) {
            logger.warn("解绑银行卡成功，用户id：{}", id);
            return "success";
        }
        logger.warn("错误的解绑银行卡操作，用户id：{}", id);
        model.addAttribute("code", -1);
        model.addAttribute("message", "非法操作");
        return "exception";
    }

    /**
     * 产品列表（下拉单选框接口）
     */
    @RequestMapping(value = "/a/customer/product", method = RequestMethod.GET)
    public String getProductSelects(Model model) {
        model.addAttribute("productSelects", consumer.getCustomerService().getProductSelects());
        return "productSelects";
    }

    /**
     * 查询前台用户系统的用户交易记录
     */
    @RequestMapping(value = "/a/customer/deal", method = RequestMethod.GET)
    public String getUserDealList(DealsQuery query, Model model) {
        PageHandleBO<DealRecord> pageHandleBO = consumer.getCustomerService().findDealsByQuery(query);
        model.addAttribute("deals", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "dealList";
    }

    /**
     * 查询前台用户系统的投资记录
     */
    @RequestMapping(value = "/a/customer/investment", method = RequestMethod.GET)
    public String getUserInvestmentList(InvestsQuery query, Model model) {
        Long current = System.currentTimeMillis();
        //借款状态 -1：未使用 0：全部 1:使用中 2：已过期
        Integer type = query.getType();
        if (type == null) {

        } else if (type == -1) {
            query.setMaxFinish(current);
            query.setMinFinish(current - 3 * 24 * 60 * 60 * 1000);
        } else if (type == -2) {
            query.setMinFinish(current);
        }
        PageHandleBO<UserInvestBO> pageHandleBO = consumer.getCustomerService().findInvestsByQuery(query);
        model.addAttribute("current", System.currentTimeMillis());
        model.addAttribute("userInvests", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "userInvestList";
    }

}
