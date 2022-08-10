<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View</title>
</head>
<body>
<h1 style="color: lightsalmon; text-align: center">View Employer Details</h1>

<display:table name="${list}" requestURI="/view-employer" pagesize="3">

 <display:column title="Id" property="id"></display:column>
 <display:column title="Name" property="name"></display:column>
<display:column title="Email" property="email"></display:column>
<display:column title="Mobile" property="mobile"></display:column>
<display:column title="Address" property="address"></display:column>

<display:column title="Edit" url="/edit-employer" paramId="id" paramProperty="id">Edit</display:column>

<display:column title="Delete" url="/delete-employer" paramId="id" paramProperty="id">Delete</display:column>
 

</display:table>
</body>
</html>