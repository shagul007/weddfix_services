<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%if(request.getParameter("request") != null) {
	if(session.getAttribute("sendInfoId") == null) {
		session.setAttribute("sendInfoId", request.getParameter("request"));
	}
}%>
<script type="text/javascript">
	var sendInfoId = '<%=session.getAttribute("sendInfoId") %>';
	if(sendInfoId == 'null') {
		window.location.href = "home";
	} 
	
</script>
<META HTTP-EQUIV="Refresh" CONTENT="0; URL=submit_feedback">
</head>
<body>
<div class="main-container" style="min-height: 485px;">
<div class="container">
    <div class="row">
    <div class="col-md-12">
		<div class="dashboard-page-head">
		<div class="page-header">
		<h1>Redirecting. Please wait...</h1>
		</div>
	</div>
</div>
</div>
</div>
</div>
  </body>
</html>