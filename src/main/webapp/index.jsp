<%@include file="taglib.jsp"%>
<c:set var="title" value="Stardew Valley Assistant" />
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />

${updateMessage}
<c:set var="updateMessage" value="" scope="session" />
<br>
<h1>Search for an item</h1>
<c:if test="${pageContext.request.isUserInRole('user')}">
<h3>Welcome, ${currentUser}!</h3>
</c:if>


<form method="get" action="searchItem">
    <label for="searchTerm">Enter item name:</label>
    <input type="text" id="searchTerm" name="searchTerm">
    <button type="submit" name="submit" value="search">Search</button>
</form>


<c:if test="${pageContext.request.isUserInRole('admin')}">
    <p>I don't know if I will have any admin functionality, but if I do, you would be able to see it!</p>
</c:if>
<c:import url="template/footer.jsp" />