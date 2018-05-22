<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="investments" var="invest" items="${invests}">
        <json:object>
            <json:property name="claimNumber" value="${invest.claimNumber}"/>
            <json:property name="investNumber" value="${invest.investNumber}"/>
            <json:property name="userName" value="${invest.userName}"/>
            <json:property name="productName" value="${invest.productName}"/>
            <json:property name="amount" value="${invest.amount}"/>
            <json:property name="start" value="${invest.start}"/>
            <json:property name="finish" value="${invest.finish}"/>
        </json:object>
    </json:array>
</json:object>