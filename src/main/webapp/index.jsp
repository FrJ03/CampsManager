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
	<p>Welcome <jsp:getProperty property="emailUser" name="customerBean"/>!! </p>
	<p><a href="/Proyecto-Programacion-Web/mvc/controller/login/changeData.jsp">Change data</a></p>
	<p><a href="/Proyecto-Programacion-Web/mvc/controller/logout/logout.jsp">Log out</a></p>

	<% if(customerBean.getRol() == "Admin"){ %>

            <p>Welcome <jsp:getProperty property="emailUser" name="customerBean"/>!! </p>
            <p><a href="/Proyecto-Programacion-Web/mvc/controller/login/changeData.jsp">Change data</a></p>
	    <p><a href="/Proyecto-Programacion-Web/mvc/controller/logout/logout.jsp">Log out</a></p>

        <!-- PONER divs DEL CSS para que quede bonito -->
           <br><br>
           <p>Admin Operations:</p>
           <br>
        	<button type="button" class="small-button" onclick="window.location.href='/Proyecto-Programacion-Web/mvc/view/admin/createActivityView.jsp'">Create Activity</button><br>
        	<br>
        	<button type="button" class="small-button" onclick="window.location.href='/Proyecto-Programacion-Web/mvc/view/admin/createCampView.jsp'">Create Camp</button><br>
        	<br>
        	<button type="button" class="small-button" onclick="window.location.href='/Proyecto-Programacion-Web/mvc/view/admin/createMonitorView.jsp'">Create Monitor</button><br>
        	<br>
        	<button type="button" class="small-button" onclick="window.location.href='/Proyecto-Programacion-Web/mvc/view/admin/associateActivitytoCampForm.jsp'">Associate Activity to Camp</button><br>
        	<br>
        	<button type="button" class="small-button" onclick="window.location.href='/Proyecto-Programacion-Web/mvc/view/admin/associateMonitortoActivityView.jsp'">Associate Monitor to Activity</button><br>
        	<br>
        	<button type="button" class="small-button" onclick="window.location.href='/Proyecto-Programacion-Web/mvc/view/admin/associateMonitortoCampView.jsp'">Associate Monitor to Camp</button><br>

	<% } if(customerBean.getRol() == "Client"){ %>

	    <p>Welcome <jsp:getProperty property="emailUser" name="customerBean"/>!! </p>
            <p><a href="/Proyecto-Programacion-Web/mvc/controller/login/changeData.jsp">Change data</a></p>
	    <p><a href="/Proyecto-Programacion-Web/mvc/controller/logout/logout.jsp">Log out</a></p>

	    <br><br>
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
