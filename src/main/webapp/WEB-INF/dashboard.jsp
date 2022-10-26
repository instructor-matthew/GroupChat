<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Put a Sock In It</title>
<link rel="stylesheet" href="/css/dashboard.css">
</head>
<body>
<div class="top">
	<div class="nav">
		<h1 class="nav-title">Put A Sock(et) In It</h1>
        <ul class="nav-links">
            <li><a href="#" class="active">Home</a></li>
            <li><a href="#">Edit Profile</a></li>
            <li><a href="/chat/new">Start New Chat</a></li>
            <li><form action="/logout"><button class="btn">Sign Out</button></form></li>
        </ul>
	</div>
</div>

<div class="container">
       <div class="row">
              <div class="col">
                <div class="card">
                    <div class="header">
                        <span class="connections">${loggedUser.chatrooms.size() }</span>
                        Current Chats
                    </div>
                    <div class="user-line">

                            <c:forEach items="${allChats}" var="chat">
                            <span class="start">
							<a href="/chat/${chat.id}">
							<c:forEach items="${chat.usersInChat}" var="user">
							${user.fullName}
							</c:forEach>
							 Conversation
							 </a>
							 </span>
						    <span class="end">
							<img src="images/close-circle.png" alt="decline" class="icon-s">
                        </span>
							</c:forEach>



                    </div>
                </div>
          </div>
            <div class="col-2">
                <div class="user-card">
                    <div class="card-header">
                        <img src="${loggedUser.pic }" alt="user pic" class="avatar-m">
                    </div>
                    <div class="card-body">
                        <h1>${loggedUser.fullName}</h1>
                        <h3>
                            <img src="images/map-marker.png" alt="pin" class="icon-s"> 
                            Location
                        </h3>
                        <p>[[Biography Goes Here]]<br>
                       Joined [[Sign Up Date]}</p>
                        <p>
                            <a href="#">
                                <img src="images/gear.png" alt="gear" class="icon-s"> 
                                edit profile
                            </a>
                        </p>
                    </div>
                </div>
              </div>

  	</div>
</div>
 

</body>
</html>