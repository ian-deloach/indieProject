<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--TODO Create a logout page and update the app client on AWS -->
<html>
    <head>
        <c:import url="components/bootstrapHeader.jsp"/>
        <title>Bite Size</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty userName}">
                <c:import url="components/guestNavbar.jsp"/>
                <a href = "logIn">Log in</a>
            </c:when>
            <c:otherwise>
                <c:import url="components/navbar.jsp"/>
                <h3>Welcome ${userName}</h3>
                <p>Your email: ${userEmail}</p>
            </c:otherwise>
        </c:choose>
        <c:import url="components/bootstrapBody.jsp"/>
    </body>
    </html>
