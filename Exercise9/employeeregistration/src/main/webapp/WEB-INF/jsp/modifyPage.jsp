<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<br/><br/>
<table style="width: 50%" align="center">
    <tr>
        <td>
            &nbsp;${message}
        </td>
    </tr>
</table>
<form method="POST" action="/modify.html">
    <table style="width: 50%" align="center">
        <tr>
            <td>
                Enter Employee ID:
            </td>
            <td><center>
                <input type="text" name="id"/>
                <input type="submit" value="Submit"/>
                </center>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
