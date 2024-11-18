<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>My Tasks</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#searchTerm").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#taskTable > tbody > tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
</head>
<body>
<c:import url="components/navbar.jsp"/>
<!--TODO Make every page only accessible if you have a valid email in the session -->
    <h2>All Tasks</h2>

    <div class="input-group mb3">
            <input id="searchTerm" class="form-control" type="text" placeholder="Search...">
    </div>

    <table id="taskTable" class="table">
        <thead class="table-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Urgency</th>
                <th scope="col">Description</th>
                <th scope="col">Theme</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="task" items="${tasks}">
                <c:choose>
                    <c:when test="${task.urgency=='Urgent'}">
                        <tr class="table-primary">
                            <td><a href="/BiteSize_war/edit-task?id=${task.id}">${task.name}</a></td>
                            <td>${task.urgency}</td>
                            <td>${task.description}</td>
                            <td>${task.theme.name}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td><a href="/BiteSize_war/edit-task?id=${task.id}">${task.name}</a></td>
                            <td>${task.urgency}</td>
                            <td>${task.description}</td>
                            <td>${task.theme.name}</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tbody>
    </table>

<c:import url="components/footer.jsp"/>
<c:import url="components/bootstrapBody.jsp"/>
</body>
</html>
