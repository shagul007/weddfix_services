<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<!-- <link rel="stylesheet" href="/css/lightbox.min.css"> -->

<script src="/js/jquery.swipebox.js"></script>
<link rel="stylesheet" href="/css/swipebox.css">

<style type="text/css">
.jssora03l, .jssora03r {
	top: 45px !important;
}
</style>
<script src="/js/jssor.slider-22.0.15.min.js" type="text/javascript" data-library="jssor.slider" data-version="22.0.15"></script>
    <script type="text/javascript">
        jssor_1_slider_init = function() {

            var jssor_1_options = {
              $AutoPlay: false,
              $AutoPlaySteps: 1,
              $SlideDuration: 160,
              $SlideWidth: 294,
              $SlideSpacing: 3,
              $Cols: 4,
              $Loop: 0, 
              $ArrowNavigatorOptions: {
                $Class: $JssorArrowNavigator$,
                $Steps: 1
              },
              $BulletNavigatorOptions: {
                $Class: $JssorBulletNavigator$,
                $SpacingX: 1,
                $SpacingY: 1
              }
            };

            var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_1_slider.$ScaleWidth(refSize);
                }
                else {
                    window.setTimeout(ScaleSlider, 30);
                }
            }
            ScaleSlider();
            $Jssor$.$AddEvent(window, "load", ScaleSlider);
            $Jssor$.$AddEvent(window, "resize", ScaleSlider);
            $Jssor$.$AddEvent(window, "orientationchange", ScaleSlider);
            /*responsive code end*/
        };
 </script>
 <style>
        /* jssor slider bullet navigator skin 03 css */
        /*
        .jssorb03 div           (normal)
        .jssorb03 div:hover     (normal mouseover)
        .jssorb03 .av           (active)
        .jssorb03 .av:hover     (active mouseover)
        .jssorb03 .dn           (mousedown)
        */
        .jssorb03 {
            position: absolute;
        }
        .jssorb03 div, .jssorb03 div:hover, .jssorb03 .av {
            position: absolute;
            /* size of bullet elment */
            width: 21px;
            height: 21px;
            text-align: center;
            line-height: 21px;
            color: black;
            font-size: 12px;
            background: url('img/b03.png') no-repeat;
            overflow: hidden;
            cursor: pointer;
        }
        .jssorb03 div { background-position: -5px -4px; }
        .jssorb03 div:hover, .jssorb03 .av:hover { background-position: -35px -4px; }
        .jssorb03 .av { background-position: -65px -4px; }
        .jssorb03 .dn, .jssorb03 .dn:hover { background-position: -95px -4px; }

        /* jssor slider arrow navigator skin 03 css */
        /*
        .jssora03l                  (normal)
        .jssora03r                  (normal)
        .jssora03l:hover            (normal mouseover)
        .jssora03r:hover            (normal mouseover)
        .jssora03l.jssora03ldn      (mousedown)
        .jssora03r.jssora03rdn      (mousedown)
        .jssora03l.jssora03ldn      (disabled)
        .jssora03r.jssora03rdn      (disabled)
        */
        .jssora03l, .jssora03r {
            display: block;
            position: absolute;
            /* size of arrow element */
            width: 55px;
            height: 55px;
            cursor: pointer;
            background: url('img/a03.png') no-repeat;
            overflow: hidden;
        }
        .jssora03l { background-position: -3px -33px; }
        .jssora03r { background-position: -63px -33px; }
        .jssora03l:hover { background-position: -123px -33px; }
        .jssora03r:hover { background-position: -183px -33px; }
        .jssora03l.jssora03ldn { background-position: -243px -33px; }
        .jssora03r.jssora03rdn { background-position: -303px -33px; }
        .jssora03l.jssora03lds { background-position: -3px -33px; opacity: .3; pointer-events: none; }
        .jssora03r.jssora03rds { background-position: -63px -33px; opacity: .3; pointer-events: none; }
    </style>
