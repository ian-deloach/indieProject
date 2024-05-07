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
                <h1>Welcome to BiteSize!</h1>
                <p>As you can probably see, I did not finish on time :(</p>
            </c:when>
            <c:otherwise>
                <c:import url="components/navbar.jsp"/>
                <h3>Welcome, ${userName}!</h3>
                <p>This would be where I put the daily schedule that the app generates... if it worked...</p>
            </c:otherwise>
        </c:choose>

        <c:import url="components/footer.jsp"/>
        <c:import url="components/bootstrapBody.jsp"/>
    </body>
    </html>
