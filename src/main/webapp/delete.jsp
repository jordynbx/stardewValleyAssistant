<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Delete Note" />
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />

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
<c:import url="template/footer.jsp" />