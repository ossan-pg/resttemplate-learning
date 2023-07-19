<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>RestTemplateサンプル</title>
</head>
<body>
    <form:form
            modelAttribute="inputForm"
            action="${pageContext.request.contextPath}/result">
        <form:label path="id">id:</form:label>
        <form:input path="id" />
        <br>
        <form:label path="name">name:</form:label>
        <form:input path="name" />
        <br>
        <input type="submit" />
    </form:form>
</body>
</html>