<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="account" value="${userInfo.user.account}"/>
    <json:property name="name" value="${userInfo.identity.name}"/>
    <json:property name="idCard" value="${userInfo.identity.idCard}"/>
    <json:property name="number" value="${userInfo.user.number}"/>
    <json:property name="signUpAt" value="${userInfo.user.createAt}"/>
</json:object>