
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="../../css/style.css">
        <meta charset="UTF-8">
        <title>Create Activity</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage="";

            if(customerBean != null || !customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../webapp/index.jsp";
                <jsp:include page="<%=nextPage--%>"><jsp:include>;
            }
            if(customerBean.getRolName() == "Client"){
                //Lo mando a la página de admin
                nextPage="../../controller/assistant/assistant.jsp";
                <jsp:include page="<%=nextPage%>"></jsp:include>
            }
            else{
                if(customerBean.getRolName() == "None"){
                    nextPage="../../../webapp/index.jsp";
                    <jsp:include page="<%=nextPage%>"><jsp:include>;
                }
            }
        %>

        <ul>
            <li><a href="./adminView.jsp"></a>Admin page</li>
            <li><a href="../../controller/logout/logout.jsp"></a>Logout</li>
            <li><a href="../register/changeDataView.jsp"></a>Modify user info</li>
        </ul>

<!-- formulario para crear act -->
              <h2>Create Activity</h2>
              <form action="../../controller/servlets/CreateActivity.java" method="post">
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
