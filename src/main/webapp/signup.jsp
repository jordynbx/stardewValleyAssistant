<%@include file="taglib.jsp"%>
<c:set var="title" value="Sign Up" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

${errorMessage}
<c:set var="errorMessage" value="" scope="session" />
<form action="signUpAction" method="post">

    <div class="form-group">
        <label for="userEmail">Email address</label>
        <input type="email" class="form-control" name="userEmail" id="userEmail" value="${enteredEmail}" placeholder="Enter email" required>
    </div>
    <div class="form-group">
        <label for="userEmail">Username</label>
        <input type="text" class="form-control" name="userUsername" id="userUsername" value="${enteredUsername}" maxlength="20" placeholder="Enter username" required>
    </div>
    <div class="form-group">
        <label for="userPassword">Password</label>
        <input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Password" required>
    </div>
    <div class="form-group">
        <label for="confirmPassword">Re-enter Password</label>
        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Password" required>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>

</form>

<%@include file="template/footer.jsp"%>