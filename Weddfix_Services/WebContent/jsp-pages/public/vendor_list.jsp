<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style type="text/css">
.shortlist {
	color: #ffc513;
}
@media only screen and (min-width:768px) and (max-width: 2500px) {
.review{float: left; margin-left: 450px; margin-right: 450px;}
#img-big{width: 182px;}
}
@media only screen and (min-width:0px) and (max-width: 768px) {
#img-big{width: 260px;}
}
@media only screen and (min-width:993px) and (max-width: 2500px) {
.title{display: inline-block;
    font-size: 13px !important;
    overflow: hidden !important;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 120px;
}
.vendor-detail .location {
    color: #9c9693;
    display: inline-block;
    font-size: 12px;
    font-weight: 500;
    height: 20px;
    margin-bottom: 20px;
    overflow: hidden !important;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 120px;
}
.price, .cap-people {
    color: #3c3634;
    display: block;
    font-size: 13px;
    letter-spacing: -1px;
}
}
</style>
<script type="text/javascript">
var userId = '<%=session.getAttribute("userId") %>';
var vendorId = '<%=session.getAttribute("vendorId") %>';
var headerSearchCategoryId = '<%=session.getAttribute("headerSearchCategoryId") %>';
if (headerSearchCategoryId == 'null' && vendorId == 'null' && userId == 'null') {
	window.location.href = "home";
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
	
	function shortlist(id, index){
		 $.ajax({
			data : {
					shortlistedVendorId : id
			},
		     url:'shortlistSuccess.action',
		     type: "POST",
	         async: false,
		     success: function (data) {
		    	$("#shortlist"+index).hide();
		    	$("#unshortlista"+index).remove();
		    	$("#unshortlist"+index).append('<a id="unshortlista'+index+'" href="javascript:unshortlist(' + data.shortlistId + ','+ index +')" style="color: #ab4898"><i class="fa fa-heart"></i></a>');
		    	$("#unshortlist"+index).show();
		    	$("#success").show();
		     },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
// 		 		alert("error");
			}
		});
		}
	
	function unshortlist(id, index) {
		$.ajax({
			data : {
					shortlistedId : id
			},
		     url:'unShortlistSuccess.action',
		     type: "POST",
	         async: false,
		     success: function (data) {
				$("#unshortlist"+index).hide();
		    	$("#shortlist"+index).show();
				$("#unShortlistSuccess").show();
		     },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
// 		 		alert("error");
			}
		});
	}
	
	function closePopup() {
		$("#success").hide();
		$("#unShortlistSuccess").hide();
	}
