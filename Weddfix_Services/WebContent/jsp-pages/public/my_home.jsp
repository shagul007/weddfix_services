<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript">
	var userId = '<%=session.getAttribute("userId")%>';
	if (userId == 'null') {
		window.location.href = "login";
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
						<img height="132" width="132" src="<s:url action="ImageAction?imageId=%{myPersonalDetailsBean['fileName']}" />" alt="<s:property value="%{myPersonalDetailsBean['fileName']}" />" class="img-circle" />
					</c:if>
					<c:if test="${myPersonalDetailsBean['fileName'] == null }">
						<img src="images/couple-profile.jpg" alt="" class="img-circle">
					</c:if>
					</div>
					<div class="profile-info col-md-9">
						<h1 class="profile-title"><s:property value="%{myPersonalDetailsBean['fullName']}"/><small>Welcome
								Back</small>
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
						<li class="active"><a href="my_home"><i
								class="fa fa-dashboard db-icon"></i>My Dashboard</a></li>
						<li><a href="my_wishlist"><i
								class="fa fa-heart db-icon"></i>My Wishlist </a></li>
						<li><a href="my_bookings"><i
								class="fa fa-list-alt db-icon"></i>My Bookings</a></li>
						<li><a href="my_profile"><i
								class="fa fa-user db-icon"></i>My Profile</a></li>
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
		            <h1>My Dashboard <small>Your Summary</small></h1>
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
				<c:if test="${myPersonalDetailsBean['weddingDaysLeft'] != null }">
				<div class="col-md-4">
					<!-- wedding days block -->
					<div class="bg-white pinside40 mb30">
						<h4>Wedding days to go</h4>
						<div class="wd-days-count mb40 mt40">
							<h1 class="title-number"><s:property value="%{myPersonalDetailsBean['weddingDaysLeft']}"/> days left</h1>
						</div>
						<div><s:property value="%{myPersonalDetailsBean['weddingDateStr']}"/></div>
					</div>
				</div>
				</c:if>
                <div class="col-md-4">
                    <div class="bg-white pinside40 mb30">
                        <h4>Wishlist Item</h4>
                        <div class="wd-days-count mb40 mt40">
                            <h1 class="title-number"><s:property value="%{myPersonalDetailsBean['myShortlistedTotalCount']}"/></h1>
                        </div>
                        <div><a href="my_wishlist">Compare &amp; Finalize</a></div>
                    </div>
                </div>
                <div class="col-md-4">
					<div class="bg-white pinside40 mb30">
						<h4>My Bookings</h4>
						<div class="wd-days-count mb40 mt40">
							<h1 class="title-number"><s:property value="%{myPersonalDetailsBean['myBookingTotalCount']}"/></h1>
						</div>
						<div><a href="my_bookings">View My Bookings</a></div>
					</div>
				</div>
            </div>
        </div>
    </div>
</body>
</html>