package com.ptteng.utlis;

import com.ptteng.bo.AllocationBO;
import com.ptteng.domain.business.Interest;
import com.ptteng.domain.business.Invest;

import java.util.List;

/**
 * 金钱计算工具类
 */
public class MoneyUtil {
    private static final long YEAR_TO_SECOND = 31536000000L;

    //2018年1月1日0时0分0秒
    private static final long DAY_EARN_START = 1514736000000L;
    //2018年1月2日0时0分0秒
    private static final long DAY_EARN_STOP = 1514822400000L;

    private static AllocationBO getAllocation(long amount, int rate, long start, long finish) {
        long total = new Double(((double) finish - start) / YEAR_TO_SECOND * rate * amount / 10000).longValue();
        long current = System.currentTimeMillis();
        long end = current > finish ? finish : current;
        long assigned = new Double(((double) end - start) / YEAR_TO_SECOND * rate * amount / 10000).longValue();
        long unassigned = total - assigned;
        AllocationBO allocation = new AllocationBO();
        allocation.setAssigned(assigned);
        allocation.setUnassigned(unassigned);
        return allocation;
    }

    public static AllocationBO getInvestAllo(Invest invest) {
        return getAllocation(invest.getAmount(), invest.getRate(), invest.getStart(), invest.getFinish());
    }

    public static AllocationBO getInterestsAllo(List<Interest> interests) {
        AllocationBO allocation = new AllocationBO();
        long assigned = 0;
        long unassigned = 0;
        for (Interest interest : interests) {
            allocation = getAllocation(interest.getAmount(), interest.getRate(), interest.getStart(), interest.getFinish());
            assigned += allocation.getAssigned();
            unassigned += allocation.getUnassigned();
        }
        allocation.setAssigned(assigned);
        allocation.setUnassigned(unassigned);
        return allocation;
    }

    /**
     * 订单单日收益
     */
    public static Long dayEarn(Invest invest){
        return getAllocation(invest.getAmount(), invest.getRate(), DAY_EARN_START, DAY_EARN_STOP).getUnassigned();
    }
}