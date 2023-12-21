<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Associate Form</title>
<link rel="stylesheet" href="/Proyecto-Programacion-Web/css/styles.css">
</head>
<body>
	<body>
		<% 
		String messageNextPage = (String)request.getAttribute("message");
		String h2 = (String)request.getAttribute("h2");
		String atribute1 = (String)request.getAttribute("atribute1");
		String atribute2 = (String)request.getAttribute("atribute2");
		String path = (String)request.getAttribute("path");
		if (messageNextPage == null) messageNextPage = "";
			if (customerBean == null || !customerBean.getRol().equals("Admin")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
	<jsp:include page="adminNav.html"></jsp:include>
	<h1 class="Title"><%=h2%></h1>
	<p><%=messageNextPage%></p>
    <form class="Form" method="post" action="<%=path%>">
	<label for="<%=atribute1%>"><%=atribute1%>: </label>
	<input type="number" name="<%=atribute1%>" min=1><br/>
	<label for="<%=atribute2%>"><%=atribute2%>: </label>
	<input type="number" name="<%=atribute2%>" min=1><br/>
	<input type="Submit" value="Submit">
</form>
</body>
</body>
</html>