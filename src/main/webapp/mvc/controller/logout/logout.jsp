<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

	<jsp:setProperty property="emailUser" value="" name="customerBean"/>

<jsp:include page="../../../index.jsp"></jsp:include>