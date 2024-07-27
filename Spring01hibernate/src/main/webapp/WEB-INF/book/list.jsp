<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book list</title>
</head>
<body>
<h1>Books list</h1>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Rating</th>
        <th>Description</th>
        <th>Publisher</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.title}</td>
            <td>${b.rating}</td>
            <td>${b.description}</td>
            <td>${b.publisher.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>