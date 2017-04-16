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
    width: 300px;}
.vendor-detail .location {
    color: #9c9693;
    display: inline-block;
    font-size: 13px;
    font-weight: 500;
    height: 20px;
    margin-bottom: 20px;
    overflow: hidden !important;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 300px;
}
.vendor-detail .location {
	margin-bottom: 5px;
}
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
	    	 window.location.href = "vendor_details";
	    },
	 	error : function(xhr, textStatus, errorThrown) {
			// Handle error
//	 		alert("error");
		}
	});
	}
	
	function unshortlist(id) {
		$("#delShortlistedId").val(id);
		$("#delete_shortlisted_confirm").show();
	}

	function unshortlistConfirm() {
		$.ajax({
			data : {
					shortlistedId : $("#delShortlistedId").val()
			},
		     url:'unShortlistSuccess.action',
		     type: "POST",
	         async: false,
		     success: function (data) {
		    	 window.location.replace("my_wishlist");
		     },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
			 		alert("error");
			}
		});
	}

	function cancel() {
		$("#delete_shortlisted_confirm").hide();
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
						<li class="active"><a href="my_wishlist"><i
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
<div class="main-container" style="min-height: 365px;">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="dashboard-page-head">
          <div class="page-header">
            <h1>My Wishlist <small><s:property value="%{myWishlist[0]['shortlistedTotalCount']}" /> vendor in wishlist</small></h1>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="wishlist-board">
          <div class="row">
            <s:iterator value="myWishlist" status="incr">
            <div class="col-md-4">
              <div class="vendor-list-block mb30" id="row<s:property value="#incr.index" />"><!-- vendor list block -->
                <div class="vendor-img"> 
                <a href="vendor_details?vendor_name=<s:property value="%{myWishlist[#incr.index]['vendorName']}" />">
                <c:if test="${myWishlist[incr.index]['fileName'] != null}">
		        	<img height="194" width="360" src="<s:url action="ImageAction?imageId=%{myWishlist[#incr.index]['fileName']}" />" alt="<s:property value="%{myWishlist[#incr.index]['fileName']}" />" />
		    	  </c:if>
		         <c:if test="${myWishlist[incr.index]['fileName'] == null}">
					<img src="images/vendor-new-1.jpg" alt="wedding venue" class="img-responsive">
		         </c:if>
                </a>
                  <div class="category-badge"><a href="#" class="category-link"><s:property value="%{myWishlist[#incr.index]['categoryName']}" /></a></div>
                  <div class="price-lable"><s:property value="%{myWishlist[#incr.index]['price']}" /></div>
                  <div class="favorite-action"> <a href="javascript:unshortlist(<s:property value="%{myWishlist[#incr.index]['shortlistedId']}" />)" class="fav-icon"><i class="fa fa-close"></i></a> </div>
                </div>
                <div class="vendor-detail"><!-- vendor details -->
                  <div class="caption">
                    <h2><a href="vendor_details?vendor_name=<s:property value="%{myWishlist[#incr.index]['vendorName']}" />" class="title"><s:property value="%{myWishlist[#incr.index]['companyName']}" /> </a></h2>
                    <div class="vendor-meta"> <span class="location"> <i class="fa fa-map-marker map-icon"></i> <s:property value="%{myWishlist[#incr.index]['address']}" />, <s:property value="%{myWishlist[#incr.index]['location']}" />, 
            		<s:property value="%{myWishlist[#incr.index]['cityName']}" />, <s:property value="%{myWishlist[#incr.index]['pincode']}" />, <s:property value="%{myWishlist[#incr.index]['countryName']}" />.</span> 
                    <span class="rating" style="position: absolute;">
                    <c:if test="${myWishlist[incr.index]['maxRating'] != null}">
		            <c:forEach var="i" begin="1" end="${myWishlist[incr.index]['maxRating']}">
		            <i class="fa fa-star"></i> 
					</c:forEach>
					<c:forEach var="i" begin="1" end="${myWishlist[incr.index]['minRating']}">
		            <i class="fa fa-star-o"></i> 
					</c:forEach>
		            <span class="rating-count">(<s:property value="%{myWishlist[#incr.index]['maxUsersRating']}" />)</span> 
		            </c:if>
		            </span>
                    </div>
                  </div>
                </div>
                <!-- /.vendor details --> 
              </div>
              <!-- /.vendor list block --> 
            </div>
              </s:iterator>
          </div>
          <c:if test="${myWishlist[0]['categoryName'] != null}">
              <div class="col-md-12 tp-pagination" id="pageNavPosition"></div>
           </c:if>
        </div>
      </div>
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
        <s:iterator value="myWishlist">
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