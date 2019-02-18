<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mate login</title>
</head>
<body>
<div>
    <form class = form-login action="<c:url value="/servlet/login"/>" method = "post">
        <h1 class = "h3 mb-3 font-weight-normal">Please, enter your login and password</h1>

        <label for = "inputLogin" class = "sr-only">Login</label>
        <input name="login" type="login" id="inputLogin" class="form-control" placeholder="Login" required autofocus>

        <label for = "inputPassword" class = "sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password">

        <button class="btn btn-lg btn-primary btn-block" type="submit">LOG IN</button>
    </form>
</div>
</body>
</html>