</script>
<style type="text/css">
@media only screen and (min-width:444px) and (max-width: 2500px) {
.noVendorMsg{color: #a6419f; margin-left: 250px;}
}
@media only screen and (min-width:0px) and (max-width: 444px) {
.noVendorMsg{color: #a6419f; margin-left: 50px;}
}
</style>
</head>
<body>
<div class="tp-breadcrumb">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <ol class="breadcrumb">
                        <li><a href="home">Home</a></li>
                        <li class="active">Venue Listing</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
<div class="main-container">
  <div class="container">
    <div class="row">
      <div class="col-md-3">
        <div class="filter-sidebar">
          <div class="col-md-12 form-title">
            <h2>Refine Your Search</h2>
          </div>
          <s:form name="headerSearchForm" id="headerSearchForm" method="post">
            <div class="col-md-12 form-group">
              <label class="control-label" for="venuetype">Category</label>
              <select id="headerCategoryId" name="headerCategoryId" class="form-control" style="height: 34px; margin-bottom: 0;">
                    <option value="-1">Select Category</option>
                  </select>
            </div>
            <div class="col-md-12 form-group">
              <label class="control-label" for="venuetype">City</label>
              <select id="headerCityId" name="headerCityId" class="form-control" style="height: 34px; margin-bottom: 0;">
                    <option value="-1">Select City</option>
                  </select>
            </div>
            <!-- <div class="col-md-12 form-group">
               <div class="price-range default-range">
                       <label for="amount" class="control-label">Price range:</label>
                  <input type="text" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
                  <div id="slider-range"></div>
                    </div>
            </div>
            <div class="col-md-12 form-group rating">
              <label class="control-label">Select Rating </label>
              <div class="checkbox checkbox-success">
                <input type="checkbox" name="checkbox" id="checkbox-0" value="1" class="styled">
                <label for="checkbox-0" class="control-label"> <i class="fa fa-star"></i> </label>
              </div>
              <div class="checkbox checkbox-success">
                <input type="checkbox" name="checkbox" id="checkbox-1" value="2" class="styled">
                <label for="checkbox-1" class="control-label"> <i class="fa fa-star"></i> <i class="fa fa-star"></i> </label>
              </div>
              <div class="checkbox checkbox-success">
                <input type="checkbox" name="checkbox" id="checkbox-2" value="3" class="styled">
                <label for="checkbox-2" class="control-label"> <i class="fa fa-star"></i> <i class="fa fa-star"></i><i class="fa fa-star"></i> </label>
              </div>
              <div class="checkbox checkbox-success">
                <input type="checkbox" name="checkbox" id="checkbox-3" value="4" class="styled">
                <label for="checkbox-3" class="control-label"> <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> </label>
              </div>
              <div class="checkbox checkbox-success">
                <input type="checkbox" name="checkbox" id="checkbox-4" value="5" class="styled">
                <label for="checkbox-4" class="control-label"> <i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i> </label>
              </div>
            </div> -->
            <div class="col-md-12 form-group">
             <input type="button" onclick="search_category_by_city();" class="btn btn-primary btn-lg btn-block" value="Find a Service" />
            </div>
          </s:form>
        </div>
      </div>
      <div class="col-md-9">
          <%-- <c:if test="${vendorList[0]['companyName'] != null}">
    	<s:iterator value="vendorList" status="incr">
        <div class="vendor-box-list" id="row<s:property value="#incr.index" />"> <!-- vendor list -->
          <div class="row">
            <div class="col-md-5 no-right-pd">
              <div class="vendor-image"><!-- venue pic --> 
                <a href="javascript:vendorDetails(<s:property value="%{vendorList[#incr.index]['id']}" />)">
		        <c:if test="${vendorList[incr.index]['fileName'] != null}">
		        	<img height="198" width="350" src="<s:url action="ImageAction?imageId=%{vendorList[#incr.index]['fileName']}" />" alt="<s:property value="%{vendorList[#incr.index]['fileName']}" />" />
		    	  </c:if>
		         <c:if test="${vendorList[incr.index]['fileName'] == null}">
					<img src="images/vendor-1.jpg" alt="wedding venue" class="img-responsive">
		         </c:if>
		         </a>
		         <c:if test="${userId != null}">
		         <c:if test="${vendorList[incr.index]['shortlisted'] != true}">
                <div class="favourite-bg" id="shortlist<s:property value="%{#incr.index}" />">
                	<a href="javascript:shortlist(<s:property value="%{vendorList[#incr.index]['id']}" />, <s:property value="%{#incr.index}" />);" class="shortlist"><i class="fa fa-heart"></i></a>
                </div>
               <div class="favourite-bg" id="unshortlist<s:property value="%{#incr.index}" />"  style="display: none;">
                	<a href="javascript:unshortlist(<s:property value="%{vendorList[#incr.index]['shortlistedId']}" />, <s:property value="%{#incr.index}" />);" style="color: #ab4898"><i class="fa fa-heart"></i></a>
                </div>
                </c:if>
                 <c:if test="${vendorList[incr.index]['shortlisted'] == true}">
                 <div class="favourite-bg" id="shortlist<s:property value="%{#incr.index}" />"  style="display: none;">
                	<a href="javascript:shortlist(<s:property value="%{vendorList[#incr.index]['id']}" />, <s:property value="%{#incr.index}" />);" class="shortlist"><i class="fa fa-heart"></i></a>
                </div>
                <div class="favourite-bg" id="unshortlist<s:property value="%{#incr.index}" />">
                	<a id="unshortlista<s:property value="%{#incr.index}" />" href="javascript:unshortlist(<s:property value="%{vendorList[#incr.index]['shortlistedId']}" />, <s:property value="%{#incr.index}" />);" style="color: #ab4898"><i class="fa fa-heart"></i></a>
                </div>
                </c:if>
                </c:if>
              </div>
            </div>
            <!-- /.venue pic -->
            <div class=" col-md-7 no-left-pd"><!-- venue details -->
              <div class="vendor-list-details">
                <div class="caption"><!-- caption -->
                  <h2><a href="javascript:vendorDetails(<s:property value="%{vendorList[#incr.index]['id']}" />)" class="title"><s:property value="%{vendorList[#incr.index]['companyName']}" /></a></h2>
                  <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{vendorList[#incr.index]['address']}" />, <s:property value="%{vendorList[#incr.index]['location']}" />, 
            		<s:property value="%{vendorList[#incr.index]['cityName']}" />, <s:property value="%{vendorList[#incr.index]['pincode']}" />, <s:property value="%{vendorList[#incr.index]['countryName']}" />.</p>
                  <c:if test="${vendorList[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${vendorList[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${vendorList[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{vendorList[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
                </div>
                <!-- /.caption -->
                <div class="vendor-price">
                  <div class="price">Rs. <s:property value="%{vendorList[#incr.index]['price']}" /></div>
                </div>
              </div>
            </div>
          </div>
        </div><!-- /.vendor list -->
            </s:iterator>
        <div class="col-md-12 tp-pagination" id="pageNavPosition"></div>
            </c:if> --%>
	<c:if test="${vendorList[0]['companyName'] != null}">
    <s:iterator value="vendorList" status="incr">
      <div class="col-md-3 vendor-box" id="row<s:property value="#incr.index" />"><!-- venue box start-->
        <div class=""><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{vendorList[#incr.index]['vendorName']}" />">
        <c:if test="${vendorList[incr.index]['fileName'] != null}">
        	<img id="img-big" height="130" src="<s:url action="ImageAction?imageId=%{vendorList[#incr.index]['fileName']}" />" alt="<s:property value="%{vendorList[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${vendorList[incr.index]['fileName'] == null}">
			<img id="img-big" height="130" src="images/vendor-1.jpg" alt="wedding venue" class="img-responsive">
         </c:if>
         </a>
         <c:if test="${userId != null}">
		         <c:if test="${vendorList[incr.index]['shortlisted'] != true}">
                <div class="favourite-bg" id="shortlist<s:property value="%{#incr.index}" />">
                	<a href="javascript:shortlist(<s:property value="%{vendorList[#incr.index]['id']}" />, <s:property value="%{#incr.index}" />);" class="shortlist"><i class="fa fa-heart"></i></a>
                </div>
               <div class="favourite-bg" id="unshortlist<s:property value="%{#incr.index}" />"  style="display: none;">
<%--                 	<a href="javascript:unshortlist(<s:property value="%{vendorList[#incr.index]['shortlistedId']}" />, <s:property value="%{#incr.index}" />);" style="color: #ab4898"><i class="fa fa-heart"></i></a> --%>
                </div>
                </c:if>
                 <c:if test="${vendorList[incr.index]['shortlisted'] == true}">
                 <div class="favourite-bg" id="shortlist<s:property value="%{#incr.index}" />"  style="display: none;">
                	<a href="javascript:shortlist(<s:property value="%{vendorList[#incr.index]['id']}" />, <s:property value="%{#incr.index}" />);" class="shortlist"><i class="fa fa-heart"></i></a>
                </div>
                <div class="favourite-bg" id="unshortlist<s:property value="%{#incr.index}" />">
                	<a id="unshortlista<s:property value="%{#incr.index}" />" href="javascript:unshortlist(<s:property value="%{vendorList[#incr.index]['shortlistedId']}" />, <s:property value="%{#incr.index}" />);" style="color: #ab4898"><i class="fa fa-heart"></i></a>
                </div>
                </c:if>
                </c:if>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{vendorList[#incr.index]['vendorName']}" />" class="title"><s:property value="%{vendorList[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{vendorList[#incr.index]['address']}" />, <s:property value="%{vendorList[#incr.index]['location']}" />, 
            <s:property value="%{vendorList[#incr.index]['cityName']}" />, <s:property value="%{vendorList[#incr.index]['pincode']}" />, <s:property value="%{vendorList[#incr.index]['countryName']}" />.</p>
            <c:if test="${vendorList[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${vendorList[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${vendorList[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{vendorList[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{vendorList[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      <div class="col-md-12 tp-pagination" id="pageNavPosition"></div>
      </c:if>
      <!-- /.venue box start-->            
            <c:if test="${vendorList[0]['companyName'] == null}">
            	<span class="noVendorMsg">Sorry, no vendor found.</span>
            </c:if>
      </div>
    </div>
  </div>
</div>
 <div id="success" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: green;"><i class="fa fa-edit"></i> Success</h4>
		</div>
		<div class="content">
			<p>Shortlisted successfully.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closePopup();">Close</a>
		</div>
	</div>
	</div>
	<div id="unShortlistSuccess" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: green;"><i class="fa fa-edit"></i> Success</h4>
		</div>
		<div class="content">
			<p>Removed from shortlisted.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closePopup();">Close</a>
		</div>
	</div>
	</div>
<!-- 	//don't delete below table -->
<!--     This table for pagination -->
		<table id="results" style="display: none;">
        <tr>
        </tr>
        <s:iterator value="vendorList">
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