<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglib.jsp"%>
<c:set var="title" value="Edit Note" />
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />

<h2>Edit Note</h2>
<p>Current note: ${note.noteContent}</p>

<form action="editNoteAction" method="post">
    <div class="form-group">
        <label for="editNote">New note:</label>
        <input type="text" class="form-control" id="editNote" name="editNote" value="${note.noteContent}">
    </div>
    <input type="hidden" id="noteIdEdit" name="noteIdEdit" value="${note.id}">
    <button type="submit" name="submit" class="btn btn-success" value="update">Submit</button>
    <button type="submit" name="submit" class="btn btn-warning" value="cancel">Cancel</button>
</form>
<c:import url="template/footer.jsp" />