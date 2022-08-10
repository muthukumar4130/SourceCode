<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta  charset=ISO-8859-1">
<title>Create Employer</title>
</head>
<body>
<h1 style="color: lightsalmon; text-align: center">Employer Details</h1>

<form:form action ="createemployer" method="post" modelAttribute="employerBO">

<label>Employer Name</label>   
<form:input type="text" path="name"/><br>
<br>
<label>Employer EmailId</label>            
<form:input type="text" path="email"/><br>
<br>
<label>Employer PhoneNumber</label>        
<form:input type="text" path="mobile"/><br>
<br>
<label>Employer Address</label>        
<form:input type="text" path="address"/><br>


<form:hidden path="id"/>

<form:button>submit</form:button>
</form:form>
</body>
</html>