<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.apache.http.client.ClientProtocolException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript">
var role = '<%=session.getAttribute("role") %>';
if(role == 'null') {
	window.location.href = "login";
}

if(role == 'USER') {
// 	window.location.href = "my_home";
	window.location.href = "home";
}

if(role == 'VENDORADMIN') {
	window.location.href = "vendor_dashboard";
}

</script>
</head>
<body>
<div class="main-container" style="min-height: 365px;">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="dashboard-page-head">
          <div class="page-header">
            <h1>Admin <small> manage the masters</small></h1>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="wishlist-board">
          <div class="row">
            <div class="col-md-3">
              <div class="vendor-list-block mb30"><!-- vendor list block -->
                <div class="vendor-img"> 
                <a href="masters_home">
                <img height="146" width="262" class="img-responsive" alt="No Image"
										src="<%=request.getContextPath()%>/images/temp.jpg">
                </a>
                </div>
                <div class="vendor-detail"><!-- vendor details -->
                  <div class="caption">
                    <h2><a href="masters_home" class="title">Masters</a></h2>
                  </div>
                </div>
                <!-- /.vendor details --> 
              </div>
              <!-- /.vendor list block --> 
            </div>
            <div class="col-md-3">
              <div class="vendor-list-block mb30"><!-- vendor list block -->
                <div class="vendor-img"> 
                <a href="user_role">
                <img height="146" width="262" class="img-responsive" alt="No Image"
										src="<%=request.getContextPath()%>/images/temp.jpg">
                </a>
                </div>
                <div class="vendor-detail"><!-- vendor details -->
                  <div class="caption">
                    <h2><a href="user_role" class="title">User Role Settings</a></h2>
                  </div>
                </div>
                <!-- /.vendor details --> 
              </div>
              <!-- /.vendor list block --> 
            </div>
            <div class="col-md-3">
              <div class="vendor-list-block mb30"><!-- vendor list block -->
                <div class="vendor-img"> 
                <a href="upgrade_plan_master">
                <img height="146" width="262" class="img-responsive" alt="No Image"
										src="<%=request.getContextPath()%>/images/temp.jpg">
                </a>
                </div>
                <div class="vendor-detail"><!-- vendor details -->
                  <div class="caption">
                    <h2><a href="upgrade_plan_master" class="title">Upgrade Plan</a></h2>
                  </div>
                </div>
                <!-- /.vendor details --> 
              </div>
              <!-- /.vendor list block --> 
            </div>
            <div class="col-md-3">
              <div class="vendor-list-block mb30"><!-- vendor list block -->
                <div class="vendor-img"> 
                <a href="promotion_details">
                <img height="146" width="262" class="img-responsive" alt="No Image"
										src="<%=request.getContextPath()%>/images/temp.jpg">
                </a>
                </div>
                <div class="vendor-detail"><!-- vendor details -->
                  <div class="caption">
                    <h2><a href="promotion_details" class="title">Promotions</a></h2>
                  </div>
                </div>
                <!-- /.vendor details --> 
              </div>
              <!-- /.vendor list block --> 
            </div>
            <div class="col-md-3">
              <div class="vendor-list-block mb30"><!-- vendor list block -->
                <div class="vendor-img"> 
                <a href="banner_photos">
                <img height="146" width="262" class="img-responsive" alt="No Image"
										src="<%=request.getContextPath()%>/images/temp.jpg">
                </a>
                </div>
                <div class="vendor-detail"><!-- vendor details -->
                  <div class="caption">
                    <h2><a href="banner_photos" class="title">Banner Photos</a></h2>
                  </div>
                </div>
                <!-- /.vendor details --> 
              </div>
              <!-- /.vendor list block --> 
            </div>
            <div class="col-md-3">
              <div class="vendor-list-block mb30"><!-- vendor list block -->
                <div class="vendor-img"> 
                <a href="vendor_status">
                <img height="146" width="262" class="img-responsive" alt="No Image"
										src="<%=request.getContextPath()%>/images/temp.jpg">
                </a>
                </div>
                <div class="vendor-detail"><!-- vendor details -->
                  <div class="caption">
                    <h2><a href="vendor_status" class="title">Vendor Role Settings</a></h2>
                  </div>
                </div>
                <!-- /.vendor details --> 
              </div>
              <!-- /.vendor list block --> 
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>