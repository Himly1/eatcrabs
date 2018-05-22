<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="customers" var="customer" items="${customers}">
        <json:object>
            <json:property name="id" value="${customer.user.id}"/>
            <json:property name="number" value="${customer.user.number}"/>
            <json:property name="account" value="${customer.user.account}"/>
            <json:property name="createAt" value="${customer.user.createAt}"/>
            <json:property name="freeze" value="${customer.user.freeze}"/>
            <json:property name="name" value="${customer.name}"/>
            <json:property name="total" value="${customer.depositorInfoBO.total}"/>
            <json:property name="income" value="${customer.depositorInfoBO.income}"/>
        </json:object>
    </json:array>
</json:object>