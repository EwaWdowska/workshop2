<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Multiply</title>
</head>
<body>
<table>
    <c:forEach begin="1" end="${size}" varStatus="outer">
        <tr>
            <c:forEach begin="1" end="${size}" varStatus="inner">
                <td>${inner.index * outer.index}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
