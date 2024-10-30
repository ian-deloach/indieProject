<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Add Task</title>
</head>
<body>
<c:import url="components/navbar.jsp"/>

<!-- TODO Currently, this is a bug. Does not delete itself properly -->
    <c:choose>
        <c:when test="${empty addMessage}">
            <h2>Add Task</h2>
        </c:when>
    <c:otherwise>
            <h3>${addMessage}</h3>
    </c:otherwise>
    </c:choose>

    <!--TODO Create dynamic theme dropdown list -->

    <form action="add-task" method="POST">
        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName" size="30">
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
        <textarea id="description" name="description" rows="5" cols="30"></textarea>
        <br><br>
        <input type="submit" value="Add Task">
    </form>

<c:import url="components/footer.jsp"/>
<c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
