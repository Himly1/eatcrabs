package com.ptteng.service;

import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.Bank;
import com.ptteng.domain.business.Banner;
import com.ptteng.domain.business.Event;
import com.ptteng.domain.business.Opinion;
import com.ptteng.vo.backstage.BanksQuery;
import com.ptteng.vo.backstage.OnlineVO;
import com.ptteng.vo.common.BannersQuery;
import com.ptteng.vo.backstage.EventsQuery;
import com.ptteng.vo.backstage.OpinionsQuery;

public interface OperationService {

    /**
     * 查询银行信息列表
     */
    PageHandleBO<Bank> findBanksByQuery(BanksQuery query);

    /**
     * 查询banner图列表
     */
    PageHandleBO<Banner> findBannersByQuery(BannersQuery query);

    /**
     * 查询活动消息列表
     */
    PageHandleBO<Event> findEventsByQuery(EventsQuery query);

    /**
     * 查询用户意见列表
     */
    PageHandleBO<Opinion> findOpinionsByQuery(OpinionsQuery query);

    /**
     * 查看单个banner
     */
    Banner getSingleBanner(Long id);

    /**
     * 编辑单个banner
     */
    boolean editSingleBanner(Long id, Banner banner);

    /**
     * 新增单个banner
     */
    Banner addSingleBanner(Banner banner);

    /**
     * 上下线banner
     */
    boolean onlineSingleBanner(OnlineVO vo);


    /**
     * 删除banner
     */
    void deleteSingleBanner(Long id);

    /**
     * 编辑单条活动信息
     */
    Event getSingleEvent(Long id);

    /**
     * 编辑单条活动信息
     */
    boolean editSingleEvent(Long id, Event event);

    /**
     * 新增单条活动信息
     */
    Event addSingleEvent(Event event);

    /**
     * 上下线活动消息
     */
    boolean onlineSingleEvent(OnlineVO vo);


    /**
     * 删除活动消息
     */
    void deleteSingleEvent(Long id);

    /**
     * 查看单条银行信息
     */
    Bank getSingleBank(Long id);

    /**
     * 编辑单条银行信息
     */
    boolean editSingleBank(Long id, Bank bank);

    /**
     * 新增单条银行信息
     */
    Bank addSingleBank(Bank bank);

    /**
     * 删除银行消息
     */
    void deleteSingleBank(Long id);

    /**
     * 查看单条意见
     */
    Opinion getSingleOpinion(Long id);

    /**
     * 删除单条意见
     */
    void deleteSingleOpinion(Long id);


}
