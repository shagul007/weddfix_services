<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="com.weddfix.web.util.CommonConstants"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!-- <title>Wedding Vendor | Find The Best Wedding Vendors</title> -->
<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
<!-- Template style.css -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="/css/owl.theme.css">
<link rel="stylesheet" type="text/css" href="/css/owl.transitions.css">
<link rel="stylesheet" type="text/css" href="/css/fontello.css">

<!-- Font used in template -->
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Roboto:400,400italic,500,500italic,700,700italic,300italic,300' rel='stylesheet' type='text/css'>
<!--font awesome icon -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- favicon icon -->
<link rel="shortcut icon" href="/images/favicon.png" type="image/x-icon">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="/js/jquery.min.js"></script> 
<!-- Include all compiled plugins (below), or include individual files as needed --> 
<script src="/js/bootstrap.min.js"></script> 

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<!-- Calendar -->
    <link href="/css/calendar.css" rel="stylesheet"> 

    <link href="/css/common.css" rel="stylesheet">
    
    <link href="/css/jquery.dataTables.min.css" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" media="print,screen,embossed,all" href="/resources/css/jqgrid/ui.jqgrid.css" />
  
  	<link rel="stylesheet" type="text/css" media="print,screen,embossed,all" href="/resources/css/jquery/ui-lightness/jquery-ui-1.8.14.custom.css" />
    
    <!-- Fancybox css -->
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="/js/jquery-1.9.1.js"></script>  
  <script src="/js/jquery-ui.js"></script>
  <script type="text/javascript" src="/resources/js/jquery/jquery-1.7.1.min.js" charset="utf-8"></script> 
  <script type="text/javascript" src="/resources/js/jquery/jquery-ui-1.8.6.custom.min.js" charset="utf-8"></script>
  <script type="text/javascript" src="/resources/js/jquery/jquery-ui-1.8.14.custom.min.js" charset="utf-8"></script> 
  <script type="text/javascript" src="/resources/js/jqgrid/grid.locale-en.js" charset="utf-8"></script>
  <script type="text/javascript" src="/resources/js/jqgrid/jquery.jqGrid.min.js" charset="utf-8"></script>
  <script src="/js/validation.js"></script>
  <script src="/js/common.js"></script>
  <!-- Custom js -->
  <script src="/js/jquery.dataTables.min.js"></script>
  <script src="/js/md5.js"></script>
<!-- Custom Theme files -->
<!--   <link href="/css/form.style.css" rel='stylesheet' type='text/css' /> -->
  
  <script type="text/javascript">
  $(document).ready(function() {
	  $.ajax({
			url : "loadCategoryAndCityMasters.action",
			dataType : "json",
			success : function(data) {
					$.each(data.orgMap, function(key,
							value) {
						$("#headerCategoryId").append(
								$("<option></option>").attr("value", key).text(
										value));
					});
					$("#headerCategoryId").val(<%=session.getAttribute("headerSearchCategoryId")%>);
					$.each(data.allCityMap, function(key,
							value) {
						$("#headerCityId").append(
								$("<option></option>").attr("value", key).text(
										value));
					});
					$("#headerCityId").val(<%=session.getAttribute("headerSearchCityId")%>);
			},
			error : function(xhr, textStatus, errorThrown) {
				// Handle error
			}
		});
	});
  
function search_category(id) {
	/* $.ajax({
		data : {
			
			headerSearchCategoryId : id,
			headerSearchCityId : -1
		},
		url : "searchCategoryByCity.action",
		dataType : "json",
		success : function(data) {
			window.location.href = "vendor_list";
		},
		error : function(xhr, textStatus, errorThrown) {
			// Handle error
		}
	}); */
	var categoryVal = $("#headerCategoryId option:selected").text();
	var category = categoryVal.replace(' ', '_').toLowerCase();
	window.location.href = "vendor_list?search="+category;
}
function search_category_by_city() {
	var headerCategoryId = document.forms["headerSearchForm"]["headerCategoryId"].value;
	var headerCityId = document.forms["headerSearchForm"]["headerCityId"].value;

	if(headerCategoryId == "-1" && headerCityId == "-1"){
		$('#headerCategoryId').addClass( 'error' );
		$('#headerCityId').addClass('error');
	} else if(headerCategoryId == "-1"){
		$('#headerCityId').removeClass('error');
		$('#headerCategoryId').addClass( 'error' );
	} else if(headerCityId == "-1"){
		$('#headerCategoryId').removeClass( 'error' );
		$('#headerCityId').addClass('error');
	} else {
		$('#headerCityId').removeClass('error');
		$('#headerCategoryId').removeClass('error');
		
		var categoryVal = $("#headerCategoryId option:selected").text();
		var category = categoryVal.replace(' ', '_').toLowerCase();
		var city = $("#headerCityId option:selected").text();
		
		window.location.href = "vendor_list?search="+category+"&city="+city;
	/* $.ajax({
		data : {
			
			headerSearchCategoryId : $("#headerCategoryId").val(),
			headerSearchCityId : $("#headerCityId").val()
		},
		url : "searchCategoryByCity.action",
		dataType : "json",
		success : function(data) {
			window.location.href = "vendor_list";
		},
		error : function(xhr, textStatus, errorThrown) {
			// Handle error
		}
	}); */
	}
}
</script>
</head>
<div class="collapse" id="searcharea"><!-- top search -->
  <div class="input-group">
    <input type="text" class="form-control" placeholder="Search for...">
    <span class="input-group-btn">
    <button class="btn btn-primary" type="button">Search</button>
    </span> </div>
