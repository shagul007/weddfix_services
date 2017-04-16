<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%if(request.getParameter("request") != null) {
	if(session.getAttribute("sendInfoId") == null) {
		session.setAttribute("sendInfoId", request.getParameter("request"));
	}
}%>
<script type="text/javascript">
	var sendInfoId = '<%=session.getAttribute("sendInfoId") %>';
	if(sendInfoId == 'null') {
		window.location.href = "home";
	} 
	
	function setRating(rating) {
		 for (var i = 1; i <= 5; i++) {
			 if(i == rating) {
				 $("#rating"+i).prop('checked', true);
			 } else {
			 	$("#rating"+i).prop('checked', false);
			 }
				 
		 }
		$("#rating").val(rating);
	}
	
	function validateReviewForm() {
		validateReviewFields([ 'rating', 'name', 'email', 'reviewTitle', 'reviewTextArea' ]);
	}
	
	function validateReviewFields(elementIds) {
		var count = 0;
		var length = elementIds.length;
		var flag = false;
		$.each( elementIds, function( index, id ){
			var elementValue = document.forms["reviewForm"][id].value;
			
			if (elementValue == null || elementValue == "" || elementValue == "-1" || id == 'email') {
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
			submitReview();
		}
		}
	
	function submitReview(){

		 $.ajax({
		     url:'reviewSuccess.action',
		     type: "POST",
	         async: false,
		     data: $("#reviewForm").serialize(),
		     success: function (data) {
		    	 $("#reviewSuccess").show();
		    },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
		 		alert("error");
			}
		});
		}
	
	function closePopup() {
		$("#reviewSuccess").hide();
		window.location.replace("home");
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
		<h1>Feedback requested from</h1>
		</div>
	</div>
     <div class="row" style="margin-bottom: 35px;">
      <div class="col-md-12 my-listing-dashboard">
        
        <div class="table-head">
          <div class="row">
            <div class="col-md-2"><span class="th-title">Image</span></div>
            <div class="col-md-3"><span class="th-title">Title</span></div>
            <div class="col-md-3"><span class="th-title">Price</span></div>
            <div class="col-md-3"><span class="th-title">Feedback Status</span></div>
          </div>
        </div>
        <div class="listing-row"><!-- listing row -->
          <div class="row">
            <div class="col-md-2 listing-thumb">
            <c:if test="${categoryInfoBean['fileName'] != null }">
            	<img height="77" width="554" src="<s:url action="ImageAction?imageId=%{categoryInfoBean['fileName']}" />" alt="<s:property value="%{categoryInfoBean['fileName']}" />" class="img-responsive">
			</c:if>
			<c:if test="${categoryInfoBean['fileName'] == null }">
				<img src="images/vendor-new-1.jpg" alt="" class="img-responsive">
			</c:if>
            </div>
            <div class="col-md-3 listing-title"><h2><s:property value="%{categoryInfoBean['companyName']}" /></h2> </div>
            <div class="col-md-3 listing-price">Rs.<s:property value="%{categoryInfoBean['price']}" /></div>
            <div class="col-md-3 listing-action"><a href="#" class="btn btn-primary  btn-xs">
            <%if(session.getAttribute("feedbackStatus") == null){ %>
            Feedback Requested
            <%} else { %>
            Feedback Request Completed
            <%} %>
            </a></div>
          </div>
        </div><!-- listing row -->
    </div>
  </div>
  <%if(session.getAttribute("feedbackStatus") == null){ %>
<div class="row">
    <div class="col-md-12">
                  <div class="review-list review-form" id="review">
                    <div class="panel panel-default">
                      <div class="panel-body">
                        <h1>Write Your Review</h1>
                        <s:form name="reviewForm" id="reviewForm" method="post">
                         <div id="error"></div>
                        <input type="hidden" id="vendorId" name="vendorId" value="<%=session.getAttribute("vendorId") %>" />
                        <input type="hidden" id="rating" name="rating" value="5"/>
                         <input type="hidden" id="maxRating" name="maxRating" value="<%=session.getAttribute("maxRating") %>"/>
                          <input type="hidden" id="maxUsersRating" name="maxUsersRating" value="<%=session.getAttribute("maxUsersRating") %>"/>
                          <div class="rating-group">
                            <div class="radio radio-success radio-inline">
                              <input type="radio" id="rating1" value="1" onclick="setRating(1)">
                              <label for="radio1" class="radio-inline"> <span class="rating"><i class="fa fa-star"></i></span> </label>
                            </div>
                            <div class="radio radio-success radio-inline">
                              <input type="radio" id="rating2" value="2" onclick="setRating(2)">
                              <label for="radio2" class="radio-inline"> <span class="rating"><i class="fa fa-star"></i><i class="fa fa-star"></i></span> </label>
                            </div>
                            <div class="radio radio-success radio-inline">
                              <input type="radio" id="rating3" value="3" onclick="setRating(3)">
                              <label for="radio3" class="radio-inline"> <span class="rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span> </label>
                            </div>
                            <div class="radio radio-success radio-inline">
                              <input type="radio" id="rating4" value="4" onclick="setRating(4)">
                              <label for="radio4" class="radio-inline"> <span class="rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span> </label>
                            </div>
                            <div class="radio radio-success radio-inline">
                              <input type="radio" id="rating5" value="5" checked="checked" onclick="setRating(5)">
                              <label for="radio5" class="radio-inline"> <span class="rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span> </label>
                            </div>
                          </div>
                          
                          <!-- Text input-->
                          <div class="form-group">
                            <label class=" control-label" for="name">Name<span class="required">*</span></label>
                            <div class="">
                              <input id="name" name="name" type="text" maxlength="100" onkeyup="checkName(this);" placeholder="Name" class="form-control input-md" required>
                            </div>
                          </div>
                          
                          <!-- Text input-->
                          <div class="form-group">
                            <label class=" control-label" for="email">E-Mail<span class="required">*</span></label>
                            <div class=" ">
                              <input id="email" name="email" type="text" maxlength="100" placeholder="E-Mail" class="form-control input-md" required>
                            </div>
                          </div>
                          
                          <!-- Text input-->
                          <div class="form-group">
                            <label class=" control-label" for="reviewtitle">Review Title<span class="required">*</span></label>
                            <div class=" ">
                              <input id="reviewTitle" name="reviewTitle" type="text" maxlength="1000" placeholder="Review Title" class="form-control input-md" required>
                            </div>
                          </div>
                          
                          <!-- Textarea -->
                          <div class="form-group">
                            <label class=" control-label">Write Review<span class="required">*</span></label>
                            <div class="">
                              <textarea id="reviewTextArea" name="review" class="form-control" rows="8"></textarea>
                            </div>
                          </div>
                          <!-- Button -->
                          <div class="form-group">
                            <input type="button" class="btn btn-primary" onclick="validateReviewForm()" value="Submit Review" />
                          </div>
                        </s:form>
                      </div>
                    </div>
                  </div>
    </div>
</div>
<%} else { %>
<div class="review"> <a class="btn tp-btn-primary btn-block tp-btn-lg">You have already submitted your feedback</a></div>
<%} %>
</div>
</div>
</div>
</div>
<div id="reviewSuccess" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: green;"><i class="fa fa-edit"></i> Success</h4>
		</div>
		<div class="content">
			<p>Thanks for your review.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closePopup();">Close</a>
		</div>
	</div>
	</div>
  </body>
</html>