<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Associate monitor to camp</title>
    <link rel="stylesheet" href="/Proyecto-Programacion-Web/css/style.css">
</head>
<body>
	<% 
		String messageNextPage = (String)request.getAttribute("message");
		if (messageNextPage == null) messageNextPage = "";
			if (customerBean == null || !customerBean.getRol().equals("Admin")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
    <jsp:include page="</Proyecto-Programacion-Web/include/templates/adminNav.html>"></jsp:include>
    <h1 class="Form">Associate special monitor to a camp</h1>
	<%= messageNextPage %><br/><br/>
    <form action="/Proyecto-Programacion-Web/AssociateMonitortoCamp" method="post">
        <label for="special">Search by Available Seats::</label>
        <input type="number" name="availableSeats" id="availableSeats" min="1">
        <button type="submit">Search</button>
    </form>
	 <h2>Associate supervisor monitor to a camp</h2>
    <form action="/Proyecto-Programacion-Web/AssociateMonitortoCamp" method="post">
        <label for="availableSeats">Searc:</label>
        <input type="number" name="availableSeats" id="availableSeats" min="1">
        <button type="submit">Search</button>
    </form>
</body>
</html>
