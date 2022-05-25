<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Lagerstyring
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>
    <div class="container-knapper-lager-side">
        <div>
            <button type="button" data-bs-toggle="modal" data-bs-target="#exampleModal2" name="priceEditor"
                    class="btn btn-secondary" style="margin-right: 5px"> Skift pris på produkt </button>
            <br>
        </div>

        <div>
            <button type="button" data-bs-toggle="modal" data-bs-target="#descriptionEditor" name="descriptionEditor"
                    class="btn btn-secondary"> Skift produkt beskrivelse </button>
            <br>
        </div>
    </div>

        <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Indtast produkt ID og hvad den nye pris skal være</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="fc/lager" >
                            <input type="hidden" name="command" value="lager">
                            <label for="produktId">Produkt id:</label>
                            <br>
                            <input type="number" id="produktId" name="produktId" required min="1" max="${sessionScope.LagerListe.size()}"/>
                            <br>
                            <br>
                            <label for="nyPris">Ny pris:</label>
                            <br>
                            <input type="number" id="nyPris" name="nyPris" step="0.01"/>
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

        <div class="modal fade" id="descriptionEditor" tabindex="-1" aria-labelledby="descriptionEditor"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="descriptionEditor2">Indtast produkt ID og hvad den nye beskrivelse skal være</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="fc/lager" >
                            <input type="hidden" name="command" value="lager">
                            <label for="description">Produkt id:</label>
                            <br>
                            <input type="number" id="description" name="description" required min="1" max="${sessionScope.LagerListe.size()}"/>
                            <br>
                            <br>
                            <label for="nyPris">Ny beskrivelse:</label>
                            <br>
                            <input type="text" id="nyBeskrivelse" name="nyBeskrivelse"/>
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

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Produkt ID</th>
                    <th>Produkt Beskrivelse</th>
                    <th>Unit pris</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="LagerListe" items="${sessionScope.LagerListe}">
                    <tr>
                        <td>${LagerListe.lagerId}</td>
                        <td>${LagerListe.lagerDescription}</td>
                        <td>${LagerListe.lagerPrice}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>