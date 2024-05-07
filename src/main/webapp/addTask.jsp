<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Add Task</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>

    <form action="/add-task" method="POST">
        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName">
        <br>
        <label for="deadline">Deadline (Optional):</label>
        <input type="date" id="deadline" name="deadline">
        <br>
        <label for="urgent">Urgent</label>
        <input type="checkbox" id="urgent" name="urgent">
        <br>
        <label for="description">Description:</label>
        <br>
        <textarea id="description" name="description" rows="5" cols="30"></textarea>
    </form>

<c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
