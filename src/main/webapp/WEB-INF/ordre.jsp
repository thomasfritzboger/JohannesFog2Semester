<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>
        <h3>Alle godkendte ordre</h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Ordre id</th>
                <th>Bruger id</th>
                <th>Købsdato</th>
                <th>Afsluttet</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="requestApproved" items="${sessionScope.requestApproved}">
                <tr>
                    <td>${requestApproved.carportId}</td>
                    <td>${requestApproved.userId}</td>
                    <td>${requestApproved.created}</td>
                    <td>${requestApproved.accepted}</td>
                    <td>
                        <form>
                            <button formaction="" name="stykliste" value=""
                                    class="btn btn-secondary">
                                Se stykliste
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>