<%@include file="taglib.jsp"%>
<c:set var="title" value="Forgot password" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

${updateMessage}
<c:set var="updateMessage" value="" scope="session" />
<form action="newPasswordAction">
    <input type="hidden" id="token" name="token" value="${token}">
    <div class="form-group">
        <label for="newPassword">Enter new password</label>
        <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Password" required>
    </div>
    <div class="form-group">
        <label for="confirmNewPassword">Re-enter new password</label>
        <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" placeholder="Password" required>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>

</form>

<%@include file="template/footer.jsp"%>