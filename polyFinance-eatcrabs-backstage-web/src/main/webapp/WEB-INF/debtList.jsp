<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="debts" var="debt" items="${debts}">
        <json:object>
            <json:property name="id" value="${debt.id}"/>
            <json:property name="number" value="${debt.number}"/>
            <json:property name="name" value="${debt.name}"/>
            <json:property name="mobile" value="${debt.mobile}"/>
            <json:property name="idCard" value="${debt.idCard}"/>
            <json:property name="term" value="${debt.finish-debt.start}"/>
            <json:property name="rate" value="${debt.rate}"/>
            <json:property name="start" value="${debt.start}"/>
            <json:property name="finish" value="${debt.finish}"/>
            <json:property name="amount" value="${debt.amount}"/>
            <json:property name="match" value="${debt.match}"/>
            <json:property name="used" value="${current>debt.finish?2:current<debt.start?-1:1}"/>
            <json:property name="complete" value="${debt.match==0?-1:debt.match<debt.amount?1:2}"/>
        </json:object>
    </json:array>
</json:object>