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
    <h1 class="text-center">${item.name}</h1>

    <div class="clearfix">

        <div class="card border-info mb-3" id="results-searches" style="max-width: 20rem;">
            <div class="card-body">
                <h4 class="card-title">Recent Searches</h4>
                <c:forEach var="searchString" items="${userSearchItemNames}">
                    <p class="card-text"><a href="searchItem?searchTerm=${searchString}&submit=search">${searchString}</a></p>
                </c:forEach>
            </div>
        </div>

        <div class="card-deck my-cards">

            <div class="card text-white bg-success mb-3 my-card" style="max-width: 20rem;">
                <div class="card-body">
                    <h4 class="card-title">Seeds</h4>
                    <p class="card-text">
                        Purchase price: ${crop.seedPrice}<br>
                        Sell price: ${crop.sellPrice}<br>
                    </p>
                </div>
            </div>

            <div class="card text-white bg-warning mb-3 my-card" style="max-width: 20rem;">
                <div class="card-body">
                    <h4 class="card-title">Season</h4>
                    <p class="card-text">${crop.season}</p>
                </div>
            </div>
        </div>

        <div class="card-deck my-cards">

            <div class="card text-white bg-info mb-3 my-card" style="max-width: 20rem;">
                <div class="card-body">
                    <h4 class="card-title">Bundles</h4>
                    <p class="card-text">${crop.bundles}</p>
                </div>
            </div>

            <div class="card text-white bg-danger mb-3 my-card" style="max-width: 20rem;">
                <div class="card-body">
                    <h4 class="card-title">Recipes</h4>
                    <p class="card-text">${crop.recipes}</p>
                </div>
            </div>
        </div>

        <div class="w-75 float-left">
        <h2>Notes</h2>
        <c:if test="${!pageContext.request.isUserInRole('user')}">
            <p><a href="signup">Create an account</a> or <a href="login.jsp">log in</a> to manage your notes!</p>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('user')}">
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
                    <input type="text" class="form-control" id="userNote" name="userNote" placeholder="Enter note" >
                    <small id="noteLimit" class="form-text text-muted">Notes have a 50 character limit</small>
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

        </div>
    </div>
</c:if>
<br>


<a href="index.jsp">Search again</a>


<%@include file="template/footer.jsp"%>