<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="advices" var="invest" items="${invests}">
        <json:object>
            <json:property name="investId" value="${invest.id}"/>
            <json:property name="userName" value="${invest.userName}"/>
            <json:property name="productName" value="${invest.productName}"/>
            <json:property name="investNumber" value="${invest.investNumber}"/>
            <json:property name="amount" value="${invest.amount}"/>
            <json:property name="finish" value="${invest.finish}"/>
        </json:object>
    </json:array>
</json:object>