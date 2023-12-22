<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="/Proyecto-Programacion-Web/css/styles.css">
        <meta charset="UTF-8">
        <title>Cancel registration</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage = (String)request.getAttribute("message");

            if (customerBean == null || !customerBean.getRol().equals("Client")) {
    			//No debería estar aquí -> flujo salta a index.jsp
    				nextPage = "/include/errors/errorRol.jsp";
    		%>
    				<jsp:forward page="<%=nextPage%>"></jsp:forward>
    		<% 
   			}
        %>

		<jsp:include page="/include/templates/assistantNav.html"></jsp:include>
        <h1 class="Title">Cancel registration</h1>
        <form class="Form" action="/Proyecto-Programacion-Web/CancelRegistration" method="post">
            
            <label for="idCamp">Camp to cancel your registration</label>
            <input type="number" name="idCamp" id="idCamp" required>

            <label for="idAssistant">Assistant ID:</label>
            <input type="number" name="idAssistant" id="idAssistant" required>
            
            
			<input type="submit" value="Submit">
        </form>

    </body>
</html>
