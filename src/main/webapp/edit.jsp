<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Edit Note" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<h2>Edit Note</h2>
<p>Current note: ${note.noteContent}</p>

<form action="editNoteAction" method="get">
    <div class="form-group">
        <label for="editNote">New note:</label>
        <input type="text" class="form-control" id="editNote" name="editNote" value="${note.noteContent}">
    </div>
    <input type="hidden" id="noteId" name="noteId" value="${note.id}">
    <button type="submit" name="submit" class="btn btn-primary" value="update">Submit</button>
</form>
<%@include file="template/footer.jsp"%>