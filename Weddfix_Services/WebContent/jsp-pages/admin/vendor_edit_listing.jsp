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
	
	$(document).ready(function() {
		$("#country_Id").val(<%=session.getAttribute("countryId")%>);
		$("#categoryId").val(<%=session.getAttribute("categoryId")%>);
		$("#countryId").val($("#country_Id").val());
		onLoadStateByCountryId();
		onLoadCityByStateId();
	});

	function setCountryId() {
		$("#countryId").val($("#country_Id").val());
	}
	
	function onLoadStateByCountryId() {
		var id = $("#country_Id").val();
		$("#stateId option").remove();
		$("#stateId").append(
				$("<option></option>").attr("value", "-1").text(
						"--- SELECT ---"));
		$.ajax({
			data : {
	
				country_Id : id
			},
			url : "loadStateByCountryId.action",
			dataType : "json",
			success : function(data) {
					$.each(data.stateMap, function(key,
							value) {
						$("#stateId").append(
								$("<option></option>").attr("value", key).text(
										value));
					});
					$("#stateId").val(<%=session.getAttribute("stateId")%>);
			},
			error : function(xhr, textStatus, errorThrown) {
				// Handle error
				alert(xhr);
				alert(textStatus);
				alert(errorThrown);
			}
		});
		
	}
	
	function onLoadCityByStateId() {
		var id = <%=session.getAttribute("stateId")%>;
		$("#cityId option").remove();
		$("#cityId").append(
				$("<option></option>").attr("value", "-1").text(
						"--- SELECT ---"));
		$.ajax({
			data : {
	
				state_Id : id
			},
			url : "loadCityByStateId.action",
			dataType : "json",
			success : function(data) {
					$.each(data.cityMap, function(key,
							value) {
						$("#cityId").append(
								$("<option></option>").attr("value", key).text(
										value));
					});
					$("#cityId").val(<%=session.getAttribute("cityId")%>);
			},
			error : function(xhr, textStatus, errorThrown) {
				// Handle error
				alert(xhr);
				alert(textStatus);
				alert(errorThrown);
			}
		});
		
	}
	
	function validateForm() {
		validateAddListingFields([ 'companyName', 'contactName', 'mobile', 'email', 'categoryId'
		                           , 'price', 'address', 'location', 'country_Id', 'stateId', 'cityId', 'pincode' ]);
	}
	
	function validateAddListingFields(elementIds) {
		var count = 0;
		var length = elementIds.length;
		var flag = false;
		$.each( elementIds, function( index, id ){
			var elementValue = document.forms["fileForm"][id].value;
			
			if (elementValue == null || elementValue == "" || elementValue == "-1" || id == 'email' || id == 'mobile') {
				if(elementValue == null || elementValue == "" || elementValue == "-1"){
					$('#' + id).addClass( 'error' );
					if($("#errorValue").val() == undefined){
						$('#error').append(
						'<span id="errorMessage" style="color: red; margin-bottom: 20px;">* Please fill all mandetory fields.</span><input type="hidden" id="errorValue" value="1">').html;
					}
				} else {
					count = count + 1;
					$('#' + id).removeClass('error');
				}
			
				if(id == 'email' && $("#emailMessageValue").val() == undefined && elementValue != null && elementValue != ""){
					var atpos = elementValue.indexOf("@");
					var dotpos = elementValue.lastIndexOf(".");
					if (atpos < 1 || dotpos < atpos + 2
							|| dotpos + 2 > elementValue.length) {
						count = count - 1;
						$('#' +id).after(
						'<span id="emailMessage" style="width: auto; display: block; color: red;">Invalid Email Address.</span><input type="hidden" id="emailMessageValue" value="1">').html;
					} else {
						$('#emailMessage').remove();
						$('#emailMessageValue').remove();
					}
				} else if(id == 'email' && elementValue != null && elementValue != "") {
					var atpos = elementValue.indexOf("@");
					var dotpos = elementValue.lastIndexOf(".");
					if (atpos < 1 || dotpos < atpos + 2
							|| dotpos + 2 > elementValue.length) {
						count = count - 1;
						if($("#emailMessageValue").val() == undefined){
						$('#' +id).after(
						'<span id="emailMessage" style="width: auto; display: block; color: red;">Invalid Email Address.</span><input type="hidden" id="emailMessageValue" value="1">').html;
						}
					} else {
						$('#emailMessage').remove();
						$('#emailMessageValue').remove();
					}
				}
				if(id == 'mobile' && $("#mobileMessageValue").val() == undefined && elementValue != null && elementValue != ""){
					if (elementValue.length < 10) {
						count = count - 1;
						$('#' +id).after(
						'<span id="mobileMessage" style="width: auto; display: block; color: red;">Invalid Mobile Number.</span><input type="hidden" id="mobileMessageValue" value="1">').html;
					 } else {
							$('#mobileMessage').remove();
							$('#mobileMessageValue').remove();
					 }
				 } else if(id == 'mobile' && elementValue != null && elementValue != "") {
					 if (elementValue.length < 10) {
						 count = count - 1;
						 if($("#mobileMessageValue").val() == undefined){
							$('#' +id).after(
							'<span id="mobileMessage" style="width: auto; display: block; color: red;">Invalid Mobile Number.</span><input type="hidden" id="mobileMessageValue" value="1">').html;
						 }
						 } else {
								$('#mobileMessage').remove();
								$('#mobileMessageValue').remove();
						 }
					}
			} else {
				count = count + 1;
				$('#' + id).removeClass('error');
			}
		
			
//			alert(count+" "+length);			
			if(length == count){
				$('#error').hide();
				flag = true;
			} 
		});
		
		if(flag){
//			alert("Success");
			checkCompanyNameAlredyExist();
		}
		}
	
	function checkCompanyNameAlredyExist() {
		$.ajax({
			data : {
				vendorId: $("#id").val(),
				compName : $("#companyName").val()
			},
			url : "checkOtherCompanyNameAlredyExist.action",
			dataType : "json",
			success : function(data) {
				if (data.compName != $("#companyName").val()) {
					$('#companyExistMessage').remove();
					formSubmit();
				} else {
					if($("#companyExistMessageValue").val() == undefined){
						$('#companyName').after(
						'<span id="companyExistMessage" style="width: auto; display: block; color: red;">This Company Name already exist.</span><input type="hidden" id="companyExistMessageValue" value="1">').html;
					}
					return false;
				}
			},
			error : function(xhr, textStatus, errorThrown) {
				// Handle error
				return false;
			}
		});
	}
	
	function oncheckCompanyNameAlredyExist() {
		$.ajax({
			data : {
				vendorId: $("#id").val(),
				compName : $("#companyName").val()
			},
			url : "checkOtherCompanyNameAlredyExist.action",
			dataType : "json",
			success : function(data) {
				if (data.compName != $("#companyName").val()) {
					$('#companyExistMessageValue').remove();
					$('#companyExistMessage').remove();
				} else {
					if($("#companyExistMessageValue").val() == undefined){
						$('#companyName').after(
						'<span id="companyExistMessage" style="width: auto; display: block; color: red;">This Company Name already exist.</span><input type="hidden" id="companyExistMessageValue" value="1">').html;
					}
					return false;
				}
			},
			error : function(xhr, textStatus, errorThrown) {
				// Handle error
				return false;
			}
		});
	}
	
	function formSubmit(){
		
		$("#loading").show();

		 $.ajax({
		     url:'vendor_add_listing_success.action',
		     type: "POST",
            data: new FormData(document.getElementById("fileForm")),
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
		     success: function (data) {
		    	 $("#loading").hide();
		    	 $("#success").show();
		    },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
				$("#loading").hide();
		 		alert("error");
			}
		});
		}
	
	function uploadMorePhotos(){
		var elementValue = document.forms["moreFileForm"]["userFile"].value;

		if(elementValue == null || elementValue == "" || elementValue == "-1"){
			$('#moreUserFile').addClass( 'error' );
		} else {
			$('#moreUserFile').removeClass('error');
			$("#loading").show();
			 $.ajax({
			     url:'uploadMorePhotos.action',
			     type: "POST",
	           data: new FormData(document.getElementById("moreFileForm")),
	           enctype: 'multipart/form-data',
	           processData: false,
	           contentType: false,
			     success: function (data) {
			    	 $("#loading").hide();
			    	 $("#upload_more_photos_success").show();
			    },
			 	error : function(xhr, textStatus, errorThrown) {
					// Handle error
					$("#loading").hide();
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
				window.location.href = "vendor_edit_listing";
			},
			error : function(xhr, textStatus, errorThrown) {

			}
		});
	}
	
	function cancel() {
		$("#delete_photo_confirm").hide();
	}
	
	function closePopup() {
		$("#loading").hide();
		$("#success").hide();
		$("#upload_more_photos_success").hide();
		window.location.replace("vendor_edit_listing");
	}
	
	function closeMorePhotosPopup() {
		$("#loading").hide();
		$("#success").hide();
		$("#upload_more_photos_success").hide();
		window.location.replace("vendor_edit_listing");
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
			<li class="active"><a href="vendor_edit_listing"><i class="fa fa-plus-square db-icon"></i>Edit listing</a></li>
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
<div class="main-container">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="dashboard-page-head">
          <div class="page-header">
            <h1>Edit Your Listing</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-8 add-listing-dashboard">
        <div class="row">
          <div class="col-md-12 dashboard-form">
            <div class="bg-white pinside40 mb30">
              <s:form name="fileForm" id="fileForm" method="post" enctype="multipart/form-data">
              <div id="error"><s:actionerror /></div>
                <s:hidden name="id" id="id" value="%{categoryInfoBean['id']}" />
                <s:hidden name="countryId" id="countryId" />
                <input type="hidden" name="photoType" id="photoType" value="P<s:property value="%{categoryInfoBean['id']}" />" />
				<c:if test="${categoryInfoBean['categoryPictureId'] != null }">
				<input type="hidden" name="profilePic" id="profilePic" value="<s:property value="%{categoryInfoBean['categoryPictureId']}" />" />
				</c:if>
				<c:if test="${categoryInfoBean['categoryPictureId'] == null }">
				<input type="hidden" name="profilePic" id="profilePic" value="null" />
				</c:if>
               <div class="row">
                <!-- Form Name -->
              <h2 class="form-title">Upload Photo</h2>
              
              <!-- File Button -->
              <div class="form-group">
                <div class="col-md-4">
                  <div class="photo-upload">
				  	<c:if test="${categoryInfoBean['categoryPictureId'] != null }">
                  	<img height="132" width="200" src="<s:url action="ImageAction?imageId=%{categoryInfoBean['fileName']}" />" alt="<s:property value="%{categoryInfoBean['fileName']}" />" />
				  </c:if>
				  <c:if test="${categoryInfoBean['categoryPictureId'] == null }">
				  	<img src="/images/profile-dashbaord.png" alt="">
				  </c:if>
				  </div>
                </div>
                <div class="col-md-8 upload-file">
                	<input id="userFile" name="userFile" class="input-file" type="file" onchange="isValidFile(this)" accept="image/png,image/gif,image/jpeg,image/pjpeg,image/jpg" />
                </div>
              </div>
              </div>
                <!-- Form Name -->
                <h2 class="form-title">Location Description &amp; Price</h2>
                
                <!-- Text input-->
                <div class="row">
                <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label" for="name">Company Name<span class="required">*</span></label>
                  <div class="">
                    <input id="companyName" name="companyName" type="text" value="<s:property value="%{categoryInfoBean['companyName']}" />" maxlength="100" onkeypress="oncheckCompanyNameAlredyExist();" onkeyup="checkName(this);" placeholder="Company Name" class="form-control input-md">
                  </div>
                </div>
                </div>
               <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label" for="name">Contact Name<span class="required">*</span></label>
                  <div class="">
                    <input id="contactName" name="contactName" type="text" value="<s:property value="%{categoryInfoBean['contactName']}" />" maxlength="100" onclick="oncheckCompanyNameAlredyExist();" onkeyup="checkName(this);" placeholder="Contact Name" class="form-control input-md">
                  </div>
                </div>
                </div>
                </div>
                <div class="row">
                <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label" for="name">Mobile<span class="required">*</span></label>
                  <div class="">
                    <input id="mobile" name="mobile" type="text" value="<s:property value="%{categoryInfoBean['mobile']}" />" maxlength="10" onclick="oncheckCompanyNameAlredyExist();" onkeyup="checkNumeric(this);" placeholder="Mobile" class="form-control input-md">
                  </div>
                </div>
                </div>
               <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label" for="name">Phone</label>
                  <div class="">
                    <input id="phone" name="phone" type="text" value="<s:property value="%{categoryInfoBean['phone']}" />" maxlength="10" onclick="oncheckCompanyNameAlredyExist();" onkeyup="checkNumeric(this);" placeholder="Phone" class="form-control input-md">
                  </div>
                </div>
                </div>
                </div>
                <div class="row">
               <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label" for="name">Email<span class="required">*</span></label>
                  <div class="">
                    <input id="email" name="email" type="text" value="<s:property value="%{categoryInfoBean['email']}" />" onclick="oncheckCompanyNameAlredyExist();" maxlength="100" placeholder="Email" class="form-control input-md">
                  </div>
                </div>
                </div>
               <div class="col-md-6">
                <div class="form-group">
                <label class="control-label" for="name">Website Url</label>
                <div class="">
                  <input id="websiteUrl" name="websiteUrl" type="text" value="<s:property value="%{categoryInfoBean['websiteUrl']}" />" onclick="oncheckCompanyNameAlredyExist();" maxlength="250" placeholder="Website Url" class="form-control input-md" required="">
                </div>
               </div>
               </div>
               </div>
               <div class="row">
                  <div class="col-md-6"> 
                    <!-- Select Basic -->
                    <div class="form-group">
                      <label class="control-label" for="categoryId">Category Type<span class="required">*</span></label>
                      <div class="">
                        <select id="categoryId" name="categoryId" class="form-control" onclick="oncheckCompanyNameAlredyExist();">
                          <option value="-1">--- SELECT ---</option>
						<s:iterator value="orgMap">
							<option value="<s:property value="key" />"><s:property value="value" /></option>
						</s:iterator>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6"> 
                    <!-- Select Basic -->
                    <div class="form-group">
                  <label class="control-label" for="name">Price<span class="required">*</span></label>
                  <div class="">
                    <input id="price" name="price" type="text" value="<s:property value="%{categoryInfoBean['price']}" />" maxlength="12" onclick="oncheckCompanyNameAlredyExist();" onkeyup="checkNumeric(this);" placeholder="Price" class="form-control input-md">
                  </div>
                </div>
                  </div>
                </div>
               <div class="row">
                  <div class="col-md-12">
                    <div class="form-group">
                      <label class="control-label" for="name">Address<span class="required">*</span></label>
                      <div class="">
                        <textarea class="form-control" id="address" name="address" rows="6" onclick="oncheckCompanyNameAlredyExist();"><s:property value="%{categoryInfoBean['address']}" /></textarea>
                      </div>
                    </div>
                  </div>
                </div>
               <div class="row">
               <div class="col-md-6">
                <div class="form-group">
                <label class="control-label" for="name">Location<span class="required">*</span></label>
                <div class="">
                  <input id="location" name="location" type="text" value="<s:property value="%{categoryInfoBean['location']}" />" maxlength="100" onclick="oncheckCompanyNameAlredyExist();" placeholder="Location" class="form-control input-md" required="">
                </div>
               </div>
               </div>
               <div class="col-md-6">
               <div class="form-group">
                  <label class="control-label" for="name">Country<span class="required">*</span></label>
                  <div class="">
                    <select class="form-control" id="country_Id" name="country_Id" onchange="setCountryId(); loadStateByCountryId(this);" onclick="oncheckCompanyNameAlredyExist();" onkeyup="setCountryId()">
						<option value="-1">--- SELECT ---</option>
					<s:iterator value="countryMap">
						<option value="<s:property value="key" />"><s:property value="value" /></option>
					</s:iterator>
					</select>
                  </div>
                </div>
                </div>
                </div>
               <div class="row">
                <div class="col-md-6"> 
                <div class="form-group">
                  <label class="control-label" for="name">State<span class="required">*</span></label>
                  <div class="">
                    <select class="form-control" id="stateId" name="stateId" onchange="loadCityByStateId(this)" onclick="oncheckCompanyNameAlredyExist();">
						<option value="-1">--- SELECT ---</option>
					</select>
                  </div>
                </div>
                </div>
               <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label" for="name">City<span class="required">*</span></label>
                  <div class="">
                    <select class="form-control" id="cityId" name="cityId" onclick="oncheckCompanyNameAlredyExist();">
						<option value="-1">--- SELECT ---</option>
					</select>
                  </div>
                </div>
                </div>
                </div>
                <div class="form-group row">
                  <label class="col-md-12 control-label" for="name">Pincode<span class="required">*</span></label>
                  <div class="col-md-12">
                    <input id="pincode" name="pincode" type="text" value="<s:property value="%{categoryInfoBean['pincode']}" />" maxlength="6" onclick="oncheckCompanyNameAlredyExist();" onkeyup="checkNumeric(this);" placeholder="Pincode" class="form-control input-md">
                  </div>
                </div>
                <!-- Textarea -->
                <div class="form-group row">
                  <label class="col-md-12 control-label" for="description">Descriptions</label>
                  <div class="col-md-12">
                    <textarea class="form-control" id="description" name="description" rows="6" onclick="oncheckCompanyNameAlredyExist();"><s:property value="%{categoryInfoBean['description']}" /></textarea>
                  </div>
                </div>
                <h2 class="form-title mt30">Map Location Info</h2>
                <!-- <div class="row">
                  <div class="col-md-6"> --> 
                    <!-- Text input-->
                    <div class="form-group row">
                      <label class="col-md-12 control-label" for="state">Embed Map Url (for Google Maps)</label>
                      <div class="col-md-12">
                        <input id="latitude" name="latitude" type="text" value="<s:property value="%{categoryInfoBean['latitude']}" />" onclick="oncheckCompanyNameAlredyExist();" placeholder="Embed Map Url" class="form-control input-md" required="">
                      </div>
                    </div>
                    <h2 class="form-title mt30">Video Info</h2>
                    <!-- Text input-->
                    <div class="form-group row">
                      <label class="col-md-12 control-label" for="state">Video Url (Add your youtube videos by seperating ,(commas) For eg: https://www.youtube.com/watch?v=5ZkD_o6NLK0, https://www.youtube.com/watch?v=DYoQJ7w2ULw)</label>
                      <div class="col-md-12">
                      <c:if test="${categoryInfoBean['categoryVideoUrl'] == null }">
                      	<textarea class="form-control" id="categoryVideoUrl" name="categoryVideoUrl" rows="6" onclick="oncheckCompanyNameAlredyExist();" placeholder="Add your youtube videos by seperating ,(commas) For eg: https://www.youtube.com/watch?v=5ZkD_o6NLK0, https://www.youtube.com/watch?v=DYoQJ7w2ULw"></textarea>
                      </c:if>
                      <c:if test="${categoryInfoBean['categoryVideoUrl'] != null }">
                        <textarea class="form-control" id="categoryVideoUrl" name="categoryVideoUrl" rows="6" onclick="oncheckCompanyNameAlredyExist();" placeholder="Add your youtube videos by seperating ,(commas) For eg: https://www.youtube.com/watch?v=5ZkD_o6NLK0, https://www.youtube.com/watch?v=DYoQJ7w2ULw"><s:property value="%{categoryInfoBean['categoryVideoUrl']}" /></textarea>
                      </c:if>
                      </div>
                    </div>
                  <%-- </div>
                  <div class="col-md-6"> 
                    <!-- Text input-->
                    <div class="form-group">
                      <label class="control-label" for="state">Longitude (for Google Maps)<span class="required">*</span></label>
                      <div class="">
                        <input id="state" name="State" type="text" placeholder="Longitude" class="form-control input-md" required="">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-12 mb20">
                    <div class="map" id="googleMap"></div>
                  </div>
                </div>  --%>
                <div class="row">
                  <div class="col-md-12">
                  	<input type="button" class="btn btn-primary" onclick="return validateForm();" value="Update"/>
                  </div>
                </div>
              </s:form>
            </div>
          </div>
          </div>
          </div>
          <div class="col-md-4 right-sidebar">
		<div class="row">
       <div class="col-md-12 widget widget-recent-post"><!-- widget -->
       		<s:form action="uploadMorePhotos" name="moreFileForm" id="moreFileForm" method="post" enctype="multipart/form-data">
       		<input type="hidden" name="profilePic" id="profilePic" value="null" />
       		<input type="hidden" name="pgOrgId" id="pgOrgId" value="<%=session.getAttribute("editCategoryTypeId") %>" />
       		<input type="hidden" name="pgPhotoType" id="pgPhotoType" value="<%=session.getAttribute("editPhotoType") %>" />
            <div class="well-box">
              <h2 class="widget-title">Upload more photos</h2>
              <div class="rc-post-holder row">
              <div class="col-md-8 post-image">
                	<input id="moreUserFile" name="userFile" multiple class="input-file" type="file" onchange="isValidFile(this)" accept="image/png,image/gif,image/jpeg,image/pjpeg,image/jpg" />
                </div>
                  <div class="col-md-8 post-image">
                  	<input type="button" class="btn btn-primary" onclick="return uploadMorePhotos();" value="Upload"/>
                  </div>
                </div>
            <s:iterator value="moreCategoryPhotos">
            <div class="rc-post-holder row">
              <div class="col-md-6">
                <div class="post-image"><a href="#"><img src="<s:url action="ImageAction?imageId=%{fileName}" />" class="img-responsive" alt=""></a></div>
              </div>
              <div class="rc-post col-md-6">
                <div class="post-meta" style="margin-top: 30px;"> <a href="javascript:deletePhoto(<s:property value="%{id}" />);" class="btn btn-danger btn-xs">Delete</a> </div>
              </div>
            </div>
            </s:iterator>
            </div>
            </s:form>
          </div>
          <!-- /.widget -->
		 </div>
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
			<p>Your listing updated successfully.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closePopup();">Close</a>
		</div>
	</div>
	</div>
	<div id="upload_more_photos_success" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: green;"><i class="fa fa-edit"></i> Success</h4>
		</div>
		<div class="content">
			<p>Photo uploaded successfully.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closeMorePhotosPopup();">Close</a>
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
	<div id="loading" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="content" style="">
			<img alt="Loading" src="/images/loading.gif" style="float: left;">
			<p style="margin: 37px 0px 0px 130px;">Please wait...</p>
		</div>
	</div>
	</div>
</body>
</html>