<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="name" value="${debt.name}"/>
    <json:property name="idCard" value="${debt.idCard}"/>
    <json:property name="mobile" value="${debt.mobile}"/>
    <json:property name="number" value="${debt.number}"/>
    <json:property name="rate" value="${debt.rate}"/>
    <json:property name="amount" value="${debt.amount}"/>
    <json:property name="term" value="${debt.finish-debt.start}"/>
    <json:property name="start" value="${debt.start}"/>
    <json:property name="finish" value="${debt.finish}"/>
    <json:property name="kind" value="${debt.kind}"/>
    <json:property name="tip" value="${debt.tip}"/>
    <json:property name="doc" value="${debt.doc}"/>
    <json:property name="match" value="${debt.match}"/>
    <json:property name="content" value="${debt.content}"/>
</json:object>