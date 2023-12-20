<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>homepage</title>
<link rel="stylesheet" href="/Proyecto-Programacion-Web/css/styles.css" type="text/css">
</head>
<body>
<% 
	if (request.getParameter("reset") != null) {
	%> 
	<jsp:setProperty property="emailUser" value="" name="customerBean"/>
	<%
	}
	if (customerBean == null || customerBean.getEmailUser()=="") {
%>
<h1 class="Title">Camps Manager</h1>
<div class="ButtonContainer">
	<a class="Button" href="/Proyecto-Programacion-Web/mvc/controller/login/login.jsp">Log in</a>
	<a class="Button" href="/Proyecto-Programacion-Web/mvc/controller/login/registerController.jsp">Sign up</a>
</div>
<% } else { %>

	<% if(customerBean.getRol() == "Admin"){ %>
		
		<nav class="Nav">
			<ul class="nav1">
				<li><a href="/Proyecto-Programacion-Web/index.jsp" class="navLink">Home</a></li>
				<li class="desplegar">Camps Management
					<ul class="nav2">
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/admin/createCampView.jsp" class="navLink">Create Camp</a></li>
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/admin/createActivityView.jsp" class="navLink">Create Activity</a></li>
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/admin/createMonitorView.jsp" class="navLink">Create Monitor</a></li>
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/admin/associateActivitytoCampForm.jsp" class="navLink">Activity to Camp</a></li>
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/admin/associateMonitortoActivityView.jsp" class="navLink">Monitor to Activity</a></li>
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/admin/associateMonitortoCampView.jsp" class="navLink">Monitor to Camp</a></li>
					</ul>
				</li>
				<li><a href="/Proyecto-Programacion-Web/mvc/controller/login/changeData.jsp" class="navLink">Edit Profile</a></li>
				<li><a href="/Proyecto-Programacion-Web/mvc/controller/logout/logout.jsp" class="navLink">Log out</a></li>
			</ul>
		</nav>
	<% }else if(customerBean.getRol() == "Client"){ %>
	
			<nav class="Nav">
			<ul class="nav1">
				<li><a href="/Proyecto-Programacion-Web/index.jsp" class="navLink">Home</a></li>
				<li class="desplegar">Camps
					<ul class="nav2">
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/assistant/checkCampsDateView.jsp" class="navLink">By Date</a></li>
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/assistant/searchCampamentSeatsLevelForm.jsp" class="navLink">By Level or Seats</a></li>
					</ul>
				</li>
				<li class="desplegar">Registration
					<ul class="nav2">
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/assistant/doRegisterView.jsp" class="navLink">Make</a></li>
						<li class="li2"><a href="/Proyecto-Programacion-Web/mvc/view/assistant/cancelRegisterView.jsp" class="navLink">Cancel</a></li>
					</ul>
				</li>
				<li><a href="/Proyecto-Programacion-Web/mvc/controller/login/changeData.jsp" class="navLink">Edit Profile</a></li>
				<li><a href="/Proyecto-Programacion-Web/mvc/controller/logout/logout.jsp" class="navLink">Log out</a></li>
			</ul>
		</nav>	
	<% } %>
<% } %>
</body>
</html>