<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--TODO Create a logout page and update the app client on AWS -->
<html>
    <head>
        <c:import url="components/bootstrapHeader.jsp"/>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty userName}">
                <c:import url="components/guestNavbar.jsp"/>
                <h1 style="margin: 1em;">Welcome to BiteSize!</h1>
            </c:when>
            <c:otherwise>
                <c:import url="components/navbar.jsp"/>
                <h3 style="margin: 1em;">Welcome, ${userName}!</h3>
            </c:otherwise>
        </c:choose>

        <c:import url="components/footer.jsp"/>
        <c:import url="components/bootstrapBody.jsp"/>
    </body>
    </html>
