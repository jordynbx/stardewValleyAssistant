<%@include file="taglib.jsp"%>
<c:set var="title" value="Log in" />
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />

${updateMessage}
<c:set var="updateMessage" value="" scope="session" />
<FORM ACTION="j_security_check" METHOD="POST">

    <div class="form-group">
        <label for="user-username">Username</label>
        <input TYPE="TEXT" NAME="j_username"  class="form-control" id="user-username"
               placeholder="Enter username">
    </div>
    <div class="form-group">
        <label for="user-password">Password</label>
        <input TYPE="PASSWORD" NAME="j_password" class="form-control" id="user-password" placeholder="Password">
    </div>
     <INPUT TYPE="SUBMIT" class="btn btn-info" VALUE="Log In">
</FORM>

<a href="forgotPassword.jsp">Forgot password?</a>

<c:import url="template/footer.jsp" />