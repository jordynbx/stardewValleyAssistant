<%@include file="taglib.jsp"%>
<html>
<body>
<h1>Stardew Valley Assistant</h1>
<%--<a href="search.jsp">Log in</a>--%>

<a href = 'loginAction'><button>Log In</button></a>

<c:if test="${pageContext.request.isUserInRole('user')}">
    <p>You are logged in! In the future, page content will show including fields for notes and checkboxes for logged in users.</p>
</c:if>

<c:if test="${pageContext.request.isUserInRole('admin')}">
    <p>I don't know if I will have any admin functionality, but if I do, you would be able to see it!</p>
</c:if>
</body>
</html>
