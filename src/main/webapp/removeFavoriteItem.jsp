<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Remove From Favorites" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>
<%--TODO implement cancel button that returns to the results page--%>
<h2>Remove from favorites</h2>
<p>Remove ${item.name} from favorites? </p>

<form action="removeFavoriteAction" method="get">
    <input type="hidden" id="favoriteItemId" name="favoriteItemId" value="${item.id}">
    <input type="hidden" id="favoriteId" name="favoriteId" value="${favorite.id}">
    <button type="submit" name="submit" class="btn btn-danger" value="confirm">Confirm</button>
    <button type="submit" name="submit" class="btn btn-warning" value="cancel">Cancel</button>
</form>
<%@include file="template/footer.jsp"%>