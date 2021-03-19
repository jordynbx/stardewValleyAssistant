<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Result" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>

<h2>Search Results:</h2>
<%--<c:if test="${success == 'false'}">--%>
<c:if test="${!success}">
    <p>${message}</p>
</c:if>
<c:if test="${success}">
<p>Item: ${item.name}<br>
    Seed price: ${crop.seedPrice}<br>
    Sell price: ${crop.sellPrice}<br>
    Recipes: ${crop.recipes}<br>
    Bundles: ${crop.bundles}<br>
    Growth season: ${crop.season}<br>
</p>
</c:if>
<a href="index.jsp">Search again</a>


<%@include file="template/footer.jsp"%>