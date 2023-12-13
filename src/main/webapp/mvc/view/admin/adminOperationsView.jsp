<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="../../css/style.css">
        <meta charset="UTF-8">
        <title>Admin Operations</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage = (String)request.getAttribute("message");

            if(customerBean != null || !customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../webapp/index.jsp";
                <jsp:forward page="<%=nextPage%>"></jsp:forward>
            }
            if(customerBean.getRolName() == "Client"){
                //Lo mando a la página de admin
                nextPage="../../controller/assistant/assistant.jsp";
                <jsp:forward page="<%=nextPage%>"></jsp:forward>
            }
            else{
                if(customerBean.getRolName() == "None"){
                    nextPage="../../../webapp/index.jsp";
                    <jsp:forward page="<%=nextPage%>"></jsp:forward>
                }
            }
        %>

        <ul>
            <li><a href="../../controller/admin/admin.jsp"></a>Admin index</li>
            <li><a href="../../controller/logout/logout.jsp"></a>Logout</li>
            <li><a href="../register/changeDataView.jsp"></a>Modify user info</li>
        </ul>

        <!-- PONER divs DEL CSS para que quede bonito -->
        			<br><br>
                    <p>Admin Operations:</p>
                    <br>
        			<button type="button" class="small-button" onclick="window.location.href='./createActivityView.jsp'">Create Activity</button><br>
        			<br><br>
        			<button type="button" class="small-button" onclick="window.location.href='./createCampView.jsp'">Create Camp</button><br>
        			<br><br>
        			<button type="button" class="small-button" onclick="window.location.href='./createMonitorView.jsp'">Create Monitor</button><br>
        			<br><br>
        			<button type="button" class="small-button" onclick="window.location.href='./associateActivitytoCampForm.jsp'">Associate Activity to Camp</button><br>
        			<br><br>
        			<button type="button" class="small-button" onclick="window.location.href='./associateMonitortoActivityView.jsp'">Associate Monitor to Activity</button><br>
        			<br><br>
        			<button type="button" class="small-button" onclick="window.location.href='./associateMonitortoCampView.jsp'">Associate Monitor to Camp</button><br>
        			<br><br>
    </body>
</html>
