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
    <br/>
</head>
<body>
<br/><br/>
<table style="width:100%">
        <c:forEach items="${employeeData}" var="employee"><tr>
            <c:forEach items="${employee}" varStatus="loop">
                <td>${employee[loop.index]}</td>
            </c:forEach></tr>
        </c:forEach>
    <tr>
        <td>
            ${message}
        </td>
    </tr>
</table>
<br/>
<center>
    <form action="/view.html" method="POST">
        <input type="submit" value="View More"/>
        <input type="hidden" name="beginIndex" value="${beginIndex}"/>
        <input type="hidden" name="maxResult" value="${maxResult}"/>
        <input type="hidden" name="sortBy" value="${sortBy}"/>
        <input type="hidden" name="sort" value="${sort}"/>
    </form>
    <a href="/viewPage.html">Back</a><br/>
    <a href="http://localhost:8080" >Back to Index</a>
</center>
</body>
</html>