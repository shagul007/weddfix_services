<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="main-container">
	<div class="container">
		<div class="row">
			<div align="center" style="height: 200px;">
				<div style="margin: 13% 0px 0px;">
					<div style="margin-bottom: 10px; color: #000;">${successMessage}</div>
					<div>
						<a href="${hrefParamSuccess}.action"><img
							src="images/goback.jpg" alt="goback" border="0" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>