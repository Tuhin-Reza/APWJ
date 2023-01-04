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
<input type="button" value="Add RestAmountTaxPay" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/createRestAmountTaxPay';return false;"><br><br>
<form:form>
<c:if test="${!empty restAmountTaxPays}">
    <table align="left" border="1">
        <thead>
        <tr>
            <th>Name</th>
            <th>Percentage</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${restAmountTaxPays}" var="restAmountTaxPay">
            <tr>
                <c:url var="updateLink" value="/admins/restAmountTaxPayEdit">
                    <c:param name="id" value="${restAmountTaxPay.id}" />
                </c:url>
                <c:url var="deleteLink" value="/admins/deleteRestAmountTaxPay">
                    <c:param name="id" value="${restAmountTaxPay.id}"/>
                </c:url>
                <td>${restAmountTaxPay.amount}</td>
                <td>${restAmountTaxPay.percentage} %</td>
                <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
</form:form>
<br><br><br>
<input type="button" value="Back" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/home';return false;"><br><br>
</body>
</html>
