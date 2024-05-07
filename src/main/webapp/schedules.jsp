<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Schedule</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>

<table>
    <thead>
    <th>Name</th>
    <th>Expiration Date:</th>
    <th>Date Created:</th>
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


<c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
