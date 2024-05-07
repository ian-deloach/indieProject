<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Error!</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>
<h1>Uh oh!</h1>
<p>It looks like something went wrong!</p>
<br>
<hr>
<br>
<c:choose>
    <c:when test="${empty errorMessage}">
        <p>Unfortunately, no error message could be found :(</p>
    </c:when>
    <c:otherwise>
        <p>${errorMessage}</p>
    </c:otherwise>
</c:choose>

<c:import url="components/footer.jsp"/>
<c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
