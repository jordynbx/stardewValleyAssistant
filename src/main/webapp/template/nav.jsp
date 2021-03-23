<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index.jsp">Stardew Valley Assistant</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor03">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <c:if test="${!loggedIn}">
            <li class="nav-item">
                <a class="nav-link" href="signup">Sign Up</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="loginAction">Login</a>
            </li>
            </c:if>
            <c:if test="${loggedIn}">
            <li class="nav-item">
                <a class="nav-link" href="logoutAction">Log Out</a>
            </li>
            </c:if>
        <%--            <li class="nav-item">--%>
<%--                <a class="nav-link" href="#">About</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item dropdown">--%>
<%--                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>--%>
<%--                <div class="dropdown-menu">--%>
<%--                    <a class="dropdown-item" href="#">Action</a>--%>
<%--                    <a class="dropdown-item" href="#">Another action</a>--%>
<%--                    <a class="dropdown-item" href="#">Something else here</a>--%>
<%--                    <div class="dropdown-divider"></div>--%>
<%--                    <a class="dropdown-item" href="#">Separated link</a>--%>
<%--                </div>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--        <form class="form-inline my-2 my-lg-0">--%>
<%--            <input class="form-control mr-sm-2" type="text" placeholder="Search">--%>
<%--            <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>--%>
<%--        </form>--%>
    </div>
</nav>