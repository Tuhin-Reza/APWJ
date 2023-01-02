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
<input type="button" value="Add Exempted" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/createExemptedPercentage';return false;"><br><br>
<c:if test="${!empty exemptedPercentages}">
    <table align="left" border="1">
        <thead>
        <tr>
            <th>Name</th>
            <th>Percentage</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${exemptedPercentages}" var="exemptedPercentage">
            <tr>
                <c:url var="updateLink" value="/admins/editExemptedPercentage">
                    <c:param name="id" value="${exemptedPercentage.id}" />
                </c:url>
                <c:url var="deleteLink" value="/admins/deleteExemptedPercentage">
                    <c:param name="id" value="${exemptedPercentage.id}" />
                </c:url>
                <td>${exemptedPercentage.name}</td>
                <td>${exemptedPercentage.percentage}%</td>
                <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
