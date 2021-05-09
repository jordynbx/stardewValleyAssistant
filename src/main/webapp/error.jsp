<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Error " />
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />


<p class="text-danger"> There was an error${message}. Please try again.</p>

<p><a href="index.jsp">Return to home page.</a></p>

<c:import url="template/footer.jsp" />