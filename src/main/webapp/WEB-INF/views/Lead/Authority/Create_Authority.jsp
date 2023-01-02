<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 12/11/2022
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style type="text/css">
    .error {
        color: red;
    }
</style>
<head>
    <title>Assign Authority</title>
</head>
<body>
<table>
    <form:form action="submit" modelAttribute="authority">
        <tr>
            <td>Authority Name:</td>
            <td>
                <form:input path="authority_name" id="authority_name"/><br>
                <form:errors path="authority_name" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td colspan='2'>
                <input type="submit" value="Register">
                <input type="reset" value="Reset" />
            </td>
        </tr>
    </form:form>
</table>
</body>
</html>
