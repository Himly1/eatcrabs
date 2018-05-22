<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="请求成功"/>

    <json:object name="product">
        <json:property name="id" value="${product.id}"/>
        <json:property name="name" value="${product.name}"/>
        <json:property name="rate" value="${product.rate}"/>
        <json:property name="term" value="${product.term}"/>
        <json:property name="least" value="${product.least}"/>
        <json:property name="step" value="${product.step}"/>
        <json:property name="number" value="${product.number}"/>
        <json:property name="repay" value="${product.repay}"/>
        <json:property name="begin" value="${product.begin}"/>
        <json:property name="quota" value="${product.quota}"/>
        <json:property name="content" value="${product.content}"/>
    </json:object>

    <json:array name="dealRecords" var="dealRecord" items="${dealRecords}">
        <json:object>
            <json:property name="userName" value="${dealRecord.userName}"/>
            <json:property name="amount" value="${dealRecord.amount}"/>
            <json:property name="createAt" value="${dealRecord.createAt}"/>
        </json:object>
    </json:array>
</json:object>