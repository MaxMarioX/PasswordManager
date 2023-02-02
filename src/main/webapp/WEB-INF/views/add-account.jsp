<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Add new account</p>
<%--@elvariable id="account" type="org.pm.entity.Account"--%>
<form:form method="post" action="/test/add-account" modelAttribute="account">
    <div>
        Account number: <form:input path="account_number"/><form:errors path="account_number"/>
    </div>
    <div>
        Account name:<form:input path="account_name"/><form:errors path="account_name"/>
    </div>
    <div>
        Account surname:<form:input path="account_surname"/><form:errors path="account_surname"/>
    </div>
    <div>
        Account email:<form:input path="account_email"/><form:errors path="account_email"/>
    </div>
    <div>
        Account phone:<form:input path="account_phone"/><form:errors path="account_phone"/>
    </div>
    <div>
        Account password:<form:input path="account_password"/><form:errors path="account_password"/>
    </div>
    <input type="submit" value="Save">
</form:form>

</body>
</html>
