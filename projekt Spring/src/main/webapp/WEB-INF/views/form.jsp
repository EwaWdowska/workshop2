<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
<form action="/first/form" method="post">
    <label for="paramName">Podaj wartość parametru</label>
    <input type="text" name="paramName" id="paramName"><br>
    <label for="paramDate">Podaj datę w formacie "yyyy-MM-dd"</label>
    <input type="text" name="paramDate" id="paramDate"><br>
    <button type="submit">Send</button>
</form>
</body>
</html>