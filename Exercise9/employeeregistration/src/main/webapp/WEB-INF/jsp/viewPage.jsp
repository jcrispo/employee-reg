<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Page</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <br/><br/><br/><br/><br/>
</head>
<body>
<table style="width: 50%" align="center">
    <tr>
        <td>

        </td>
    </tr>
</table>
    <form method="POST" action="/view.html">
    <table style="width: 50%" align="center">
        <tr>
            <td>
                <table style="width: 100%; border-style: none ">
                    <tr>
                        <td>
                            <center>
                                Sort By:
                            </center>
                        </td>
                        <td><center>
                            <select id="sortBy" name="sortBy">
                                <option value=" ORDER BY E.id" selected="selected">Employee ID</option>
                                <option value=" ORDER BY E.firstName">First Name</option>
                                <option value=" ORDER BY E.middleName">Middle Name</option>
                                <option value=" ORDER BY E.lastName">Last Name</option>
                                <option value=" ORDER BY E.birthDate">Birth Date</option>
                                <option value=" ORDER BY C.salary">Salary</option>
                            </select>
                            </center>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                <select id="sort" name="sort">
                    <option value=" ASC" selected="selected">Ascending</option>
                    <option value=" DESC">Descending</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <center>
                    Show How Many Results?:
                </center>
            </td>
            <td>
                    <input type="text" name="maxResult" value="10"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                    <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
    </form>
<table style="width: 50%" align="center">
    <tr>
        <td>
            <center>
                &nbsp;${message}
            </center>
        </td>
    </tr>
</table>
<br/>
<a href="http://localhost:8080"><center>Back to Index</center></a>
</body>
</html>
