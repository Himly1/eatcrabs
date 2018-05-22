<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="deals" var="deal" items="${deals}">
        <json:object>
            <json:property name="id" value="${deal.id}"/>
            <json:property name="userMobile" value="${deal.userMobile}"/>
            <json:property name="createAt" value="${deal.createAt}"/>
            <json:property name="userName" value="${deal.userName}"/>
            <json:property name="number" value="${deal.number}"/>
            <json:property name="productName" value="${deal.productName}"/>
            <json:property name="amount" value="${deal.amount}"/>
            <json:property name="pay" value="${deal.pay}"/>
            <json:property name="success" value="${deal.success}"/>
            <json:property name="dealBy" value="${deal.dealBy}"/>
        </json:object>
    </json:array>
</json:object>