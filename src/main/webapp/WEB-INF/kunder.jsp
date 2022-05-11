<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Kunder
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>


        <!--knap til at fremsøge kunder. Der tjekkes længere nede om en ekstra tabel med kundeinformation bør vises-->
        <button class="btn btn-info">
            Fremsøg kunde
        </button>

        <!--TODO tjek hvis en kunde er slået op. Hvis ja, så fremvis tabellen og headeren-->
        <h3>Her er den kunde du søgte efter</h3>

        <!--Hvis kunden ikke findes så hvis - kan evt. slettes-->
        <h3>Kunden du søgte kunne ikke findes</h3>


        <!--Tabel med alle dine kunder-->
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Kunde ID</th>
                <th>Email</th>
                <th>Telefonnr.</th>
                <th>Adresse / postnr</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="kunder" items="${sessionScope.customerlist}">
                <tr>
                    <td>${kunder.userId}</td>
                    <td>${kunder.email}</td>
                    <td>${kunder.phoneNumber}</td>
                    <td>${kunder.postalCode}, ${kunder.address}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>



    </jsp:body>

</t:pagetemplate>