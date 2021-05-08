<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Edit Note" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<h1>Favorites</h1>
<h2>Crops</h2>
<c:if test="${favoriteCrops.size() >= 1}">
    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col" class="th-fave" style="background-color: #4DADAF;">Crop</th>
                <th scope="col" class="th-fave" style="background-color: #4DADAF;">Seed Price</th>
                <th scope="col" class="th-fave" style="background-color: #4DADAF;">Sell Price</th>
                <th scope="col" class="th-fave" style="background-color: #4DADAF;">Seasons</th>
                <th scope="col" class="th-fave" style="background-color: #4DADAF;">Bundles</th>
                <th scope="col" class="th-fave" style="background-color: #4DADAF;">Recipes</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="crop" items="${favoriteCrops}">
            <tr class="clickable"
                onclick="window.location='searchItem?submit=search&searchTerm=${crop.item.name}'">
                <td class="td-fave" style="background-color: #D08AAD;">${crop.item.name}</td>
                <td class="td-fave" style="background-color: #D08AAD;">${crop.seedPrice}</td>
                <td class="td-fave" style="background-color: #D08AAD;">${crop.sellPrice}</td>
                <td class="td-fave" style="background-color: #D08AAD;">${crop.season}</td>
                <td class="td-fave" style="background-color: #D08AAD;">${crop.bundles}</td>
                <td class="td-fave" style="background-color: #D08AAD;">${crop.recipes}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${favoriteCrops.size() == 0}">
    <p>Add some crops to your favorites to see their data here.</p>
</c:if>
<c:if test="${favoriteItems.size() >= 1}">
    <h2>Other items</h2>
    <ul>
        <c:forEach var="item" items="${favoriteItems}">
            <li><a href="searchItem?submit=search&searchTerm=${item.name}">${item.name}</a></li>
        </c:forEach>
    </ul>
</c:if>

<%@include file="template/footer.jsp"%>