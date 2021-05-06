<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Delete Note" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<h2>Delete Note</h2>
<p>Are you sure you want to delete this note?
    <br/>${note.noteContent}
    <br/><br/>
    Click Delete Note to confirm, or cancel to return to the results page.
</p>

<form action="deleteNoteAction" method="post">
    <input type="hidden" id="noteId" name="noteId" value="${note.id}">
    <button type="submit" name="submit" class="btn btn-danger" value="delete">Delete Note</button>
    <button type="submit" name="submit" class="btn btn-warning" value="cancel">Cancel</button>
</form>
<%@include file="template/footer.jsp"%>