<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 1/3/2023
  Time: 12:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <form:form action="restAmountTaxPayUpdate" modelAttribute="restAmountTaxPay">
        <tr>
            <td>Id</td>
            <td>
                <form:input type="hidden" path="id"readonly="true"/>
                <form:errors path="id" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Amount :</td>
            <td>
                <form:input path="amount" id="amount"/><br>
                <form:errors path="amount" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>Percentage :</td>
            <td>
                <form:input path="percentage" id="percentage"/><br>
                <form:errors path="percentage" cssClass="error"/>
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
<br>
<input type="button" value="Back" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/home';return false;"><br><br>
</body>
</html>
