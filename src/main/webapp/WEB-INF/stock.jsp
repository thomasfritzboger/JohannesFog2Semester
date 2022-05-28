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
    <div class="container-buttons-stock-page">
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

                        <form action="fc/stock" >
                            <input type="hidden" name="command" value="stock">
                            <label for="productId">Produkt id:</label>
                            <br>
                            <input type="number" id="productId" name="productId" required min="1" max="${sessionScope.stockList.size()}"/>
                            <br>
                            <br>
                            <label for="newPrice">Ny pris:</label>
                            <br>
                            <input type="number" id="newPrice" name="newPrice" step="0.01"/>
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

                        <form action="fc/stock" >
                            <input type="hidden" name="command" value="stock">
                            <label for="description">Produkt id:</label>
                            <br>
                            <input type="number" id="description" name="description" required min="1" max="${sessionScope.LagerListe.size()}"/>
                            <br>
                            <br>
                            <label for="newPrice">Ny beskrivelse:</label>
                            <br>
                            <input type="text" id="newDescription" name="newDescription"/>
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
                <c:forEach var="stockList" items="${sessionScope.stockList}">
                    <tr>
                        <td>${stockList.stockId}</td>
                        <td>${stockList.stockDescription}</td>
                        <td>${stockList.unitPrice}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>