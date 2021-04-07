<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Result" />
<%@include file="template/head.jsp"%>
<%@include file="template/header.jsp"%>
<%@include file="template/nav.jsp"%>

<%--Show alert if note was successfully added--%>
<c:if test="${showUpdateMessage}">
    <div class="alert alert-dismissible alert-${messageType} pt-1 mt-1">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        ${updateMessage}
    </div>
</c:if>

<%--Show alert if the item wasn't found--%>
<c:if test="${!success}">
    <h2>Search Results:</h2>
    <p>${message}</p>
</c:if>

<%--If item was found, show the item data--%>
<c:if test="${success}">
    <h1 class="text-center">${item.name}</h1>

    <div class="clearfix">

        <%@include file="resultsdisplay/recentSearches.jsp"%>

        <%@include file="resultsdisplay/itemCards.jsp"%>

        <%@include file="resultsdisplay/userNotes.jsp"%>

    </div>
</c:if>
<br>

<a href="index.jsp">Search again</a>

<%@include file="template/footer.jsp"%>