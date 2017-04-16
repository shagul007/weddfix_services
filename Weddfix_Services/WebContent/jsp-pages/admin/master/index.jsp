<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
var role = '<%=session.getAttribute("role") %>';
if(role == 'null') {
	window.location.href = "login";
}

if(role != 'ADMIN') {
	window.location.href = "home";
}
</script>
</head>
<body>
		<h2 style="text-align: center; color: #531844;">Masters</h2>
		<center>
			<table border="1" id="myTable" class="table table-striped" style="width: 75%;">
				<tr>
					<th>S.No.</th>
					<th>Index</th>
				</tr>
				<tr>
					<td align="center">1</td>
					<td><a href="role_master">Role Master</a></td>
				</tr>
				<tr>
					<td align="center">2</td>
					<td><a href="organization_master">Organization Master</a></td>
				</tr>
				<tr>
					<td align="center">3</td>
					<td><a href="status_master">Status Master</a></td>
				</tr>
				<tr>
					<td align="center">4</td>
					<td><a href="currency_master">Currency Master</a></td>
				</tr>
				<tr>
					<td align="center">5</td>
					<td><a href="country_master">Country Master</a></td>
				</tr>
				<tr>
					<td align="center">6</td>
					<td><a href="state_master">State Master</a></td>
				</tr>
				<tr>
					<td align="center">7</td>
					<td><a href="city_master">City Master</a></td>
				</tr>
			</table>
		</center>
</body>
</html>