</div>
<!-- /.top search -->
<div class="header">
<div class="top-bar">
    <div class="container">
      <div class="row">
        <div class="col-md-8 top-message">
        <p>Welcome to Weddfix</p>
      </div>
      <%-- <div class="col-md-7">
      <%if (ActionContext.getContext().getName() != null) {
					if(!ActionContext.getContext().getName()
							.equalsIgnoreCase("login") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("logout") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("register") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_list")) {%>
            <s:form name="headerSearchForm" id="headerSearchForm" method="post">
                <div class="form-group col-md-4" style="margin: 5px 0;">
                  <select id="headerCategoryId" name="headerCategoryId" class="form-control" style="height: 34px; margin-bottom: 0;">
                    <option value="-1">Select Category</option>
                  </select>
                </div>
                <div class="form-group col-md-4" style="margin: 5px 0;">
                  <select id="headerCityId" name="headerCityId" class="form-control" style="height: 34px; margin-bottom: 0;">
                    <option value="-1">Select City</option>
                  </select>
                </div>
                <div class="form-group col-md-4" style="height: 34px; margin: 5px 0;">
                  <input type="button" class="btn btn-primary" onclick="search_category_by_city();" style="background-color: #cf6ac8; padding: 7px;" value="Find a Service" />
                </div>
            </s:form>
          <%}} %>
          </div> --%>
          <div class="col-md-4 top-links">
        <ul class="listnone">
        <%if (ActionContext.getContext().getName() != null) {
					if(ActionContext.getContext().getName()
							.equalsIgnoreCase("home")) {%>
          <li><a target="_blank" href="/matrimony/home"> Matrimony</a></li>
          <%}} %>
          <c:if test="${role != 'VENDORADMIN' }">
          <%if (ActionContext.getContext().getName() != null) {
					if(!ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_login") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_register") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("logout") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_logout") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_registered_successfully")) {%>
          <li><a href="vendor_login"> Are you vendor?</a></li>
          <%}} %>
          </c:if>
          <c:choose>
			<c:when test="${userName == null}">
			  <%if(!ActionContext.getContext().getName()
							.equalsIgnoreCase("home")) {%>
			  <li><a href="home">Home</a></li>
			  <%} %>
           		<%if (ActionContext.getContext().getName() != null) {
					if(!ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_login") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_register") && !ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_registered_successfully")) {%>
			  <%if(!ActionContext.getContext().getName()
							.equalsIgnoreCase("login")) {%>
			  <li><a href="login">Log in</a></li>
			  <%} %>
			  <%if(!ActionContext.getContext().getName()
							.equalsIgnoreCase("register")) {%>
	          <li><a href="register">Register</a></li>
			  <%} %>
	          <%}} %>
	          <%if (ActionContext.getContext().getName() != null) {
					if(ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_login") || ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_register") || ActionContext.getContext().getName()
							.equalsIgnoreCase("vendor_registered_successfully")) {%>
			  <li><a href="vendor_login"> Are you vendor?</a></li>
	          <li><a href="vendor_register">Register</a></li>
	          <%}} %>
          		
			</c:when>
			<c:otherwise>
				<c:if test="${verifiedMobileNumber == false && role != 'VENDORADMIN'}">
				<script type="text/javascript">
				var pathname = window.location.pathname.substr(window.location.pathname.lastIndexOf('/') + 1);
				if(pathname != 'verify_mobile') {
					window.location.href = "verify_mobile";
				}
				</script>
				</c:if>
				<c:if test="${verifiedMobileNumber == false && role == 'VENDORADMIN' }">
				<script type="text/javascript">
				var pathname = window.location.pathname.substr(window.location.pathname.lastIndexOf('/') + 1);
				if(pathname != 'verify_vendor_mobile') {
					window.location.href = "verify_vendor_mobile";
				}
				</script>
				</c:if>
				<%if(!ActionContext.getContext().getName()
							.equalsIgnoreCase("home")) {%>
				  <li><a href="home">Home</a></li>
				<%} %>
				<c:if test="${role == 'VENDORADMIN' }">
				<li><a style="cursor: pointer;" href="cart"><i class="glyphicon glyphicon-shopping-cart"></i>
				<c:if test="${myPlanId != null }">
              		<span class="itemCount">1</span>
              	</c:if>
              	</a></li>
              	</c:if>
              	<c:if test="${role != 'VENDORADMIN' }">
					<li><a href="logout">Logout</a></li>
				</c:if>
				<c:if test="${role == 'VENDORADMIN' }">
					<li><a href="vendor_logout">Logout</a></li>
				</c:if>
			</c:otherwise>
		</c:choose>
