<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style type="text/css">
.title{display: inline-block;
    overflow: hidden !important;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 200px;}
</style>
<script type="text/javascript">
var userId = '<%=session.getAttribute("userId") %>';
if (userId == 'null') {
	window.location.href = "login";
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
//	            	alert("a"+i);
            	$("#row"+i).hide();
            	i = i + 1;
//	            	alert("b"+i);
//	                rows[i].style.display = 'none';
            } else {
            	i = i - 1;
//	            	alert("c"+i);
            	$("#row"+i).show();
            	$("#loadingPopup").hide();
            	i = i + 1;
//	            	alert("d"+i);
//	                rows[i].style.display = '';
            }
        }
    }
     
    this.showPage = function(pageNumber) {
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

	function vendorDetails(id){
		 $.ajax({
			data : {
					vendorId : id
			},
		     url:'vendorDetailsSession.action',
		     type: "POST",
	       async: false,
		     success: function (data) {
		    	 window.location.href = "vendor_booking_details";
		    },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
//		 		alert("error");
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
						<li><a href="my_home"><i
								class="fa fa-dashboard db-icon"></i>My Dashboard</a></li>
						<li><a href="my_wishlist"><i
								class="fa fa-heart db-icon"></i>My Wishlist </a></li>
						<li class="active"><a href="my_bookings"><i
								class="fa fa-list-alt db-icon"></i>My Bookings</a></li>
						<li><a href="my_profile"><i
								class="fa fa-user db-icon"></i>My Profile</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<div class="main-container" style="min-height: 365px;">
<div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="dashboard-page-head">
          <div class="page-header">
            <h1>My Bookings <small>Find your bookings.</small></h1>
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
            <div class="col-md-1"><span class="th-title">Price</span></div>
            <div class="col-md-2"><span class="th-title">Contact Details</span></div>
            <div class="col-md-2"><span class="th-title">Address</span></div>
            <div class="col-md-2"><span class="th-title">Booking Status</span></div>
          </div>
        </div>
        <s:iterator value="myBookinglist" status="incr">
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
            <div class="col-md-1 listing-price">Rs.<s:property value="%{price}" /></div>
            <div class="col-md-2 listing-title"><s:property value="%{contactName}" />
            <s:property value="%{contactEmail}" />
            <s:property value="%{contactMobile}" /></div>
            <div class="col-md-2 listing-address"><s:property value="%{address}" />, <s:property value="%{location}" />, <s:property value="%{cityName}" />, 
            <s:property value="%{pincode}" />, <s:property value="%{countryName}" /></div>
            <div class="col-md-2 listing-action"><a href="javascript:vendorDetails(<s:property value="%{id}" />)" class="btn btn-primary  btn-xs">Booked</a></div>
<%--             <div class="col-md-2 listing-action"><a href="javascript:update(<s:property value="%{id}" />, <s:property value="%{categoryId}" />, '<s:property value="%{photoType}" />');" class="btn btn-primary  btn-xs">Edit</a> <a href="javascript:deleteCategoryInfo(<s:property value="%{id}" />);" class="btn btn-danger btn-xs">Delete</a></div> --%>
          </div>
        </div><!-- listing row -->
        </s:iterator>
        </div>
        <c:if test="${myBookinglist[0]['companyName'] != null}">
        <div class="col-md-12 tp-pagination" id="pageNavPosition"></div>
        </c:if>
    </div>
  </div>
</div>
 <div id="delete_shortlisted_confirm" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<s:hidden id="delShortlistedId" name="delShortlistedId"/>
		<div class="modal-header">
			<h4 class="modal-title" style="color: red;"><i class="fa fa-edit"></i> Delete photo</h4>
		</div>
		<div class="content">
			<p>Are you sure?</p>
		</div>
		<div class="alerty-action">
			<a class="btn-cancel" onclick="cancel();">Cancel</a>
			<a class="btn-ok" id="btnUpload" onclick="unshortlistConfirm();">Delete</a>
		</div>
	</div>
	</div>
<!-- 	//don't delete below table -->
<!--     This table for pagination -->
		<table id="results" style="display: none;">
        <tr>
        </tr>
        <s:iterator value="myBookinglist">
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
        var pager = new Pager('results', 8); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
    //--></script>
<!--     End This table for pagination -->
</body>
</html>