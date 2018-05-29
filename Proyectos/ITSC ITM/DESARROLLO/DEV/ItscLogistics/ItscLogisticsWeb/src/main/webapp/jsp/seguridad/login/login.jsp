<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
    <link href="resources/bootstrap/css/bootstrap_t.css" rel="stylesheet">
    <style type="text/css">
      
    </style>
</head>
<body>
  <div class="container">
    <div class="content">
      <div class="row">
        <div class="login-form">
          <h2>Login</h2>
          <form action="login.action" method="post">
            <fieldset>
              <div class="clearfix">
                <input type="text" name="username" placeholder="Username">
              </div>
              <div class="clearfix">
                <input type="password" name="password" placeholder="Password">
              </div>
              <button class="btn primary" type="submit">Sign in</button>
            </fieldset>
          </form>
        </div>
      </div>
    </div>
  </div> <!-- /container -->
</body>
</html>