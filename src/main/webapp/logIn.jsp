<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="components/bootstrapHeader.jsp"/>
    <link href="css/main.css" rel="stylesheet">
    <title>Log In</title>
</head>
<body>
  <c:choose>
    <c:when test="${empty userName}">
      <c:import url="components/guestNavbar.jsp"/>
      <h1>Log In / Sign Up</h1>
    </c:when>
    <c:otherwise>
      <c:import url="components/navbar.jsp"/>
    </c:otherwise>
  </c:choose>

  <form action="logIn" method="POST" style="">
    <label for="email">Email:</label>
    <br>
    <input type="text" id="email" name="email" size="30">
    <br><br>
    <label for="password">Password:</label>
    <br>
    <input type="password" id="password" name="password" size="30">
    <br><br>
    <input type="submit" value="Log In">

    <c:import url="components/footer.jsp"/>
    <c:import url="components/bootstrapBody.jsp"/>
  </form>
</body>
</html>
