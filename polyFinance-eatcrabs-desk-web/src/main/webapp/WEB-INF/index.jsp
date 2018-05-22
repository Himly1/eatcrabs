<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:array name="banners" var="banner" items="${banners}">
        <json:object>
            <json:property name="id" value="${banner.id}"/>
            <json:property name="img" value="${banner.img}"/>
            <json:property name="content" value="${banner.content}"/>
        </json:object>
    </json:array>

    <json:array name="products" var="product" items="${products}">
        <json:object>
            <json:property name="id" value="${product.id}"/>
            <json:property name="name" value="${product.name}"/>
            <json:property name="rate" value="${product.rate}"/>
            <json:property name="term" value="${product.term}"/>
            <json:property name="least" value="${product.term}"/>
        </json:object>
    </json:array>
</json:object>