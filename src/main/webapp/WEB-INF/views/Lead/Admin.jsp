<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 1/3/2023
  Time: 1:54 AM
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
<input type="button" value="Authorities List" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/authorities/list';return false;"><br><br>

<input type="button" value="Add Tax Zone & Amount" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/listTaxZone';return false;"><br><br>
<input type="button" value="Tax Rate Payable and Category All" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/listTaxRatePayCat';return false;"><br><br>
<input type="button" value="Tax Payer Category All" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/listTaxPayerCategory';return false;"><br><br>

<input type="button" value="Exempted All" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/listExemptedPercentage';return false;"><br><br>
<input type="button" value="Rest Amount Tax Payable All" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/admins/listRestAmountTaxPay';return false;"><br><br>

<input type="button" value="Tax Calculator History" onclick="window.location.href='';return false;"><br><br>
<input type="button" value="User List" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/users/list';return false;"><br><br>
<input type="button" value="Calculation History" onclick="window.location.href='http://localhost:8080/5_Tuhin_Git_war_exploded/taxCalculators/listTaxHistory';return false;"><br><br>






</body>
</html>
