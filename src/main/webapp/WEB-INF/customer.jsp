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
        <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#searchCustomerModal">
            Fremsøg kunde
        </button>
        <br>
        <br>
        <!-- Modal -->
        <div class="modal fade" id="searchCustomerModal" tabindex="-1" aria-labelledby="searchCustomerModal"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="searchCustomerModal">Kundesøgning</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="fc/customer" method="post">
                            <input type="hidden" name="command" value="customer"/>

                            <label for="searchedEmail">Søg efter kunde med deres email:</label>
                            <br>
                            <input type="email" id="searchedEmail" name="searchedEmail" required/>
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

        <c:if test="${searchedCustomer != null}">
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
                    <td>${searchedCustomer.userId}</td>
                    <td>${searchedCustomer.email}</td>
                    <td>${searchedCustomer.phoneNumber}</td>
                    <td>${searchedCustomer.postalCode}, ${searchedCustomer.address}</td>
                </tr>
                </tbody>
            </table>

        </c:if>

        <c:if test="${searchedCustomer == null && searchedEmail != null}">
            <!--Hvis kunden ikke findes så hvis - kan evt. slettes-->
            <b><h5>Kunde med email: (${searchedEmail}) kunne ikke findes</h5></b>
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
            <c:forEach var="customer" items="${sessionScope.customerList}">
                <tr>
                    <td>${customer.userId}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phoneNumber}</td>
                    <td>${customer.postalCode}, ${customer.address}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>