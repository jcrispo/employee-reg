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
</head>
<body>
    <p>
        ${message}
    </p>
    <table style="width:100%">
        <p>
            <c:forEach items="${employeeData}" var="employee"><tr>
                <c:forEach items="${employee}" varStatus="loop">
                    <td>${employee[loop.index]}</td>
                </c:forEach></tr>
            </c:forEach>
        </p>
    </table>
    <p>
    <table style="width:30%">
    <tr><form action="/edit.html" method="POST">
        <td>
        <select id="field" name="field">
        <option value=":firstName">First Name</option>
        <option value=":middleName">Middle Name</option>
        <option value=":lastName" selected="selected">Last Name</option>
        <option value=":gender">Gender</option>
        <option value=":birthDate">Birth Date</option>
        <option value=":hireDate">Hire Date</option>
        <option value=":salary">Salary</option>
        </select>
        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="employeeData" value="${employeeData}"/>
        </td><td>
        <input type="text" name="fieldValue"/>
        </td><td>
        <button type="submit">Edit</button>
        </td>
    </form></tr>
        <tr>
    <form action="/editPosition.html" method="POST">
        <td>
            Edit Position
        </td><td>
        <select id="positionId" name="positionId">
            <c:forEach items="${positionData}" var="position">
                <option value=${position[0]}>${position[1]}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="id" value="${id}"/>
        </td><td>
        <button type="submit">Edit</button>
        </td>
    </form></tr>
    </table>
    <p><form action="/delete.html" method="POST">
        <input type="hidden" name="id" value="${id}"/>
        <button type="submit">Delete</button>
    </form> </p>
    <a href="http://localhost:8080" >Go Back to Index</a>
</body>
</html>
