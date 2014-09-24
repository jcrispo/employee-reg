<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <br/> Input User Data <br/>
    <%--<form:form method="POST" commandName="user">--%>
        <%--user id: <form:input path="id"/><br/>--%>
        <%--last name: <form:input path="lastname"/><br/>--%>
        <%--email: <form:input path="email"/><br/>--%>
        <%--<input type="submit" value="Submit"/>--%>
    <%--</form:form>--%>
    <form method="POST" id="add" name="add">
        User ID: <input type="text" id="User ID" name="id" value="" /><br/>
        Last Name: <input type="text" id="lastName" name="lastName" value="" /><br/>
        Email: <input type="text" id="email" name="email" value="" /><br/>
        <button formaction="/view.html">Add Data</button>
    </form>
    <a href="http://localhost:8080">Back to Index</a>
</body>
</html>
