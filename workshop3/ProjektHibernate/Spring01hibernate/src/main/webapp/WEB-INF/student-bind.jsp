<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create student</title>
</head>
<body>
<h1>Create student</h1>
<form:form method="post" modelAttribute="student">
    <label>
        First name: <form:input path="firstName"/>
    </label>
    <hr/>
    <label>
        Last name: <form:input path="lastName"/>
    </label>
    <hr/>
    <label>Gender:
        <form:radiobutton path="gender" label="Male" value="M"/>
        <form:radiobutton path="gender" label="Female" value="M"/>
    </label>
    <hr/>
    <label>
        Country: <form:select path="country" items="${countries}"/>
    </label>
    <hr/>
    <label>
        Notes: <form:textarea path="notes" rows="5" cols="5"/>
    </label>
    <hr/>
    <label>
        <form:checkbox path="mailingList" /> Add to mailing list?
    </label>
    <hr/>
    <label>
        Programming skills: <form:select path="programmingSkills" items="${programmingSkills}" multiple="true"/>
    </label>
    <hr/>
    <label>
        Hobbies: <form:checkboxes path="hobbies" items="${hobbies}"/>
    </label>
    <hr/>
    <form:button>Save</form:button>
</form:form>
</body>
</html>