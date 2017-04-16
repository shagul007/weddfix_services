<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.weddfix.web.formbean.DirectoryVendorPwResetFormBean" %>
<%@ page import="com.weddfix.web.implementation.CommonMasterDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	CommonMasterDaoImpl cm = new CommonMasterDaoImpl();
	DirectoryVendorPwResetFormBean u = cm.getVendorByPasswordResetKey(request.getParameter("key"));
    if (u != null) {
        session.setAttribute("resetUserId", u.getUserId());
%>
        <META HTTP-EQUIV="Refresh" CONTENT="0; URL=v_reset2">
    <% }
%>
<html>
<head></head>
<body>
<div class="main-container" style="height: 425px;">
	<div class="container">
		<div class="row">
		<s:form cssClass="form" name="form" id="form" method="post">
		<div class="form-title-row">
    	<h1>Password reset not found</h1>
    	</div>
    	<div class="form-row">
        The password reset key supplied has not been found. It could be that it has expired,
        in that case, <a href="vendor_forgot_password">please start the reset process again</a>.
    	</div>
    </s:form>
    </div>
</div>
</div>
</body>
</html>