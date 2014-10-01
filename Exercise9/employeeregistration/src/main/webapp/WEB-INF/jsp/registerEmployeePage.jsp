<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <br/><br/><br/><br/><br/>
</head>
<body>
<table style="width: 40%" align="center">
    <tr>
        <td><center>Input User Data</center></td>
    </tr>
</table>
<form method="POST" id="add" name="add">
    <table style="width:40%" align="center">
        <td width="50%"><center>First Name:</center></td><td><input type="text" id="firstName" name="firstName"/></td>
        <tr>
            <td width="50%"><center>Middle Name:</center></td><td><input type="text" id="middleName" name="middleName"/></td>
        </tr>
        <tr>
            <td width="50%"><center>Last Name:</center></td><td><input type="text" id="lastName" name="lastName"/></td>
        </tr>
        <tr>
            <td width="50%"><center>Gender:</center>
            </td><td><input type="radio" name="gender" value="male" checked="checked"/>Male
                <input type="radio" name="gender" value="female"/>Female
            </td>
        </tr>
        <tr>
            <td width="50%"><center>Birth Date:</center></td><td><input type="text" id="birthDate" name="birthDate"/></td>
        </tr>
        <tr>
            <td>
                <center>Edit Position</center>
            </td>
            <td>
                <select id="positionId" name="positionId">
                    <c:forEach items="${positionData}" var="position">
                        <option value=${position[0]}>${position[1]}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="id" value="${id}"/>
                <input type="hidden" name="positionData" value="${positionData}"/>
            </td>
        </tr>
        <tr>
            <td width="50%"><center>Hire Date:</center></td><td><input type="text" id="hireDate" name="hireDate"  /></td>
        </tr>
        <tr>
            <td width="50%"><center>Salary:</center></td><td><input type="text" id="salary" name="salary"  /></td>
        </tr>
        <tr>
            <td></td>
            <td width="50%"><center><button formaction="/registerEmployee.html">Add Data</button></center></td>
        </tr>
    </table>
</form>
<table style="width:40%" align="center">
    <tr>
        <td>
            <center>
                <a href="/populateDb.html">Populate Database With Random Data</a>
            </center>
        </td>
        <td>
            <center>
                <a href="http://localhost:8080">Back to Index</a>
            </center>
        </td>
    </tr>
</table>
<table style="width:40%" align="center">
    <tr>
        <td>
            <center>
                &nbsp;${message}
            </center>
        </td>
    </tr>
</table>
</body>
</html>
