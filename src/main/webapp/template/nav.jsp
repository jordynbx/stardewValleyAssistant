<%@include file="../taglib.jsp"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light" id="nav-location">
    <a class="navbar-brand" href="index.jsp">Stardew Valley Assistant</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor03">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="home">Home
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
                <a class="nav-link" href="favorites">Favorites</a>
            </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="contact">Contact</a>
            </li>
            <c:if test="${loggedIn}">
            <li class="nav-item">
                <a class="nav-link" href="account">Account</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logoutAction">Log Out</a>
            </li>
            </c:if>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="get" action="searchItem">
            <input class="form-control mr-sm-2" type="text" id="searchTerm"
                   name="searchTerm" placeholder="Enter item name">
            <button class="btn btn-info my-2 my-sm-0" type="submit"
                    name="submit" value="search">Search</button>
        </form>
    </div>
</nav>