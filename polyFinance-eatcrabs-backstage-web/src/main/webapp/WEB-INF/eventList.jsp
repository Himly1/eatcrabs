<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="events" var="event" items="${events}">
        <json:object>
            <json:property name="id" value="${event.id}"/>
            <json:property name="title" value="${event.title}"/>
            <json:property name="aims" value="${event.aims}"/>
            <json:property name="updateAt" value="${event.updateAt}"/>
            <json:property name="online" value="${event.online}"/>
            <json:property name="content" value="${event.content}"/>
            <json:property name="img" value="${event.img}"/>
        </json:object>
    </json:array>
</json:object>