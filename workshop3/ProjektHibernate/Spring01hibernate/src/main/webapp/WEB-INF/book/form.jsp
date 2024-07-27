<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Book form</title>
<style> .error{
    color: red;
    font-weight: bold;
}
</style>
</head>
<body>
<form:form modelAttribute="book" method="post">
    <label>
        Title: <form:input path="title" />
        <form:errors path="title" cssClass="error">
        </form:errors>
    </label>
    <label>
        Rating: <form:input path="rating" type="number"/>
        <form:errors path="rating" cssClass="error">
        </form:errors>
    </label>
    <label>
        Description: <form:textarea path="description" rows="5" cols="20" />
        <form:errors path="description" cssClass="error">
        </form:errors>
    </label>
    <label>
        Publisher: <form:select path="publisher" items="${publishers}" itemValue="id" itemLabel="name" />
    </label>
    <label>
        Pages: <form:input path="pages" type="number" min="2"/>
        <form:errors path="pages" cssClass="error">
        </form:errors>
    </label>
    <label>
        Author: <form:input path="author" type="text" min="2"/>
        <form:errors path="author" cssClass="error">
        </form:errors>
    </label>
    <input type="submit">
</form:form>
</body>
</html>