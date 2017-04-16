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
		window.location.href = "login";
	}
	
	if(role != 'ADMIN') {
		window.location.href = "my_home";
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
//		            	alert("a"+i);
	            	$("#row"+i).hide();
	            	i = i + 1;
//		            	alert("b"+i);
//		                rows[i].style.display = 'none';
	            } else {
	            	i = i - 1;
//		            	alert("c"+i);
	            	$("#row"+i).show();
	            	$("#loadingPopup").hide();
	            	i = i + 1;
//		            	alert("d"+i);
//		                rows[i].style.display = '';
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
	
	function uploadMorePhotos(){
		var elementValue = document.forms["moreFileForm"]["userFile"].value;

		if(elementValue == null || elementValue == "" || elementValue == "-1"){
			$('#moreUserFile').addClass( 'error' );
		} else {
			$('#moreUserFile').removeClass('error');
			$("#uploadingMsg").show();
			 $.ajax({
			     url:'uploadBannerPhotos.action',
			     type: "POST",
			     async: false,
	           data: new FormData(document.getElementById("moreFileForm")),
	           enctype: 'multipart/form-data',
	           processData: false,
	           contentType: false,
			     success: function (data) {
			    	 $("#uploadingMsg").hide();
			    	 window.location.href = "banner_photos";
			    },
			 	error : function(xhr, textStatus, errorThrown) {
					// Handle error
			 		alert("error");
				}
			});
		}
		}
	
	function deletePhoto(id) {
		$("#delPhotoId").val(id);
		$("#delete_photo_confirm").show();
	}
	
	function deletePhotoConfirm() {
		$.ajax({
			data : {
				delPhotoId : $("#delPhotoId").val()
			},
			url : "deletePhoto.action",
			dataType : "json",
			success : function(data) {
				$("#delete_photo_confirm").hide();
				window.location.href = "banner_photos";
			},
			error : function(xhr, textStatus, errorThrown) {

			}
		});
	}
	
	function activate(id, index){
		 $.ajax({
			data : {
					bannerId : id,
					bannerStatus : 'ACTIVE'
			},
		     url:'activateBannerPhotoSuccess.action',
		     type: "POST",
	         async: false,
		     success: function (data) {
		    	$("#activate"+index).hide();
		    	$("#deactivatea"+index).remove();
		    	$("#deactivate"+index).append('<a class="btn btn-danger btn-xs" id="deactivatea'+index+'" href="javascript:deactivate(' + id + ','+ index +')" style="background-color: darkgreen; color: #fff; height: 25px; width: 100px; font-size: 15px;">ACTIVE</a>');
		    	$("#deactivate"+index).show();
// 		    	$("#success").show();
		     },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
//		 		alert("error");
			}
		});
		}
	
	function deactivate(id, index) {
		$.ajax({
			data : {
				bannerId : id,
				bannerStatus : 'DEACTIVE'
			},
		     url:'deActivateBannerPhotoSuccess.action',
		     type: "POST",
	         async: false,
		     success: function (data) {
				$("#deactivate"+index).hide();
		    	$("#activate"+index).show();
// 				$("#unShortlistSuccess").show();
		     },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
//		 		alert("error");
			}
		});
	}
	
	function cancel() {
		$("#delete_photo_confirm").hide();
	}
	
	function closePopup() {
		window.location.replace("banner_photos");
	}
	
	function closeMorePhotosPopup() {
		window.location.replace("banner_photos");
	}

