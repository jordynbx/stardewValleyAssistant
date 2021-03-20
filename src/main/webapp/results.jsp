<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Result" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<%--TODO remove when done with testing --%>
<p>Current user: ${username}</p>

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
    <c:if test="${pageContext.request.isUserInRole('user')}">
        <p>
            Favorite: ${isFavorite}<br>
            Your notes: ${notes.note}
        </p>
        <h3>Notes</h3>
        <form action="addUserInput" method="get">
            <div class="form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="addToFavorites" value="addToFavorites">
                    Add to favorites?
                </label>
            </div>
            <div class="form-group">
                <label for="userNote">Add a note:</label>
                <input type="text" class="form-control" id="userNote" name="userNote" placeholder="Enter note">
            </div>
            <input type="hidden" id="itemId" name="itemId" value="${item.id}">
            <button type="submit" class="btn btn-primary">Submit</button>
<%--            <label for="favorite">Add to favorites?</label>--%>
<%--            <input type="checkbox" id="favorite" value="yes"><br>--%>
<%--            <label for="userNote">Add a note:</label>--%>
<%--            <input type="text" id="userNote" name="userNote">--%>
<%--            <button type="submit" name="submit" value="submit">Submit</button>--%>
        </form>
    </c:if>
</c:if>
<br>
<a href="index.jsp">Search again</a>


<%@include file="template/footer.jsp"%>