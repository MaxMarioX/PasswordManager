<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of logs</title>
</head>
<body>
<table>
    <tr>
        <th>Lp</th>
        <th>Account ID</th>
        <th>Account owner</th>
        <th>Data</th>
        <th>Message</th>
    </tr>
    <%
        Integer counter = (Integer) application.getAttribute("hitCounter");
        counter = 0;
    %>
    <c:forEach items="${logs}" var="log">
    <%
        counter++;
        application.setAttribute("hitCounter",counter);
    %>
        <tr>
            <td><%=counter%></td>
            <td>${log.account.account_number}</td>
            <td>${log.account.account_name} ${log.account.account_surname}</td>
            <td>${log.log_date}</td>
            <td>${log.log_msg}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
