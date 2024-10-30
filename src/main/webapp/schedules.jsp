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
        <label for="scheduleName">New Schedule:</label>
        <input type="text" id="scheduleName" name="scheduleName">
        <input type="submit" value="Generate new schedule">
    </form>

    <jsp:useBean id="userSchedules" scope="request" type="java.util.List"/>
    <c:forEach items="${userSchedules}" var="schedule">
        <table style="border: 1px solid black">
            <tr>
                <th colspan="4" style="text-align: center">${schedule.name}</th>
            </tr>
            <c:forEach var="task" items="${schedule.tasks}">
                <tr>
                    <td>${task.name}</td>
                    <td>${task.urgency}</td>
                    <td>${task.theme.name}</td>
                    <td>${task.description}</td>
                </tr>
            </c:forEach>
        </table>
    </c:forEach>
    </table>
    <br><hr>

    <c:import url="components/footer.jsp"/>
    <c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
