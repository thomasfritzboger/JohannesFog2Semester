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
        <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#fremsogkundemodal">
            Fremsøg kunde
        </button>
        <br>
        <br>

            <!-- Modal -->
            <div class="modal fade" id="fremsogkundemodal" tabindex="-1" aria-labelledby="fremsogkundemodal"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="fremsogkundemodal">Kundesøgning</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <form action="fc/kunder" method="post">
                                <input type="hidden" name="command" value="kunder"/>

                                <label for="soegtemail">Søg efter kunde med deres email:</label>
                                <br>
                                <input type="email" id="soegtemail" name="soegtemail" required/>
                                <br> <br>
                                <button type="submit" class="btn btn-dark">
                                    Søg
                                </button>
                                <br>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </td>

        <c:if test="${searchedcustomer != null}">
            <!--TODO tjek hvis en kunde er slået op. Hvis ja, så fremvis tabellen og headeren-->
            <b><h5>Fandt kunden du søgte: </h5></b>

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
                    <tr>
                        <td>${searchedcustomer.userId}</td>
                        <td>${searchedcustomer.email}</td>
                        <td>${searchedcustomer.phoneNumber}</td>
                        <td>${searchedcustomer.postalCode}, ${searchedcustomer.address}</td>
                    </tr>
                </tbody>
            </table>

        </c:if>


        <c:if test="${searchedcustomer == null && soegtemail != null}">
        <!--Hvis kunden ikke findes så hvis - kan evt. slettes-->
            <b><h5>Kunde med email: (${soegtemail}) kunne ikke findes</h5></b>
        </c:if>


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