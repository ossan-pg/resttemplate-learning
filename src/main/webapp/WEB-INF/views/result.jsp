<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html>
<head>
    <title>RestTemplateサンプル</title>
</head>
<body>
    <h2>status</h2>
    <p>${status}</p>

    <h2>headers</h2>
    <p>
        <ul><c:forEach var="h" items="${headers}">
            <li>${h}</li>
        </c:forEach></ul>
    </p>

    <h2>body</h2>
    <p>${body}</p>
</body>
</html>