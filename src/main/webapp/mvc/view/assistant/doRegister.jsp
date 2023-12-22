<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/Proyecto-Programacion-Web/css/styles.css">
    <title>Registration</title>
</head>
<body>
	<% 
		String messageNextPage = (String)request.getAttribute("message");
		
			if (customerBean == null || !customerBean.getRol().equalsIgnoreCase("Client")) {
			//No debería estar aquí -> flujo salta a index.jsp
				String nextPage = "/include/errors/errorRol.jsp";
		%>
				<jsp:forward page="<%=nextPage%>"></jsp:forward>
		<% 
			}
		%>
   
<jsp:include page="../../../include/templates/assistantNav.html"></jsp:include>
<h1 class="Title">Make Registration</h1>
    
    <!-- Formulario de Registro Parcial -->
    <form class="Form" method="post" action="Proyecto-Programacion-Web/DoRegistration">
        <label for="idCamp">Camp ID</label>
        <input type="text" id="idCamp" name="idCamp" required>
        <label for="type">Type</label>
		<select id="type" name="type" required>
           <option value="Full">Full</option>
           <option value="Partial">Partial</option>
       </select>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
