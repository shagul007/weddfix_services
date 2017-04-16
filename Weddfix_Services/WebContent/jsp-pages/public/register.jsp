<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script type="text/javascript">
	function validateForm() {
		validate([ 'fullName', 'email' ,'password',
					'password2', 'mobile', 'country_Id', 'stateId', 'cityId', 'pincode' ]);
	}
	
	$(document).ready(function() {
		$("#country_Id option[value=98]").attr('selected', 'selected');
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
	
</script>
</head>

<body>
<div class="main-container">
  <div class="container">
    <div class="row">
      <div class="col-md-12 profile-dashboard">
        <div class="row">
          <div class="col-md-12 dashboard-form calender">
            <s:form cssClass="form-horizontal" action="registered_successfully" name="form" id="form" method="post">
              <div class="bg-white pinside40 mb30"> 
                <!-- Form Name -->
                <!-- Text input-->
                <div id="error"></div>
				<s:hidden id="validateUser" value="1" />
				<s:hidden name="countryId" id="countryId" />
                <h2 class="form-title">Weddfix Register</h2>
                <div class="form-group">
                  <label class="col-md-2 control-label" for="name">Full Name<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="fullName" name="fullName" type="text" maxlength="100" onkeyup="checkName(this);" placeholder="Name" class="form-control input-md">
                  </div>
                  <label class="col-md-2 control-label" for="name">Email<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="email" name="email" type="text" maxlength="100" placeholder="Email" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-2 control-label" for="name">Password<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="password" name="password" type="password" maxlength="20" placeholder="Password" class="form-control input-md">
                  </div>
                  <label class="col-md-2 control-label" for="name">Re-type Password<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="password2" name="password2" type="password" maxlength="20" placeholder="Re-type Password" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-2 control-label" for="name">Mobile<span class="required">*</span></label>
                  <div class="col-md-4">
                    <input id="mobile" name="mobile" type="text" maxlength="10" onkeyup="checkNumeric(this);" placeholder="Mobile" class="form-control input-md">
                  </div>
                  <label class="col-md-2  control-label" for="weddingdate">Wedding Date</label>
                  <div class="col-md-4">
                    <div class="input-group">
                      <input type="text" class="form-control futureCalendar" id="weddingDate" name="wdStr" placeholder="Wedding Date">
                      <span class="input-group-addon" id="basic-addon2"><i class="fa fa-calendar"></i></span> </div>
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
                <!-- Button -->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="submit"></label>
                  <div class="col-md-4">
                    <input type="button" class="btn btn-primary" onclick="return validateForm();" value="Register"/>
                  </div>
                </div>
              </div>
            </s:form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
