<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Associate monitor to activity</title>
	<link rel="stylesheet" href="Proyecto-Programacion-Web/css/style.css">
</head>
<body>
	<% 
		String messageNextPage = (String)request.getAttribute("message");
		
			if (customerBean == null || !customerBean.getRol().equals("Admin")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
	<jsp:include page="</Proyecto-Programacion-Web/include/templates/adminNav.html>"></jsp:include>
    <h1 class="Form">Associate monitor to an activity</h1>
	<%= messageNextPage %><br/><br/>
    <form class="Form" action="/Proyecto-Programacion-Web/AssociateMonitortoActivity" method="post">
        <label for="mon">Monitor: </label>
	<input type="text" name="mon"><br/>
	<label for="activity">Activity: </label>
	<input type="text" name="activity" ><br/>
	<input type="submit" value="Submit">
    </form>

<!-- VOLVER A INDEX -->
<jsp:include page="/include/templates/returnToIndex.jsp"></jsp:include>

</body>
</html>
