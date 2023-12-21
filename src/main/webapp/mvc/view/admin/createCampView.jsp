<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    	<link rel="stylesheet" href="css/styles.css">
        <meta charset="UTF-8">
        <title>Create Camp</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage = (String)request.getAttribute("message");

            if(customerBean == null || customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../index.jsp";
        %>
                <jsp:forward page="<%=nextPage%>"/>
        <%
            }
            if(customerBean.getRol() == "Client"){
                //Lo mando a la página
                nextPage="../../../index.jsp";
        %>
                <jsp:forward page="<%=nextPage%>"/>
        <%
            }
            else{
                if(customerBean.getRol() == "None"){
                    nextPage="../../../index.jsp";
        %>
                    <jsp:forward page="<%=nextPage%>"/>
        <%
                }
            }
        %>

		<jsp:include page="../../../include/templates/adminNav.html"></jsp:include>
<!-- formulario para crear camp -->
              <h1 class="Title">Create Camp</h1>
              <form  class="Form" action="../../controller/servlets/CreateCamp.java" method="post">
                  <label for="initDate">Start Date:</label>
                  <input type="date" id="initDate" name="initDate" required>
          
                  <label for="endDate">End Date:</label>
                  <input type="date" id="endDate" name="endDate" required>
          
                  <label for="level">Level:</label>
          			<select id="level" required>
			            <option value="Infantil">Infantile</option>
			            <option value="Juvenil">Juvenile</option>
			            <option value="Adolescente">Teenager</option>
			        </select>
                  <label for="maxMonitors">Max Number of Assistants:</label>
                  <input type="number" id="maxMonitors" name="maxMonitors" required>
          
                  <input type="submit" value="Create Camp">
              </form>

    </body>
</html>
