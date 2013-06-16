<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="header">
    <h1>Requested a single Coworker by ID</h1>
</div>
<div id="entityDetails">
    <div id="coworkerDetails">
    <table>
        <tr>
            <td>${coworker.coworkerId}</td>
            <td>${coworker.firstName}</td>
            <td>${coworker.lastName}</td>
            <td><fmt:formatDate value="${coworker.creationDate}" /></td>
            <td><fmt:formatDate value="${coworker.lastUpdatedDate}" /></td>
        </tr>
     </table>
    </div>
</div>