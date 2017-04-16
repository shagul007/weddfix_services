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

	function update(id, categoryId, photoType) {
		$.ajax({
			data : {
				editCategoryId : id,
				editCategoryTypeId : categoryId,
				editPhotoType : photoType
			},
			url : "vendor_edit_listing_session.action",
			dataType : "json",
			success : function(data) {
				window.location.href = "vendor_edit_listing";
			},
			error : function(xhr, textStatus, errorThrown) {

			}
		});
	}
	
	function upgrade(id) {
		$.ajax({
			data : {
				editCategoryId : id,
			},
			url : "vendor_edit_listing_session.action",
			dataType : "json",
			success : function(data) {
				window.location.href = "upgrade";
			},
			error : function(xhr, textStatus, errorThrown) {

			}
		});
	}
	
	function deleteCategoryInfo(id) {
		$("#delCategoryId").val(id);
		$("#delete_category_confirm").show();
	}
	
	function deleteCategoryInfoConfirm() {
		$.ajax({
			data : {
				deleteCategoryId : $("#delCategoryId").val()
			},
			url : "deleteCategoryInfo.action",
			dataType : "json",
			success : function(data) {
				$("#delete_category_confirm").hide();
				window.location.href = "vendor_my_listing";
			},
			error : function(xhr, textStatus, errorThrown) {

			}
		});
	}
	
	function cancel() {
		$("#delete_category_confirm").hide();
	}
	
	function Pager(tableName, itemsPerPage) {
	    this.tableName = tableName;
	    this.itemsPerPage = itemsPerPage;
	    this.currentPage = 1;
	    this.pages = 0;
	    this.inited = false;
	     
	    this.showRecords = function(from, to) {        
	        var rows = document.getElementById(tableName).rows;
	        // i starts from 1 to skip table header row
	        for (var i = 1; i < rows.length; i++) {
	            if (i < from || i > to)  {
	            	i = i - 1;
// 	            	alert("a"+i);
	            	$("#row"+i).hide();
	            	i = i + 1;
// 	            	alert("b"+i);
// 	                rows[i].style.display = 'none';
	            } else {
	            	i = i - 1;
// 	            	alert("c"+i);
	            	$("#row"+i).show();
	            	$("#loadingPopup").hide();
	            	i = i + 1;
// 	            	alert("d"+i);
// 	                rows[i].style.display = '';
	            }
	        }
	    }
	     
	    this.showPage = function(pageNumber) {
	    		$("#li"+pageNumber).addClass('active');
	    		$("#loadingPopup").show();
	     if (! this.inited) {
	      alert("not inited");
	      return;
	     }
	 
	     	var oldPageAnchor = document.getElementById('pg'+this.currentPage);
	        oldPageAnchor.className = 'pg-normal';
	        $("#li"+this.currentPage).removeClass('active');
	         
	        this.currentPage = pageNumber;
	        var newPageAnchor = document.getElementById('pg'+this.currentPage);
	        newPageAnchor.className = 'pg-selected';
	        $("#li"+this.currentPage).addClass('active');
	         
	        var from = (pageNumber - 1) * itemsPerPage + 1;
	        var to = from + itemsPerPage - 1;
	        this.showRecords(from, to);
	    }   
	     
	    this.prev = function() {
	        if (this.currentPage > 1)
	            this.showPage(this.currentPage - 1);
	    }
	     
	    this.next = function() {
	        if (this.currentPage < this.pages) {
	            this.showPage(this.currentPage + 1);
	        }
	    }                        
	     
	    this.init = function() {
	        var rows = document.getElementById(tableName).rows;
	        var records = (rows.length - 1); 
	        this.pages = Math.ceil(records / itemsPerPage);
	        this.inited = true;
	    }
	 
	    this.showPageNav = function(pagerName, positionId) {
	     if (! this.inited) {
	      alert("not inited");
	      return;
	     }
	     var element = document.getElementById(positionId);
	      
	     var pagerHtml = '<ul class="pagination">';
	    	 pagerHtml += '<li> <a href="javascript:' + pagerName + '.prev();" aria-label="Previous"> <span aria-hidden="true">Previous</span> </a> </li>';
	    for (var page = 1; page <= this.pages; page++) {
            	pagerHtml += '<li id="li'+ page + '"><a id="pg' + page + '" href="javascript:' + pagerName + '.showPage(' + page + ');">' + page + '</a> </li> ';
	    }
	        pagerHtml += '<li> <a href="javascript:'+pagerName+'.next();" aria-label="Next"> <span aria-hidden="true">NEXT</span> </a> </li>';
	         
	        element.innerHTML = pagerHtml;
	    }
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
			<li class="active"><a href="vendor_my_listing"><i
					class="fa fa-list db-icon"></i>My Listing </a></li>
			<li><a href="vendor_add_listing"><i class="fa fa-plus-square db-icon"></i>Add listing</a></li>
			<li><a href="vendor_profile"><i
					class="fa fa-user db-icon"></i>My Profile</a></li>
			<li><a href="upgrade"><i
					class="fa fa-list-alt db-icon"></i>Pricing Plan</a></li>
			<!-- <li><a href="account_details"><i class="fa fa-user db-icon"></i>Account Details</a>
			</li> -->
      	</ul>
      </div>
    </div>
  </div>
</div>
<div class="main-container" style="min-height: 365px;">
<div class="container">
	<%if(request.getParameter("choose") != null) { if(request.getParameter("choose").equals("service")) { %>
	<div class="row">
       <div class="col-md-12">
       	<span style="color: red;">Please choose a service to upgrade</span>
       </div>
     </div>
     <%}} %>
    <div class="row">
      <div class="col-md-12">
        <div class="dashboard-page-head">
          <div class="page-header">
            <h1>My Venue Listing <small>Find your listing and edit or delete your venue listing.</small></h1>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-12 my-listing-dashboard">
        
        <div class="table-head">
          <div class="row">
            <div class="col-md-2"><span class="th-title">Image</span></div>
            <div class="col-md-3"><span class="th-title">Title</span></div>
            <div class="col-md-2"><span class="th-title">Address</span></div>
            <div class="col-md-1"><span class="th-title">Price</span></div>
            <div class="col-md-2"><span class="th-title">Action</span></div>
            <div class="col-md-2"><span class="th-title">Active Plan</span></div>
          </div>
        </div>
        <s:iterator value="categoryInfoListBean" status="incr">
        <div class="listing-row" id="row<s:property value="#incr.index" />"><!-- listing row -->
          <div class="row">
            <div class="col-md-2 listing-thumb">
            <c:if test="${fileName != null }">
            	<img height="77" width="554" src="<s:url action="ImageAction?imageId=%{fileName}" />" alt="<s:property value="%{fileName}" />" class="img-responsive">
			</c:if>
			<c:if test="${fileName == null }">
				<img src="images/vendor-new-1.jpg" alt="" class="img-responsive">
			</c:if>
            </div>
            <div class="col-md-3 listing-title"><h2><s:property value="%{companyName}" /></h2> </div>
            <div class="col-md-2 listing-address"><s:property value="%{address}" />, <s:property value="%{location}" />, <s:property value="%{cityName}" />, 
            <s:property value="%{pincode}" />, <s:property value="%{countryName}" /></div>
            <div class="col-md-1 listing-price">Rs.<s:property value="%{price}" /></div>
            <div class="col-md-2 listing-action"><a href="javascript:update(<s:property value="%{id}" />, <s:property value="%{categoryId}" />, '<s:property value="%{photoType}" />');" class="btn btn-primary  btn-xs" style="margin: 5px 10px 5px 0;">Edit & Add More Images</a> <c:if test="${planName == null }"><a href="javascript:deleteCategoryInfo(<s:property value="%{id}" />);" class="btn btn-danger btn-xs">Delete</a></c:if></div>
            <c:if test="${planName == null }">
            <div class="col-md-2 listing-action"><a href="javascript:upgrade(<s:property value="%{id}" />);" class="btn btn-primary  btn-xs" style="white-space: normal; padding:5px;">Upgrade to Show your service to public</a></div>
            </c:if>
            <c:if test="${planName != null }">
            <div class="col-md-2 listing-action">
            <span style="font-size: 14px;">Paid Name:</span> <span class="btn btn-primary  btn-xs" style="background-color: #cd853f;"><s:property value="%{planName}"/></span>
            <span style="font-size: 14px;">Paid Amount:</span> <span class="btn btn-primary  btn-xs" style="background-color: #a6419f;">Rs.<s:property value="%{amount}"/></span>
            <span style="font-size: 14px;">Validity:</span> <c:if test="${validity > 0 }"><span class="btn btn-primary  btn-xs" style="background-color: #b22222;"><s:property value="%{validity}" /> days left </span></c:if> <c:if test="${validity <= 0 }"><span class="btn btn-primary  btn-xs" style="background-color: #ff0000;">Expired</span></c:if>
            <c:if test="${validity <= 0 }"><a href="javascript:upgrade(<s:property value="%{id}" />);" class="btn btn-primary  btn-xs" style="white-space: normal; padding:5px;">Upgrade</a></c:if>
            </div>
            </c:if>
          </div>
        </div><!-- listing row -->
        </s:iterator>
        </div>
        <c:if test="${categoryInfoListBean[0]['companyName'] != null}">
        <div class="col-md-12 tp-pagination" id="pageNavPosition"></div>
        </c:if>
    </div>
  </div>
</div>
<div id="delete_category_confirm" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<s:hidden id="delCategoryId" name="delCategoryId"/>
		<div class="modal-header">
			<h4 class="modal-title" style="color: red;"><i class="fa fa-edit"></i> Delete your listing</h4>
		</div>
		<div class="content">
			<p>Are you sure?</p>
		</div>
		<div class="alerty-action">
			<a class="btn-cancel" onclick="cancel();">Cancel</a>
			<a class="btn-ok" id="btnUpload" onclick="deleteCategoryInfoConfirm();">Delete</a>
		</div>
	</div>
	</div>
<!-- 	//don't delete below table -->
<!--     This table for pagination -->
		<table id="results" style="display: none;">
        <tr>
        </tr>
        <s:iterator value="categoryInfoListBean">
        <tr>
        </tr>
        </s:iterator>
        </table>
        <!-- <div id="loadingPopup" class="overlay" style="opacity: 1; visibility: visible; display: green;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: red;"><i class="fa fa-edit"></i> Please wait</h4>
		</div>
		<div class="content">
			<p>Loading...</p>
		</div>
	</div>
	</div> -->
	<script type="text/javascript"><!--
        var pager = new Pager('results', 5); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
    //--></script>
<!--     End This table for pagination -->
</body>
</html>