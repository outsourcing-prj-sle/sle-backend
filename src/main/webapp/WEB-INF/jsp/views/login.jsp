<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <script type="text/javascript">
        function showError(message) {
            alert(message);
        }

        <c:if test="${not empty errorMessage}">
            showError("${errorMessage}");
        </c:if>
    </script>
</head>
<body>
    <h1>Login</h1>
    <form action="/api/login" method="post">
        <div>
            <label for="id">ID:</label>
            <input type="text" id="id" name="id" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>
</body>
</html>