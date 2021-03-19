<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Result" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>


<%--<c:if test="${success == 'false'}">--%>
<c:if test="${!success}">
    <h2>Search Results:</h2>
    <p>${message}</p>
</c:if>
<c:if test="${success}">
    <h2 class="text-center">${item.name}</h2>
    <p>
        Seed price: ${crop.seedPrice}<br>
        Sell price: ${crop.sellPrice}<br>
        Recipes: ${crop.recipes}<br>
        Bundles: ${crop.bundles}<br>
        Growth season: ${crop.season}<br>
    </p>
    <p>
        Favorite: ${isFavorite}<br>
        Your notes: ${notes.note}
    </p>
    <c:if test="${pageContext.request.isUserInRole('user')}">
        <form action="addUserInput" method="get">
            <label for="favorite">Add to favorites?</label>
            <input type="checkbox" id="favorite" value="yes">
            <label for="userNote">Add a note:</label>
            <input type="text" id="userNote" name="userNote">
            <button type="submit" name="submit" value="submit">Submit</button>
        </form>
    </c:if>
</c:if>
<a href="index.jsp">Search again</a>


<%@include file="template/footer.jsp"%>