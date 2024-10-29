<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log In</title>
</head>
<body>
  <form action="logIn" method="POST">
    <label for="email">Email:</label>
    <input type="text" id="email" name="email">
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password">
    <input type="submit" value="Log In">
  </form>
</body>
</html>
