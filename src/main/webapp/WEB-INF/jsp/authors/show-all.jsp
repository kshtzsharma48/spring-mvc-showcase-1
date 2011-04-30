<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<h2>Authors:</h2>
<table>
    <thead>
    <th>First name</th>
    <th>Last name</th>
    <th>Email</th>
    <th>Books</th>
    </thead>
    <c:forEach var="author" items="${authors}">
        <tr>
            <td>${author.firstName}</td>
            <td>${author.lastName}</td>
            <td>${author.email}</td>
            <td>
                <spring:url value="authors/{authorId}/books" var="booksUrl">
                    <spring:param name="authorId" value="${author.objectId}"/>
                </spring:url>
                <a href="${fn:escapeXml(booksUrl)}">Link</a>
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>


