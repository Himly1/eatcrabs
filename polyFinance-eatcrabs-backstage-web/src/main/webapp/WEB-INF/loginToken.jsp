<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>
    <json:property name="account" value="${account}"/>
    <json:property name="roleName" value="${token.roleName}"/>

    <json:array name="perms" var="perm" items="${token.perms}">
        <json:object>
            <json:property name="fmoduleId" value="${perm.fmoduleId}"/>
            <json:property name="moduleId" value="${perm.id}"/>
            <json:property name="moduleName" value="${perm.name}"/>
        </json:object>
    </json:array>
</json:object>