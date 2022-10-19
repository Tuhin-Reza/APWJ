<%--
  Created by IntelliJ IDEA.
  User: MY PC
  Date: 10/18/2022
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Calculator</title>
    <style>
        fieldset {
             height: 20%;
             width: 30%;
             background: #e1eff2;
             border: 1px solid rgb(255,232,57);
             text-align: center;
             margin-left: 32%;
             margin-top: 10%;
   }
</style>
</head>
<body>
<form method="post" action="calculation1">
    <fieldset>
        <label>Input_A:</label>
        <input type="number"  name="inputA"> <br><br>
        <label>Input_B:</label>
        <input type="number"  name="inputB"> <br><br>

        <input type="submit" value="Addition" name="Addition">
        <input type="submit" method="post" formaction="calculation2"  value="Subtraction" name="Subtraction">
        <input type="submit" method="post" formaction="calculation3"  value="Multiplication" name="Multiplication">
        <input type="submit" method="post" formaction="calculation4"  value="Division" name="Division">
       <br><br>
        Result : ${inputC}
    </fieldset>
</form>





</body>
</html>
