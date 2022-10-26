<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Put a Sock In It</title>
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Barlow" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Courgette&display=swap" rel="stylesheet">

<link rel="stylesheet" href="/css/normalize.css">
<link rel="stylesheet" href="/css/grid.css">
</head>
<body>
<header>
<nav>
<div class="main-nav">
    <p class="sig">put A Sock(et) in it</p>
</div>
</nav>
</header>

  <div class="hero-text-box">
      <p class="sig">put A Sock(et) in it</p>
      <p class="slogan">chat. made. simple.</p>
      <div class="register">
        <form method="post" action="/register">
          {% csrf_token %}
          <input type="text" name="name" placeholder="Your Name*"><input type="text" name="email"
            placeholder="Your Email*"><input type="password" name="password"
            placeholder="Choose Password*"><button>Register</button>
        </form>
      </div>
    </div>

<div class="container">
<h1>Put a Sock(et) In It</h1>
<div class="row">
<div class="col">
<h3>Login</h3>
<p>${loginError}</p>
<form method="POST" action="/login">
<div class="form-group">
<label>Email:</label>
<input class="form-control" type="email" name="loginEmail">
</div>
<div class="form-group">
<label>Password:</label>
<input class="form-control" type="password" name="loginPassword">
<button class="btn btn-danger">Login</button>
</div>
</form>
</div>
<div class="col">
<h3>Register</h3>
<form:form action="/register" method="post" modelAttribute="user">
			    <div class="form-group">
			        <form:label path="userName">UserName</form:label>
			        <form:errors path="userName"/>
			        <form:input class="form-control" path="userName"/>
			    </div>
			    <div class="form-group">
			        <form:label path="fullName">Full Name</form:label>
			        <form:errors path="fullName"/>
			        <form:input class="form-control" path="fullName"/>
			    </div>
			    <div class="form-group">
			        <form:label path="email">Email</form:label>
			        <form:errors path="email"/>
			       <form:input type="email" class="form-control" path="email"/>
			    </div>
			    <div class="form-group">
			        <form:label path="password">Password</form:label>
			        <form:errors path="password"/>
			       <form:input type="password" class="form-control" path="password"/>
			    </div>
			    <div class="form-group">
			        <form:label path="confirmPassword">Confirm Password</form:label>
			        <form:errors path="confirmPassword"/>
			       <form:input type="password" class="form-control" path="confirmPassword"/>
			    </div>
			    <input class="btn btn-danger" type="submit" value="Submit"/>
			</form:form>
    </div>


</div>
</div>


</div>
</body>
</html>