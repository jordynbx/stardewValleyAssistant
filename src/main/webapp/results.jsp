<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Result" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>

<h2>Search Results:</h2>
<p>Item: ${item.name}<br>
    Seed price: ${crop.seedPrice}<br>
    Sell price: ${crop.sellPrice}<br>
    Used in recipes: ${crop.hasRecipe}<br>
    Growth season: ${crop.season}<br>
</p>


<%@include file="template/footer.jsp"%>