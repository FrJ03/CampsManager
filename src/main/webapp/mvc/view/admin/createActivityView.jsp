<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="css/styles.css">
        <meta charset="UTF-8">
        <title>Create Activity</title>
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
                //Lo mando a la página de admin
                nextPage="../../controller/assistant/assistant.jsp";
        %>
                <jsp:forward page="<%=nextPage%>"></jsp:forward>
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
<!-- formulario para crear act -->
              <h1 class="Title">Create Activity</h1>
              <form class="Form" action="../../controller/servlets/CreateActivity.java" method="post">
                  <label for="name">Name of Activity:</label>
                  <input type="text" id="name" name="name" required>
          
                  <label for="level">Level:</label>
                  <select id="level" required>
			            <option value="Infantil">Infantile</option>
			            <option value="Juvenil">Juvenile</option>
			            <option value="Adolescente">Teenager</option>
			        </select>
          
                  <label for="maxAssistants">Max Number of Assistants:</label>
                  <input type="number" id="maxAssistants" name="maxAssistants" required>
          
                  <label for="maxMonitors">Max Number of Monitors:</label>
                  <input type="number" id="maxMonitors" name="maxMonitors" required>

                  <label for="turn">Turn:</label>
                  <select id="level" required>
			            <option value="Morning">Morning</option>
			            <option value="Afternoon">Afternoon</option>
			        </select>
          
                  <input type="submit" value="Create Activity">
              </form>

    </body>
</html>
