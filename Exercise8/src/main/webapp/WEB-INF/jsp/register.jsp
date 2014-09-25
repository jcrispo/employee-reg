<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <p>
    <br/> Input User Data <br/>
  </p>
    <%--<form:form method="POST" commandName="user">--%>
        <%--user id: <form:input path="id"/><br/>--%>
        <%--last name: <form:input path="lastname"/><br/>--%>
        <%--email: <form:input path="email"/><br/>--%>
        <%--<input type="submit" value="Submit"/>--%>
    <%--</form:form>--%>
    <form method="POST" id="add" name="add">
        <table style="width:100%">
            <tr>
                <td width=20%">User ID: <input type="text" id="User ID" name="id" /></td>
                <td width="80%"></td>
            </tr>
                <td width=20%">First Name: <input type="text" id="firstName" name="firstName"/></td>
                <td width="80%"></td>
            <tr>
                <td width=20%">Middle Name: <input type="text" id="middleName" name="middleName"/></td>
                <td width="80%"></td>
            </tr>
            <tr>
                <td width=20%">Last Name: <input type="text" id="lastName" name="lastName"/></td>
                <td width="80%"></td>
            </tr>
            <tr>
                <td width=20%">Birth Date: <input type="text" id="birthDate" name="birthDate"/></td>
                <td width="80%"></td>
            </tr>
            <tr>
                <td width=20%">Gender: <input type="text" id="gender" name="gender"/></td>
                <td width="80%"></td>
            </tr>
            <tr>
                <td width=20%">Email: <input type="text" id="email" name="email"  /></td>
                <td width="80%"></td>
            </tr>
            <tr>
                <td width=20%"><button formaction="/view.html">Add Data</button></td>
                <td width="80%"></td>
            </tr>
        </table>
    </form>
    <a href="http://localhost:8080">Back to Index</a>
</body>
</html>
