<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
    <head>
    // PONER CSS
    <link rel="stylesheet" href="../../css/style.css">
        <meta charset="UTF-8">
        <title>Welcome, assistant!</title>
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
            // LINKAR EL JSP DE OPERACIONES DE ASISTENTE
            <li><a href=""></a>What you can do</li>
            <li><a href="../../controller/logout/logout.jsp"></a>Logout</li>
            // LINKAR MODIFICAR DATOS
            <li><a href=""></a>Modify user info</li>
        </ul>
//Mensaje de bienvenida
Welcome assistant <jsp:getProperty name="customerBean" property="emailUser_"/><br>
//Fecha
Today is: <%= new java.util.Date() %><br>

//Campamentos a los que está inscrito, antigua a nueva (por fecha), indicando id de camp y fecha de inicio


    </body>
</html>