</script>
</head>
<body>
<div class="main-container">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="dashboard-page-head">
          <div class="page-header">
            <h1>Add Your Banner</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-8 add-listing-dashboard">
        <div class="row">
        <s:iterator value="bannerPhotosBean" status="incr">
         <div class="col-md-6">
                    <div class="vendor-list-block mb30"  id="row<s:property value="#incr.index" />"><!-- vendor list block -->
                      <div class="vendor-img"> 
                      <a href="#">
                      <c:if test="${bannerPhotosBean[incr.index]['fileName'] != null}">
			        	<img height="194" width="360" src="<s:url action="ImageAction?imageId=%{bannerPhotosBean[#incr.index]['fileName']}" />" alt="<s:property value="%{bannerPhotosBean[#incr.index]['fileName']}" />" />
			    	  </c:if>
			         <c:if test="${bannerPhotosBean[incr.index]['fileName'] == null}">
						<img src="images/vendor-new-1.jpg" alt="wedding venue" class="img-responsive">
			         </c:if>
                      </a>
<!--                         <div class="category-badge"><a href="#" class="category-link">category-badge</a></div> -->
<!--                         <div class="price-lable">$600 - $800</div> -->
                        <div class="favorite-action"> <a href="javascript:deletePhoto(<s:property value="%{bannerPhotosBean[#incr.index]['id']}" />)" class="fav-icon"><i class="fa fa-close"></i></a> </div>
                      </div>
                      <div class="vendor-detail"><!-- vendor details -->
                        <div class="caption">
                          <c:if test="${bannerPhotosBean[incr.index]['statusName'] != 'ACTIVE'}">
                <div id="activate<s:property value="%{#incr.index}" />">
                	<a style="color: #fff; height: 25px; width: 100px; font-size: 15px;" class="btn btn-danger btn-xs" href="javascript:activate(<s:property value="%{bannerPhotosBean[#incr.index]['id']}" />, <s:property value="%{#incr.index}" />);" class="shortlist">INACTIVE</a>
                </div>
               <div id="deactivate<s:property value="%{#incr.index}" />"  style="display: none;">
<%--                 	<a href="javascript:unshortlist(<s:property value="%{bannerPhotosBean[#incr.index]['shortlistedId']}" />, <s:property value="%{#incr.index}" />);" style="color: #ab4898"><i class="fa fa-heart"></i></a> --%>
                </div>
                </c:if>
                 <c:if test="${bannerPhotosBean[incr.index]['statusName'] == 'ACTIVE'}">
                 <div id="activate<s:property value="%{#incr.index}" />"  style="display: none;">
                	<a style="color: #fff; height: 25px; width: 100px; font-size: 15px;" class="btn btn-danger btn-xs" href="javascript:activate(<s:property value="%{bannerPhotosBean[#incr.index]['id']}" />, <s:property value="%{#incr.index}" />);" class="shortlist">INACTIVE</a>
                </div>
                <div id="deactivate<s:property value="%{#incr.index}" />">
                	<a class="btn btn-danger btn-xs" id="deactivatea<s:property value="%{#incr.index}" />" href="javascript:deactivate(<s:property value="%{bannerPhotosBean[#incr.index]['id']}" />, <s:property value="%{#incr.index}" />);" style="background-color: darkgreen; color: #fff; height: 25px; width: 100px; font-size: 15px;">ACTIVE</a>
                </div>
                </c:if>
                        </div>
                      </div>
                      <!-- /.vendor details --> 
                    </div>
                    <!-- /.vendor list block --> 
                  </div>
          </s:iterator>
          </div>
          <c:if test="${bannerPhotosBean[0]['fileName'] != null}">
          <div class="col-md-12 tp-pagination" id="pageNavPosition"></div>
          </c:if>
          </div>
          <div class="col-md-4 right-sidebar">
		<div class="row">
       <div class="col-md-12 widget widget-recent-post"><!-- widget -->
       		<s:form action="uploadMorePhotos" name="moreFileForm" id="moreFileForm" method="post" enctype="multipart/form-data">
       		<input type="hidden" name="profilePic" id="profilePic" value="null" />
<!--        		<input type="hidden" name="pgOrgId" id="pgOrgId" value="1" /> -->
       		<input type="hidden" name="pgPhotoType" id="pgPhotoType" value="BANNERPHOTO" />
            <div class="well-box">
              <h2 class="widget-title">Upload banner photos</h2>
              <div class="rc-post-holder row">
              <div class="col-md-8 post-image">
                	<input id="moreUserFile" name="userFile" class="input-file" type="file" onchange="isValidFile(this)" accept="image/png,image/gif,image/jpeg,image/pjpeg,image/jpg" />
                </div>
                  <div class="col-md-8 post-image">
                  	<input type="button" class="btn btn-primary" onclick="return uploadMorePhotos();" value="Upload"/>
                	<span id="uploadingMsg" style="display: none; color: #a56a96; float: left;">Uploading... Please wait.</span> 
				</div>
                </div>
            </div>
            </s:form>
          </div>
          <!-- /.widget -->
		 </div>
       </div>
    </div>
  </div>
</div>
	<div id="delete_photo_confirm" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<s:hidden id="delPhotoId" name="delPhotoId"/>
		<div class="modal-header">
			<h4 class="modal-title" style="color: red;"><i class="fa fa-edit"></i> Delete photo</h4>
		</div>
		<div class="content">
			<p>Are you sure?</p>
		</div>
		<div class="alerty-action">
			<a class="btn-cancel" onclick="cancel();">Cancel</a>
			<a class="btn-ok" id="btnUpload" onclick="deletePhotoConfirm();">Delete</a>
		</div>
	</div>
	</div>
	<!-- 	//don't delete below table -->
<!--     This table for pagination -->
		<table id="results" style="display: none;">
        <tr>
        </tr>
        <s:iterator value="bannerPhotosBean">
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
        var pager = new Pager('results', 6); 
        pager.init(); 
        pager.showPageNav('pager', 'pageNavPosition'); 
        pager.showPage(1);
    //--></script>
<!--     End This table for pagination -->
</body>
</html>