<%@page import="javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity View</title>
</head>
<body>
<%  String id = (String)request.getAttribute("idAct"); 
	String nameAct = (String)request.getAttribute("nameAct");
	String turno = (String)request.getAttribute("turnoAct");
	String monitoresMax = (String)request.getAttribute("monitoresMaxAct");
	String participantesMax = (String)request.getAttribute("participantesMax");
	String nivel = (String)request.getAttribute("nivelAct");
%>
    <div >
        <%if(turno=="true") {%>
        	<h1>Activity Details</h1>
   		<%} %>
        <div >
            <strong>ID:</strong> <%=id %>
        </div>
         <% if (nameAct != null){%>
        <div >
            <strong>Name:</strong> <%= nameAct %>
        </div>
         <%} if (nivel != null){%>
        <div >
            <strong>Educational Level:</strong> <%= nivel %>
        </div>
        <%} if (monitoresMax != null){%>
        <div >
            <strong>Max Monitors:</strong> <%= monitoresMax %>
        </div>
        <% }if (turno != null){%>
        <div >
            <strong>Shift:</strong> <%= turno%>
        </div>
        <%} if (participantesMax != null){%>
        <div >
            <strong>Max Participants:</strong> <%= participantesMax %>
        </div>
         <%} %>
    </div>
    <br>
</body>
</html>