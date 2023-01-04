<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 11/8/2022
  Time: 1:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tax Calculator History</title>
    <style type="text/css">
        body {
            background-color: #f6f6ff;
            font-family: Calibri, Myriad;
        }

        #main {
            width: 780px;
            padding: 20px;
            margin: auto;
        }
        #main2 {
            width: 780px;
            padding: 20px;
            margin: auto;
        }

        table.timecard {
            margin: auto;
            width: 600px;
            border-collapse: collapse;
            border: 1px solid #fff; /*for older IE*/
            border-style: hidden;
        }

        table.timecard caption {
            background-color: #f79646;
            color: #fff;
            font-size: x-large;
            font-weight: bold;
            letter-spacing: .3em;
        }

        table.timecard thead th {
            padding: 8px;
            background-color: #fde9d9;
            font-size: large;
        }

        table.timecard thead th#thDay {
            width: 40%;
        }

        table.timecard thead th#thRegular, table.timecard thead th#thOvertime, table.timecard thead th#thTotal {
            width: 20%;
        }

        table.timecard th, table.timecard td {
            padding: 3px;
            border-width: 1px;
            border-style: solid;
            border-color: #f79646 #ccc;
        }

        table.timecard td {
            text-align: right;
        }

        table.timecard tbody th {
            text-align: ;
            font-weight: normal;
        }

        table.timecard tfoot {
            font-weight: bold;
            font-size: large;
            background-color: #687886;
            color: #fff;
        }

        table.timecard tr.even {
            background-color: #fde9d9;
        }
    </style>
</head>
<body>
<div id="main">
<c:if test="${!empty taxHistories}">
<table class="timecard">
    <caption>Tax Calculator History</caption>
    <thead>
    <tr>
        <th>TaxPayerCategory</th>
        <th>Zone</th>
        <th>NetTaxAbleIncome</th>
        <th>TaxLiabilityAmount</th>
        <th>TotalPayAbleTaxAmount</th>
        <th>TotalPayAbleTaxAmountM</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${taxHistories}" var="taxHistorie">
        <tr class="odd">
            <c:url var="deleteLink" value="/admins/deleteTaxHistory">
                <c:param name="id" value="${taxHistorie.id}}"/>
            </c:url>
            <td>${taxHistorie.username}</td>
            <td>${taxHistorie.taxPayerCategory}</td>
            <td>${taxHistorie.zone}</td>
            <td>${taxHistorie.netTaxAbleIncome}</td>
            <td>${taxHistorie.taxLiabilityAmount}</td>
            <td>${taxHistorie.totalPayAbleTaxAmountM}</td>
            <td>${taxHistorie.totalPayAbleTaxAmount}</td>
<%--            <td> <a href="${deleteLink}">Delete</a></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
    </div>


</body>
</html>
