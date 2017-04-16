<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<style>
@media only screen and (min-width:768px) and (max-width: 1500px) {
#t_footer {
	margin-right: 475px;
}
}
</style>
<%if(ActionContext.getContext().getName() != null) {
	if(ActionContext.getContext().getName().equalsIgnoreCase("home") || ActionContext.getContext().getName().equalsIgnoreCase("contact")) {%>
<div class="footer"><!-- Footer -->
  <div class="container">
    <div class="row">
      <div class="col-md-5 ft-aboutus">
        <h2>Wedding.Vendor</h2>
        <p>At Wedding Vendor our purpose is to help people find  great online network connecting wedding suppliers and wedding couples who use those suppliers. <a href="#">Start Find a Service!</a></p>
        <a href="#" class="btn btn-default">Find a Service</a> </div>
      <div class="col-md-3 ft-link">
        <h2>Useful links</h2>
        <ul>
          <li><a href="contact">About Us</a></li>
          <li><a href="contact">Contact us</a></li>
<!--           <li><a href="#">News</a></li> -->
<!--           <li><a href="#">Career</a></li> -->
          <li><a href="#">Privacy Policy</a></li>
          <li><a href="#">Terms of Use</a></li>
        </ul>
      </div>
      <div class="col-md-4 newsletter">
        <h2>Subscribe for Newsletter</h2>
        	  <div id="subscriberSuccess" style="display: none; text-align: center;"><h3 style="color: #a56a96;">Subscribed successfully.</h3></div>
              <div id="subscriberError" style="display: none; text-align: center;"><h3 style="color: #a56a96;">You have already subscribed.</h3></div>
         <form id="subscriber" name="subscriber">
              <s:hidden id="validateUser" value="1" />
          <div class="input-group">
            <input type="text" id="subscriberEmail" name="subscriberEmail" class="form-control" placeholder="Enter E-Mail Address" required>
            <span class="input-group-btn">
            <button class="btn btn-default" type="button" onclick="return validateSubscriberForm();">Submit</button>
            </span> </div>
          <!-- /input-group --> 
          
          <!-- /.col-lg-6 -->
        </form>
        <div class="social-icon">
          <h2>Be Social &amp; Stay Connected</h2>
          <ul>
            <li><a target="_blank" href="https://facebook.com/weddfix/"><i class="fa fa-facebook-square"></i></a></li>
            <li><a target="_blank" href="https://twitter.com/info_weddfix"><i class="fa fa-twitter-square"></i></a></li>
<!--             <li><a href="#"><i class="fa fa-google-plus-square"></i></a></li> -->
<!--             <li><a href="#"><i class="fa fa-instagram"></i></a></li> -->
<!--             <li><a href="#"><i class="fa fa-flickr"></i></a></li> -->
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- /.Footer -->
<div class="tiny-footer"><!-- Tiny footer -->
  <div class="container">
    <div class="row">
      <div class="col-md-12" style="color: #fff;">Copyright © 2016 - www.weddfix.com. All Rights Reserved</div>
    </div>
  </div>
</div>
<!-- /. Tiny Footer --> 
<%} else {%>
<div class="tiny-footer"><!-- Tiny footer -->
  <div class="container">
    <div class="row">
      <div class="col-md-5" id="t_footer" style="color: #fff;">Copyright © 2016 - www.weddfix.com. All Rights Reserved</div>
    		<a target="_blank" href="https://facebook.com/weddfix/" style="margin-right: 15px; color: #fff;"><i class="fa fa-facebook"></i></a>
            <a target="_blank" href="https://twitter.com/info_weddfix" style="color: #fff;"><i class="fa fa-twitter"></i></a>
    </div>
  </div>
</div>
<!-- /. Tiny Footer --> 
<%}} %>
<!-- Flex Nav Script --> 
<script src="/js/jquery.flexnav.js" type="text/javascript"></script> 
<script src="/js/navigation.js"></script> 
<!-- slider --> 
<script src="/js/owl.carousel.min.js"></script> 
<script type="text/javascript" src="/js/thumbnail-slider.js"></script>
<script type="text/javascript" src="/js/slider.js"></script> 
<!-- testimonial --> 
<script type="text/javascript" src="/js/testimonial.js"></script> 
<!-- sticky header --> 
<script src="/js/jquery.sticky.js"></script> 
<script src="/js/header-sticky.js"></script>
<script type="text/javascript" src="/js/price-slider.js"></script>
