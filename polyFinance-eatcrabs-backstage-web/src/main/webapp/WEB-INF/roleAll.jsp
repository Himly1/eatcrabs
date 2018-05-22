<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:array name="roles" var="role" items="${roles}">
        <json:object>
            <json:property name="id" value="${role.id}"/>
            <json:property name="name" value="${role.name}"/>
        </json:object>
    </json:array>
</json:object>