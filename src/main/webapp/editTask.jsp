<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Edit Task</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>
    <h2>Edit Task</h2>

    <form action="/BiteSize_war/edit-task" method="POST">
        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName" value="${task.name}">
        <br>
        <!--TODO remember to enable deadlines at some point
        <label for="deadline">Deadline (Optional):</label>
        <input type="date" id="deadline" name="deadline">
        <br>-->
        <label for="urgent">Urgent</label>
        <input type="checkbox" id="urgent" name="urgent">
        <br>
        <label for="description">Description:</label>
        <br>
        <textarea id="description" name="description" rows="5" cols="30">${task.description}</textarea>
        <br>
        <input type="submit" value="Edit Task"> <a id="deleteLink" href="/BiteSize_war/delete-task?id=${task.id}">Delete Task</a>

        <input type="hidden" id="taskId" name="taskId" value="${task.id}">
    </form>

<c:import url="components/footer.jsp"/>
<c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
