<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p>
    ${message}<br/><br/>
    <br/> Input User Data <br/>
</p>
<form method="POST" id="add" name="add">
    <table style="width:100%">
        <td width="10%">First Name:</td><td><input type="text" id="firstName" name="firstName"/></td>
        <tr>
            <td width="10%">Middle Name:</td><td><input type="text" id="middleName" name="middleName"/></td>
        </tr>
        <tr>
            <td width="10%">Last Name:</td><td><input type="text" id="lastName" name="lastName"/></td>
        </tr>
        <tr>
            <td width="10%">Gender:<br>
                <input type="radio" name="gender" value="male"/>Male<br>
                <input type="radio" name="gender" value="female"/>Female
            </td>
        </tr>
        <tr>
            <td width="10%">Birth Date:</td><td><input type="text" id="birthDate" name="birthDate"/></td>
        </tr>
        <tr>
            <td width="10%">Position ID:</td><td><input type="text" id="positionId" name="positionId"  /></td>
        </tr>
        <tr>
            <td width="10%">Hire Date:</td><td><input type="text" id="hireDate" name="hireDate"  /></td>
        </tr>
        <tr>
            <td width="10%">Salary:</td><td><input type="text" id="salary" name="salary"  /></td>
        </tr>
        <tr>
            <td width="10%"><br><button formaction="/registerEmployee.html">Add Data</button></td>
        </tr>
    </table>
</form>
<p>
    <a href="/populateDb.html">Populate Database With Random Data</a>
</p>
</body>
</html>
