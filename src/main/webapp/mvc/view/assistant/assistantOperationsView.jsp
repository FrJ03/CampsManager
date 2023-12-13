<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    // PONER CSS
    <link rel="stylesheet" href="../../css/style.css">
        <meta charset="UTF-8">
        <title>What you can do as an assistant</title>
    </head>
    <body>
        <%
            String nextPage="";
            String messageNextPage="";

            if(customerBean != null || !customerBean.getEmailUser().equals("")){
                //No debería estar aquí, lo mando al index
                nextPage="../../../webapp/index.jsp";
                <jsp:include page="<%=nextPage%>"><jsp:include>;
            }
            if(customerBean.getRolName() == "Admin"){
                //Lo mando a la página de admin
                nextPage="../../controller/admin/admin.jsp";
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
            <li><a href="../../controller/assistant/assistant.jsp"></a>User index</li>
            <li><a href="../../controller/logout/logout.jsp"></a>Logout</li>
            <li><a href="../register/changeDataView.jsp"></a>Modify user info</li>
        </ul>
<br>
        <p>
            You can:<br>
            <br>
            <button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/checkCampsView.jsp'">Check available camps</button>
            <br>
            <button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/searchCampView.jsp'">Search camp</button>
            <br>
            <button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/doRegisterView.jsp'">Make a registration for a camp</button>
            <br>
            <button type="button" class="small-button" onclick="window.location.href='<%= request.getContextPath() %>/mvc/view/cancelRegisterView.jsp'">Cancel a registration for a camp</button>
        </p>

    </body>
</html>
