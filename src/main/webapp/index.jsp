<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>homepage</title>
<link rel="stylesheet" href="css/styles.css" type="text/css">
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
				<li class="li1"><a href="index.jsp">Home</a></li>
				<li class="li1" class="desplegar">Camps Management
					<ul class="nav2">
						<li class="li2"><a href="mvc/view/admin/createCampView.jsp">Create Camp</a></li>
						<li class="li2"><a href="mvc/view/admin/createActivityView.jsp">Create Activity</a></li>
						<li class="li2"><a href="mvc/view/admin/createMonitorView.jsp">Create Monitor</a></li>
						<li class="li2"><a href="mvc/view/admin/associateActivitytoCampForm.jsp">Activity to Camp</a></li>
						<li class="li2"><a href="mvc/view/admin/associateMonitortoActivityView.jsp">Monitor to Activity</a></li>
						<li class="li2"><a href="mvc/view/admin/associateMonitortoCampView.jsp">Monitor to Camp</a></li>
					</ul>
				</li>
				<li class="li1"><a href="mvc/controller/login/changeData.jsp">Edit Profile</a></li>
				<li class="li1"><a href="mvc/controller/logout/logout.jsp">Log out</a></li>
			</ul>
		</nav>
	<% }else if(customerBean.getRol() == "Client"){ %>
            <p>Assistant Operations:</p>
            <br>
            	<button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/Proyecto-Programacion-Web/mvc/view/assistant/checkCampsView.jsp'">Check available camps</button>
            	<br>
            	<button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/Proyecto-Programacion-Web/mvc/view/assistant/searchCampView.jsp'">Search camp</button>
            	<br>
            	<button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/Proyecto-Programacion-Web/mvc/view/assistant/doRegisterView.jsp'">Make a registration for a camp</button>
            	<br>
            	<button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/Proyecto-Programacion-Web/mvc/view/assistant/cancelRegisterView.jsp'">Cancel a registration for a camp</button>
	
	<% } %>
<% } %>
</body>
</html>
