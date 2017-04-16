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
<style type="text/css">
.pricing-table-grid {
    border: 1px solid #e7e7e7;
    padding: 1em 2em 1.5em;
    transition: all 0.5s ease 0s;
}
a.order-btn {
    background: #531844 none repeat scroll 0 0;
    border: medium none;
    color: #fff;
    display: block;
    font-size: 13px;
    margin: 1em auto auto;
    outline: medium none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    text-transform: uppercase;
    width: 55%;
}
.pricing-table-grid h3 {
    color: #000;
    display: block;
    font-size: 1.5em;
    line-height: 0.5em;
    margin: 0;
    padding: 20px 0 10px;
    text-align: center;
}
.pricing-table-grid ul {
    margin: 0;
    padding: 0;
}
.pricing-table-grid ul li {
    border-bottom: 1px solid #f4f4f4;
    list-style: outside none none;
    text-align: center;
}
.pricing-table-grid ul li span {
    background: #c58ab6 none repeat scroll 0 0;
    color: #fff;
    font-size: 14px;
    margin-bottom: 1em;
    padding: 10px;
    text-transform: uppercase;
    transition: all 0.5s ease 0s;
}
.pricing-table-grid ul li a, .pricing-table-grid ul li span {
    color: #555;
    display: block;
    font-size: 0.85em;
    padding: 10px 0;
    text-decoration: none;
}
span.month1 {
    font-size: 13px;
    line-height: 3em;
}
.pricing-table-grid:hover {
	border-color: #531844;
}

.pricing-table-grid:hover li span {
	background: #531844;
	color: #FFF;
}
.pricing-table-grid:hover a.order-btn {
	background: #C58AB6;
}

</style>
<script type="text/javascript">
	var userId = '<%=session.getAttribute("userId") %>';
	if(userId == 'null') {
		window.location.href = "login";
	} 

	function upgrade(id) {
		$.ajax({
			data : {
				myPlanId : id
			},
			url : "cart_session.action",
			dataType : "json",
			success : function(data) {
				window.location.href = "cart";
			},
			error : function(xhr, textStatus, errorThrown) {
				
			}
		});
	}
</script>
</head>
<body>
<div class="tp-dashboard-nav">
		<div class="container">
			<div class="row">
				<div class="col-md-12 dashboard-nav">
					<ul class="nav nav-pills nav-justified">
						<li><a href="vendor_dashboard"><i
					class="fa fa-dashboard db-icon"></i>My Dashboard</a></li>
					<li><a href="vendor_bookings"><i
							class="fa fa-user db-icon"></i>Bookings</a></li>
					<li><a href="vendor_my_listing"><i
							class="fa fa-list db-icon"></i>My Listing </a></li>
					<li><a href="vendor_add_listing"><i class="fa fa-plus-square db-icon"></i>Add listing</a></li>
					<li><a href="vendor_profile"><i
							class="fa fa-user db-icon"></i>My Profile</a></li>
					<li class="active"><a href="upgrade"><i
							class="fa fa-list-alt db-icon"></i>Pricing Plan</a></li>
					<!-- <li><a href="account_details"><i class="fa fa-user db-icon"></i>Account Details</a>
					</li> -->
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
		<h1>Select Price and Go<small> Are you vendor? upgrade your account to get vendor features</small></h1>
		</div>
	</div>
</div>
   <s:iterator value="upgradePlanInfoBean" status="incr">
   <div class="col-md-3 pricing-table">
	  <div class="pricing-table-grid">
		<h3><span>Rs.</span><s:property value="%{upgradePlanInfoBean[#incr.index]['amount']}" /><br><span class="month1"><s:property value="%{upgradePlanInfoBean[#incr.index]['validity']}" /></span></h3>
		<ul>
			<li><span style="color: #fff;"><s:property value="%{upgradePlanInfoBean[#incr.index]['planName']}" /></span></li>
			<li><a href="#"><i class="fa fa-user icon_3"></i> Vendor Profile Highlight</a></li>
			<li><a href="#"><i class="fa fa-envelope-o icon_3"></i> Get Email Alert</a></li>
			<c:if test="${upgradePlanInfoBean[incr.index]['getSMSAlert'] == true}">
			<li><a href="#"><i class="fa fa-smile-o icon_3"></i> Get SMS Alert</a></li>
			</c:if>
		</ul>
		<a class="popup-with-zoom-anim order-btn" href="javascript:upgrade(<s:property value="%{upgradePlanInfoBean[#incr.index]['id']}" />);">Upgrade</a>
	  </div>
	  </div>
	  </s:iterator>
    </div>
</div>
</div>
  </body>
</html>