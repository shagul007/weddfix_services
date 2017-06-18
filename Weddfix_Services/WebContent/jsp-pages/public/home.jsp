<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.weddfix.web.util.CommonConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%session.setAttribute("headerSearchCategoryId", null);
   session.setAttribute("headerSearchCityId", null);%>
<html>
<head>
<style type="text/css">
.jssora03l, .jssora03r {
	top: 119.5px !important;
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
        jssor_2_slider_init = function() {

            var jssor_2_options = {
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

            var jssor_2_slider = new $JssorSlider$("jssor_2", jssor_2_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_2_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_2_slider.$ScaleWidth(refSize);
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
        jssor_3_slider_init = function() {

            var jssor_3_options = {
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

            var jssor_3_slider = new $JssorSlider$("jssor_3", jssor_3_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_3_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_3_slider.$ScaleWidth(refSize);
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
        jssor_4_slider_init = function() {

            var jssor_4_options = {
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

            var jssor_4_slider = new $JssorSlider$("jssor_4", jssor_4_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_4_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_4_slider.$ScaleWidth(refSize);
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
        jssor_5_slider_init = function() {

            var jssor_5_options = {
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

            var jssor_5_slider = new $JssorSlider$("jssor_5", jssor_5_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_5_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_5_slider.$ScaleWidth(refSize);
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
        jssor_6_slider_init = function() {

            var jssor_6_options = {
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

            var jssor_6_slider = new $JssorSlider$("jssor_6", jssor_6_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_6_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_6_slider.$ScaleWidth(refSize);
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
        jssor_7_slider_init = function() {

            var jssor_7_options = {
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

            var jssor_7_slider = new $JssorSlider$("jssor_7", jssor_7_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_7_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_7_slider.$ScaleWidth(refSize);
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
        jssor_8_slider_init = function() {

            var jssor_8_options = {
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

            var jssor_8_slider = new $JssorSlider$("jssor_8", jssor_8_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_8_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_8_slider.$ScaleWidth(refSize);
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
        jssor_9_slider_init = function() {

            var jssor_9_options = {
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

            var jssor_9_slider = new $JssorSlider$("jssor_9", jssor_9_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_9_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_9_slider.$ScaleWidth(refSize);
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
        jssor_10_slider_init = function() {

            var jssor_10_options = {
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

            var jssor_10_slider = new $JssorSlider$("jssor_10", jssor_10_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_10_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_10_slider.$ScaleWidth(refSize);
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
        jssor_11_slider_init = function() {

            var jssor_11_options = {
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

            var jssor_11_slider = new $JssorSlider$("jssor_11", jssor_11_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_11_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_11_slider.$ScaleWidth(refSize);
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
        jssor_12_slider_init = function() {

            var jssor_12_options = {
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

            var jssor_12_slider = new $JssorSlider$("jssor_12", jssor_12_options);

            /*responsive code begin*/
            /*you can remove responsive code if you don't want the slider scales while window resizing*/
            function ScaleSlider() {
                var refSize = jssor_12_slider.$Elmt.parentNode.clientWidth;
                if (refSize) {
                    refSize = Math.min(refSize, 1178);
                    jssor_12_slider.$ScaleWidth(refSize);
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
.title{display: inline-block;
    overflow: hidden !important;
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 200px;}
.vendor-box .rating {
    position: absolute;
}
@media only screen and (min-width:768px) and (max-width: 2500px) {
.main-container {
    padding-bottom: 40px;
    padding-top: 250px;
}
.finder-caption h1, .finder-caption p {
    color: #3c3634;
    margin-bottom: 15px;
    margin-top: 15px;
}
.finder-caption h1 {
	font-size: 33px;
}
}
@media only screen and (min-width:444px) and (max-width: 2500px) {
.section-space100{padding-top: 100px;}
#slider .item img {height: 390px;}
}
@media only screen and (min-width:0px) and (max-width: 444px) {
.section-space100{padding-top: 50px;}
}
</style>
<script type="text/javascript">
	function validateSubscriberForm() {
		validateSubscriberFileds([ 'subscriberEmail' ]);
	
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
//		 		alert("error");
			}
		});
		}
	
	function view_all(id) {
		$.ajax({
			data : {
				
				headerSearchCategoryId : id,
				headerSearchCityId : -1
			},
			url : "searchCategoryByCity.action",
			dataType : "json",
			success : function(data) {
				window.location.href = "vendor_list";
			},
			error : function(xhr, textStatus, errorThrown) {
				// Handle error
			}
		});
	}
	
</script>
</head>
<body>
  <div class="slider-bg"><!-- slider start-->
  <div id="slider" class="owl-carousel owl-theme slider">
  <s:iterator value="activeBannerPhotosBean" status="incr">
	<c:if test="${activeBannerPhotosBean[incr.index]['fileName'] != null}">
      	<div class="item"><img src="<s:url action="ImageAction?imageId=%{activeBannerPhotosBean[#incr.index]['fileName']}" />" alt="<s:property value="%{activeBannerPhotosBean[#incr.index]['fileName']}" />" /></div>
     </c:if>
      <c:if test="${activeBannerPhotosBean[incr.index]['fileName'] == null}">
		<div class="item"><img src="images/hero-image.jpg" alt="Wedding couple just married"></div>
      </c:if>
    </s:iterator>
  </div>
  </div>
  <div class="slider-bg">
  <div class="find-section"><!-- Find search section-->
    <div class="container">
      <div class="row">
        <div class="col-md-offset-1 col-md-10 finder-block">
          <div class="finder-caption">
            <h1>Find your perfect Wedding Service</h1>
            <p>Over <strong>1200+ Wedding Service </strong>for you special date &amp; Find the perfect venue.</p>
          </div>
          <div class="finderform">
            <s:form name="headerSearchForm" id="headerSearchForm" method="post">
              <div class="row">
                <div class="form-group col-md-4">
                  <select id="headerCategoryId" name="headerCategoryId" class="form-control">
                    <option value="-1">Select Service Category</option>
                  </select>
                </div>
                <div class="form-group col-md-4">
                  <select id="headerCityId" name="headerCityId" class="form-control">
                    <option value="-1">Select City</option>
                  </select>
                </div>
                <div class="form-group col-md-4">
                  <input type="button" class="btn btn-primary btn-lg btn-block" onclick="search_category_by_city();" value="Find a Service" />
                </div>
              </div>
            </s:form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- /.Find search section--> 
</div>
<!-- slider end-->
<div class="main-container">
  <div class="container">
    <div class="row">
    <c:if test="${weddingHalls[0]['companyName'] != null}">
    <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{weddingHalls[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.WEDDING_HALLS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_1" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="weddingHalls" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{weddingHalls[#incr.index]['vendorName']}" />">
        <c:if test="${weddingHalls[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{weddingHalls[#incr.index]['fileName']}" />" alt="<s:property value="%{weddingHalls[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${weddingHalls[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{weddingHalls[#incr.index]['vendorName']}" />" class="title"><s:property value="%{weddingHalls[#incr.index]['companyName']}" /></a></h2>
            <p class="location" style="margin-bottom: 7px;"><i class="fa fa-map-marker"></i> <s:property value="%{weddingHalls[#incr.index]['address']}" />, <s:property value="%{weddingHalls[#incr.index]['location']}" />, 
            <s:property value="%{weddingHalls[#incr.index]['cityName']}" />, <s:property value="%{weddingHalls[#incr.index]['pincode']}" />, <s:property value="%{weddingHalls[#incr.index]['countryName']}" />.</p>
            <c:if test="${weddingHalls[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${weddingHalls[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${weddingHalls[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{weddingHalls[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{weddingHalls[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px !important;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px !important;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_1_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${studios[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{studios[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.STUDIOS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	  </div>
	  <div id="jssor_2" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="studios" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{studios[#incr.index]['vendorName']}" />">
        <c:if test="${studios[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{studios[#incr.index]['fileName']}" />" alt="<s:property value="%{studios[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${studios[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{studios[#incr.index]['vendorName']}" />" class="title"><s:property value="%{studios[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{studios[#incr.index]['address']}" />, <s:property value="%{studios[#incr.index]['location']}" />, 
            <s:property value="%{studios[#incr.index]['cityName']}" />, <s:property value="%{studios[#incr.index]['pincode']}" />, <s:property value="%{studios[#incr.index]['countryName']}" />.</p>
            <c:if test="${studios[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${studios[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${studios[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{studios[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{studios[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_2_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${decorations[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{decorations[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.DECORATIONS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_3" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="decorations" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{decorations[#incr.index]['vendorName']}" />">
        <c:if test="${decorations[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{decorations[#incr.index]['fileName']}" />" alt="<s:property value="%{decorations[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${decorations[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{decorations[#incr.index]['vendorName']}" />" class="title"><s:property value="%{decorations[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{decorations[#incr.index]['address']}" />, <s:property value="%{decorations[#incr.index]['location']}" />, 
            <s:property value="%{decorations[#incr.index]['cityName']}" />, <s:property value="%{decorations[#incr.index]['pincode']}" />, <s:property value="%{decorations[#incr.index]['countryName']}" />.</p>
            <c:if test="${decorations[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${decorations[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${decorations[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{decorations[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{decorations[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_3_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${beautyParlours[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{beautyParlours[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.BEAUTY_PARLOURS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_4" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="beautyParlours" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{beautyParlours[#incr.index]['vendorName']}" />">
        <c:if test="${beautyParlours[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{beautyParlours[#incr.index]['fileName']}" />" alt="<s:property value="%{beautyParlours[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${beautyParlours[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{beautyParlours[#incr.index]['vendorName']}" />" class="title"><s:property value="%{beautyParlours[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{beautyParlours[#incr.index]['address']}" />, <s:property value="%{beautyParlours[#incr.index]['location']}" />, 
            <s:property value="%{beautyParlours[#incr.index]['cityName']}" />, <s:property value="%{beautyParlours[#incr.index]['pincode']}" />, <s:property value="%{beautyParlours[#incr.index]['countryName']}" />.</p>
            <c:if test="${beautyParlours[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${beautyParlours[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${beautyParlours[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{beautyParlours[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{beautyParlours[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_4_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${jewelShops[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{jewelShops[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.JEWEL_SHOPS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_5" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    
    <s:iterator value="jewelShops" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{jewelShops[#incr.index]['vendorName']}" />">
        <c:if test="${jewelShops[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{jewelShops[#incr.index]['fileName']}" />" alt="<s:property value="%{jewelShops[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${jewelShops[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{jewelShops[#incr.index]['vendorName']}" />" class="title"><s:property value="%{jewelShops[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{jewelShops[#incr.index]['address']}" />, <s:property value="%{jewelShops[#incr.index]['location']}" />, 
            <s:property value="%{jewelShops[#incr.index]['cityName']}" />, <s:property value="%{jewelShops[#incr.index]['pincode']}" />, <s:property value="%{jewelShops[#incr.index]['countryName']}" />.</p>
            <c:if test="${jewelShops[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${jewelShops[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${jewelShops[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{jewelShops[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{jewelShops[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_5_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${caterings[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{caterings[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.CATERINGS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_6" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="caterings" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{caterings[#incr.index]['vendorName']}" />">
        <c:if test="${caterings[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{caterings[#incr.index]['fileName']}" />" alt="<s:property value="%{caterings[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${caterings[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{caterings[#incr.index]['vendorName']}" />" class="title"><s:property value="%{caterings[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{caterings[#incr.index]['address']}" />, <s:property value="%{caterings[#incr.index]['location']}" />, 
            <s:property value="%{caterings[#incr.index]['cityName']}" />, <s:property value="%{caterings[#incr.index]['pincode']}" />, <s:property value="%{caterings[#incr.index]['countryName']}" />.</p>
            <c:if test="${caterings[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${caterings[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${caterings[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{caterings[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{caterings[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
	      jssor_6_slider_init();
	</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${entertainments[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{entertainments[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.ENTERTAINMENTS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_7" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="entertainments" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{entertainments[#incr.index]['vendorName']}" />">
        <c:if test="${entertainments[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{entertainments[#incr.index]['fileName']}" />" alt="<s:property value="%{entertainments[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${entertainments[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{entertainments[#incr.index]['vendorName']}" />" class="title"><s:property value="%{entertainments[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{entertainments[#incr.index]['address']}" />, <s:property value="%{entertainments[#incr.index]['location']}" />, 
            <s:property value="%{entertainments[#incr.index]['cityName']}" />, <s:property value="%{entertainments[#incr.index]['pincode']}" />, <s:property value="%{entertainments[#incr.index]['countryName']}" />.</p>
            <c:if test="${entertainments[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${entertainments[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${entertainments[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{entertainments[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{entertainments[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_7_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${weddingClothes[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{weddingClothes[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.WEDDING_CLOTHES_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_8" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="weddingClothes" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{weddingClothes[#incr.index]['vendorName']}" />">
        <c:if test="${weddingClothes[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{weddingClothes[#incr.index]['fileName']}" />" alt="<s:property value="%{weddingClothes[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${weddingClothes[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{weddingClothes[#incr.index]['vendorName']}" />" class="title"><s:property value="%{weddingClothes[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{weddingClothes[#incr.index]['address']}" />, <s:property value="%{weddingClothes[#incr.index]['location']}" />, 
            <s:property value="%{weddingClothes[#incr.index]['cityName']}" />, <s:property value="%{weddingClothes[#incr.index]['pincode']}" />, <s:property value="%{weddingClothes[#incr.index]['countryName']}" />.</p>
            <c:if test="${weddingClothes[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${weddingClothes[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${weddingClothes[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{weddingClothes[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{weddingClothes[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_8_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${textiles[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{textiles[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.TEXTILES_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_9" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="textiles" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{textiles[#incr.index]['vendorName']}" />">
        <c:if test="${textiles[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{textiles[#incr.index]['fileName']}" />" alt="<s:property value="%{textiles[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${textiles[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{textiles[#incr.index]['vendorName']}" />" class="title"><s:property value="%{textiles[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{textiles[#incr.index]['address']}" />, <s:property value="%{textiles[#incr.index]['location']}" />, 
            <s:property value="%{textiles[#incr.index]['cityName']}" />, <s:property value="%{textiles[#incr.index]['pincode']}" />, <s:property value="%{textiles[#incr.index]['countryName']}" />.</p>
            <c:if test="${textiles[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${textiles[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${textiles[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{textiles[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{textiles[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_9_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${travels[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{travels[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.TRAVELS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_10" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="travels" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{travels[#incr.index]['vendorName']}" />">
        <c:if test="${travels[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{travels[#incr.index]['fileName']}" />" alt="<s:property value="%{travels[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${travels[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{travels[#incr.index]['vendorName']}" />" class="title"><s:property value="%{travels[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{travels[#incr.index]['address']}" />, <s:property value="%{travels[#incr.index]['location']}" />, 
            <s:property value="%{travels[#incr.index]['cityName']}" />, <s:property value="%{travels[#incr.index]['pincode']}" />, <s:property value="%{travels[#incr.index]['countryName']}" />.</p>
            <c:if test="${travels[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${travels[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${travels[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{travels[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{travels[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_10_slider_init();
	</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${hotels[0]['companyName'] != null}">
       <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{hotels[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.HOTELS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_11" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="hotels" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{hotels[#incr.index]['vendorName']}" />">
        <c:if test="${hotels[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{hotels[#incr.index]['fileName']}" />" alt="<s:property value="%{hotels[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${hotels[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{hotels[#incr.index]['vendorName']}" />" class="title"><s:property value="%{hotels[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{hotels[#incr.index]['address']}" />, <s:property value="%{hotels[#incr.index]['location']}" />, 
            <s:property value="%{hotels[#incr.index]['cityName']}" />, <s:property value="%{hotels[#incr.index]['pincode']}" />, <s:property value="%{hotels[#incr.index]['countryName']}" />.</p>
            <c:if test="${hotels[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${hotels[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${hotels[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{hotels[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{hotels[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
		jssor_11_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
      </div>
      <div class="row">
      <c:if test="${weddingAstrologers[0]['companyName'] != null}">
      <div class="col-md-12 vendor-listing">
		<h3><s:property value="%{weddingAstrologers[0]['categoryName']}" />
		<a class="btn btn-default" href="vendor_list?search=<%=CommonConstants.WEDDING_ASTROLOGERS_STR%>" style="float: right; font-size: 10px; padding: 5px;">View All</a>
		</h3>
	</div>
	<div id="jssor_12" style="position: relative; margin: 0 auto; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden; visibility: hidden;">
	<div data-u="slides" style="cursor: default; position: relative; top: 0px; left: 0px; width: 1178px; height: 350px; overflow: hidden;">
    <s:iterator value="weddingAstrologers" status="incr">
      <div class="col-md-3 vendor-box"><!-- venue box start-->
        <div class="vendor-image"><!-- venue pic --> 
        <a href="vendor_details?vendor_name=<s:property value="%{weddingAstrologers[#incr.index]['vendorName']}" />">
        <c:if test="${weddingAstrologers[incr.index]['fileName'] != null}">
        	<img height="146" width="264" class="img-responsive" src="<s:url action="ImageAction?imageId=%{weddingAstrologers[#incr.index]['fileName']}" />" alt="<s:property value="%{weddingAstrologers[#incr.index]['fileName']}" />" />
          </c:if>
         <c:if test="${weddingAstrologers[incr.index]['fileName'] == null}">
			<img height="146" width="264" class="img-responsive" src="images/vendor-1.jpg" alt="wedding venue">
         </c:if>
         </a>
<!--           <div class="favourite-bg"><a href="#" class=""><i class="fa fa-heart"></i></a></div> -->
        </div>
        <!-- /.venue pic -->
        <div class="vendor-detail"><!-- venue details -->
          <div class="caption"><!-- caption -->
            <h2><a href="vendor_details?vendor_name=<s:property value="%{weddingAstrologers[#incr.index]['vendorName']}" />" class="title"><s:property value="%{weddingAstrologers[#incr.index]['companyName']}" /></a></h2>
            <p class="location"><i class="fa fa-map-marker"></i> <s:property value="%{weddingAstrologers[#incr.index]['address']}" />, <s:property value="%{weddingAstrologers[#incr.index]['location']}" />, 
            <s:property value="%{weddingAstrologers[#incr.index]['cityName']}" />, <s:property value="%{weddingAstrologers[#incr.index]['pincode']}" />, <s:property value="%{weddingAstrologers[#incr.index]['countryName']}" />.</p>
            <c:if test="${weddingAstrologers[incr.index]['maxRating'] != null}">
            <div class="rating" style="margin: -13px 0px -12px;"> 
            <c:forEach var="i" begin="1" end="${weddingAstrologers[incr.index]['maxRating']}">
            <i class="fa fa-star"></i> 
			</c:forEach>
			<c:forEach var="i" begin="1" end="${weddingAstrologers[incr.index]['minRating']}">
            <i class="fa fa-star-o"></i> 
			</c:forEach>
            <span class="rating-count">(<s:property value="%{weddingAstrologers[#incr.index]['maxUsersRating']}" />)</span> 
            </div>
            </c:if>
          </div>
          <!-- /.caption -->
          <div class="vendor-price">
            <div class="price">Rs. <s:property value="%{weddingAstrologers[#incr.index]['price']}" /></div>
          </div>
        </div>
        <!-- venue details --> 
      </div>
      </s:iterator>
      </div>
      <!-- Bullet Navigator -->
        <!-- <div data-u="navigator" class="jssorb03" style="bottom:10px;right:10px;">
            bullet navigator item prototype
            <div data-u="prototype" style="width:21px;height:21px;">
                <div data-u="numbertemplate"></div>
            </div>
        </div> -->
        <!-- Arrow Navigator -->
        <span data-u="arrowleft" class="jssora03l" style="top:0px;left:15px;width:55px;height:55px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora03r" style="top:0px;right:8px;width:55px;height:55px;" data-autocenter="2"></span>
      </div>
      <script type="text/javascript">
			jssor_12_slider_init();
		</script>
      </c:if>
      <!-- /.venue box start-->
    </div>
    <%-- <div class="row">
      <div class="col-md-12 tp-pagination">
        <ul class="pagination">
          <li> <a href="#" aria-label="Previous"> <span aria-hidden="true">Previous</span> </a> </li>
          <li class="active"><a href="#">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li> <a href="#" aria-label="Next"> <span aria-hidden="true">NEXT</span> </a> </li>
        </ul>
      </div>
    </div> --%>
  </div>
</div>
<!-- <div class="section-space80 bg-light">Testimonial Section
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="section-title mb60 text-center">
          <h1>Just Get Married Happy Couple</h1>
          <p>Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text.</p>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12 tp-testimonial">
        <div id="testimonial" class="owl-carousel owl-theme">
          <div class="item testimonial-block">
            <div class="couple-pic"><img src="images/couple.jpg" alt="" class="img-circle"></div>
            <div class="feedback-caption">
              <p>"Had our wedding on 15th may 2015 and have to say Jenny and the team made it a wonderful and enjoyable day were Notting was a problem from the build up to the day."</p>
            </div>
            <div class="couple-info">
              <div class="name">Dave Macwan</div>
              <div class="date">Thu, 21st June, 2015</div>
            </div>
          </div>
          <div class="item testimonial-block">
            <div class="couple-pic"><img src="images/couple-2.jpg" alt="" class="img-circle"></div>
            <div class="feedback-caption">
              <p>"Vestibulum vitae neque urna. Duis ut mauris mi. Sed vehicula vestibulum finias their default model text and a search for lorem ipsum will uncover manym elit posuerenia eget sem."</p>
            </div>
            <div class="couple-info">
              <div class="name">Marry &amp; Leary</div>
              <div class="date">Thu, 13th July, 2015</div>
            </div>
          </div>
          <div class="item testimonial-block">
            <div class="couple-pic"><img src="images/couple-3.jpg" alt="" class="img-circle"></div>
            <div class="feedback-caption">
              <p>"Had our wedding on 15th Oct 2015 and have to say Jenny and the team made it a wonderful and enjoyable day were Notting was a problem from the build up to the day."</p>
            </div>
            <div class="couple-info">
              <div class="name">Jhon Doe &amp; Doe Jassica</div>
              <div class="date">Thu, 21st Aug, 2015</div>
            </div>
          </div>
          <div class="item testimonial-block">
            <div class="couple-pic"><img src="images/couple-4.jpg" alt="" class="img-circle"></div>
            <div class="feedback-caption">
              <p>"Etiam ut metus nisi. Sed non laoreet nisi tinciin interdum risus felis enjoyable day were Notting was a problem from the build up to the dayvel eleifend milaoreet consectetur."</p>
            </div>
            <div class="couple-info">
              <div class="name">Dave Macwan</div>
              <div class="date">Thu, 12th Sept, 2015</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div> -->
<section class="section-space100 bg-light module parallax parallax-2">
  <div class="container">
    <div class="row">
      <div class="col-md-offset-1 col-md-6">
        <div class="tool-description text-center">
             <h1>Mobile Weddfix Services</h1>
          <p>The next generation of matchmaking is here!</p>
          <a><img src="images/button-app-store.png" alt="" style="margin-bottom: 14px; margin-right: 10px;"></a>
          <a><img src="images/button-google-play.png" alt="" style="margin-bottom: 14px; margin-right: 10px;"></a>
          </div>
      </div>
      <!-- <div class="col-md-4"><div class="app-mobile">
        <img src="images/app-screen.png" alt="">
      </div></div> -->
  </div>
  </div>
</section>
    <!-- #endregion Jssor Slider End -->
  </body>
</html>