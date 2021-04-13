<%@include file="taglib.jsp"%>
<c:set var="title" value="Forgot password" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<%--${updateMessage}--%>
<%--<c:set var="updateMessage" value="" scope="session" />--%>
<form action="resetPasswordAction">

    <div class="form-group">
        <label for="userEmail">Enter email address</label>
        <input type="email" class="form-control" name="userEmail" id="userEmail" placeholder="Enter email" required>
    </div>
    <div class="form-group">
        <label for="userEmail">Enter username</label>
        <input type="text" class="form-control" name="userUsername" id="userUsername" required>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>

</form>

<%@include file="template/footer.jsp"%>