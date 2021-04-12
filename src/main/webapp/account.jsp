<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Account" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<h2>Manage Account</h2>
<p>Username: ${currentUser}</p>

${message}
<c:set var="message" value="" scope="session" />

<h4>Change Password</h4>
<form action="changePasswordAction" method="get">
    <div class="form-group">
        <label for="currentPassword">Password</label>
        <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Enter current password" required>
    </div>
    <div class="form-group">
        <label for="newPassword">Enter new password</label>
        <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Enter new password" required>
    </div>
    <div class="form-group">
        <label for="confirmNewPassword">Re-enter Password</label>
        <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" placeholder="Confirm new password" required>
    </div>
    <button type="submit" name="submit" class="btn btn-success" value="confirm">Confirm</button>
</form>
<%@include file="template/footer.jsp"%>