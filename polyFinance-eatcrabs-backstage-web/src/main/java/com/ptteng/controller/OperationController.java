package com.ptteng.controller;

import com.alibaba.fastjson.JSON;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.Bank;
import com.ptteng.domain.business.Banner;
import com.ptteng.domain.business.Event;
import com.ptteng.domain.business.Opinion;
import com.ptteng.dubbo.BackstageConsumer;
import com.ptteng.utlis.validator.ValidatorUtil;
import com.ptteng.vo.backstage.BanksQuery;
import com.ptteng.vo.backstage.OnlineVO;
import com.ptteng.vo.common.BannersQuery;
import com.ptteng.vo.backstage.OpinionsQuery;
import com.ptteng.vo.backstage.EventsQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台运营管理这部分
 */
@Controller
public class OperationController {
    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);
    @Autowired
    private BackstageConsumer consumer;

    /**
     * 查看banner图列表
     */
    @RequestMapping(value = "/a/banner/list", method = RequestMethod.GET)
    public String getBannerList(BannersQuery query, Model model) {
        PageHandleBO<Banner> pageHandleBO = consumer.getOperationService().findBannersByQuery(query);
        model.addAttribute("banners", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "bannerList";
    }

    /**
     * 查看单个banner
     */
    @ResponseBody
    @RequestMapping(value = "/a/banner/{id}", method = RequestMethod.GET)
    public String getSingleBanner(@PathVariable Long id) {
        Banner banner = consumer.getOperationService().getSingleBanner(id);
        Map<String, Object> result = new HashMap<>();
        result.put("name", banner.getName());
        result.put("content", banner.getContent());
        result.put("img", banner.getImg());
        return JSON.toJSONString(result);
    }

    /**
     * 编辑单个banner图
     */
    @RequestMapping(value = "/a/banner/edit/{id}", method = RequestMethod.POST)
    public String editSingleBanner(@PathVariable Long id, @RequestBody Banner banner, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(banner);
        if (errorJson != null) {
            logger.info("编辑banner接口接收的非法参数：{}，id：{}", banner.toString(), id);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("编辑banner接口接收参数：{}，id：{}", banner.toString(), id);
        if (consumer.getOperationService().editSingleBanner(id, banner)) {
            logger.warn("编辑banner成功，id：{}", id);
            return "success";
        }
        logger.warn("编辑banner失败，id：{}", id);
        model.addAttribute("code", -1);
        model.addAttribute("message", "该banner不存在");
        return "exception";
    }

    /**
     * 新增单个banner
     */
    @RequestMapping(value = "/a/banner/new", method = RequestMethod.POST)
    public String addSingleBanner(@RequestBody Banner banner, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(banner);
        if (errorJson != null) {
            logger.info("新增banner接口接收的非法参数：{}", banner.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("新增banner接口接收参数：{}", banner.toString());
        Long id = consumer.getOperationService().addSingleBanner(banner).getId();
        logger.warn("新增banner成功，banner图id：{}", id);
        return "success";
    }


    /**
     * 上下线单个banner
     */
    @RequestMapping(value = "/a/banner/online", method = RequestMethod.POST)
    public String onlineSingleBanner(@RequestBody OnlineVO vo, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(vo);
        if (errorJson != null) {
            logger.info("上下线banner接口接收的非法参数：{}", vo.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("上下线banner接口接收参数：{}", vo.toString());
        if (consumer.getOperationService().onlineSingleBanner(vo)) {
            logger.warn("上下线banner成功，banner图id：{}", vo.getId());
            return "success";
        }
        logger.warn("上下线banner失败，banner图id：{}", vo.getId());
        model.addAttribute("code", -1);
        model.addAttribute("message", "banner图不存在");
        return "exception";
    }


    /**
     * 删除单条banner
     */
    @RequestMapping(value = "/a/banner/delete/{id}", method = RequestMethod.POST)
    public String deleteSingleBanner(@PathVariable Long id) {
        consumer.getOperationService().deleteSingleBanner(id);
        logger.info("成功删除banner，id：{}", id);
        return "success";
    }

    /**
     * 活动消息模板列表
     */
    @RequestMapping(value = "/a/event/list", method = RequestMethod.GET)
    public String getEventList(EventsQuery query, Model model) {
        PageHandleBO<Event> pageHandleBO = consumer.getOperationService().findEventsByQuery(query);
        model.addAttribute("events", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "eventList";
    }

    /**
     * 查看单个活动消息模板
     */
    @ResponseBody
    @RequestMapping(value = "/a/event/{id}", method = RequestMethod.GET)
    public String getSingleTip(@PathVariable Long id) {
        Event event = consumer.getOperationService().getSingleEvent(id);
        Map<String, Object> result = new HashMap<>();
        result.put("title", event.getTitle());
        result.put("content", event.getContent());
        result.put("img", event.getImg());
        result.put("aims", event.getAims());
        return JSON.toJSONString(result);
    }

    /**
     * 修改单个活动消息模板
     */
    @RequestMapping(value = "/a/event/edit/{id}", method = RequestMethod.POST)
    public String editSingleTip(@PathVariable Long id, @RequestBody Event event, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(event);
        if (errorJson != null) {
            logger.info("编辑活动信息接口接收的非法参数：{}，id：{}", event.toString(), id);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("编辑活动信息接口接收参数：{}，id：{}", event.toString(), id);
        if (consumer.getOperationService().editSingleEvent(id, event)) {
            logger.warn("编辑活动信息成功，id：{}", id);
            return "success";
        }
        logger.warn("编辑活动信息失败，id：{}", id);
        model.addAttribute("code", -1);
        model.addAttribute("message", "该活动信息不存在");
        return "exception";
    }

    /**
     * 新增单个活动消息
     */
    @RequestMapping(value = "/a/event/new", method = RequestMethod.POST)
    public String addSingleTip(@RequestBody Event event, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(event);
        if (errorJson != null) {
            logger.info("新增活动信息接口接收的非法参数：{}", event.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("新增活动信息接口接收参数：{}", event.toString());
        Long id = consumer.getOperationService().addSingleEvent(event).getId();
        logger.warn("新增活动信息成功，活动信息id：{}", id);
        return "success";
    }


    /**
     * 上下线单个event
     */
    @RequestMapping(value = "/a/event/online", method = RequestMethod.POST)
    public String onlineSingleEvent(@RequestBody OnlineVO vo, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(vo);
        if (errorJson != null) {
            logger.info("上下线活动消息接口接收的非法参数：{}", vo.toString());
            model.addAttribute("message", errorJson);
            model.addAttribute("code", -1);
            return "exception";
        }
        logger.info("上下线活动消息接口接收参数：{}", vo.toString());
        if (consumer.getOperationService().onlineSingleEvent(vo)) {
            logger.warn("上下线活动消息成功，活动消息id：{}", vo.getId());
            return "success";
        }
        logger.warn("上下线活动消息失败，活动消息id：{}", vo.getId());
        model.addAttribute("code", -1);
        model.addAttribute("message", "活动消息不存在");
        return "exception";
    }

    /**
     * 删除单条活动信息
     */
    @RequestMapping(value = "/a/event/delete/{id}", method = RequestMethod.POST)
    public String deleteSingleEvent(@PathVariable Long id) {
        consumer.getOperationService().deleteSingleEvent(id);
        logger.info("成功删除活动信息，id：{}", id);
        return "success";
    }

    /**
     * 查看银行列表
     */
    @RequestMapping(value = "/a/bank/list", method = RequestMethod.GET)
    public String getBankList(BanksQuery query, Model model) {
        PageHandleBO<Bank> pageHandleBO = consumer.getOperationService().findBanksByQuery(query);
        model.addAttribute("banks", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "bankList";
    }


    /**
     * 查看单条银行信息
     */
    @ResponseBody
    @RequestMapping(value = "/a/bank/{id}", method = RequestMethod.GET)
    public String editSingleBank(@PathVariable Long id) {
        return JSON.toJSONString(consumer.getOperationService().getSingleBank(id));
    }

    /**
     * 修改单个银行
     */
    @RequestMapping(value = "/a/bank/edit/{id}", method = RequestMethod.POST)
    public String editSingleBank(@PathVariable Long id, @RequestBody Bank bank, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(bank);
        if (errorJson != null) {
            logger.info("修改银行接口接收的非法参数：{}，id：{}", bank.toString(), id);
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("修改银行接口接收参数：{}，id：{}", bank.toString(), id);
        if (consumer.getOperationService().editSingleBank(id, bank)) {
            logger.warn("编辑银行成功，id：{}", id);
            return "success";
        }
        logger.warn("编辑银行失败，id：{}", id);
        model.addAttribute("code", -1);
        model.addAttribute("message", "该银行信息不存在");
        return "exception";
    }

    /**
     * 新增单个银行
     */
    @RequestMapping(value = "/a/bank/new", method = RequestMethod.POST)
    public String addSingleBank(@RequestBody Bank bank, Model model) {
        String errorJson = ValidatorUtil.getErrorJSON(bank);
        if (errorJson != null) {
            logger.info("新增银行接口接收的非法参数：{}", bank.toString());
            model.addAttribute("code", -1);
            model.addAttribute("message", errorJson);
            return "exception";
        }
        logger.info("新增银行接口接收参数：{}", bank.toString());
        Long id = consumer.getOperationService().addSingleBank(bank).getId();
        logger.warn("新增银行成功，银行id：{}", id);
        return "success";
    }


    /**
     * 删除单条银行信息
     */
    @RequestMapping(value = "/a/bank/delete/{id}", method = RequestMethod.POST)
    public String deleteSingleBank(@PathVariable Long id) {
        consumer.getOperationService().deleteSingleBank(id);
        logger.info("成功删除银行，id：{}", id);
        return "success";
    }

    /**
     * 用户意见列表
     */
    @RequestMapping(value = "/a/opinion/list", method = RequestMethod.GET)
    public String getOpinionList(OpinionsQuery query, Model model) {
        PageHandleBO<Opinion> pageHandleBO = consumer.getOperationService().findOpinionsByQuery(query);
        model.addAttribute("opinions", pageHandleBO.getObjects());
        model.addAttribute("count", pageHandleBO.getCount());
        return "opinionList";
    }

    /**
     * 查看单条意见
     */
    @ResponseBody
    @RequestMapping(value = "/a/opinion/{id}", method = RequestMethod.GET)
    public String getSingleOpinion(@PathVariable Long id) {
        return JSON.toJSONString(consumer.getOperationService().getSingleOpinion(id));
    }

    /**
     * 删除单条意见
     */
    @RequestMapping(value = "/a/opinion/delete/{id}", method = RequestMethod.POST)
    public String deleteSingleOpinion(@PathVariable Long id) {
        consumer.getOperationService().deleteSingleOpinion(id);
        logger.info("成功删除意见，id：{}", id);
        return "success";
    }

}
