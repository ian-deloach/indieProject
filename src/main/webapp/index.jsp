<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <c:import url="components/bootstrapHeader.jsp"/>
        <title>BiteSize</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty username}">
                <c:import url="components/guestNavbar.jsp"/>
                <div class="card text-center">
                    <div class="card-body">
                        <h1 class="card-title">Welcome to BiteSize!</h1>
                        <h3 class="card-text">Let's get some work done today</h3>
                        <a href="log-in" class="btn btn-primary">Log In / Sign Up</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:import url="components/navbar.jsp"/>
                <div class="card text-center">
                    <div class="card-body">
                        <h1 class="card-title">Welcome, ${username}!</h1>
                        <h3 class="card-text">Here's your schedule for today</h3>
                        <p>This is where the schedule would go.</p>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <c:import url="components/footer.jsp"/>
        <c:import url="components/bootstrapBody.jsp"/>
    </body>
</html>
