<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<json:object>
    <json:property name="code" value="0"/>
    <json:property name="message" value="处理成功"/>

    <json:property name="count" value="${count}"/>
    <json:array name="staffs" var="staffInfo" items="${staffInfos}">
        <json:object>
            <json:property name="id" value="${staffInfo.staff.id}"/>
            <json:property name="account" value="${staffInfo.staff.account}"/>
            <json:property name="roleName" value="${staffInfo.roleName}"/>
            <json:property name="createBy" value="${staffInfo.staff.createBy}"/>
            <json:property name="createAt" value="${staffInfo.staff.createAt}"/>
            <json:property name="updateBy" value="${staffInfo.staff.updateBy}"/>
            <json:property name="updateAt" value="${staffInfo.staff.updateAt}"/>
            <json:property name="moblie" value="${staffInfo.staff.mobile}"/>
        </json:object>
    </json:array>
</json:object>