<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Associate monitor to activity</title>
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
    <h2>Associate monitor to an activity</h2>
	<%= messageNextPage %><br/><br/>
    <form action="/Proyecto-Programacion-Web/AssociateMonitortoActivity" method="post">
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