<style type="text/css">
@media only screen and (min-width:768px) and (max-width: 2500px) {
.review{float: left; margin-left: 450px; margin-right: 450px;}
#img-big{height: 30em;}
/* #img-small{height: 200} */
}
@media only screen and (min-width:0px) and (max-width: 768px) {
#img-big{height: 13em;}
/* #img-small{height: 200} */
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
	
	function validateForm() {
		validateRegisterFields([ 'fullName', 'registerEmail' ,'password',
					'password2', 'mobile', 'country_Id', 'stateId', 'cityId', 'pincode' ]);
	}
	
	function validateRegisterFields(elementIds) {
		var count = 0;
		var length = elementIds.length;
		var flag = false;
		$.each( elementIds, function( index, id ){
			var elementValue = document.forms["registerForm"][id].value;
			
			if (elementValue == null || elementValue == "" || elementValue == "-1" || id == 'email' || id == 'password' || id == 'password2' || id == 'mobile') {
				if(elementValue == null || elementValue == "" || elementValue == "-1"){
					$('#' + id).addClass( 'error' );
					if($("#error").val() == undefined){
						$('#error').append(
						'<span id="errorMessage" style="color: red; margin-bottom: 20px;">* Please fill all mandetory fields.</span><input type="hidden" id="errorValue" value="1">').html;
					}
				} else {
					count = count + 1;
					$('#' + id).removeClass('error');
				}
			
				if(id == 'registerEmail' && $("#emailMessageValue").val() == undefined && elementValue != null && elementValue != ""){
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
				} else if(id == 'registerEmail' && elementValue != null && elementValue != "") {
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
				if (elementValue != document.forms["registerForm"]["password"].value) {
					count = count - 1;
					$('#password2').after(
					'<span id="passwordMessage2" style="width: auto; display: block; color: red;">Both Password are not equal. Please re-enter password.</span><input type="hidden" id="passwordMessageValue2" value="1">').html;
					document.forms["registerForm"]["password"].value = "";
					document.forms["registerForm"][id].value = "";
				} else {
					$('#passwordMessage2').remove();
					$('#passwordMessageValue2').remove();
				}
				} 
				if(id == 'password2' && elementValue != null && elementValue != "") {
					if (elementValue != document.forms["registerForm"]["password"].value) {
						count = count - 1;
						 if($("#passwordMessageValue2").val() == undefined){
							$('#' +id).after(
							'<span id="passwordMessage2" style="width: auto; display: block; color: red;">Both Password are not equal. Please re-enter password.</span><input type="hidden" id="passwordMessageValue2" value="1">').html;
							document.forms["registerForm"]["password"].value = "";
							document.forms["registerForm"][id].value = "";
						 }
						 } else {
								$('#passwordMessage2').remove();
								$('#passwordMessageValue2').remove();
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
			checkUserAlreadyExist(document.forms["registerForm"]["email"].value);
		}
		}
	
	function checkUserAlreadyExist(email) {
		$.ajax({
			data : {
				emailId : email
			},
			url : "checkUserAlreadyExist.action",
			dataType : "json",
			success : function(data) {
				if (data.emailId != email) {
					$('#emailExistMessage').remove();
					submitRegister();
				} else {
					if($("#emailExistMessageValue").val() == undefined){
						$('#registerEmail').after(
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
	
	function submitRegister(){

		 $.ajax({
		     url:'registeredSuccess.action',
		     type: "POST",
	         async: false,
		     data: $("#registerForm").serialize(),
		     success: function (data) {
		    	 $("#registerSuccess").show();
		    },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
		 		alert("error");
			}
		});
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
	
	function validateSendNowForm() {
		validateSendNowFields([ 'name', 'sendUserEmail' , 'phone' ]);
	}
	
	function validateSendNowFields(elementIds) {
		var count = 0;
		var length = elementIds.length;
		var flag = false;
		$.each( elementIds, function( index, id ){
			var elementValue = document.forms["sendForm"][id].value;
			
			if (elementValue == null || elementValue == "" || elementValue == "-1" || id == 'sendUserEmail' || id == 'mobile') {
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
			
				if(id == 'sendUserEmail' && $("#emailMessageValue").val() == undefined && elementValue != null && elementValue != ""){
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
				} else if(id == 'sendUserEmail' && elementValue != null && elementValue != "") {
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
			sendNow();
		}
		}
	
	function sendNow(){

		 $.ajax({
		     url:'sendNowSuccess.action',
		     type: "POST",
	         async: false,
		     data: $("#sendForm").serialize(),
		     success: function (data) {
		    	 $("#sendSuccess").show();
		    },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
		 		alert("error");
			}
		});
		}
	
	function validateLoginForm() {
		validateLoginFields([ 'loginEmail', 'password1' ]);

	}
	
	function validateLoginFields(elementIds) {
		var count = 0;
		var length = elementIds.length;
		var flag = false;
		$.each( elementIds, function( index, id ){
			var elementValue = document.forms["loginForm"][id].value;
			
			if (elementValue == null || elementValue == "" || elementValue == "-1" || id == 'loginEmail') {
				if(elementValue == null || elementValue == "" || elementValue == "-1"){
					$('#' + id).addClass( 'error' );
					if($("#errorValue").val() == undefined){
						$('#errorLogin').append(
						'<span id="errorMessage" style="color: red; margin-bottom: 20px;">* Please fill all mandetory fields.</span><input type="hidden" id="errorValue" value="1">').html;
					}
				} else {
					count = count + 1;
					$('#' + id).removeClass('error');
				}
			
				if(id == 'loginEmail' && $("#emailMessageValue").val() == undefined && elementValue != null && elementValue != ""){
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
				} else if(id == 'loginEmail' && elementValue != null && elementValue != "") {
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
				$('#errorLogin').hide();
				flag = true;
			} 
		});
		
		if(flag){
//			alert("Success");
			loginNow();
		}
		}
	
	function loginNow(){

		 $.ajax({
		     url:'loginSuccess.action',
		     type: "POST",
	         async: false,
		     data: $("#loginForm").serialize(),
		     success: function (data) {
		    	 if(data.loginFormBean == null){
		    		 $("#loginError").show();
		    	 } else {
		    		 window.location.replace("vendor_details?vendor_name=<%=session.getAttribute("vendorName")%>");
		    	 }
		    },
		 	error : function(xhr, textStatus, errorThrown) {
				// Handle error
		 		$("#loginError").show();
			}
		});
		}
	
	
	function closePopup() {
		$("#reviewSuccess").hide();
		$("#registerSuccess").hide();
		$("#sendSuccess").hide();
		$("#loginError").hide();
		window.location.replace("vendor_details?vendor_name=<%=session.getAttribute("vendorName")%>");
	}
	
	function closeLoginPopup() {
		$("#loginError").hide();
	}
</script>
</head>
<body>
<div class="tp-breadcrumb">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <ol class="breadcrumb">
                        <li><a href="home">Home</a></li>
                        <li><a href="vendor_list">Venue Listing</a></li>
                        <li class="active">Venue Details</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
<div class="main-container">
<s:iterator value="vendorDetails" status="incr">
  <div class="container tabbed-page st-tabs">
    <div class="row tab-page-header">
      <div class="col-md-8 title"> <!-- <a href="#" class="label-primary">Boutique</a> -->
        <h1><s:property value="%{vendorDetails[#incr.index]['companyName']}" /></h1>
        <p class="location"><i class="fa fa-map-marker"></i><s:property value="%{vendorDetails[#incr.index]['address']}" />, <s:property value="%{vendorDetails[#incr.index]['location']}" />,
        <s:property value="%{vendorDetails[#incr.index]['cityName']}" />, <s:property value="%{vendorDetails[#incr.index]['pincode']}" />, <s:property value="%{vendorDetails[#incr.index]['countryName']}" />.</p>
        <hr>
         <c:if test="${vendorDetails[incr.index]['maxRating'] != null}">
            <div class="rating"> 
            <c:forEach var="i" begin="1" end="${vendorDetails[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${vendorDetails[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{vendorDetails[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
      </div>
      <div class="col-md-4 venue-data">
        <div class="venue-info"><!-- venue-info-->
          <%-- <div class="capacity">
            <div>Capacity:</div>
            <span class="cap-people"> 50 - 300 </span> </div> --%>
          <div class="pricebox">
            <div>Price starting from:</div>
            <span class="price">Rs. <s:property value="%{vendorDetails[#incr.index]['price']}" /></span></div>
        </div>
        <c:if test="${userId != null }">
        <a href="#inquiry" class="btn btn-default btn-lg btn-block">Book Now</a>
        </c:if>
        <c:if test="${userId == null }">
        <a href="#login" class="btn btn-default btn-lg btn-block">Login and Book</a>
        <a href="#register" class="btn btn-default btn-lg btn-block">Register and Book</a>
        </c:if>
         </div>
    </div>
    <div class="row">
      <div class="col-md-12"> 
       <%--  <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
          <li role="presentation" class="active"><a href="#photo" title="Gallery" aria-controls="photo" role="tab" data-toggle="tab"> <i class="fa fa-photo"></i> <span class="tab-title">Photo</span></a></li>
           <c:if test="${vendorDetails[incr.index]['description'] != null && vendorDetails[incr.index]['description'] != ''}">
          <li role="presentation">
          <a href="#about" title="about info" aria-controls="about" role="tab" data-toggle="tab">
          <i class="fa fa-info-circle"></i>
          <span class="tab-title">About</span>
          </a>
          </li>
          </c:if>
          <c:if test="${vendorDetails[incr.index]['latitude'] != null && vendorDetails[incr.index]['latitude'] != ''}">
          <li role="presentation"><a href="#onmap" title="Location" aria-controls="onmap" role="tab" data-toggle="tab"> <i class="fa fa-map-marker"></i> <span class="tab-title">On map</span></a></li>
		  </c:if>
		  <c:if test="${vendorDetails[incr.index]['categoryVideoUrl'] != null && vendorDetails[incr.index]['categoryVideoUrl'] != ''}">
          <li role="presentation"><a href="#video" title="Video" aria-controls="video" role="tab" data-toggle="tab"> <i class="fa fa-youtube-play"></i> <span class="tab-title">Video</span></a></li>
		  </c:if>
          <li role="presentation"><a href="#amenities" title="Amenities" aria-controls="amenities" role="tab" data-toggle="tab"> <i class="fa fa-asterisk"></i> <span class="tab-title">Amenities</span></a></li>
          <li role="presentation"><a href="#reviews" title="Review" aria-controls="reviews" role="tab" data-toggle="tab"> <i class="fa fa-commenting"></i> <span class="tab-title">Reviews</span></a></li>
        </ul> --%>
        
        <!-- Tab panes -->
<!--         <div class="tab-content">  tab content start -->
          <div role="tabpanel" class="tab-content" id="photo">
          <h2>Photo Gallery</h2>
            <div id="sync1" class="owl-carousel">
            <c:if test="${vendorDetails[incr.index]['fileName'] != null}">
           		<div class="item">
		        	<a class="swipebox" href="<s:url action="ImageAction?imageId=%{vendorDetails[#incr.index]['fileName']}" />">
		        	<img id="img-big" class="img-responsive" src="<s:url action="ImageAction?imageId=%{vendorDetails[#incr.index]['fileName']}" />" alt="<s:property value="%{vendorDetails[#incr.index]['fileName']}" />" />
		    	  	</a>
		    	  </div>
		    	  </c:if>
		         <c:if test="${vendorDetails[incr.index]['fileName'] == null}">
		         <div class="item">
					<img src="images/vendor-1.jpg" alt="" class="img-responsive">
					</div>
		         </c:if>
               <s:iterator value="moreCategoryPhotos" status="incr">
		         <c:if test="${moreCategoryPhotos[incr.index]['fileName'] != null}">
		         <div class="item">
		         <a class="swipebox" href="<s:url action="ImageAction?imageId=%{moreCategoryPhotos[#incr.index]['fileName']}" />">
		        	<img id="img-big" class="img-responsive" src="<s:url action="ImageAction?imageId=%{moreCategoryPhotos[#incr.index]['fileName']}" />" alt="<s:property value="%{moreCategoryPhotos[#incr.index]['fileName']}" />" />
		    	  </a>
		    	  </div>
		    	  </c:if>
		    	  </s:iterator>
            </div>
            <div id="sync2" class="owl-carousel">
           	 <c:if test="${vendorDetails[incr.index]['fileName'] != null}">
           	 	<div class="item">
		        	<img height="100" width="125" id="img-small" class="img-responsive" src="<s:url action="ImageAction?imageId=%{vendorDetails[#incr.index]['fileName']}" />" alt="<s:property value="%{vendorDetails[#incr.index]['fileName']}" />" />
		    	 </div>
		    	  </c:if>
		         <c:if test="${vendorDetails[incr.index]['fileName'] == null}">
					<div class="item">
					<img src="images/vendor-1.jpg" alt="" class="img-responsive">
		         	</div>
		         </c:if>
		         <s:iterator value="moreCategoryPhotos" status="incr">
		         <c:if test="${moreCategoryPhotos[incr.index]['fileName'] != null}">
		        	<div class="item">
		        	<img height="100" width="125" id="img-small" class="img-responsive" src="<s:url action="ImageAction?imageId=%{moreCategoryPhotos[#incr.index]['fileName']}" />" alt="<s:property value="%{moreCategoryPhotos[#incr.index]['fileName']}" />" />
		    	  	</div>
		    	  </c:if>
		    	  </s:iterator>
            </div>
          </div>
          <c:if test="${youtubeVideoUrls[incr.index]['categoryVideoUrl'] != null && youtubeVideoUrls[incr.index]['categoryVideoUrl'] != ''}">
          <div role="tabpanel" class="tab-content" id="video">
          <h2>Video Gallery</h2>
          <div class="row">
          <div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 150px; overflow: hidden; visibility: hidden;">
		  <div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 150px; overflow: hidden;">
          <s:iterator value="youtubeVideoUrls" status="incr">
          <div class="col-md-3">
                    <div class="vendor-list-block mb30"><!-- vendor list block -->
						  <a class="swipebox-video" rel="youtube" href="<s:property value="%{youtubeVideoUrls[#incr.index]['categoryVideoUrl']}" />">
						    <img class="img-responsive" src="<s:property value="%{youtubeVideoUrls[#incr.index]['categoryVideoImage']}" />"/>
						  </a>
                    </div>
                    <!-- /.vendor list block --> 
                  </div>
            </s:iterator>
            <span data-u="arrowleft" class="jssora03l" style="left:-2px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="right:-2px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_1_slider_init();
		</script>
		</div>
            </div>
          </div>
          </c:if>
          <c:if test="${vendorDetails[incr.index]['description'] != null && vendorDetails[incr.index]['description'] != ''}">
          <div role="tabpanel" class="tab-content" id="about">
            <div class="venue-details">
              <h2>About Us</h2>
              <p><s:property value="%{vendorDetails[#incr.index]['description']}" /></p>
             </div>
          </div>
          </c:if>
          <c:if test="${vendorDetails[incr.index]['latitude'] != null && vendorDetails[incr.index]['latitude'] != ''}">
          <div role="tabpanel" class="tab-content" id="onmap">
          	<h2>Map</h2>
            <div id="googleMap" class="map">
            <iframe src="<s:property value="%{vendorDetails[#incr.index]['latitude']}" />" width="100%" height="450" frameborder="0" style="border:0"></iframe>
            </div>
          </div>
          </c:if>
          <div role="tabpanel" class="tab-content" id="reviews"> 
            <!-- comments -->
            <div class="customer-review">
              <div class="row">
                <div class="col-md-12">
                  <h2>Users Review</h2>
                  <s:iterator value="reviewListBean" status="incr">
                  <div class="review-list" id="row<s:property value="#incr.index" />"> 
                    <!-- First Comment -->
                    <div class="row">
                      <div class="col-md-2 col-sm-2 hidden-xs">
                        <div class="user-pic"> 
                        <c:if test="${reviewListBean[incr.index]['fileName'] != null}">
				        	<img height="100" width="100" class="img-responsive img-circle" src="<s:url action="ImageAction?imageId=%{reviewListBean[#incr.index]['fileName']}" />" alt="<s:property value="%{reviewListBean[#incr.index]['fileName']}" />" />
				    	  </c:if>
				         <c:if test="${reviewListBean[incr.index]['fileName'] == null}">
		                        <img class="img-responsive img-circle" src="images/userpic.jpg" alt=""> 
				         </c:if>
                        </div>
                      </div>
                      <div class="col-md-10 col-sm-10">
                        <div class="panel panel-default arrow left">
                          <div class="panel-body">
                            <div class="text-left">
                              <h3><s:property value="%{reviewListBean[#incr.index]['reviewTitle']}" /></h3>
                              <div class="rating" style="margin: -13px 0px -12px;"> 
                              <c:forEach var="i" begin="1" end="${reviewListBean[incr.index]['maxRating']}">
					            <i class="fa fa-star"></i> 
							  </c:forEach>
							  <c:forEach var="i" begin="1" end="${reviewListBean[incr.index]['minRating']}">
				            	<i class="fa fa-star-o"></i> 
							  </c:forEach>
							  </div>
                            </div>
                            <div class="review-post">
                              <p> <s:property value="%{reviewListBean[#incr.index]['review']}" /> </p>
                            </div>
                            <div class="review-user">By <a href="mailto:<s:property value="%{reviewListBean[#incr.index]['email']}" />"><s:property value="%{reviewListBean[#incr.index]['name']}" /></a>, on <span class="review-date"></span><s:property value="%{reviewListBean[#incr.index]['createdDate']}" /></div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                    </s:iterator>
                    <c:if test="${reviewListBean[0]['review'] != null}">
                      <div class="col-md-12 tp-pagination" id="pageNavPosition"></div>
                      </c:if>
                  <%-- <div class="review"> <a class="btn tp-btn-primary btn-block tp-btn-lg" role="button" data-toggle="collapse" href="#review" aria-expanded="false" aria-controls="review"> Write A Review </a> </div>
                  <div class="collapse review-list review-form" id="review">
                    <div class="panel panel-default">
                      <div class="panel-body">
                        <h1>Write Your Review</h1>
                        <s:form name="reviewForm" id="reviewForm" method="post">
                         <div id="error"></div>
                        <s:hidden id="vendorId" name="vendorId" value="%{vendorDetails[#incr.index]['id']}" />
                        <s:hidden id="rating" name="rating" value="1"/>
                         <s:hidden id="maxRating" name="maxRating" value="%{vendorDetails[#incr.index]['maxRating']}"/>
                          <s:hidden id="maxUsersRating" name="maxUsersRating" value="%{vendorDetails[#incr.index]['maxUsersRating']}"/>
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
                            <label class=" control-label" for="name">Name</label>
                            <div class="">
                              <input id="name" name="name" type="text" maxlength="100" onkeyup="checkName(this);" placeholder="Name" class="form-control input-md" required>
                            </div>
                          </div>
                          
                          <!-- Text input-->
                          <div class="form-group">
                            <label class=" control-label" for="email">E-Mail</label>
                            <div class=" ">
                              <input id="email" name="email" type="text" maxlength="100" placeholder="E-Mail" class="form-control input-md" required>
                            </div>
                          </div>
                          
                          <!-- Text input-->
                          <div class="form-group">
                            <label class=" control-label" for="reviewtitle">Review Title</label>
                            <div class=" ">
                              <input id="reviewTitle" name="reviewTitle" type="text" maxlength="1000" placeholder="Review Title" class="form-control input-md" required>
                            </div>
                          </div>
                          
                          <!-- Textarea -->
                          <div class="form-group">
                            <label class=" control-label">Write Review</label>
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
                  </div> --%>
                </div>
              </div>
            </div>
          </div>
<!--         </div> -->
<!--         /.tab content start  -->
      </div>
    </div>
    <div class="row">
    <c:if test="${userId == null }">
    <div class="col-md-8" >
        <div class="side-box" id="register">
          <h2>Register</h2>
          <p>Fill your details and Book the venue.</p>
          <form id="registerForm" name="registerForm" method="post">
            
            <!-- Form Name -->
                <!-- Text input-->
                <div id="error"></div>
				<s:hidden id="validateUser" value="1" />
				<s:hidden name="countryId" id="countryId" />
				
                <div class="form-group">
                  <label class="control-label" for="name">Full Name<span class="required">*</span></label>
                  <div class="">
                    <input id="fullName" name="fullName" type="text" maxlength="100" onkeyup="checkName(this);" placeholder="Name" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="name">Email<span class="required">*</span></label>
                  <div class="">
                    <input id="registerEmail" name="email" type="text" maxlength="100" placeholder="Email" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="name">Password<span class="required">*</span></label>
                  <div class="">
                    <input id="password" name="password" type="password" maxlength="20" placeholder="Password" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="name">Re-type Password<span class="required">*</span></label>
                  <div class="">
                    <input id="password2" name="password2" type="password" maxlength="20" placeholder="Re-type Password" class="form-control input-md">
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="name">Mobile<span class="required">*</span></label>
                  <div class="">
                    <input id="mobile" name="mobile" type="text" maxlength="10" onkeyup="checkNumeric(this);" placeholder="Mobile" class="form-control input-md">
                  </div>
                </div>
                <!-- Select Basic -->
             <div class="default-calender">
                <div class="form-group">
                  <label class="control-label" for="weddingdate">Wedding Date</label>
                  <div class="">
                    <div class="input-group">
                      <input type="text" class="form-control futureCalendar" id="weddingDate" name="wdStr" placeholder="Wedding Date">
                      <span class="input-group-addon" id="basic-addon2"><i class="fa fa-calendar"></i></span> </div>
                  </div>
                </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="name">Country<span class="required">*</span></label>
                  <div class="">
                    <select class="form-control" id="country_Id" name="country_Id" onchange="setCountryId(); loadStateByCountryId(this);" onkeyup="setCountryId()">
						<option value="-1">--- SELECT ---</option>
					<s:iterator value="countryMap">
						<option value="<s:property value="key" />"><s:property value="value" /></option>
					</s:iterator>
					</select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="name">State<span class="required">*</span></label>
                  <div class="">
                    <select class="form-control" id="stateId" name="stateId" onchange="loadCityByStateId(this)">
						<option value="-1">--- SELECT ---</option>
					</select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="name">City<span class="required">*</span></label>
                  <div class="">
                    <select class="form-control" id="cityId" name="cityId">
						<option value="-1">--- SELECT ---</option>
					</select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="name">Pincode<span class="required">*</span></label>
                  <div class="">
                    <input id="pincode" name="pincode" type="text" maxlength="6" onkeyup="checkNumeric(this);" placeholder="Pincode" class="form-control input-md">
                  </div>
                </div>
                <!-- Button -->
                <div class="form-group">
                  <label class="control-label" for="submit"></label>
                  <div class="">
                    <input type="button" class="btn btn-primary" onclick="return validateForm();" value="Register"/>
                  </div>
                </div>
          </form>
        </div>
      </div>
      </c:if>
      <c:if test="${userId != null }">
      <div class="col-md-8" >
        <div class="side-box" id="inquiry">
          <h2>Send Enquiry to Vendor</h2>
          <p>Fill your details and a Venue Specialist will get back to you shortly.</p>
          <s:form id="sendForm" name="sendForm">
          <s:hidden id="vendorId" name="vendorId" value="%{vendorDetails[#incr.index]['id']}" />
          <s:hidden id="vendorContactName" name="vendorContactName" value="%{vendorDetails[#incr.index]['contactName']}" />
          <s:hidden id="vendorMobile" name="vendorMobile" value="%{vendorDetails[#incr.index]['mobile']}" />
          <s:hidden id="vendorPhone" name="vendorPhone" value="%{vendorDetails[#incr.index]['phone']}" />
          <s:hidden id="vendorEmail" name="vendorEmail" value="%{vendorDetails[#incr.index]['email']}" />
          <s:hidden id="vendorCompanyName" name="vendorCompanyName" value="%{vendorDetails[#incr.index]['companyName']}" />
          <s:hidden id="vendorWebsiteUrl" name="vendorWebsiteUrl" value="%{vendorDetails[#incr.index]['userWebsiteUrl']}" />
            <s:iterator value="personalDetailsBean" status="incr">
            <!-- Text input-->
            <div class="form-group">
              <label class="control-label" for="name-one">Name:<span class="required">*</span></label>
              <div class="">
                <input id="name" name="name" type="text" maxlength="100" onkeyup="checkName(this);" placeholder="Name" class="form-control input-md" value="<s:property value="%{personalDetailsBean[#incr.index]['fullName']}" />">
              </div>
            </div>
            
            <!-- Text input-->
            <div class="form-group">
              <label class="control-label" for="phone">Phone:<span class="required">*</span></label>
              <div class="">
                <input id="phone" name="mobile" type="text" maxlength="10" onkeyup="checkNumeric(this);" placeholder="Phone" class="form-control input-md" value="<s:property value="%{personalDetailsBean[#incr.index]['mobile']}" />">
                <span class="help-block"> </span> </div>
            </div>
            
            <!-- Text input-->
            <div class="form-group">
              <label class="control-label" for="email-one">E-Mail:<span class="required">*</span></label>
              <div class="">
                <input id="sendUserEmail" name="email" type="text" placeholder="E-Mail" class="form-control input-md" value="<s:property value="%{personalDetailsBean[#incr.index]['email']}" />">
              </div>
            </div>
            
            <!-- Select Basic -->
             <div class="default-calender">
                <div class="form-group">
                  <label class="control-label" for="weddingdate">Wedding Date</label>
                  <div class="">
                    <div class="input-group">
                      <input type="text" class="form-control futureCalendar" id="weddingDate" name="wdStr" placeholder="Wedding Date" value="<s:property value="%{personalDetailsBean[#incr.index]['weddingDate']}" />">
                      <span class="input-group-addon" id="basic-addon2"><i class="fa fa-calendar"></i></span> </div>
                  </div>
                </div>
                </div>
            
            
            <%-- <div class="form-group">
              <label class="control-label" for="guest">Number of Guests:<span class="required">*</span></label>
              <div class="">
                <select id="guest" name="guest" class="form-control">
                  <option value="35 to 50">35 to 50</option>
                  <option value="50  to 65">50  to 65</option>
                  <option value="65 to 85">65 to 85</option>
                  <option value="85 to 105">85 to 105</option>
                </select>
              </div>
            </div> --%>
            <!-- Multiple Radios -->
            <div class="form-group">
              <label class="control-label">Send me info via</label>
              <div class="checkbox checkbox-success">
                <input type="checkbox" name="sendEmail" id="checkbox-0" class="styled" value="true">
                <label for="checkbox-0" class="control-label"> E-Mail </label>
              </div>
              <div class="checkbox checkbox-success">
                <input type="checkbox" name="needCallBack" id="checkbox-1" class="styled" value="true">
                <label for="checkbox-1" class="control-label"> Need Call back </label>
              </div>
            </div>
            </s:iterator>
            <div class="form-group">
              <input type="button" class="btn btn-primary btn-lg btn-block" onclick="validateSendNowForm();" value="Book Now" />
            </div>
          </s:form>
        </div>
      </div>
      </c:if>
      <c:if test="${userId != null }">
      <div class="col-md-4">
        <div class="profile-sidebar side-box"> 
          <!-- SIDEBAR USERPIC -->
          <div class="profile-userpic"> 
          <c:if test="${vendorDetails[incr.index]['userFileName'] != null}">
		        	<img height="135" width="135" src="<s:url action="ImageAction?imageId=%{vendorDetails[#incr.index]['userFileName']}" />" alt="<s:property value="%{vendorDetails[#incr.index]['userFileName']}" />" />
		    	  </c:if>
		         <c:if test="${vendorDetails[incr.index]['fileName'] == null}">
					<img src="images/profile_user.jpg" class="img-responsive img-circle" alt=""> 
		         </c:if>
          </div>
          <div class="profile-usertitle">
            <div class="profile-usertitle-name">
              <h2><s:property value="%{vendorDetails[#incr.index]['userFullName']}" /></h2>
            </div>
            <div class="profile-address"> <i class="fa fa-map-marker"></i> <s:property value="%{vendorDetails[#incr.index]['userCityName']}" />
            , <s:property value="%{vendorDetails[#incr.index]['userStateName']}" />, <s:property value="%{vendorDetails[#incr.index]['userPincode']}" />
            , <s:property value="%{vendorDetails[#incr.index]['userCountryName']}" /> </div>
             <c:if test="${vendorDetails[incr.index]['userWebsiteUrl'] != null && vendorDetails[incr.index]['userWebsiteUrl'] != ''}">
            <div class="profile-website"> <i class="fa fa-link"></i> <a target="_blank" href="<s:property value="%{vendorDetails[#incr.index]['userWebsiteUrl']}" />"><s:property value="%{vendorDetails[#incr.index]['userWebsiteUrl']}" /></a> </div>
          	</c:if>
          </div>
        </div>
      </div>
      </c:if>
      <c:if test="${userId == null }">
      <div class="col-md-4">
        <div class="profile-sidebar side-box" id="login"> 
        <h2>Login</h2>
          <s:form name="loginForm"
				id="loginForm" method="post">
						<div id="errorLogin"></div>
						<div class="form-group">
							<label class="control-label"> Email:<span class="required">*</span></label>
							<div class="">
							<input id="loginEmail" name="email"
								type="text" maxlength="100" placeholder="Email" class="form-control input-md"/>
								</div>
						</div>
						<div class="form-group">
							<label class="control-label"> Password:<span class="required">*</span></label>
							<div class=""> <input id="password1"
								name="password" type="password" maxlength="20" placeholder="Password" class="form-control input-md"/>
								</div>
						</div>
						<div class="form-group">
							<input type="button" class="btn btn-primary btn-lg btn-block" onclick="return validateLoginForm();" value="Login"/>
						</div>
						<div class="form-group">
						<a href="forgot_password">Forgot password ?</a> 
						</div>
			</s:form>
        </div>
      </div>
      </c:if>
      <c:if test="${vendorDetails[incr.index]['userFacebookUrl'] != null || vendorDetails[incr.index]['userTwitterUrl'] != null || vendorDetails[incr.index]['userInstagramUrl'] != null
      || vendorDetails[incr.index]['userLinkedinUrl'] != null || vendorDetails[incr.index]['userPinterestUrl'] != null}">
      <div class="col-md-4">
        <div class="social-sidebar side-box">
          <ul class="listnone follow-icon">
          <c:if test="${vendorDetails[incr.index]['userFacebookUrl'] != null}">
            <li><a href="<s:property value="%{vendorDetails[#incr.index]['userFacebookUrl']}" />"><i class="fa fa-facebook-square"></i></a></li>
            </c:if>
            <c:if test="${vendorDetails[incr.index]['userTwitterUrl'] != null}">
            <li><a href="<s:property value="%{vendorDetails[#incr.index]['userTwitterUrl']}" />"><i class="fa fa-twitter-square"></i></a></li>
            </c:if>
            <c:if test="${vendorDetails[incr.index]['userInstagramUrl'] != null}">
            <li><a href="<s:property value="%{vendorDetails[#incr.index]['userInstagramUrl']}" />"><i class="fa fa-instagram"></i></a></li>
            </c:if>
            <c:if test="${vendorDetails[incr.index]['userLinkedinUrl'] != null}">
            <li><a href="<s:property value="%{vendorDetails[#incr.index]['userLinkedinUrl']}" />"><i class="fa fa-linkedin-square"></i></a></li>
            </c:if>
            <c:if test="${vendorDetails[incr.index]['userPinterestUrl'] != null}">
            <li><a href="<s:property value="%{vendorDetails[#incr.index]['userPinterestUrl']}" />"><i class="fa fa-pinterest-square"></i></a></li>
            </c:if>
          </ul>
        </div>
      </div>
      </c:if>
    </div>
  </div>
  </s:iterator>
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
	<div id="registerSuccess" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: green;"><i class="fa fa-edit"></i> Success</h4>
		</div>
		<div class="content">
			<p>Registered successfully.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closePopup();">Close</a>
		</div>
	</div>
	</div>
	<div id="sendSuccess" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: green;"><i class="fa fa-edit"></i> Thanks for your interest</h4>
		</div>
		<div class="content">
			<p>We have sent vendor information to your emailid and mobile number. Our vendor will contact you soon.</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closePopup();">Close</a>
		</div>
	</div>
	</div>
	<div id="loginError" class="overlay" style="opacity: 1; visibility: visible; display: none;">
	<div class="popup">
		<div class="modal-header">
			<h4 class="modal-title" style="color: red;"><i class="fa fa-edit"></i> Error</h4>
		</div>
		<div class="content">
			<p>Invalid Username/Password. Please try again...</p>
		</div>
		<div class="alerty-action">
			<a class="btn-ok" id="btnUpload" onclick="closeLoginPopup();">Close</a>
		</div>
	</div>
	</div>
    <%-- <script>
    $(function() {
        $("#weddingDate").datepicker();
    });
    </script> --%>
    <!-- 	//don't delete below table -->
<!--     This table for pagination -->
		<table id="results" style="display: none;">
        <tr>
        </tr>
        <s:iterator value="reviewListBean">
        <tr>
        </tr>
        </s:iterator>
        </table>
       <!--  <div id="loadingPopup" class="overlay" style="opacity: 1; visibility: visible; display: green;">
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
<%-- <script src="/js/lightbox.min.js"></script> --%>
<script type="text/javascript">
;( function( $ ) {

	$( '.swipebox' ).swipebox({
		useCSS : true, // false will force the use of jQuery for animations
		initialIndexOnArray: 0, // which image index to init when a array is passed
		removeBarsOnMobile : false, // false will show top navigation bar on mobile devices
		hideCloseButtonOnMobile : false, // true will hide the close button on mobile devices
		removeBarsOnMobile : false, // false will show bottom bar on mobile devices
		hideBarsDelay : 3000, // delay before hiding bars on desktop
		videoMaxWidth : 1140, // videos max width
		beforeOpen: function(){} , // called before opening
		afterOpen: null, // called after opening
		afterClose: function(){}, // called after closing
		afterMedia: function(){}, // called after media is loaded
		loopAtEnd: false, // true will return to the first image after the last image is reached
		autoplayVideos: true, // true will autoplay Youtube and Vimeo videos
		queryStringData: {}, // plain object with custom query string arguments to pass/override for video URLs,
		toggleClassOnLoad: '', // CSS class that can be toggled when the slide will be loaded (like 'hidden' of Bootstrap)
		useSVG: true
	});

} )( jQuery );
</script>
<script type="text/javascript">
;( function( $ ) {

	$( '.swipebox-video' ).swipebox( {
		useCSS : true, // false will force the use of jQuery for animations
		initialIndexOnArray: 0, // which image index to init when a array is passed
		removeBarsOnMobile : false, // false will show top navigation bar on mobile devices
		hideCloseButtonOnMobile : false, // true will hide the close button on mobile devices
		removeBarsOnMobile : false, // false will show bottom bar on mobile devices
		hideBarsDelay : 3000, // delay before hiding bars on desktop
		videoMaxWidth : 1140, // videos max width
		beforeOpen: function(){} , // called before opening
		afterOpen: null, // called after opening
		afterClose: function(){}, // called after closing
		afterMedia: function(){}, // called after media is loaded
		loopAtEnd: false, // true will return to the first image after the last image is reached
		autoplayVideos: true, // true will autoplay Youtube and Vimeo videos
		queryStringData: {}, // plain object with custom query string arguments to pass/override for video URLs,
		toggleClassOnLoad: '', // CSS class that can be toggled when the slide will be loaded (like 'hidden' of Bootstrap)
		useSVG: true
	} );

} )( jQuery );
</script>
</html>