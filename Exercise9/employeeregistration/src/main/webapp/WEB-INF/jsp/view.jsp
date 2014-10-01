<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>View</title>
    <style>
         table, th, td {
             border: 1px solid black;
         }
    </style>
</head>
<body>
<table style="width:100%">
    <p>
        <c:forEach items="${employeeData}" var="employee"><tr>
            <c:forEach items="${employee}" varStatus="loop">
                <td>${employee[loop.index]}</td>
            </c:forEach></tr>
        </c:forEach>
    </p>
</table>
    <p>${message}</p>
    <a href="http://localhost:8080" >Go Back to Index</a>
</body>
</html>