<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View</title>
</head>
<body>

<h2 style="color:lightsalmon; text-align:center">View Employer Attendance Details</h2>

<display:table name="${list}" requestURI="/view-employer-attendance" pagesize="3">
<display:column title="Id" property="id"></display:column>
<display:column title="Name" property="name"></display:column>
<display:column title="Date" property="date"></display:column>
<display:column title="InTime" property="inTime"></display:column>
<display:column title="OutTime" property="outTime"></display:column>
<display:column title="Status" property="status"></display:column>
</display:table>

</body>
</html>