<!--           <li><a role="button" data-toggle="collapse" href="#searcharea" aria-expanded="false" aria-controls="searcharea"> <i class="fa fa-search"></i> </a></li> -->
        </ul>
      </div>
    </div>
  </div>
</div>
</div>
<div style="background-color: #cf6ac8;">
  <div class="container">
    <div class="row">
      <div class="col-md-2 logo">
        <div class="navbar-brand"> 
        <c:choose>
			<c:when test="${userName == null}">
           		<a class="brand" href="home"><img height="70" width="90" src="/images/weddfix_logo.png" alt="logo"></a>
			</c:when>
			<c:otherwise>
			<c:if test="${role == 'VENDORADMIN' }">
				<a class="brand" href="vendor_dashboard"><img height="70" width="90" src="/images/weddfix_logo.png" alt="logo"></a>
			</c:if>
			<c:if test="${role != 'VENDORADMIN' }">
				<a class="brand" href="my_home"><img height="70" width="90" src="/images/weddfix_logo.png" alt="logo"></a>
			</c:if>
			</c:otherwise>
			</c:choose>
        </div>
      </div>
      <div class="col-md-10">
        <div class="navigation">
          <div class="menu-button">Menu</div>
          <ul data-breakpoint="800" class="flexnav">
          	<li>
          	<c:if test="${role == 'ADMIN'}">
          		<a href="admin_home">Admin Home</a>
          	</c:if>
          	</li>
          	<li>
          	<c:choose>
			<c:when test="${userName == null}">
<!--            		<a href="home">Home</a> -->
			</c:when>
			<c:otherwise>
			<c:if test="${role == 'VENDORADMIN' }">
				<a href="vendor_dashboard">My Home</a>
			</c:if>
			<c:if test="${role != 'VENDORADMIN' }">
				<a href="my_home">My Home</a>
			</c:if>
			</c:otherwise>
			</c:choose>
            </li>
            <li><a href="vendor_list?search=<%=CommonConstants.WEDDING_HALLS_STR%>">Wedding Halls</a>
            </li>
            <li><a href="vendor_list?search=<%=CommonConstants.STUDIOS_STR%>">Studios</a>
            </li>
            <li><a href="vendor_list?search=<%=CommonConstants.DECORATIONS_STR%>">Decorations</a>
            </li>
            <li><a href="vendor_list?search=<%=CommonConstants.BEAUTY_PARLOURS_STR%>">Beauty Parlours</a>
            </li>
            <li><a>More Services</a>
              <ul>
                <li><a href="vendor_list?search=<%=CommonConstants.JEWEL_SHOPS_STR%>">Jewel Shops</a></li>
                <li><a href="vendor_list?search=<%=CommonConstants.CATERINGS_STR%>">Caterings</a></li>
                <li><a href="vendor_list?search=<%=CommonConstants.WEDDING_ASTROLOGERS_STR%>">Wedding Astrologers</a></li>
                <li><a href="vendor_list?search=<%=CommonConstants.WEDDING_CLOTHES_STR%>">Wedding Clothes</a></li>
              	<li><a href="vendor_list?search=<%=CommonConstants.ENTERTAINMENTS_STR%>">Entertainments</a></li>
                <li><a href="vendor_list?search=<%=CommonConstants.TEXTILES_STR%>">Textiles</a></li>
                <li><a href="vendor_list?search=<%=CommonConstants.TRAVELS_STR%>">Travels</a></li>
                <li><a href="vendor_list?search=<%=CommonConstants.HOTELS_STR%>">Hotels</a></li>
              </ul>
            </li>
            <c:if test="${role != 'ADMIN' }">
	            <li><a href="contact">Contact Us</a></li>
			</c:if>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
