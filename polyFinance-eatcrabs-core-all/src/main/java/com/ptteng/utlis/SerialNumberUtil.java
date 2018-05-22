package com.ptteng.utlis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成交易流水号
 */
public class SerialNumberUtil {

    private static String TYPE = "JY";
    private static String LAST_DATE = null;
    private static SerialNumberUtil serialNumber = null;
    private static int NO = 0;

    private SerialNumberUtil(){}

    /**
     * 单例实现
     * @return
     */
    public static SerialNumberUtil getInstance(){
        if(null == serialNumber){
            synchronized (SerialNumberUtil.class){
                if( null == serialNumber){
                    serialNumber = new SerialNumberUtil();
                }
            }
        }
        return serialNumber;
    }

    public String getNumber(){
        String id = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyMMdd");
        String temp = sf.format(new Date());
        id = TYPE + temp + getCode(temp);
        return id;
    }

    private static synchronized String getCode(String newDate){
        if(!newDate.equals(LAST_DATE)){
            LAST_DATE = newDate;
            NO = 0;
        }
        String str = String.format("%06d", ++NO);
        return str;
    }
}
