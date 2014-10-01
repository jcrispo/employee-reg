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
<br/>
<table style="width:100%">
    <tr>
        <td>
            <center>
                &nbsp;${message}
            </center>
        </td>
    </tr>
</table>
<table style="width:100%">
    <c:forEach items="${employeeData}" var="employee"><tr>
        <c:forEach items="${employee}" varStatus="loop">
            <td><center>${employee[loop.index]}</center></td>
        </c:forEach></tr>
    </c:forEach>
</table>
<br/>
<table style="width:30%" align="center">
    <tr>
        <form action="/edit.html" method="POST">
        <td>
            <center>
                <select id="field" name="field">
                    <option value=":firstName">First Name</option>
                    <option value=":middleName">Middle Name</option>
                    <option value=":lastName" selected="selected">Last Name</option>
                    <option value=":gender">Gender</option>
                    <option value=":birthDate">Birth Date</option>
                    <option value=":hireDate">Hire Date</option>
                    <option value=":salary">Salary</option>
                </select>
            </center>
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="employeeData" value="${employeeData}"/>
        </td><td>
        <center><input type="text" name="fieldValue"/></center>
        </td><td>
        <center><button type="submit">Edit</button></center>
        </td>
        </form>
    </tr>
    <tr>
        <form action="/editPosition.html" method="POST">
        <td>
            <center>Edit Position</center>
        </td><td><center>
        <select id="positionId" name="positionId">
            <c:forEach items="${positionData}" var="position">
                <option value=${position[0]}>${position[1]}</option>
            </c:forEach>
        </select></center>
        <input type="hidden" name="id" value="${id}"/>
        </td><td>
        <center><button type="submit">Edit</button></center>
        </td>
        </form>
    </tr>
</table>
<table style="width:30%" align="center">
    <tr>
        <td>
            <center>Delete Employee Data</center>
        </td>
        <td>
            <form action="/delete.html" method="POST">
                <input type="hidden" name="id" value="${id}"/><br/>
                <center><button type="submit">Delete</button></center>
            </form>
        </td>
    </tr>
</table>
<a href="http://localhost:8080"><center>Back to Index</center></a>
</body>
</html>
