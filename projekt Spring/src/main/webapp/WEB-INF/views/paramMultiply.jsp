<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Param Multiply</title>
</head>
<body>
<table>
    <c:forEach begin="1" end="${rows}" varStatus="row">
        <tr>
            <c:forEach begin="1" end="${cols}" varStatus="col">
                <td>${col.index * row.index}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
