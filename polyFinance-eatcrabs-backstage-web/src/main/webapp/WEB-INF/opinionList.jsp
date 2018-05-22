<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="opinions" var="opinion" items="${opinions}">
        <json:object>
            <json:property name="id" value="${opinion.id}"/>
            <json:property name="account" value="${opinion.account}"/>
            <json:property name="name" value="${opinion.name}"/>
            <json:property name="number" value="${opinion.number}"/>
            <json:property name="createAt" value="${opinion.createAt}"/>
            <json:property name="content" value="${opinion.content}"/>
        </json:object>
    </json:array>
</json:object>