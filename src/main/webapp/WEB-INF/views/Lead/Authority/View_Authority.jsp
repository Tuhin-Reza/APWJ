<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 12/11/2022
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authority List</title>
</head>
<body>
<br>
<input type="button" value="Add Authority" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/authorities/create';return false;"><br><br>
<form:form>
<c:if test="${!empty authorities}">
    <table align="left" border="1">
        <thead>
        <tr>
            <th>Authority Name</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${authorities}" var="authority">
            <tr>
                <c:url var="updateLink" value="/authorities/edit">
                    <c:param name="authority_id" value="${authority.authority_id}" />
                </c:url>
                <c:url var="deleteLink" value="/authorities/delete">
                    <c:param name="authority_id" value="${authority.authority_id}" />
                </c:url>
                <td>${authority.authority_name}</td>
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
