<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Edit Task</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>
    <h2>Edit Task</h2>

    <form action="edit-task" method="POST">
        <div class="form-group">
            <label for="taskName">Task Name:</label>
            <input type="text" id="taskName" class="form-control" name="taskName" value="${task.name}" size="30">

            <label for="theme">Theme:</label>
            <input list="userThemes" id="theme" class="form-control" name="theme" value="${task.theme.name}"size="30">
            <datalist id="userThemes">
                <c:forEach items="${userThemes}" var="theme">
                <option value="${theme.name}">
                    </c:forEach>
            </datalist>
            <br>
            <label for="urgent" class="form-check-label">Urgent</label>
            <input type="checkbox" id="urgent" class="form-check-input" name="urgent">
            <br><br>

            <label for="description">Description:</label>
            <br>
            <textarea id="description" name="description" class="form-control" rows="5" cols="30">${task.description}</textarea>
            <br><br>
            <input type="submit" value="Edit Task"> <a id="deleteLink" href="delete-task?id=${task.id}">Delete Task</a>

            <input type="hidden" id="taskId" name="taskId" value="${task.id}">
        </div>
    </form>

<c:import url="components/footer.jsp"/>
<c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
