package com.ptteng.utlis.fuiou;

import com.ptteng.exception.InsideException;
import com.ptteng.utlis.Md5Util;
import com.ptteng.utlis.XmlUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class IdentityUtils {
    private static final String VERSION = "1.0";
    private static final String TYPEID = "NN";
    private static final String MCHNTCD = "0002900F0096235";
    private static final String MCHNTORDERID = "96325774";
    private static final String KEY = "5old71wihg2tqjug9kkpxnhx9hiujoqj";

    /**
     * 获取验证用户身份信息的富友URL
     *
     * @param name     用户姓名
     * @param idNumber 用户身份证号
     * @return utf8编码的请求URL
     */
    public static String getIdentityURL(String name, String idNumber) {
        String baseURL = "http://www-1.fuiou.com:18670/mobile_pay/checkInfo/checkIdentity.pay?FM=";
        StringBuffer sb = new StringBuffer();
        sb.append("<ORDER>\n");
        sb.append("<VERSION>");
        sb.append(VERSION);
        sb.append("</VERSION>\n");
        sb.append("<TYPEID>");
        sb.append(TYPEID);
        sb.append("</TYPEID>\n");
        sb.append("<MCHNTCD>");
        sb.append(MCHNTCD);
        sb.append("</MCHNTCD>\n");
        sb.append("<MCHNTORDERID>");
        sb.append(MCHNTORDERID);
        sb.append("</MCHNTORDERID>\n");
        sb.append("<NAME>");
        sb.append(name);
        sb.append("</NAME>\n");
        sb.append("<IDNO>");
        sb.append(idNumber);
        sb.append("</IDNO>");
        sb.append("<SIGN>");
        sb.append(getIdentitySign(name, idNumber));
        sb.append("</SIGN>\n");
        sb.append("</ORDER>");
        try {
            return baseURL + URLEncoder.encode(sb.toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new InsideException("UTF8编码失败");
        }
    }

    private static String getIdentitySign(String name, String idNumber) {
        StringBuffer sb = new StringBuffer();
        sb.append(VERSION);
        sb.append("|");
        sb.append(TYPEID);
        sb.append("|");
        sb.append(MCHNTCD);
        sb.append("|");
        sb.append(MCHNTORDERID);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(idNumber);
        sb.append("|");
        sb.append(KEY);
        return Md5Util.getMd5(sb.toString());
    }

    public static Map<String, String> getIdentityCode(String xmlStr) {
        Map<String, Object> map;
        try {
            map = XmlUtil.xmlStrToMap(xmlStr);
        } catch (Exception e) {
            throw new InsideException("解析xml发生错误");
        }
        Map<String, String> result = new HashMap<>();
        result.put("code", (String) map.get("RESPONSECODE"));
        result.put("message", (String) map.get("RESPONSEMSG"));
        return result;
    }

}
