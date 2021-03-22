<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Result" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<%--TODO edit the update messages and attributes so there is one success and one fail--%>
<%--Show alert if note was successfully added--%>
<c:if test="${showUpdateMessage}">
    <div class="alert alert-dismissible alert-success pt-1 mt-1">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        ${updateMessage}
    </div>
</c:if>
<%--TODO show alert if there was an issue adding the note?--%>

<%--Show alert if the item wasn't found--%>
<c:if test="${!success}">
    <h2>Search Results:</h2>
    <p>${message}</p>
</c:if>

<%--If item was found, show the item data--%>
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
        <h3>Notes</h3>
        <table>
            <c:forEach var="note" items="${itemNotes}">
                <tr>
                    <td class="item-td">${note.noteContent}</td>
                    <td class="item-td"><a href="edit?id=${note.id}">Edit</a></td>
                    <td class="item-td"><a href="delete?id=${note.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    <br/>
        <form action="addUserInput" method="get">

            <div class="form-group">
                <label for="userNote">Add a new note:</label>
                <input type="text" class="form-control" id="userNote" name="userNote" placeholder="Enter note">
            </div>
<%--            <div class="form-check">--%>
<%--                <label class="form-check-label">--%>
<%--                    <input class="form-check-input" type="checkbox" name="addToFavorites" value="addToFavorites">--%>
<%--                    Add to favorites?--%>
<%--                </label>--%>
<%--            </div>--%>
            <input type="hidden" id="itemId" name="itemId" value="${item.id}">
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </c:if>
</c:if>
<br>
<a href="index.jsp">Search again</a>


<%@include file="template/footer.jsp"%>