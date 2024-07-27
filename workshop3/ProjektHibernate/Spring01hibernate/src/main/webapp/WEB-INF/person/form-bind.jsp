<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Person form bind</title>
</head>
<body>
<form:form method="post" modelAttribute="person">
    <label>
        Login: <form:input path="login"/>
    </label>
    <label>
        Password: <form:password path="password"/>
    </label>
    <label>
        Email: <form:input path="email"/>
    </label>
    <input type="submit">
</form:form>
</body>
</html>