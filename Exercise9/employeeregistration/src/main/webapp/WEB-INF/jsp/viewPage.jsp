<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Page</title>
</head>
<body>
<p>
    <br/>${message}
    <form method="POST" action="/view.html">
    <table style="width:100%">
        <tr>
        <td width="20%">Starting Index:</td><td><input type="text" name="beginIndex" value="0"/></td>
        </tr><tr><td width="20%">Max Result:</td><td><input type="text" name="maxResult" value="10"/></td>
        <br/><input type="submit" value="Submit"/>
        </tr>
    </table>
    </form>
</p>
</body>
</html>
