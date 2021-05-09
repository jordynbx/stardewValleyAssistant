<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Add To Favorites" />
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />

<h2>Add to favorites</h2>
<p>Add ${item.name} to favorites? </p>

<form action="addFavoriteAction" method="post">
    <input type="hidden" id="favoriteItemId" name="favoriteItemId" value="${item.id}">
    <button type="submit" name="submit" class="btn btn-success" value="confirm">Confirm</button>
    <button type="submit" name="submit" class="btn btn-warning" value="cancel">Cancel</button>
</form>
<c:import url="template/footer.jsp" />%>