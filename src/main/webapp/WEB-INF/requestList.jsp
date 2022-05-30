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
        <button type="button" data-bs-toggle="modal" data-bs-target="#exampleModal2" name="coverageEditor"
                class="btn btn-secondary">
            Skift dækningsbidrag på carport forespørgelse
        </button>

        <br>
        <br>

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
                        <span style="float: left">
                        <form>
                            <button formaction="confirmCarportRequest" name="approve"
                                    value="${carportRequest.carportId}"
                                    class="btn btn-secondary" onclick="return confirm('Skal den godkendes?')">
                                <input type="hidden" name="command" value="confirmCarportRequest">
                                Godkend
                            </button>
                        </form>
                        </span>
                        <span style="float: left; margin-left: 5px">

                        <form>
                            <button formaction="deleteRequest" name="decline" value="${carportRequest.carportId}"
                                    class="btn btn-secondary" onclick="return confirm('Skal den afvises?')">
                                <input type="hidden" name="command" value="deleteRequest">
                                Afvis
                            </button>
                        </form>
                        </span>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2"
                             aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Indtast hvilken carport det
                                            vedrører, og hvad det nye dækningsbidrag skal være.</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">

                                        <form action="updateCarportPrice">
                                            <input type="hidden" name="command" value="updateCarportPrice">
                                            <label for="carportId">Carports id:</label>
                                            <br>
                                            <input type="number" id="carportId" name="carportId" required min="1"/>
                                            <br>
                                            <br>
                                            <div>
                                                <label for="newCoverage">Vælg dækningsgrad: </label>
                                                <br>
                                                <select class="styled-select-v2" name="newCoverage" id="newCoverage">
                                                    <option value="40">40</option>
                                                    <option value="35">35</option>
                                                    <option value="30">30</option>
                                                    <option value="25">25</option>

                                                </select>
                                            </div>
                                            <br>
                                            <br>
                                            <button type="submit" class="btn btn-dark">
                                                Bekræft ændring
                                            </button>
                                            <br>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>