<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="products" var="product" items="${products}">
        <json:object>
            <json:property name="id" value="${product.id}"/>
            <json:property name="number" value="${product.number}"/>
            <json:property name="name" value="${product.name}"/>
            <json:property name="type" value="${product.type}"/>
            <json:property name="rate" value="${product.rate}"/>
            <json:property name="term" value="${product.term}"/>
            <json:property name="least" value="${product.least}"/>
            <json:property name="begin" value="${product.begin}"/>
            <json:property name="updateAt" value="${product.updateAt}"/>
            <json:property name="onSale" value="${product.onsale}"/>
        </json:object>
    </json:array>
</json:object>