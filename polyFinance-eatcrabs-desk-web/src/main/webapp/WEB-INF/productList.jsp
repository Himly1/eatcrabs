<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:array name="products" var="product" items="${products}">
        <json:object>
            <json:property name="id" value="${product.id}"/>
            <json:property name="name" value="${product.name}"/>
            <%--0：全部 1：新手计划 2：人气精选 3：精选产品(前台首页)--%>
            <json:property name="type" value="${product.type}"/>
            <json:property name="rate" value="${product.rate}"/>
            <json:property name="term" value="${product.term}"/>
            <json:property name="least" value="${product.least}"/>
        </json:object>
    </json:array>
</json:object>