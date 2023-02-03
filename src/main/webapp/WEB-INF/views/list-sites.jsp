<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
  <tr>
    <th>Lp</th>
    <th>Name</th>
    <th>Address</th>
    <th>Login</th>
    <th>Password</th>
    <th>Date</th>
  </tr>
  <%
    Integer counter = (Integer) application.getAttribute("hitCounter");
    counter = 0;
  %>
  <c:forEach items="${sites}" var="site">
    <%
      counter++;
      application.setAttribute("hitCounter",counter);
    %>
    <tr>
      <td><%=counter%></td>
      <td>${site.site_name}</td>
      <td>${site.site_address}</td>
      <td>${site.site_login}</td>
      <td>${site.site_password}</td>
      <td>${site.site_date}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
