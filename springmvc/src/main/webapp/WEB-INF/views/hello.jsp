<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Hello</title>
</head>
<body>
	<%
	Integer id = (Integer) request.getAttribute("id");
	String name = (String) request.getAttribute("name");
	Integer salary = (Integer) request.getAttribute("salary");
	out.println(id);
	out.println(name);
	out.println(salary);
	%>
	<br />
	
	id:
	<b>${id}</b>
	name:
	<b>${name}</b>
	salary:
	<b>${salary}</b>
</body>


</html>