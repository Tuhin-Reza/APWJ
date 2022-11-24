<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 11/20/2022
  Time: 10:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Registration</title>
</head>
<body>
<form:form action="submit" modelAttribute="student">
  <label for="student_name">Name:</label>
  <form:input path="student_name" id="student_name"/>
  <form:errors path="student_name"/>
  <br><br>
  <input type="submit">

</form:form>

</body>
</html>
