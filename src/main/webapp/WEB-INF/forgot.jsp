<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <p>Please enter your email address to retrieve your password.</p>
        
        <form action="forgot" method="post">
            <label for="email">Email Address:</label>
            <input type="email" name="email" id="email">
        
            <button type="submit">Submit</button>
        </form>
        
        <p>${message}</p>
    </body>
</html>
