<%@include file="taglib.jsp"%>
<c:set var="title" value="Contact" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<p class="pt-2">Notice a mistake or want to request a new feature? Send me a message!</p>

<c:if test="${showUpdateMessage}">
    <div class="alert alert-dismissible alert-${messageType} pt-1 mt-1">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        ${emailMessage}
    </div>
</c:if>

<c:set var="emailMessage" value="" scope="session" />
<c:set var="showUpdateMessage" value="false" scope="session" />

<form action="contactAction">

    <div class="form-group">
        <label for="userMessageEmail">Enter email (optional):</label>
        <input type="email" class="form-control" name="userMessageEmail" id="userMessageEmail" value = "${returnAddressValue}">
    </div>
    <div class="form-group">
        <label for="userMessageSubject">Enter subject (required):</label>
        <input type="text" class="form-control" name="userMessageSubject" id="userMessageSubject" value = "${userMessageSubjectValue}">
    </div>
    <div class="form-group">
        <label for="userMessage">Enter message (required):</label>
        <input type="text" class="form-control" name="userMessage" id="userMessage" maxlength="500" value="${userMessageValue}" required>
    </div>
    <button type="submit" name="submit" class="btn btn-primary" value="submit">Submit</button>

</form>

<%@include file="template/footer.jsp"%>