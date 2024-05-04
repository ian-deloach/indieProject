<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--TODO Create a logout page and update the app client on AWS -->
<html>
<body>
    <h1>Bite Size</h1>
    <c:import url="components/navbar.jsp"/>
    <c:choose>
        <c:when test="${empty userName}">
            <a href = "logIn">Log in</a>
        </c:when>
        <c:otherwise>
            <h3>Welcome ${userName}</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
