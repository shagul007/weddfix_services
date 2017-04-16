<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript">
	var role = '<%=session.getAttribute("role") %>';
	if (role == 'null') {
		window.location.href = "vendor_login";
	}
	
	if(role != 'VENDORADMIN') {
		window.location.href = "my_home";
	}
	
	var resetUserId = '<%=session.getAttribute("userId") %>';
	var cPw = '<%=session.getAttribute("password") %>';
	if(resetUserId == 'null'|| cPw ==  'null') {
		window.location.href = "login";
	} 
	
	function validateForm() {
		validateUpdateProfileFields([ 'fullName', 'mobile', 'country_Id', 'stateId', 'cityId', 'pincode' ]);
	}
	
	function validateUpdateProfileFields(elementIds) {
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
			formSubmit();
		}
		}
	
	$(document).ready(function() {
		$("#country_Id").val(<%=session.getAttribute("countryId")%>);
		$("#countryId").val($("#country_Id").val());
		$("#userId").val(resetUserId);
		onLoadStateByCountryId();
		onLoadCityByStateId();
	});

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

	
	function setCountryId() {
		$("#countryId").val($("#country_Id").val());
	}
	
	function formSubmit(){

		$("#loading").show();
		
		 $.ajax({
		     url:'vendor_update_profile_success.action',
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
	
	function validateChangePasswordForm() {
		validateChangePasswordFields([ 'currentPassword', 'password', 'password2' ]);
	}
	
	function validateChangePasswordFields(elementIds) {
	var count = 0;
	var length = elementIds.length;
	var flag = false;
	$.each( elementIds, function( index, id ){
		var elementValue = document.forms["form"][id].value;
		
		if (elementValue == null || elementValue == "" || elementValue == "-1" || id == 'currentPassword' || id == 'password' || id == 'password2') {
			if(elementValue == null || elementValue == ""){
				$('#' + id).addClass('error');
				if($("#errorValue").val() == undefined){
					$('#error').append(
					'<span id="errorMessage" style="color: red; margin-bottom: 20px;">* Please fill all mandetory fields.</span><input type="hidden" id="errorValue" value="1">').html;
				}
			} else {
				count = count + 1;
				$('#' + id).removeClass('error');
			}
		
			if(id == 'currentPassword' && $("#currentPasswordMessageValue").val() == undefined && elementValue != null && elementValue != ""){
				if (cPw != CryptoJS.MD5(elementValue).toString()) {
					count = count - 1;
					$('#' +id).after(
					'<span id="currentPasswordMessage" style="width: auto; display: block; color: red;">Old password not correct.</span><input type="hidden" id="currentPasswordMessageValue" value="1">').html;
				} else {
					$('#currentPasswordMessage').remove();
					$('#currentPasswordMessageValue').remove();
				}
			} else if(id == 'currentPassword' && elementValue != null && elementValue != "") {
				 if (cPw != CryptoJS.MD5(elementValue).toString()) {
					 count = count - 1;
					 if($("#currentPasswordMessageValue").val() == undefined){
						$('#' +id).after(
						'<span id="currentPasswordMessage" style="width: auto; display: block; color: red;"Old password not correct.</span><input type="hidden" id="currentPasswordMessageValue" value="1">').html;
					 }
					 } else {
							$('#currentPasswordMessage').remove();
							$('#currentPasswordMessageValue').remove();
					 }
			}
			
			if(id == 'password' && $("#passwordMessageValue").val() == undefined && elementValue != null && elementValue != ""){
				if (elementValue.length < 6) {
					count = count - 1;
					$('#' +id).after(
					'<span id="passwordMessage" style="width: auto; display: block; color: red;">Password should be minimum six characters.</span><input type="hidden" id="passwordMessageValue" value="1">').html;
				} else {
					$('#passwordMessage').remove();
					$('#passwordMessageValue').remove();
				}
			} else if(id == 'password' && elementValue != null && elementValue != "") {
				 if (elementValue.length < 6) {
					 count = count - 1;
					 if($("#passwordMessageValue").val() == undefined){
						$('#' +id).after(
						'<span id="passwordMessage" style="width: auto; display: block; color: red;">Password should be minimum six characters.</span><input type="hidden" id="passwordMessageValue" value="1">').html;
					 }
					 } else {
							$('#passwordMessage').remove();
							$('#passwordMessageValue').remove();
					 }
			}
			if(id == 'password2' && $("#passwordMessageValue2").val() == undefined && elementValue != null && elementValue != ""){
			if (elementValue != document.forms["form"]["password"].value) {
				count = count - 1;
				$('#password2').after(
				'<span id="passwordMessage2" style="width: auto; display: block; color: red;">Both Password are not equal. Please re-enter password.</span><input type="hidden" id="passwordMessageValue2" value="1">').html;
				document.forms["form"]["password"].value = "";
				document.forms["form"][id].value = "";
			} else {
				$('#passwordMessage2').remove();
				$('#passwordMessageValue2').remove();
			}
			} 
			if(id == 'password2' && elementValue != null && elementValue != "") {
				if (elementValue != document.forms["form"]["password"].value) {
					count = count - 1;
					 if($("#passwordMessageValue2").val() == undefined){
						$('#' +id).after(
						'<span id="passwordMessage2" style="width: auto; display: block; color: red;">Both Password are not equal. Please re-enter password.</span><input type="hidden" id="passwordMessageValue2" value="1">').html;
						document.forms["form"]["password"].value = "";
						document.forms["form"][id].value = "";
					 }
					 } else {
							$('#passwordMessage2').remove();
							$('#passwordMessageValue2').remove();
					 }
				}
//			alert(count+" "+length);			
		} else {
			count = count + 1;
			$('#' + id).removeClass('error');
		}
		if(length == count){
			$('#error').hide();
			flag = true;
		}
	});
	
	if(flag){
//		alert("Success");
		changePasswordFormSubmit();
	}
	}
	
	function changePasswordFormSubmit(){

		 $.ajax({
		     url:'reset_vendor_password_success.action',
		     type: "POST",
	         async: false,
		     data: $("#form").serialize(),
		     success: function (data) {
		    	 $("#changePasswordSuccess").show();
		    },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
		 		alert("error");
			}
		});
		}
	
	function closePopup() {
		$("#loading").hide();
		$("#success").hide();
		$("#changePasswordSuccess").hide();
		window.location.replace("vendor_profile");
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
			<li><a href="vendor_add_listing"><i
					class="fa fa-plus-square db-icon"></i>Add listing</a></li>
			<li class="active"><a href="vendor_profile"><i
					class="fa fa-user db-icon"></i>My Profile</a></li>
			<li><a href="upgrade"><i
					class="fa fa-list-alt db-icon"></i>Pricing Plan</a></li>
<!-- 			<li><a href="account_details"><i class="fa fa-user db-icon"></i>Account Details</a></li> -->
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
            <h1>Vendor Profile <small>Edit and Update your profile.</small></h1>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-12 profile-dashboard">
        <div class="row">
          <div class="col-md-7 dashboard-form">
          <div class="bg-white pinside40 mb30">
            <s:form cssClass="form-horizontal" name="fileForm" id="fileForm" method="post" enctype="multipart/form-data">
              <s:hidden name="id" id="id" value="%{myPersonalDetailsBean['id']}" />
			  <s:hidden name="countryId" id="countryId" />
			  	<input type="hidden" name="photoType" id="photoType" value="P<s:property value="%{myPersonalDetailsBean['id']}" />" />
				<c:if test="${myPersonalDetailsBean['profilePictureId'] != null }">
				<input type="hidden" name="profilePic" id="profilePic" value="<s:property value="%{myPersonalDetailsBean['profilePictureId']}" />" />
				</c:if>
				<c:if test="${myPersonalDetailsBean['profilePictureId'] == null }">
				<input type="hidden" name="profilePic" id="profilePic" value="null" />
				</c:if>
              <!-- Form Name -->
              <h2 class="form-title">Upload Profile Photo</h2>
              
              <!-- File Button -->
              <div class="form-group">
                <div class="col-md-4">
                  <div class="photo-upload">
                  <c:if test="${myPersonalDetailsBean['profilePictureId'] != null }">
                  	<img height="132" width="132" class="img-circle" src="<s:url action="ImageAction?imageId=%{myPersonalDetailsBean['fileName']}" />" alt="<s:property value="%{myPersonalDetailsBean['fileName']}" />" />
				  </c:if>
				  <c:if test="${myPersonalDetailsBean['profilePictureId'] == null }">
				  	<img class="img-circle" src="/images/profile-dashbaord.png" alt="">
				  </c:if>
				  </div>
                </div>
                <div class="col-md-8 upload-file">
                	<input id="userFile" name="userFile" class="input-file" type="file" onchange="isValidFile(this)" accept="image/png,image/gif,image/jpeg,image/pjpeg,image/jpg" />
                </div>
              </div>
              <!-- Text input-->
              <h2 class="form-title">Profile Vendor</h2>
              <div class="form-group">
                  <label class="col-md-4 control-label" for="name">Vendor Name<span class="required">*</span></label>
                  <div class="col-md-8">
                    <input id="fullName" name="fullName" type="text" value="<s:property value="%{myPersonalDetailsBean['fullName']}" />" maxlength="50" onkeyup="checkName(this);" placeholder="Name" class="form-control input-md">
                  </div>
                </div>
               <div class="form-group">
                <label class="col-md-4 control-label" for="name">Vendor Website </label>
                <div class="col-md-8">
                  <input id="websiteUrl" name="websiteUrl" type="text" value="<s:property value="%{myPersonalDetailsBean['websiteUrl']}" />" maxlength="250" placeholder="Vendor Website" class="form-control input-md" required="">
                </div>
               </div>
                <div class="form-group">
                  <label class="col-md-4 control-label" for="name">Email</label>
                  <div class="col-md-8">
                    <s:property value="%{myPersonalDetailsBean['email']}" />
                  </div>
                </div>
              <div class="form-group">
                  <label class="col-md-4 control-label" for="name">Mobile<span class="required">*</span></label>
                  <div class="col-md-8">
                    <input id="mobile" name="mobile" type="text" value="<s:property value="%{myPersonalDetailsBean['mobile']}" />" maxlength="10" onkeyup="checkNumeric(this);" placeholder="Mobile" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-4 control-label" for="name">Country<span class="required">*</span></label>
                  <div class="col-md-8">
                    <select class="form-control" id="country_Id" name="country_Id" onchange="setCountryId(); loadStateByCountryId(this);" onkeyup="setCountryId()">
						<option value="-1">--- SELECT ---</option>
					<s:iterator value="countryMap">
						<option value="<s:property value="key" />"><s:property value="value" /></option>
					</s:iterator>
					</select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-4 control-label" for="name">State<span class="required">*</span></label>
                  <div class="col-md-8">
                    <select class="form-control" id="stateId" name="stateId" onchange="loadCityByStateId(this)">
						<option value="-1">--- SELECT ---</option>
					</select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-4 control-label" for="name">City<span class="required">*</span></label>
                  <div class="col-md-8">
                    <select class="form-control" id="cityId" name="cityId">
						<option value="-1">--- SELECT ---</option>
					</select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-4 control-label" for="name">Pincode<span class="required">*</span></label>
                  <div class="col-md-8">
                    <input id="pincode" name="pincode" type="text" value="<s:property value="%{myPersonalDetailsBean['pincode']}" />" maxlength="6" onkeyup="checkNumeric(this);" placeholder="Pincode" class="form-control input-md">
                  </div>
                </div>
              <!-- Textarea -->
              <div class="form-group">
                <label class="col-md-4 control-label" for="description">About Yourself</label>
                <div class="col-md-8">
                  <textarea class="form-control" id="description" name="Description" rows="6" cols="12" maxlength="2000"><s:property value="%{myPersonalDetailsBean['description']}" /></textarea>
                </div>
              </div>
              <h2 class="form-title">Social Media Profile</h2>
              <div class="form-group">
                <label class="col-md-4 control-label" for="name">Facebook Url</label>
                <div class="col-md-8">
                  <input id="name" name="name" type="text" value="<s:property value="%{myPersonalDetailsBean['facebookUrl']}" />" maxlength="250" placeholder="Facebook Url" class="form-control input-md" required="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-4 control-label" for="name">Twitter Url</label>
                <div class="col-md-8">
                  <input id="name" name="name" type="text" value="<s:property value="%{myPersonalDetailsBean['twitterUrl']}" />" maxlength="250" placeholder="Twitter Url" class="form-control input-md" required="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-4 control-label" for="name">Linkedin Url</label>
                <div class="col-md-8">
                  <input id="name" name="name" type="text" value="<s:property value="%{myPersonalDetailsBean['linkedinUrl']}" />" maxlength="250" placeholder="Linkedin Url" class="form-control input-md" required="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-4 control-label" for="name">Instagram Url</label>
                <div class="col-md-8">
                  <input id="name" name="name" type="text" value="<s:property value="%{myPersonalDetailsBean['instagramUrl']}" />" maxlength="250" placeholder="Instagram Url" class="form-control input-md" required="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-4 control-label" for="name">Pinterest Url</label>
                <div class="col-md-8">
                  <input id="name" name="name" type="text" value="<s:property value="%{myPersonalDetailsBean['pinterestUrl']}" />" maxlength="250" placeholder="Pinterest Url" class="form-control input-md" required="">
                </div>
              </div>
              <!-- Button -->
              <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                	<input type="button" class="btn btn-primary" onclick="return validateForm();" value="Update Profile"/>
                </div>
              </div>
            </s:form>
            </div>
          </div>
          <div class="col-md-5 dashboard-form">

          <div class="bg-white pinside40 mb30">
            <s:form cssClass="form-horizontal" name="form" id="form" method="post">
              <s:hidden id="userId" name="userId"/>
              <div id="error"><s:actionerror /></div>
              <!-- Form Name -->
              <h2 class="form-title">Change Password</h2>
              
              <!-- Text input-->
              <div class="form-group">
                <label class="col-md-4 control-label" for="oldpassword">Old Password</label>
                <div class="col-md-8">
                  <input id="currentPassword" name="currentPassword" type="password" placeholder="Old Password" class="form-control input-md" required="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-4 control-label" for="newpassword">New Password</label>
                <div class="col-md-8">
                  <input id="password" name="userPassword" type="password" placeholder="New Password" class="form-control input-md" required="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-4 control-label" for="ConfirmPassword">Confirm Password</label>
                <div class="col-md-8">
                  <input id="password2" name="password2" type="password" placeholder="Confirm Password" class="form-control input-md" required="">
                </div>
              </div>
              
              <!-- Button -->
              <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                  <input type="button" class="btn btn-primary" onclick="return validateChangePasswordForm();" value="Save Password" />
                </div>
              </div>
            </s:form>
            </div>
          </div>
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
			<p>Your profile has been updated successfully.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closePopup();">Close</a>
		</div>
	</div>
	</div>
	<div id="changePasswordSuccess" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: green;"><i class="fa fa-edit"></i> Success</h4>
		</div>
		<div class="content">
			<p>Your new password has been changed successfully.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closePopup();">Close</a>
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