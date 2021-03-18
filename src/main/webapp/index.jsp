<%@include file="taglib.jsp"%>
<c:set var="title" value="Stardew Valley Assistant" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>

<a href = 'loginAction'><button>Log In</button></a>
<br>
<h1>Search for an item</h1>

<form method="get" action="searchItem">
    <label for="searchTerm">Enter item name:</label>
    <input type="text" id="searchTerm" name="searchTerm">
    <button type="submit" name="submit" value="search">Search</button>
</form>

<c:if test="${pageContext.request.isUserInRole('user')}">
    <p>You are logged in! In the future, page content will show including fields for notes and checkboxes for logged in users.</p>
</c:if>

<c:if test="${pageContext.request.isUserInRole('admin')}">
    <p>I don't know if I will have any admin functionality, but if I do, you would be able to see it!</p>
</c:if>
<%@include file="template/footer.jsp"%>