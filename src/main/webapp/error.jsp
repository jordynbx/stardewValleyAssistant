<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Error " />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>


<p class="text-danger"> There was an error${message}. Please try again.</p>

<p><a href="index.jsp">Return to home page.</a></p>

<%@include file="template/footer.jsp"%>