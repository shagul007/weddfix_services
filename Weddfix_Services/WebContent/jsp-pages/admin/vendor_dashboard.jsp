<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript">
	var role = '<%=session.getAttribute("role") %>';
	if (role == 'null') {
		window.location.href = "vendor_login";
	}
	
	if(role != 'VENDORADMIN') {
		window.location.href = "my_home";
	}

</script>
</head>
<body>
	<div class="tp-dashboard-head">
		<!-- page header -->
		<div class="container">
			<div class="row">
				<div class="col-md-12 profile-header">
					<div class="profile-pic col-md-2">
					<c:if test="${myPersonalDetailsBean['fileName'] != null }">
						<img height="132" width="132" src="<s:url action="ImageAction?imageId=%{myPersonalDetailsBean['fileName']}" />" alt="<s:property value="%{myPersonalDetailsBean['fileName']}" />"  class="img-circle" />
					</c:if>
					<c:if test="${myPersonalDetailsBean['fileName'] == null }">
						<img src="images/profile-dashbaord.png" alt="" class="img-circle">
					</c:if>
					</div>
					<div class="profile-info col-md-9">
						<h1 class="profile-title"><s:property value="%{myPersonalDetailsBean['fullName']}"/><small>Welcome
								Back member</small>
						</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.page header -->
	<div class="tp-dashboard-nav">
		<div class="container">
			<div class="row">
				<div class="col-md-12 dashboard-nav">
					<ul class="nav nav-pills nav-justified">
						<li class="active"><a href="vendor_dashboard"><i
								class="fa fa-dashboard db-icon"></i>My Dashboard</a></li>
						<li><a href="vendor_bookings"><i
								class="fa fa-user db-icon"></i>Bookings</a></li>
						<li><a href="vendor_my_listing"><i
								class="fa fa-list db-icon"></i>My Listing </a></li>
						<li><a href="vendor_add_listing"><i
								class="fa fa-plus-square db-icon"></i>Add listing</a></li>
						<li><a href="vendor_profile"><i
								class="fa fa-user db-icon"></i>My Profile</a></li>
						<li><a href="upgrade"><i
								class="fa fa-list-alt db-icon"></i>Pricing Plan</a></li>
<!-- 						<li><a href="account_details"><i class="fa fa-user db-icon"></i>Account Details</a></li> -->
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="main-container">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="dashboard-page-head">
						<div class="page-header">
							<h1>Vendor Dashboard</h1>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			<c:if test="${myPersonalDetailsBean['fileName'] == null }">
				<div class="col-md-4">
					<div class="bg-white pinside40 mb30">
						<h4>Profile incomplete</h4>
						<div class="wd-days-count mb40 mt40">
							<h1 class="title-number">70%</h1>
						</div>
						<div><a href="vendor_profile">Update Profile</a></div>
					</div>
				</div>
				</c:if>
				<div class="col-md-4">
					<div class="bg-white pinside40 mb30">
						<h4>My listing</h4>
						<div class="wd-days-count mb40 mt40">
							<h1 class="title-number"><s:property value="%{myPersonalDetailsBean['myListingTotalCount']}"/></h1>
						</div>
						<div><a href="vendor_my_listing">View &amp; Edit My Listing</a></div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="bg-white pinside40 mb30">
						<h4>Bookings</h4>
						<div class="wd-days-count mb40 mt40">
							<h1 class="title-number"><s:property value="%{myPersonalDetailsBean['bookingTotalCount']}"/></h1>
						</div>
						<div><a href="vendor_bookings">View Bookings</a></div>
					</div>
				</div>
				<%-- <div class="col-md-4">
					<div class="bg-white pinside40 mb30">
						<h4>Your plan details</h4>
						<div class="wd-days-count mb40 mt40">
							<h1 class="title-number"><s:property value="%{myPersonalDetailsBean['planName']}"/></h1>
						</div>
						<div><a href="account_details">View Account Details</a></div>
					</div>
				</div> --%>
			</div>
		</div>
	</div>
</body>
</html>