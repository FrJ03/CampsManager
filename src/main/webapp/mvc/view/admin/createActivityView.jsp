<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/Proyecto-Programacion-Web/css/style.css">
        <meta charset="UTF-8">
        <title>Create Activity</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage = (String)request.getAttribute("message");

            if(customerBean != null || !customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../webapp/index.jsp";
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
                    nextPage="../../../webapp/index.jsp";
        %>
                    <jsp:forward page="<%=nextPage%>"/>
        <%
                }
            }
        %>
        <jsp:include page="/Proyecto-Programacion-Web/include/templates/adminNav.html"></jsp:include>

<!-- formulario para crear act -->
              <h1 class="Title">Create Activity</h1>
              <form class="Form" action="/Proyecto-Programacion-Web/CreateActivity" method="post">
                  <label for="initDate">Name of Activity:</label>
                  <input type="text" id="name" name="name" required>
          
                  <label for="endDate">Level:</label>
                  <input type="text" id="level" name="level" required>
          
                  <label for="maxMonitors">Max Number of Assistants:</label>
                  <input type="text" id="maxAssistants" name="maxAssistants" required>
          
                  <label for="respMonitor">Max Number of Monitors:</label>
                  <input type="text" id="maxMonitors" name="maxMonitors" required>

                  <label for="level">Turn:</label>
                  <input type="text" id="turn" name="turn" required>
          
                  <input type="submit" value="Create Activity">
              </form>

    </body>
</html>
