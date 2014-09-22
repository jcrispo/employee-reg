<html>
<head>
    <title>Spring MVC</title>
</head>
<body>
    <br/> Input User Data <br/>
    <form method="POST" id="form" name="form">
        User ID: <input type="text" id="User ID" name="id" value="" /><br/>
        First Name: <input type="text" id="firstName" name="firstName" value="" /><br/>
        Last Name: <input type="text" id="lastName" name="lastName" value="" /><br/>
        <button formaction="/addData.html">Add Data</button>
    </form>
    <p>
        <form method="POST" id="view" name="view">
            <button formaction="/view.html">View</button>
        </form>
    </p>
</body>
</html>