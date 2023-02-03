<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Add new role</p>
<%--@elvariable id="role" type="org.pm.entity.Role"--%>
<form:form method="post" action="/test/role/add" modelAttribute="role">
    <div>
        Role name: <form:input path="role_name"/><form:errors path="role_name"/>
    </div>
    <div>
        Role description: <form:input path="role_description"/><form:errors path="role_description"/>
    </div>
    <input type="submit" value="Save">
</form:form>

</body>
</html>