package com.ptteng.utlis.fuiou;

public class PayResult {
    //商户代码
    private String mchnt_cd;
    //商户订单号
    private String order_id;
    //订单日期
    private String order_date;
    //交易金额
    private String order_amt;
    //订单状态
    private String order_st;
    //错误代码
    private String order_pay_msg;
    //错误中文描述
    private String order_pay_error;
    //卡号
    private String card_no;
    //富友流水号
    private String fy_ssn;
    //支付类型
    private String pay_type;
    // RSA 通知摘要
    private String RSA;

    @Override
    public String toString() {
        return "PayResult{" +
                "mchnt_cd='" + mchnt_cd + '\'' +
                ", order_id='" + order_id + '\'' +
                ", order_date='" + order_date + '\'' +
                ", order_amt='" + order_amt + '\'' +
                ", order_st='" + order_st + '\'' +
                ", order_pay_msg='" + order_pay_msg + '\'' +
                ", order_pay_error='" + order_pay_error + '\'' +
                ", card_no='" + card_no + '\'' +
                ", fy_ssn='" + fy_ssn + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", RSA='" + RSA + '\'' +
                '}';
    }

    public String getMchnt_cd() {
        return mchnt_cd;
    }

    public void setMchnt_cd(String mchnt_cd) {
        this.mchnt_cd = mchnt_cd;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_amt() {
        return order_amt;
    }

    public void setOrder_amt(String order_amt) {
        this.order_amt = order_amt;
    }

    public String getOrder_st() {
        return order_st;
    }

    public void setOrder_st(String order_st) {
        this.order_st = order_st;
    }

    public String getOrder_pay_msg() {
        return order_pay_msg;
    }

    public void setOrder_pay_msg(String order_pay_msg) {
        this.order_pay_msg = order_pay_msg;
    }

    public String getOrder_pay_error() {
        return order_pay_error;
    }

    public void setOrder_pay_error(String order_pay_error) {
        this.order_pay_error = order_pay_error;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getFy_ssn() {
        return fy_ssn;
    }

    public void setFy_ssn(String fy_ssn) {
        this.fy_ssn = fy_ssn;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getRSA() {
        return RSA;
    }

    public void setRSA(String RSA) {
        this.RSA = RSA;
    }
}
