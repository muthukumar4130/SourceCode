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
<h1 style="color: lightsalmon; text-align: center">View Project Details</h1>

<display:table name="${list}" requestURI="/view-project" pagesize="3">

<display:column  title="ProjectId" property="projectId"></display:column>
<display:column  title="ProjectName" property="projectName"></display:column>
<display:column  title="StartDate" property="startDate"></display:column>
<display:column  title="EndDate" property="endDate"></display:column>
<display:column  title="ProjectReview" property="projectReview"></display:column>
<display:column  title="ProjectType" property="projectType"></display:column>
</display:table>

</body>
</html>