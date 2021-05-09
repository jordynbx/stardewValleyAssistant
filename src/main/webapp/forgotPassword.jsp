<%@include file="taglib.jsp"%>
<c:set var="title" value="Forgot password" />
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />

${updateMessage}
<c:set var="updateMessage" value="" scope="session" />
<form action="resetPasswordAction" method="post">

    <div class="form-group">
        <label for="userEmail">Enter email address</label>
        <input type="email" class="form-control" name="userEmail" id="userEmail" placeholder="Enter email associated with account" required>
    </div>
    <div class="form-group">
        <label for="userUsername">Enter username</label>
        <input type="text" class="form-control" name="userUsername" id="userUsername" placeholder="Enter username" required>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>

</form>

<c:import url="template/footer.jsp" />