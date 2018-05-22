<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="investments" var="userInvest" items="${userInvests}">
        <json:object>
            <json:property name="id" value="${userInvest.invest.id}"/>
            <json:property name="userMobile" value="${userInvest.invest.userMobile}"/>
            <json:property name="userName" value="${userInvest.invest.userName}"/>
            <json:property name="start" value="${userInvest.invest.start}"/>
            <json:property name="finish" value="${userInvest.invest.finish}"/>
            <json:property name="productName" value="${userInvest.invest.productName}"/>
            <json:property name="investNumber" value="${userInvest.invest.investNumber}"/>
            <json:property name="amount" value="${userInvest.invest.amount}"/>
            <json:property name="assigned" value="${userInvest.allocationBO.assigned}"/>
            <json:property name="unassigned" value="${userInvest.allocationBO.unassigned}"/>
            <json:property name="claimNumber" value="${userInvest.invest.claimNumber}"/>
            <json:property name="type" value="${current>userInvest.invest.finish?-2:current>userInvest.invest.finish-259200000?-1:1}"/>
        </json:object>
    </json:array>
</json:object>