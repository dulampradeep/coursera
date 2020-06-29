<%@page import="java.util.*" %>
<html>
	<head>
	<meta charset="utf-8">
		<title>
		login form
		</title>
		<link rel="stylesheet" type="text/css" href="style1.css"/>
                <style>
                    body{
                        background-image: url(stuff//pic1.jpg);
                    }
                </style>
	</head>
        <script src="jquery.min.js" type="text/javascript"></script>
	<body>
	<div class="box">
		<img src="stuff/logo2.png" alt="logo" class="logo">
		<h1>Login </h1>
		<form action="login_validated" method="post">
			<p>User id </p><br>
			<input type="text" name="username" placeholder="Enter username"><br>
			<p>Password
			</p><br>
			<input type="password" name="password" placeholder="Enter password">
			
			<input type="reset" name="submit" value="Reset">
			<input type="submit" name="submit" value="Login">
			<a href="#">Forgot password</a> &nbsp&nbsp
			<a href="#">Not Yet Registered</a>
		</form>
		
	</div>
	
	
	</body>
	
</html>