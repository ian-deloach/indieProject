<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>My Tasks</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>
    <h2>All Tasks</h2>

    <table>
        <thead>
            <th>Name</th>
            <th>Urgency</th>
            <!--<th>Deadline</th>-->
            <th>Description</th>
        </thead>
        <tbody>
            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td><a href="/BiteSize_war/edit-task?id=${task.id}">${task.name}</a></td>
                    <td>${task.urgency}</td>
                    <!--TODO Deadline looks gross right now. Find a way to format it first.-->
                    <%--<td>${task.deadline}</td>--%>
                    <td>${task.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


<c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
