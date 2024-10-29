<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Schedule</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>
    <h2>Schedules</h2>

    <jsp:useBean id="userSchedules" scope="request" type="java.util.List"/>
    <c:forEach items="${userSchedules}" var="schedule">
        <table>
            <th>${schedule.name}</th>
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
