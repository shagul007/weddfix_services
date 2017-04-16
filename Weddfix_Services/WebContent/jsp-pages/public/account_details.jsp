<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript">
	var userId = '<%=session.getAttribute("userId") %>';
	if(userId == 'null') {
		window.location.href = "vendor_login";
	} 
	
	function upgrade() {
		window.location.href = "upgrade";
	}
	
</script>
</head>
<body>
<div class="tp-dashboard-nav">
  <div class="container">
    <div class="row">
      <div class="col-md-12 dashboard-nav">
        <ul class="nav nav-pills nav-justified">
          <li><a href="vendor_dashboard"><i class="fa fa-dashboard db-icon"></i>My Dashboard</a></li>
		   <li><a href="vendor_bookings"><i class="fa fa-user db-icon"></i>Bookings</a></li>
           <li><a href="vendor_my_listing"><i class="fa fa-list db-icon"></i>My Listing </a></li>
           <li><a href="vendor_add_listing"><i class="fa fa-plus-square db-icon"></i>Add listing</a></li>
           <li><a href="vendor_profile"><i class="fa fa-user db-icon"></i>My Profile</a></li>
           <li><a href="upgrade"><i class="fa fa-list-alt db-icon"></i>Pricing Plan</a></li>
     	    <li class="active"><a href="account_details"><i class="fa fa-user db-icon"></i>Account Details</a></li>
         </ul>
      </div>
    </div>
  </div>
</div>
<div class="main-container">
<div class="container">
    <div class="row">
			<s:form cssClass="form" action="upgrade" name="form"
				id="form" method="post">
				<s:hidden id="userId" name="userId"/>
				<div class="form-innner-wrraper">
					<div class="form-color-background">
						<div id="error"><s:actionerror /></div>
						<div class="form-title-row">
							<h1>Account Details</h1>
						</div>
						<s:iterator value="myAccountDetails" status="incr">
						<div class="form-row">
							<label style="font-size: 17px;"> <span>Plan Name</span> <s:property value="%{myAccountDetails[0]['planName']}" />
							</label>
						</div>
						<div class="form-row">
							<label style="font-size: 17px;"> <span>Paid Amount</span> Rs. <s:property value="%{myAccountDetails[0]['amount']}" />
							</label>
						</div>
						<div class="form-row">
							<label style="font-size: 17px;"> <span>Validity</span> <c:if test="${myAccountDetails[0]['validity'] > 0 }"><s:property value="%{myAccountDetails[0]['validity']}" /> days left </c:if> <c:if test="${myAccountDetails[0]['validity'] <= 0 }">Expired</c:if>
							</label>
						</div>
						<div class="form-row">
							<label style="font-size: 17px;"> <span>Vendor Profile Highlight</span> Yes
							</label>
						</div>
						<div class="form-row">
							<label style="font-size: 17px;"> <span>Get Email Notification</span> Yes
							</label>
						</div>
						<div class="form-row">
							<label style="font-size: 17px;"> <span>Get SMS Notification</span> <c:if test="${myAccountDetails[0]['smsAlert'] == true }">Yes</c:if><c:if test="${myAccountDetails[0]['smsAlert'] == false }">No</c:if>
							</label>
						</div>
						</s:iterator>
						<div class="form-row">
							<button type="button" onclick="upgrade();">Upgrade Account</button>
						</div>
					</div>
				</div>
			</s:form>
		</div>
        </div>
    </div>
    </body>
</html>