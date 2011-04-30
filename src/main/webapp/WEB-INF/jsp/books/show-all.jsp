<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<h2>Books:</h2>
<table>
    <thead>
    <th>ISBN</th>
    <th>Title</th>
    <th>Number of pages</th>
    <th>Edition</th>
    <th>Publisher</th>
    </thead>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>
                <spring:url value="books/{isbn}" var="bookUrl">
                    <spring:param name="isbn" value="${book.isbn}"/>
                </spring:url>
                <a href="${fn:escapeXml(bookUrl)}">${book.isbn}</a>
            </td>
            <td>${book.title}</td>
            <td>${book.pages}</td>
            <td>${book.edition}</td>
            <td>${book.publisher.name}</td>
        </tr>
    </c:forEach>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>


