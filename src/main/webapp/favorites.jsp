<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Edit Note" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<h1>Favorites</h1>

<table>
    <tr>
        <th>Crop</th>
        <th>Seed Price</th>
        <th>Sell Price</th>
        <th>Bundles</th>
        <th>Recipes</th>
    </tr>
    <c:forEach var="crop" items="${favoriteCrops}">
        <tr>
<%--            TODO how do i get the crop name here if it's in another table?--%>
            <td>${crop.item.name}</td>
            <td>${crop.seedPrice}</td>
            <td>${crop.sellPrice}</td>
            <td>${crop.bundles}</td>
            <td>${crop.recipes}</td>
        </tr>
    </c:forEach>
</table>


<%@include file="template/footer.jsp"%>