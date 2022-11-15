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
    <style>
        fieldset {
            height: 62%;
            width: 30%;
            background:#FFFF00;
            border: 1px solid rgb(255,255,0);
            text-align: center;
            margin-left: 30%;
            margin-top: 8%;
        }
    </style>
</head>
<%--@elvariable id="TaxCalculation" type=""--%>
<%--@elvariable id="tax" type=""--%>
<body >
<fieldset bgcolor="gold" font-color="red">
<form:form action="taxCalculator" modelAttribute="tax" >
<table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>
    <center><tr>
        <td><h1>Tax Calculation For Salary Based</h1></td>
    </tr><center>

        <table border='0' width='480px' cellpadding='0' cellspacing='0' align='center'>


            <tr>
                <td align='center'>Choose Tax Payer Category</td>
                <td><form:select path="CTPC" id="CTPC">
                    <option value="" selected>Tax Payer Category</option>
                    <option value="General">General</option>
                    <option value="Disabled">Disabled</option>
                    <option value="Female">Female</option>
                    <option value="SeniorCitizen">Senior Citizen(60+)</option>
                    <option value="Freedom Fighter">Freedom Fighter</option>
                </form:select>
                    <form:errors path="CTPC"/></td>
            </tr>
            <tr> <td>&nbsp;</td> </tr>


            <tr>
                <td align='center'>Choose Zone </td>
                <td><form:select path="CZ" id="CZ">
                    <option value="" selected>select Zone</option>
                    <option value="DhakaChattagram">Dhaka/Chattagram</option>
                    <option value="OtherCity">Other City</option>
                    <option value="ROC">Rest Of Country</option>
                </form:select>
                    <form:errors path="CZ"/></td>
            </tr>
            <tr> <td>&nbsp;</td> </tr>


            <tr>
                <td align='center'>Basic Salary</td>
                <td>
                    <form:input type='number' value="0" path="BasicSalary" id='BasicSalary' min="0"/>
                    <form:errors path="BasicSalary"/>
                </td>
            </tr>
            <tr> <td>&nbsp;</td> </tr>

            <tr>
                <td align='center'>House Rent</td>
                <td><form:input type='number' value="0" path="HouseRent" id='HouseRent' min="0"/>
                    <form:errors path="HouseRent"/></td>
            </tr>
            <tr> <td>&nbsp;</td> </tr>

            <tr>
                <td align='center'>Medical Allowance</td>
                <td><form:input type='number' value="0" path="MedicalAllowance" id='MedicalAllowance' min="0"/>
                    <form:errors path="MedicalAllowance"/></td>
            </tr>
            <tr> <td>&nbsp;</td> </tr>

            <tr>
                <td align='center'>Conveyance Allowance</td>
                <td><form:input type="number" value="0" path="CAllowance" id="CAllowance" min="0"/>
                    <form:errors path="MedicalAllowance"/></td>
            </tr>


            <tr> <td>&nbsp;</td> </tr>

            <tr>
                <td align='center'>Bonus</td>
                <td><form:input type='number' value="0" path="Bonus" id='Bonus' min="0"/>
                    <form:errors path="Bonus"/></td>
            </tr>
            <tr> <td>&nbsp;</td> </tr>

            <tr>
                <td align='center'>Investment Allowance Rebate</td>
                <td>
                    <form:input type='number' value="0" path="IAR" id='IAR' min="0"/>
                    <form:errors path="IAR"/>
                </td>
            </tr>
            <tr> <td>&nbsp;</td> </tr>
            <tr> <td>&nbsp;</td> </tr>


            <table border='0' cellpadding='0' cellspacing='0' width='250px' align='center'>
                <tr>
                    <td align='center'><input type='reset'  name='calculate' value="Reset">
                    <td align='center'><input type='submit' name='calculate' value="Calculate"></td>
                </tr>
            </table>
        </table>
</form:form>
</fieldset>
</body>
</html>
