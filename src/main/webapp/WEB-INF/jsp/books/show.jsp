<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<h2>Book Information</h2>
<table>
    <tr>
        <th>ISBN</th>
        <td><b>${book.isbn}</b></td>
    </tr>
    <tr>
        <th>Title</th>
        <td>${book.title}</td>
    </tr>
    <tr>
        <th>Edition</th>
        <td>${book.edition}</td>
    </tr>
    <tr>
        <th>Number of pages</th>
        <td>${book.pages}</td>
    </tr>
    <tr>
        <th>Publication date</th>
        <td>${book.publicationDate}</td>
    </tr>
    <tr>
        <th>Publisher</th>
        <td>${book.publisher.name}</td>
    </tr>
    <tr>
        <th>Author(s)</th>
        <td>
            <c:forEach var="author" items="${book.authors}">
                ${author.firstName} ${author.lastName} &nbsp;
            </c:forEach>
        </td>
    </tr>

</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
