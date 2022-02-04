<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.demiglace.spring.springmvc.dto.Employee,java.util.List" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List</title>
</head>
<body>
<%
	List<Employee> employees = (List<Employee>) request.getAttribute("employees");
	for(Employee e:employees) {
		out.println(e.getId());
		out.println(e.getName());
		out.println(e.getSalary());
	}
%>
</body>
</html>