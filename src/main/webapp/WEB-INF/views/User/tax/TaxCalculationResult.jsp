<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 11/6/2022
  Time: 12:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tax Calculation Result</title>
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
      text-align: left;
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

<%--//////////////////////-----1st Table-----///////////////////////////////--%>

<div id="main">
  <table class="timecard">
    <caption>Yearly Assessment</caption>
    <thead>
    <tr>
      <th>Pay&Allowance</th>
      <th>Amount of Income</th>
      <th>Amount of Exempted Income</th>
      <th>Net Taxable Income</th>
    </tr>
    </thead>
    <tbody>
    <tr class="odd">
      <th>Basic Salary</th>
      <td>${BasicSalaryYearly}</td>
      <td>Not Applicable</td>
      <td>${BasicSalaryYearly}</td>
    </tr>
    <tr class="even">
      <th>House Rent</th>
      <td>${HouseRentYearly}</td>
      <td>${HouseRentExempted0}</td>
      <td>${HouseRentExempted}</td>
    </tr>
    <tr class="odd">
      <th>Medical Allowance</th>
      <td>${MedicalAllowanceYearly}</td>
      <td>${MedicalAllowanceExempted0}</td>
      <td>${MedicalAllowanceExempted}</td>
    </tr>
    <tr class="even">
      <th>Conveyance Allowance</th>
      <td>${CAllowanceYearly}</td>
      <td>Upto 30,000</td>
      <td>${CAllowanceExempted}</td>
    </tr>
    <tr class="odd">
      <th>Bonus</th>
      <td>${BonusYearly}</td>
      <td>Not Applicable</td>
      <td>${BonusYearly}</td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
      <th>Total</th>
      <td>${payAllowance}</td>
      <td>${amountExemptedIncome}</td>
      <td>${netTaxableIncome}</td>
    </tr>
    </tfoot>
  </table>
</div>
<br><br>


<%--//////////////////////-----2nd Table-----///////////////////////////////--%>
<div id="main2">
  <table class="timecard">
    <caption>Tax Liability Calculation</caption>
    <thead>
    <tr>
      <th>Step</th>
      <th>NTI ${netTaxableIncome}</th>
      <th>Percentage</th>
      <th>Payable Amount</th>
    </tr>
    </thead>
    <tbody>
    <tr class="odd">
      <th>On First</th>
      <td>${General}</td>
      <td>0%</td>
      <td>0</td>
    </tr>
    <tr class="even">
      <th>On Next</th>
      <td>${first}${netTaxableIncomeG1}</td>
      <td>5%</td>
      <td>${onNext1}</td>
    </tr>
    <tr class="odd">
      <th>On Next</th>
      <td>${second}${netTaxableIncomeG2}</td>
      <td>10%</td>
      <td>${onNext2}</td>
    </tr>
    <tr class="even">
      <th>On Next</th>
      <td>${third}${netTaxableIncomeG3}</td>
      <td>15%</td>
      <td>${onNext3}</td>
    </tr>
    <tr class="odd">
      <th>On Next</th>
      <td>${four}${netTaxableIncomeG4}</td>
      <td>20%</td>
      <td>${onNext4}</td>
    </tr>
    <tr class="even">
      <th>Rest Of Amount</th>
      <td>${five}${netTaxableIncomeG4}</td>
      <td>25%</td>
      <td>${onNext5}</td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
      <th>Total</th>
      <td>${netTaxableIncome}</td>
      <td></td>
      <td>${T_L_PayableAmount}</td>
    </tr>
    </tfoot>
  </table>
</div>
<br><br>
<%--//////////////////////-----3rd Table-----///////////////////////////////--%>

<div id="main2">
  <table class="timecard">
    <caption>Investment Allowance Rebate</caption>
    <thead>
    <tr>
      <th>Name</th>
      <th>Amount</th>
    </tr>
    </thead>
    <tbody>
    <tr class="odd">
      <th>Net Taxable Income</th>
      <td>${netTaxableIncome}</td>
    </tr>
    <tr class="even">
      <th>Maximum Invest(${IAR_P})</th>
      <td>${IAR}</td>
    </tr>
    <tr class="odd">
      <th>IAR_Exempted(15%)</th>
      <td>${IAR_Exempted}</td>
    </tr>
    <tr class="even">
      <th>Total Tax Liability</th>
      <td>${T_L_PayableAmount}</td>
    </tbody>
    <tfoot>
    <tr>
      <th>Total Payable Tax Amount</th>
      <td>${Total_PayAble_Amount}</td>
    </tr>
    </tfoot>
  </table>
</div>
<br><br>

<%--//////////////////////-----4th Table-----///////////////////////////////--%>

<div id="main2">
  <table class="timecard">
    <caption>Pay Able Tax Table</caption>
    <thead>
    <tr>
      <th>Name</th>
      <th>Amount</th>
    </tr>
    </thead>
    <tbody>
    <tr class="odd">
      <th>TaxPayerCategory</th>
      <td>${TaxPayerCategory}</td>
    </tr>
    <tr class="even">
      <th>Zone</th>
      <td>${Zone}</td>
    </tr>
    <tr class="odd">
      <th>NetTaxAbleIncome</th>
      <td>${NetTaxAbleIncome}</td>
    </tr>
    <tr class="even">
      <th>TaxLiabilityAmount</th>
      <td>${TaxLiabilityAmount}</td>
    </tr>
    <tr class=odd">
      <th>TotalPayAbleTaxAmount Yearly</th>
      <td>${TotalPayAbleTaxAmount}</td>
    <tr class="even">
      <th>TotalPayAbleTaxAmount Monthly</th>
      <td>${TotalPayAbleTaxAmountM}</td>
    </tr>

    </tbody>

  </table>
</div>
<br><br>




</body>
</html>
