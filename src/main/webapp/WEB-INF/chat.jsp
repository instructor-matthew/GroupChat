<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ChatRoom</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" href="/css/style.css">
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js" integrity="sha512-qTXRIMyZIFb8iQcfjXWCO8+M5Tbc38Qi5WzdPOYZHIlZpzBHG3L3by84BBBOiRGiEb7KKtAOAs5qYdUiZiQNNQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>

<body onload="connect(${chatRoom.id})">
<input type="hidden" id="chatNum" value="${chatRoom.id}"/>
<div class="container">

	<div class="row">
		<div class="col">
			Users in Chat:
			<c:forEach items="${chatRoom.usersInChat}" var="user">
			<p>${user.fullName}</p>
			<p>${user.status}</p>
			</c:forEach>
		</div>
		<div class="col">
			<div class="chat" id="chat">
				<table id="conversation">
				<tbody id="newChat">
				<tr><p>This is the beginning of the conversation between you and 
				<c:forEach items="${chatRoom.usersInChat }" var="user">
				<c:choose>
				<c:when test="${loggedInUser == user}">
				</c:when>
				<c:otherwise>				
				${user.fullName}
				 - Keep it PG</p>		
				</c:otherwise>
				</c:choose>
	
				</c:forEach>				
				</p></tr>
				<c:forEach items="${chatRoom.messages}" var="message">
				<tr>
				<td id="hehe"><img src="${message.sender.pic}"></td><td>${message.sender.fullName} - <fmt:formatDate pattern="MM/dd/YYYY h:mm a" value="${message.timestamp}"/><hr><p> ${message.content}</p></td>
				</tr>
				</c:forEach>
				</tbody>
				</table>		    
			</div>
									<form class="form-inline">
		    <div class="form-group">
		        <input type="text" id="name" class="form-control form-control-lg" placeholder="Nobody likes an introvert.. say something"><button id="send" class="btn btn-default" type="submit">Send</button>
		    </div>
			</form>
</div>

</div>
</div>
</body>
<script src="/js/chat.js"></script>
</html>