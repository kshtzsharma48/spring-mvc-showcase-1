<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>


<h2>Books written by ${author.firstName} ${author.lastName}</h2>
<ul>
    <c:forEach var="book" items="${books}">
        <li>
            <!-- TODO: need to find a way to better generate the url here -->
            <spring:url value="/api/v1/bookstore/books/{isbn}" var="bookUrl">
                <spring:param name="isbn" value="${book.isbn}"/>
            </spring:url>
            <a href="${fn:escapeXml(bookUrl)}">${book.title}</a>
        </li>
    </c:forEach>

</ul>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>



