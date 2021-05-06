<%--        Display a user's notes and a form where they can add new notes if they are logged in--%>
<div class="w-75 float-left">
    <c:if test="${pageContext.request.isUserInRole('user')}">
        <c:if test="${isFavoriteItem}">
        <p>${item.name} is on your <a href="#">favorites</a> list! (<a href="removeFavorite?id=${item.id}">Remove from favorites</a>)</p>
        </c:if>
        <c:if test="${!isFavoriteItem}">
        <p>${item.name} is not on your <a href="#">favorites</a> list. (<a href="addFavorite?id=${item.id}">Add to favorites</a>)</p>
        </c:if>

    </c:if>

    <h2>Notes</h2>
    <c:if test="${!pageContext.request.isUserInRole('user')}">
    <p><a href="signup">Create an account</a> or <a href="loginAction">log in</a> to manage your notes!</p>
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
        <br>
        <form action="addNote" method="post">

            <div class="form-group">
                <label for="userNote">Add a new note:</label>
                <input type="text" class="form-control" id="userNote" name="userNote" placeholder="Enter note" required>
                <small id="noteLimit" class="form-text text-muted">Notes have a 50 character limit</small>
            </div>

            <input type="hidden" id="itemId" name="itemId" value="${item.id}">
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </c:if>
</div>