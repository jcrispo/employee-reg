<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <br/>${message}
<form method="POST" action="/modify.html">
    <table style="width:100%">
        <tr>
            <td width="20%">Enter Employee ID:</td><td><input type="text" name="id"/></td>
            <br/><input type="submit" value="Submit"/>
        </tr>
    </table>
</form>
</body>
</html>
