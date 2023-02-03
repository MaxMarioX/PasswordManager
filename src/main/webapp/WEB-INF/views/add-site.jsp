<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add site</title>
</head>
<body>
<form:form method="post" action="/test/site/add" modelAttribute="site">
  <div>
    Site name: <form:input path="site_name"/><form:errors path="site_name"/>
  </div>
  <div>
    Address:<form:input path="site_address"/><form:errors path="site_address"/>
  </div>
  <div>
    Login:<form:input path="site_login"/><form:errors path="site_login"/>
  </div>
  <div>
    Passwords:<form:input path="site_password"/><form:errors path="site_password"/>
  </div>
  <input type="submit" value="Save">
</form:form>
</body>
</html>
