<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="javax.portlet.PortletPreferences"%>

<portlet:defineObjects />

<portlet:actionURL var="vinSearch">
	<portlet:param name="action" value="vinSearch"></portlet:param>
</portlet:actionURL>

<portlet:resourceURL var='myInfo' id='myInfo' />

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${context}/js/jquery-1.11.0.min.js"></script>

 <style>
p {
color: red;
margin: 5px;
cursor: pointer;
}
p:hover {
background: yellow;
}
</style>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	  $("#ajax-test").click(function() {
		    loadMyInfo();
		  });
	
});
function loadMyInfo(){
    var myInfoUrl = "<%=myInfo%>";
		$.ajax({
			url : myInfoUrl,
			data: { field1: "hello", field2 : "hello2"}, 
			type : 'GET',
			datatype : 'json',
			success : function(data) {
// 				var obj = $.parseJSON(data);
				alert(data);
			},
	       error: function () {
	            alert("error");
	        }
	  
		});
	}
</script>

</head>
<body>
	<form action="${vinSearch}" method="post">
		VIN : <input type="text" name="vin"> <input type="submit"
			value="find" />
	</form>
	
	<p id="ajax-test">ajax test</p>	
</body>
</html>