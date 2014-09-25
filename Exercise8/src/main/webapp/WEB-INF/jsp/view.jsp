<%@ include file="include.jsp" %>
<html>
<head>
    <title>Spring MVC: View</title>
</head>
<body>
    <h3>Users</h3>
    <c:forEach items="${model.users}" var="user">
        <c:out value="${user.id}" />--<c:out value="${user.lastName}" />--<c:out value="${user.email}" /> <br><br>
    </c:forEach>
    <br>
    <a href="http://localhost:8080">Back to Index</a>
</body>
</html>
