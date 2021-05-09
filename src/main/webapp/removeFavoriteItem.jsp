<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Remove From Favorites" />
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />

<h2>Remove from favorites</h2>
<p>Remove ${item.name} from favorites? </p>

<form action="removeFavoriteAction" method="post">
    <input type="hidden" id="favoriteItemId" name="favoriteItemId" value="${item.id}">
    <input type="hidden" id="favoriteId" name="favoriteId" value="${favorite.id}">
    <button type="submit" name="submit" class="btn btn-danger" value="confirm">Confirm</button>
    <button type="submit" name="submit" class="btn btn-warning" value="cancel">Cancel</button>
</form>
<c:import url="template/footer.jsp" />