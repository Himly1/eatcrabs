<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="banks" var="bank" items="${banks}">
        <json:object>
            <json:property name="id" value="${bank.id}"/>
            <json:property name="name" value="${bank.name}"/>
            <json:property name="number" value="${bank.number}"/>
            <json:property name="single" value="${bank.single}"/>
            <json:property name="oneDay" value="${bank.oneDay}"/>
            <json:property name="icon" value="${bank.icon}"/>
        </json:object>
    </json:array>
</json:object>