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
<input type="button" value="Add User" onclick="window.location.href='create';return false;"><br><br>
<c:if test="${!empty }">
    <table align="left" border="1">
        <thead>
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${}" var="">
            <tr>
                <c:url var="updateLink" value="/admin/">
                    <c:param name="" value="${}" />
                </c:url>
                <c:url var="deleteLink" value="/admin/">
                    <c:param name="" value="${}" />
                </c:url>
                <td>${.}</td>
                <td>${.}</td>
                <td>${.}</td>
                <td>${.}</td>
                <td>${.}</td>
                <td>${.}</td>
                <td><a href="${updateLink}">Update</a> | <a href="${deleteLink}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
