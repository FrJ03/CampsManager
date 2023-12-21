<%@page import="javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Monitor View</title>
</head>
<body>
<%  String id = (String)request.getAttribute("idMon"); 
	String name = (String)request.getAttribute("nameMon");
	String apellidos = (String)request.getAttribute("surnameMon");
	String especial = (String)request.getAttribute("especialMon");
	String turno = (String)request.getAttribute("turnoMon");
	
%>
    <div >
        <%if(turno=="true") {%>
        	<h1>Monitor Details</h1>
   		<%} %>
        <div >
            <strong>ID:</strong> <%=id %>
        </div>
         <% if (name != null){%>
        <div >
            <strong>Name:</strong> <%= name %>
        </div>
         <%} if (apellidos != null){%>
        <div >
            <strong>Surname:</strong> <%= apellidos %>
        </div>
        <%} if (especial != null){%>
        <div >
            <strong>Is Special:</strong> <%= especial %>
        </div>
        
         <%} %>
    </div>
    <br>
</body>
</html>