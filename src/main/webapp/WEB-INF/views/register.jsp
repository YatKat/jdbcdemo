<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Mate Account</title>
</head>
<body>
<div>
    <form class = form-signin action="<c:url value="/servlet/register"/>" method = "post">
        <h1 class = "h3 mb-3 font-weight-normal">Please register your account</h1>

        <label for = "inputName" class = "sr-only">Name</label>
        <input name="name" type="login" id="inputName" class="form-control" placeholder="Name" required autofocus>

        <label for = "inputLogin" class = "sr-only">Login</label>
        <input name="login" type="login" id="inputLogin" class="form-control" placeholder="Login" required autofocus>

        <label for = "inputPassword" class = "sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    </form>
</div>
</body>
</html>