<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="modules" var="module" items="${modules}">
        <json:object>
            <json:property name="moduleId" value="${module.id}"/>
            <json:property name="moduleName" value="${module.name}"/>
            <json:property name="fmoduleId" value="${module.fmoduleId}"/>
            <json:property name="updateAt" value="${module.updateAt}"/>
            <json:property name="updateBy" value="${module.updateBy}"/>
            <json:property name="createAt" value="${module.createAt}"/>
            <json:property name="createBy" value="${module.createBy}"/>
        </json:object>
    </json:array>
</json:object>