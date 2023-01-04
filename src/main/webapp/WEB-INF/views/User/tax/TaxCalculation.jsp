<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 11/4/2022
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tax Calculator</title>
</head>
<body>
<form:form action="taxCalculator" modelAttribute="taxCalculator" >
    <table>
        <tr>
            <td>Choose Tax Payer Category</td>
            <td>
                <form:select path="CTPC" id="CTPC">
                <form:option value="">Select Tax Payer Category</form:option>
                <c:forEach items="${taxPayerCategories}" var="taxPayerCategorie">
                    <form:option value="${taxPayerCategorie.name}">${taxPayerCategorie.name}</form:option>
                </c:forEach>
                </form:select> <form:errors path="CTPC" style="color:red" />
            </td>
        </tr>

        <tr>
            <td>Choose Zone </td>
            <td>
                <form:select path="CZ" id="CZ">
                    <form:option value="">Select   Tax   Zone</form:option>
                    <c:forEach items="${taxZones}" var="taxZone">
                        <form:option value="${taxZone.zone}">${taxZone.zone}</form:option>
                    </c:forEach>
                </form:select> <form:errors path="CZ" style="color:red" />
            </td>
        </tr>

        <tr>
            <td><form:label path="BasicSalary">Basic Salary</form:label></td>
            <td>
                <form:input type="number" path="BasicSalary" min="0"/>
                <form:errors path="BasicSalary" style="color:red" />
            </td>
        </tr>

        <tr>
            <td><form:label path="HouseRent">House Rent</form:label></td>
            <td>
                <form:input type="number" path="HouseRent" min="0"/>
                <form:errors path="HouseRent" style="color:red" />
            </td>
        </tr>
        <tr>
            <td><form:label path="">Medical Allowance</form:label></td>
            <td>
                <form:input type="number" path="MedicalAllowance" min="0"/>
                <form:errors path="MedicalAllowance" style="color:red" />
            </td>
        </tr>
        <tr>
            <td><form:label path="CAllowance">Conveyance Allowance</form:label></td>
            <td>
                <form:input type="number" path="CAllowance" min="0"/>
                <form:errors path="CAllowance" style="color:red" />
            </td>
        </tr>
        <tr>
            <td><form:label path="Bonus">Bonus</form:label></td>
            <td>
                <form:input type="number" path="Bonus" min="0"/>
                <form:errors path="Bonus" style="color:red" />
            </td>
        </tr>
        <tr>
            <td><form:label path="IAR"></form:label></td>
            <td>
                <form:input type="hidden" path="IAR" value="1" max="25"/>
                <form:errors path="IAR" style="color:red" />
            </td>
        </tr>
        <tr>
            <td align='center'><input type='reset'  name='calculate' value="Reset">
            <td align='center'><input type='submit' name='calculate' value="Calculate"></td>
        </tr>
    </table>
</form:form>

</body>
</html>
