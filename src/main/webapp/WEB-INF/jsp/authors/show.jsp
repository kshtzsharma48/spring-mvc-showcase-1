<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Author Information</h2>
<table>
    <tr>
        <th>Name</th>
        <td><b>${author.firstName} ${author.lastName}</b></td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${author.email}</td>
    </tr>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>

