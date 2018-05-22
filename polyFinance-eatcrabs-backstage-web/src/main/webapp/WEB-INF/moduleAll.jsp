<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:array name="modules" var="module" items="${modules}">
        <json:object>
            <json:property name="moduleId" value="${module.id}"/>
            <json:property name="moduleName" value="${module.name}"/>
            <json:property name="fmoduleId" value="${module.fmoduleId}"/>
        </json:object>
    </json:array>
</json:object>