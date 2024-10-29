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

    <c:import url="components/footer.jsp"/>
    <c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
