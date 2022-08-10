<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Employer</title>
</head>
<body>
<h2 style="color: lightsalmon; text-align: center">Attendance Details</h2>

<form:form action="create-employer-attendance" method="post" modelAttribute="employerAttendanceBO">

EmployerId
<form:input type="text" path="id"/><br>
<br> 
Employer name
<form:input type="text" path="name"/>
<form:errors path="name"></form:errors><br>
<br>
Date
<form:input type="text" path="date"/>
<form:errors path="date"></form:errors><br>
<br>
Employer Status
<form:input type="text" path="status"/>
<form:errors path="status"></form:errors><br>
<br>
Employer In Time
<form:input type="text" path="inTime"/><br>
<br>
Employer Out Time
<form:input type="text" path="outTime"/><br>
<br>

<form:button>submit</form:button>

</form:form>
</body>
</html>