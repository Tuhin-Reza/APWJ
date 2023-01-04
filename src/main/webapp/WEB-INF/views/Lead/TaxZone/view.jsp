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
<form:form>
<input type="button" value="Add Tax Zone & Percentage" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/createTaxZone';return false;"><br><br>
<c:if test="${!empty taxZones}">
    <table align="left" border="1">
        <thead>
        <tr>
            <th>Zone</th>
            <th>Amount</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${taxZones}" var="taxZone">
            <tr>
                <c:url var="updateLink" value="/admins/editTaxZone">
                    <c:param name="id" value="${taxZone.id}" />
                </c:url>
                <c:url var="deleteLink" value="/admins/deleteTaxZone">
                    <c:param name="id" value="${taxZone.id}"/>
                </c:url>
                <td>${taxZone.zone}</td>
                <td>${taxZone.amount}</td>
                <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</form:form><br>
<br><br>
<input type="button" value="Back" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/home';return false;"><br>
</body>
</html>
