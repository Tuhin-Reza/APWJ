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
<input type="button" value="Add Tax Rate Payable&Category" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/createTaxRatePayCat';return false;"><br><br>
<form:form>
<c:if test="${!empty taxRatePayCats}">
    <table align="left" border="1">
        <thead>
        <tr>
            <th>Category</th>
            <th>Amount</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${taxRatePayCats}" var="taxRatePayCat">
            <tr>
                <c:url var="updateLink" value="/admins/editTaxRatePayCat">
                    <c:param name="id" value="${taxRatePayCat.id}" />
                </c:url>
                <c:url var="deleteLink" value="/admins/deleteTaxRatePayCat">
                    <c:param name="id" value="${taxRatePayCat.id}" />
                </c:url>
                <td>${taxRatePayCat.category}</td>
                <td>${taxRatePayCat.amount}</td>
                <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</form:form>
<br><br>
<input type="button" value="Back" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/home';return false;"><br>
</body>
</html>
