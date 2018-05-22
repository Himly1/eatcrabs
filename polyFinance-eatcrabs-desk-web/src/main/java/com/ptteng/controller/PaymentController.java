package com.ptteng.controller;

import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.desk.TpwdVcodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * 支付
 */
@Controller
public class PaymentController {
    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    /**
     * 提交投资金额
     */
    @RequestMapping(value = "/b/invest/{productId}/amount", method = RequestMethod.POST)
    public String investAmount(@PathVariable Long productId, @RequestBody Long amount, Model model){
        logger.info("提交投资金额接口接收参数 id：{}，金额： {}", productId, amount);
        return "success";
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/b/invest/vcode/new", method = RequestMethod.GET)
    public String getInvestVcode(Model model){
        return "success";
    }

    /**
     * 验证交易
     */
    @RequestMapping(value = "/b/invest", method = RequestMethod.POST)
    public String invest(@RequestBody TpwdVcodeVO investVO, Model model){
        String errorJson = ValidatorUtil.getErrorJSON(investVO);
        if(null != errorJson){
            logger.warn("验证交易接口接收非法参数： {}", investVO.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("验证交易接口接收参数： {}", investVO.toString());

        return "success";
    }

    /**
     * 电子签章（提交）
     */

    @RequestMapping(value = "/b/invest/upload", method = RequestMethod.POST)
    public String investUpload(MultipartFile file, Model model){
        return "success";
    }
}
