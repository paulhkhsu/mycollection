<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<h1>This is Default Render Jsp</h1>

<portlet:defineObjects/>

<portlet:renderURL var="renderOneMethodURL">
	<portlet:param name="action" value="renderOne"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="renderTwoMethodURL">
	<portlet:param name="action" value="renderTwo"></portlet:param>
</portlet:renderURL>

<portlet:actionURL var="actionOneMethodURL">
<portlet:param name="action" value="actionOne"></portlet:param>
</portlet:actionURL>

<portlet:actionURL var="actionTwoMethodURL">
<portlet:param name="action" value="actionTwo"></portlet:param>
</portlet:actionURL>

<portlet:actionURL var="actionThreeMethodURL">
<portlet:param name="action" value="actionThree"></portlet:param>
	<portlet:param name="mvcPath" value="test.jsp" />
</portlet:actionURL>

<a href="${renderOneMethodURL}">Call RenderOne method</a>
<a href="${renderTwoMethodURL}">Call RenderTwo method</a>

<form action="${actionOneMethodURL}" method="post">
	User Name: <input type="text" name="userName">
	<input type="submit">  
</form>

<form action="${actionTwoMethodURL}" method="post">
	User Name: <input type="text" name="userName">
	<input type="submit">  
</form>
<form action="${actionThreeMethodURL}" method="post">
	User Name: <input type="text" name="userName">
	<input type="submit">  
</form>