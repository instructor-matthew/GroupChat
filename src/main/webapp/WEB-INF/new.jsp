<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Chat</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="/css/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script></head>
<body>
<div class="container">
<h1>Start New Chat</h1>
<hr>
<p>Select Up To 3 Users</p>
<input id="search" class="form-control form-control-lg" type="text" placeholder="Search For Users">
<ul class="list-group" id="result"></ul>
<div class="box-container">
<div class="box" id="box1"></div>
<div class="box" id="box2"></div>
<div class="box" id="box3"></div>
</div>
<form method="POST" action="/chat/new">
<input type="hidden" name="chat1" id="chat1" value="">
<input type="hidden" name="chat2" id="chat2" value="">
<input type="hidden" name="chat3" id="chat3" value="">

<input type="hidden" name="test" id="test" value="">
<button class="btn btn-primary btn-lg btn-block">Chat!</button>
</form>
</div>

</body>
<script src="/js/app.js"></script>
</html>