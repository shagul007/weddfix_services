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
	function validateForm() {
		validateUpdateProfileFields([ 'fullName', 'email', 'password',
		          					'password2', 'mobile', 'country_Id', 'stateId', 'cityId', 'pincode' ]);
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
						'<span id="errorMessage" style="color: red; margin-bottom: 20px;">* Please fill mandetory fields.</span><input type="hidden" id="errorValue" value="1">').html;
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
			checkVendorAlreadyExist(document.forms["fileForm"]["email"].value);
		}
		}
	
	function checkVendorAlreadyExist(email) {
		$.ajax({
			data : {
				emailId : email
			},
			url : "checkVendorAlreadyExist.action",
			dataType : "json",
			success : function(data) {
				if (data.emailId != email) {
					$('#emailExistMessage').remove();
					document.getElementById("fileForm").submit();
				} else {
					if($("#emailExistMessageValue").val() == undefined){
						$('#email').after(
						'<span id="emailExistMessage" style="width: auto; display: block; color: red;">This email already exist.</span><input type="hidden" id="emailExistMessageValue" value="1">').html;
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
	
	$(document).ready(function() {
		$("#country_Id").val('98');
		$("#countryId").val($("#country_Id").val());
		onLoadStateByCountryId();
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
	
	function setCountryId() {
		$("#countryId").val($("#country_Id").val());
	}
	
	function closePopup() {
		$("#success").hide();
		$("#changePasswordSuccess").hide();
		window.location.replace("vendor_profile");
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
            <h1>Vendor Registration</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-12 profile-dashboard">
        <div class="row">
          <div class="col-md-12 dashboard-form">
          <div class="bg-white pinside40 mb30">
            <s:form cssClass="form-horizontal" action="vendor_registered_successfully" name="fileForm" id="fileForm" method="post">
			  <div id="error"></div>
				<s:hidden id="validateUser" value="1" />
				<s:hidden name="countryId" id="countryId" />
              <div class="form-group">
                  <label class="col-md-2 control-label" for="name">Vendor Name<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="fullName" name="fullName" type="text" maxlength="50" onkeyup="checkName(this);" placeholder="Name" class="form-control input-md">
                  </div>
                <label class="col-md-2 control-label" for="name">Vendor Website </label>
                <div class="col-md-4">
                  <input id="websiteUrl" name="websiteUrl" type="text" maxlength="250" placeholder="Vendor Website" class="form-control input-md" required="">
                </div>
               </div>
                <div class="form-group">
                  <label class="col-md-2 control-label" for="name">Email<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="email" name="email" type="text" maxlength="100" placeholder="Email" class="form-control input-md">
                  </div>
                  <label class="col-md-2 control-label" for="name">Password<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="password" name="password" type="password" maxlength="20" placeholder="Password" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-2 control-label" for="name">Re-type Password<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="password2" name="password2" type="password" maxlength="20" placeholder="Re-type Password" class="form-control input-md">
                  </div>
                  <label class="col-md-2 control-label" for="name">Mobile<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="mobile" name="mobile" type="text" maxlength="10" onkeyup="checkNumeric(this);" placeholder="Mobile" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-2 control-label" for="name">Country<span class="required">*</span></label>
                  <div class="col-md-4">
                    <select class="form-control" id="country_Id" name="country_Id" onchange="setCountryId(); loadStateByCountryId(this);" onkeyup="setCountryId()">
						<option value="-1">--- SELECT ---</option>
					<s:iterator value="countryMap">
						<option value="<s:property value="key" />"><s:property value="value" /></option>
					</s:iterator>
					</select>
                  </div>
                  <label class="col-md-2 control-label" for="name">State<span class="required">*</span></label>
                  <div class="col-md-4">
                    <select class="form-control" id="stateId" name="stateId" onchange="loadCityByStateId(this)">
						<option value="-1">--- SELECT ---</option>
					</select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-2 control-label" for="name">City<span class="required">*</span></label>
                  <div class="col-md-4">
                    <select class="form-control" id="cityId" name="cityId">
						<option value="-1">--- SELECT ---</option>
					</select>
                  </div>
                  <label class="col-md-2 control-label" for="name">Pincode<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="pincode" name="pincode" type="text" maxlength="6" onkeyup="checkNumeric(this);" placeholder="Pincode" class="form-control input-md">
                  </div>
                </div>
              <!-- Textarea -->
              <!-- <div class="form-group">
                <label class="col-md-2 control-label" for="description">Description</label>
                <div class="col-md-4">
                  <textarea class="form-control" id="description" name="Description" rows="6" cols="12" maxlength="2000"></textarea>
                </div>
              </div>
              <h2 class="form-title">Social Media Profile</h2>
              <div class="form-group">
                <label class="col-md-2 control-label" for="name">Facebook Url</label>
                <div class="col-md-4">
                  <input id="name" name="name" type="text" maxlength="250" placeholder="Facebook Url" class="form-control input-md" required="">
                </div>
                <label class="col-md-2 control-label" for="name">Twitter Url</label>
                <div class="col-md-4">
                  <input id="name" name="name" type="text" maxlength="250" placeholder="Twitter Url" class="form-control input-md" required="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="name">Linkedin Url</label>
                <div class="col-md-4">
                  <input id="name" name="name" type="text" maxlength="250" placeholder="Linkedin Url" class="form-control input-md" required="">
                </div>
                <label class="col-md-2 control-label" for="name">Instagram Url</label>
                <div class="col-md-4">
                  <input id="name" name="name" type="text" maxlength="250" placeholder="Instagram Url" class="form-control input-md" required="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-2 control-label" for="name">Pinterest Url</label>
                <div class="col-md-4">
                  <input id="name" name="name" type="text" maxlength="250" placeholder="Pinterest Url" class="form-control input-md" required="">
                </div>
              </div> -->
              <!-- Button -->
              <div class="form-group" style="margin-top: 50px;">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                	<input type="button" class="btn btn-primary" onclick="return validateForm();" value="Register"/>
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
</body>
</html>