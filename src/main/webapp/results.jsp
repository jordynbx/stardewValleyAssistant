<%@include file="taglib.jsp"%>
<c:set var="title" value="Search Result" />
<%--<%@include file="template/head.jsp"%>--%>
<%--<%@include file="template/header.jsp"%>--%>
<%--<%@include file="template/nav.jsp"%>--%>
<%--<jsp:include page="template/head.jsp" />--%>
<%--<jsp:include page="template/header.jsp" />--%>
<%--<jsp:include page="template/nav.jsp" />--%>
<c:import url="template/head.jsp" />
<c:import url="template/header.jsp" />
<c:import url="template/nav.jsp" />

<%--Show alert if note was successfully added--%>
<c:if test="${showUpdateMessage}">
    <div class="alert alert-dismissible alert-${messageType} pt-1 mt-1">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        ${updateMessage}
    </div>
    <c:set var="showUpdateMessage" value="" scope="session" />
    <c:set var="updateMessage" value="" scope="session" />
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

        <c:import url="resultsdisplay/recentSearches.jsp" />

        <c:import url="resultsdisplay/itemCards.jsp" />

        <c:import url="resultsdisplay/userNotes.jsp" />

    </div>
</c:if>
<br>

<a href="home">Search again</a>

<c:import url="template/footer.jsp" />