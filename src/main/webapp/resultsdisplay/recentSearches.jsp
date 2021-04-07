<%--    Display a list of recent searches for logged in users    --%>
<div class="card border-info mb-3" id="results-searches" style="max-width: 20rem;">
    <div class="card-body">
        <h4 class="card-title">Recent Searches</h4>
        <c:if test="${pageContext.request.isUserInRole('user')}">
            <c:forEach var="searchString" items="${userSearchItemNames}">
                <p class="card-text"><a href="searchItem?searchTerm=${searchString}&submit=search">${searchString}</a></p>
            </c:forEach>
        </c:if>
        <c:if test="${!pageContext.request.isUserInRole('user')}">
            <p><a href="signup">Create an account</a> or <a href="loginAction">log in</a> to track your recent searches.</p>
        </c:if>
    </div>
</div>