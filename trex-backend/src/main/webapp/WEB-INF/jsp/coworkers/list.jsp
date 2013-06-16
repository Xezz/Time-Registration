<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="entityDetails">
    <div id="coworkerDetails">
    <table>
        <c:forEach var="coworker" items="${coworkers}">
        <tr>
            <td>${coworker.coworkerId}</td>
            <td>${coworker.firstName}</td>
            <td>${coworker.lastName}</td>
            <td><fmt:formatDate value="${coworker.creationDate}" /></td>
            <td><fmt:formatDate value="${coworker.lastUpdatedDate}" /></td>
        </tr>
        </c:forEach>
     </table>
    </div>
</div>