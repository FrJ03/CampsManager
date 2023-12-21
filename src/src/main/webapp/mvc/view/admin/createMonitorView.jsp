<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="../../../css/styles.css">
        <meta charset="UTF-8">
        <title>Create Monitor</title>
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
                <jsp:forward page="<%=nextPage%>"></jsp:forward>;
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
<!-- formulario para crear mon -->
              <h1 class="Title">Create Monitor</h1>
              <form class="Form" action="../../controller/servlets/CreateMonitor.java" method="post">
                  <label for="initDate">Name:</label>
                  <input type="text" id="name" name="name" required>
          
                  <label for="endDate">Last names:</label>
                  <input type="text" id="last_names" name="last_names" required>
          
                  <label for="respMonitor">Special Monitor?:</label>
                  <input type="text" id="special" name="special" required>
          
                  <input type="submit" value="Create Monitor">
              </form>

    </body>
</html>
