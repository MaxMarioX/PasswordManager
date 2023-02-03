<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Number</th>
        <th>Name</th>
        <th>Surname</th>
        <th>E-mail</th>
        <th>Phone</th>
        <th>Password</th>
        <th>Lock</th>
        <th>Strong Auth</th>
    </tr>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>${account.account_number}</td>
            <td>${account.account_name}</td>
            <td>${account.account_surname}</td>
            <td>${account.account_email}</td>
            <td>${account.account_phone}</td>
            <td>${account.account_password}</td>
            <td>${account.account_password_blk}</td>
            <td>${account.account_strong_auth}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
