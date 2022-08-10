<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create project</title>
</head>
<body>
<h1 style="color: lightsalmon; text-align: center">project Details</h1>

<form:form action="createproject" method="post" modelAttribute="projectBO">

<label>projectName</label>         
<form:input type="text" path="projectName"/><br>
<br>
<label>StartDate</label>               
<form:input type="text" path="startDate"/><br>
<br>
 <label>EndDate</label>             
<form:input type="text" path="endDate"/><br>
<br>
<label>projectReview</label>              
<form:input type="text" path="projectReview"/><br>
<br>
<label>ProjectType</label>        
<form:input type="text" path="projectType"/><br>
<br>
<label>Employer Id</label>
<form:input type="text" path="employerId"/><br>

<%-- <form:hidden path="projectId"/> --%>
<form:button>submit</form:button>
</form:form>

</body>
</html>