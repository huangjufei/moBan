<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
 
    String version="";
	String baseUrl="http://localhost:8080/demo/static/"+version;

%>   
   <script type="text/javascript">
  var MT={STATIC_ROOT:"<%=baseUrl%>"}
</script>
