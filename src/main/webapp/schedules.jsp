<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Schedule</title>
</head>
<body>
    <c:import url="components/navbar.jsp"/>
    <h2>Schedules</h2>
    <br>
    <form action="add-schedule" method="POST">
        <div class="mb3">
            <label for="scheduleName" class="form-label">Random Schedule Name:</label>
            <input type="text" id="scheduleName" class="form-control" name="scheduleName">
            <br>
            <input type="submit" class="btn btn-primary mb-3" value="Generate Random Schedule">
        </div>
    </form>

    <jsp:useBean id="userSchedules" scope="request" type="java.util.List"/>
    <c:forEach items="${userSchedules}" var="schedule">
        <table class="table">
            <thead class="table-dark">
                <tr>
                    <th colspan="4" style="text-align: center">${schedule.name}</th>
                </tr>
            </thead>
            <c:forEach var="task" items="${schedule.tasks}">
                <c:choose>
                    <c:when test="${task.urgency=='Urgent'}">
                        <tr class="table-primary">
                            <td>${task.name}</td>
                            <td>${task.urgency}</td>
                            <td>${task.theme.name}</td>
                            <td>${task.description}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>${task.name}</td>
                            <td>${task.urgency}</td>
                            <td>${task.theme.name}</td>
                            <td>${task.description}</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </table>
    </c:forEach>
    <br><hr>

    <c:import url="components/footer.jsp"/>
    <c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
