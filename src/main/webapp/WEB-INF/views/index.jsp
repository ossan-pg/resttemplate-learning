<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  Created by IntelliJ IDEA.
  User: ossan
  Date: 2023/07/15
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>RestTemplateサンプル</title>
</head>
<body>
    <form:form
            modelAttribute="inputForm"
            action="${pageContext.request.contextPath}/echo/hello">
        <form:label path="id">id:</form:label>
        <form:input path="id" />
        <form:label path="name">name:</form:label>
        <form:input path="name" />
        <input type="submit" />
    </form:form>
</body>
</html>