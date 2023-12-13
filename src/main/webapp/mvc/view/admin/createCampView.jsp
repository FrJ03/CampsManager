<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="../../css/style.css">
        <meta charset="UTF-8">
        <title>Create Camp</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage = (String)request.getAttribute("message");

            if(customerBean != null || !customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../webapp/index.jsp";
                <jsp:forward page="<%=nextPage%>"><jsp:forward>;
            }
            if(customerBean.getRolName() == "Client"){
                //Lo mando a la página de admin
                nextPage="../../controller/admin/admin.jsp";
                <jsp:forward page="<%=nextPage%>"></jsp:forward>
            }
            else{
                if(customerBean.getRolName() == "None"){
                    nextPage="../../../webapp/index.jsp";
                    <jsp:forward page="<%=nextPage%>"><jsp:forward>;
                }
            }
        %>

        <ul>
            <li><a href="./adminView.jsp"></a>Admin page</li>
            <li><a href="../../controller/logout/logout.jsp"></a>Logout</li>
            <li><a href="../register/changeDataView.jsp"></a>Modify user info</li>
        </ul>

<!-- formulario para crear camp -->
              <h2>Create Campament</h2>
              <form action="../../controller/servlets/CreateCamp.java" method="post">
                  <label for="initDate">Start Date:</label>
                  <input type="text" id="initDate" name="initDate" required>
          
                  <label for="endDate">End Date:</label>
                  <input type="text" id="endDate" name="endDate" required>
          
                  <label for="level">Level:</label>
                  <input type="text" id="level" name="level" required>
          
                  <label for="maxMonitors">Max Number of Assistants:</label>
                  <input type="text" id="maxMonitors" name="maxMonitors" required>
          
                  <label for="respMonitor">Responsible Monitor:</label>
                  <input type="text" id="respMonitor" name="respMonitor" required>
          
                  <input type="submit" value="Create Camp">
              </form>

    </body>
</html>
