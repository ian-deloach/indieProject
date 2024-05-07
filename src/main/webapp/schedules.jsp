<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Schedule</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>
    <h2>Schedules</h2>

    <table>
        <thead>
        <th>Name</th>
        <th>Due:</th>
        <th>Created:</th>
        </thead>
        <tbody>
        <c:forEach var="schedule" items="${schedules}">
            <tr>
                <td>${schedule.name}</td>
                <td>${schedule.expirationDate}</td>
                <td>${schedule.dateCreated}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br><hr>
    <p>These were supposed to be tables, but 1. I am out of time, and 2. They looked clunky.</p>
    <p>I thought about opting into doing click-ins like I did for tasks, but that seems counter-intuitive.</p>
    <p>Ultimately, I decided to get the CRUD working and finish after class is over.</p>

    <c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
