<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Forespørgsler
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <h3>Her er listen over afventende forespørgsler</h3>
        <button formaction="" name="prisforslag" value=""
                class="btn btn-secondary">
            Prisforslag
        </button>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>Kundes id</th>
                <th>Dato oprettet</th>
                <th>Dækningsgrad</th>
                <th>Pris for carport</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="carportRequest" items="${sessionScope.carportRequest}">
                <tr>
                    <td>${carportRequest.carportId}</td>
                    <td>${carportRequest.userId}</td>
                    <td>${carportRequest.created}</td>
                    <td>${carportRequest.coverage}</td>
                    <td>${carportRequest.price}</td>
                    <td></td>

                    <td>
                        <form method="post">
                            <button formaction="" name="forespoergsel" value=""
                                    class="btn btn-secondary">
                                Se forespørgsel
                            </button>
                            <button formaction="" name="godkend" value=""
                                    class="btn btn-secondary">
                                Godkend
                            </button>
                            <button formaction="" name="afvis" value=""
                                    class="btn btn-secondary">
                                Afvis
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>