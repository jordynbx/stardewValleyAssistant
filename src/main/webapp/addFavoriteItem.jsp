<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Add To Favorites" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>
<%--TODO implement cancel button that returns to the results page--%>
<h2>Add to favorites</h2>
<p>Add ${item.name} to favorites? </p>

<form action="addFavoriteAction" method="get">
    <input type="hidden" id="favoriteItemId" name="favoriteItemId" value="${item.id}">
    <button type="submit" name="submit" class="btn btn-success" value="confirm">Confirm</button>
    <button type="submit" name="submit" class="btn btn-warning" value="cancel">Cancel</button>
</form>
<%@include file="template/footer.jsp"%>