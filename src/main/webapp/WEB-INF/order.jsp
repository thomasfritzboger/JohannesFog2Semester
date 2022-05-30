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
                <form>
                    <tr>
                        <td>${requestApproved.carportId}</td>
                        <td>${requestApproved.userId}</td>
                        <td>${requestApproved.created}</td>
                        <td>${requestApproved.accepted}</td>
                        <td>
                            <button formaction="fc/itemList" class="btn btn-secondary">
                                <input type="hidden" name="command" value="itemList">Se stykliste
                            </button>
                        </td>
                        <input id="getCarportId0" name="getCarportId0" value="${requestApproved.carportId}" hidden>
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>