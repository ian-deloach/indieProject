<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Create Schedule</title>
    <script src="<%= request.getContextPath() %>/js/test.js"></script>
</head>
<body>
    <c:import url="components/navbar.jsp"/>

    <h2>Schedule Builder</h2>

    <table>
        <thead>
        <th>Name</th>
        <th>Urgency</th>
        <th>Description</th>
        <th>Theme</th>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td><a href="/BiteSize_war/edit-task?id=${task.id}">${task.name}</a></td>
                <td>${task.urgency}</td>
                <td>${task.description}</td>
                <td>${task.theme.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:import url="components/footer.jsp"/>
    <c:import url="components/bootstrapBody.jsp"/>
</body>
</html>

