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
<input type="button" value="Add TaxPayerCategory" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/createTaxPayerCategory';return false;"><br><br>
<c:if test="${!empty taxPayerCategorys}">
    <table align="left" border="1">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${taxPayerCategorys}" var="taxPayerCategory">
            <tr>
                <c:url var="updateLink" value="/admins/editTaxPayerCategory">
                    <c:param name="id" value="${taxPayerCategory.id}" />
                </c:url>
                <c:url var="deleteLink" value="/admins/deleteTaxPayerCategory">
                    <c:param name="id" value="${taxPayerCategory.id}" />
                </c:url>
                <td>${taxPayerCategory.id}</td>
                <td>${taxPayerCategory.name}</td>
                <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<br><br><br>
<input type="button" value="Back" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/home';return false;">
</body>
</html>
