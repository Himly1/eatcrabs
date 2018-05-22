package com.ptteng.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.Bank;
import com.ptteng.domain.business.Banner;
import com.ptteng.domain.business.Event;
import com.ptteng.domain.business.Opinion;
import com.ptteng.utlis.jpa.CopyUtil;
import com.ptteng.utlis.jpa.MySpecification;
import com.ptteng.utlis.jpa.PageableUtil;
import com.ptteng.repository.BankDAO;
import com.ptteng.repository.BannerDAO;
import com.ptteng.repository.EventDAO;
import com.ptteng.repository.OpinionDAO;
import com.ptteng.service.OperationService;
import com.ptteng.vo.backstage.BanksQuery;
import com.ptteng.vo.backstage.OnlineVO;
import com.ptteng.vo.common.BannersQuery;
import com.ptteng.vo.backstage.EventsQuery;
import com.ptteng.vo.backstage.OpinionsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * 运营管理相关service实现类
 */
@Component
@Service(version = "1.0.0", interfaceName = "com.ptteng.service.OperationService")
public class OperationServiceImpl implements OperationService {
    @Autowired
    private BannerDAO bannerDAO;
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private OpinionDAO opinionDAO;
    @Autowired
    private BankDAO bankDAO;

    /**
     * 查询银行信息列表
     */
    @Override
    public PageHandleBO<Bank> findBanksByQuery(BanksQuery query) {
        PageHandleBO<Bank> pageHandleBO = new PageHandleBO<>();
        Page<Bank> bankPage = bankDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(bankPage.getTotalElements());
        pageHandleBO.setObjects(bankPage.getContent());
        return pageHandleBO;
    }

    /**
     * 查询banner图列表
     */
    @Override
    public PageHandleBO<Banner> findBannersByQuery(BannersQuery query) {
        PageHandleBO<Banner> pageHandleBO = new PageHandleBO<>();
        Page<Banner> bannerPage = bannerDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(bannerPage.getTotalElements());
        pageHandleBO.setObjects(bannerPage.getContent());
        return pageHandleBO;
    }

    /**
     * 查询活动消息列表
     */
    @Override
    public PageHandleBO<Event> findEventsByQuery(EventsQuery query) {
        PageHandleBO<Event> pageHandleBO = new PageHandleBO<>();
        Page<Event> eventPage = eventDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(eventPage.getTotalElements());
        pageHandleBO.setObjects(eventPage.getContent());
        return pageHandleBO;
    }

    /**
     * 查询用户意见列表
     */
    @Override
    public PageHandleBO<Opinion> findOpinionsByQuery(OpinionsQuery query) {
        PageHandleBO<Opinion> pageHandleBO = new PageHandleBO<>();
        Page<Opinion> opinionPage = opinionDAO.findAll(new MySpecification<>(query), PageableUtil.getPageRequest(query));
        pageHandleBO.setCount(opinionPage.getTotalElements());
        pageHandleBO.setObjects(opinionPage.getContent());
        return pageHandleBO;
    }

    /**
     * 查看单个banner
     */
    @Override
    public Banner getSingleBanner(Long id) {
        Banner banner = bannerDAO.findOne(id);
        return banner == null ? new Banner() : banner;
    }

    /**
     * 编辑单个banner
     */
    @Override
    public boolean editSingleBanner(Long id, Banner vo) {
        Banner po = bannerDAO.findOne(id);
        if (po == null) {
            return false;
        }
        CopyUtil.copyValue(vo, po);
        po.setId(id);
        po.setUpdateAt(System.currentTimeMillis());
        bannerDAO.save(po);
        return true;
    }

    /**
     * 新增单个banner
     */
    @Override
    public Banner addSingleBanner(Banner banner) {
        banner.setOnline(-1);
        banner.setCreateAt(System.currentTimeMillis());
        banner.setUpdateAt(System.currentTimeMillis());
        return bannerDAO.save(banner);
    }


    /**
     * 上下线banner
     */
    @Override
    public boolean onlineSingleBanner(OnlineVO vo) {
        Banner banner = bannerDAO.findOne(vo.getId());
        if (banner == null) {
            return false;
        }
        banner.setOnline(vo.getOnline());
        bannerDAO.save(banner);
        return true;
    }


    /**
     * 删除banner
     */
    @Override
    public void deleteSingleBanner(Long id) {
        bannerDAO.delete(id);
    }

    /**
     * 查看单条活动信息
     */
    @Override
    public Event getSingleEvent(Long id) {
        Event event = eventDAO.findOne(id);
        return event == null ? new Event() : event;
    }

    /**
     * 编辑单条活动信息
     */
    @Override
    public boolean editSingleEvent(Long id, Event vo) {
        Event po = eventDAO.findOne(id);
        if (po == null) {
            return false;
        }
        CopyUtil.copyValue(vo, po);
        po.setId(id);
        po.setUpdateAt(System.currentTimeMillis());
        eventDAO.save(po);
        return true;
    }

    /**
     * 新增单条活动信息
     */
    @Override
    public Event addSingleEvent(Event event) {
        event.setOnline(-1);
        event.setCreateAt(System.currentTimeMillis());
        event.setUpdateAt(System.currentTimeMillis());
        return eventDAO.save(event);
    }

    /**
     * 上下线活动消息
     */
    @Override
    public boolean onlineSingleEvent(OnlineVO vo) {
        Event event = eventDAO.findOne(vo.getId());
        if (event == null) {
            return false;
        }
        event.setOnline(vo.getOnline());
        eventDAO.save(event);
        return true;
    }


    /**
     * 删除活动消息
     */
    @Override
    public void deleteSingleEvent(Long id) {
        eventDAO.delete(id);
    }

    /**
     * 查看单条银行信息
     */
    @Override
    public Bank getSingleBank(Long id) {
        Bank bank = bankDAO.findOne(id);
        return bank == null ? new Bank() : bank;
    }

    /**
     * 编辑单条银行信息
     */
    @Override
    public boolean editSingleBank(Long id, Bank vo) {
        Bank po = bankDAO.findOne(id);
        if (po == null) {
            return false;
        }
        CopyUtil.copyValue(vo, po);
        po.setId(id);
        po.setUpdateAt(System.currentTimeMillis());
        bankDAO.save(po);
        return true;
    }

    /**
     * 新增单条银行信息
     */
    @Override
    public Bank addSingleBank(Bank bank) {
        bank.setCreateAt(System.currentTimeMillis());
        bank.setUpdateAt(System.currentTimeMillis());
        return bankDAO.save(bank);
    }

    /**
     * 删除银行消息
     */
    @Override
    public void deleteSingleBank(Long id) {
        bankDAO.delete(id);
    }

    /**
     * 查看单条意见
     */
    @Override
    public Opinion getSingleOpinion(Long id) {
        Opinion opinion = opinionDAO.findOne(id);
        return opinion == null ? new Opinion() : opinion;
    }

    /**
     * 删除单条意见
     */
    @Override
    public void deleteSingleOpinion(Long id) {
        opinionDAO.delete(id);
    }

}
