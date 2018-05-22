package com.ptteng.utlis.fuiou;

import com.ptteng.exception.InsideException;
import com.ptteng.utlis.Md5Util;
import com.ptteng.utlis.XmlUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class BankCardUtils {
    private static final String MchntCd = "0002900F0096235";
    private static final String Ver = "1.30";
    private static final String OSsn = "11032302065863805732";
    private static final String KEY = "5old71wihg2tqjug9kkpxnhx9hiujoqj";
    private static final String OCerTp = "0";


    public static String getIdentityURL(String name, String idNumber, String bankCard, String mobile) {
        String baseURL = "http://www-1.fuiou.com:18670/mobile_pay/checkCard/checkCard01.pay?FM=";
        StringBuffer sb = new StringBuffer();
        sb.append("<FM>\n");
        sb.append("<MchntCd>");
        sb.append(MchntCd);
        sb.append("</MchntCd>\n");
        sb.append("<Ono>");
        sb.append(bankCard);
        sb.append("</Ono>\n");
        sb.append("<Onm>");
        sb.append(name);
        sb.append("</Onm>\n");
        sb.append("<OCerTp>");
        sb.append(OCerTp);
        sb.append("</OCerTp>\n");
        sb.append("<OCerNo>");
        sb.append(idNumber);
        sb.append("</OCerNo>\n");
        sb.append("<Mno>");
        sb.append(mobile);
        sb.append("</Mno>\n");
        sb.append("<Sign>");
        sb.append(getIBankCardSign(idNumber, bankCard));
        sb.append("</Sign>\n");
        sb.append("<Ver>");
        sb.append(Ver);
        sb.append("</Ver>\n");
        sb.append("<OSsn>");
        sb.append(OSsn);
        sb.append("</OSsn>\n");
        sb.append("</FM>");
        try {
            return baseURL + URLEncoder.encode(sb.toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new InsideException("UTF8编码失败");
        }
    }

    private static String getIBankCardSign(String idNumber, String bankCard) {
        StringBuffer sb = new StringBuffer();
        sb.append(MchntCd);
        sb.append("|");
        sb.append(Ver);
        sb.append("|");
        sb.append(OSsn);
        sb.append("|");
        sb.append(bankCard);
        sb.append("|");
        sb.append(OCerTp);
        sb.append("|");
        sb.append(idNumber);
        sb.append("|");
        sb.append(KEY);
        return Md5Util.getMd5(sb.toString());
    }

    public static Map<String, String> getBankCardCode(String xmlStr) {
        Map<String, Object> map;
        try {
            map = XmlUtil.xmlStrToMap(xmlStr);
        } catch (Exception e) {
            throw new InsideException("解析xml发生错误");
        }
        Map<String, String> result = new HashMap<>();
        result.put("code", (String) map.get("Rcd"));
        result.put("message", (String) map.get("RDesc"));
        result.put("bankName",(String) map.get("Cnm"));
        result.put("bankNumber",(String) map.get("InsCd"));
        return result;
    }
}
