<%@include file="taglib.jsp"%>
<c:set var="title" value="Log in" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>
<%--TODO make sure this update message works--%>
${updateMessage}
<FORM ACTION="j_security_check" METHOD="POST">
<%--    <TABLE>--%>
<%--        <TR><TD>User name: <INPUT TYPE="TEXT" NAME="j_username">--%>
<%--        <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password">--%>
<%--        <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">--%>
<%--    </TABLE> --%>
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


<%@include file="template/footer.jsp"%>