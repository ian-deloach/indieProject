<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <title>Log In</title>
</head>
<body>
  <c:import url="components/navbar.jsp"/>
  <form action="logIn" method="POST">
    <label for="email">Email:</label>
    <input type="text" id="email" name="email">
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <input type="submit" value="Log In">

    <c:import url="components/footer.jsp"/>
    <c:import url="components/bootstrapBody.jsp"/>
  </form>
</body>
</html>
