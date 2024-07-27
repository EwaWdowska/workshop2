<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Validation</title>
</head>
<body>
<h1>Validation result</h1>
<c:choose>
    <c:when test="${empty violations}">
        <p>No validation errors</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="cv" items="${violations}">
                <li>${cv.propertyPath} : ${cv.message}</li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${empty violationsAuthor}">
        <p>No validation errors</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="cv1" items="${violationsAuthor}">
                <li>${cv1.propertyPath} : ${cv1.message}</li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${empty violationsPublisher}">
        <p>No validation errors Publisgher</p>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="cv2" items="${violationsPublisher}">
                <li>${cv2.propertyPath} : ${cv2.message}</li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
</body>
</html>
