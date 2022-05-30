<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Profil
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <h3>Dine forespørgsler</h3>

        <button data-bs-toggle="modal" data-bs-target="#exampleModal2" name="coverageEditor"
                class="btn btn-info">
            Rediger kontaktoplysninger
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Rediger kontaktoplysninger</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Din email er: ${user.getEmail()} <br>
                        Dit tlfnr. er: ${user.getPhoneNumber()} <br>
                        Din adresse er: ${user.getAddress()} (${user.getPostalCode()}) <br> <br>
                        <input type="hidden" name="command" value="updateUserInformation">
                        <button id="newEmail" name="newEmail" class="btn btn-info" data-bs-toggle="modal"
                                data-bs-target="#exampleModal3">Rediger email
                        </button>
                        <br><br>
                        <button id="newPassword" name="newPassword" class="btn btn-info" data-bs-toggle="modal"
                                data-bs-target="#exampleModal4">Rediger kodeord
                        </button>
                        <br><br>
                        <button id="newPhoneNumber" name="newPhoneNumber" class="btn btn-info" data-bs-toggle="modal"
                                data-bs-target="#exampleModal5">Rediger telefonnr.
                        </button>
                        <br>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal til ændring af email -->
        <div class="modal fade" id="exampleModal3" tabindex="-1" aria-labelledby="exampleModalLabel3"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Rediger din email</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="fc/profile">
                            <input type="hidden" name="command" value="profile">
                            <label for="enterNewEmail">Indtast ny email: </label> <br>
                            <input type="email" id="enterNewEmail" name="enterNewEmail" required> <br> <br>
                            <button class="btn btn-info"
                                    onclick="return confirm('Er du sikker på du vil ændre din email?')">Bekræft
                            </button>
                        </form>
                        <br>
                        <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#exampleModal2">Gå Tilbage
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal til ændring af kodeord -->
        <div class="modal fade" id="exampleModal4" tabindex="-1" aria-labelledby="exampleModalLabel4"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Rediger dit kodeord</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="fc/profile">
                            <input type="hidden" name="command" value="profile">
                            <label for="newPassword">Indtast nyt kodeord: </label> <br>
                            <input type="password" id="newPassword" name="newPassword" minlength="4" placeholder="Kode"
                                   required> <br> <br>

                            <label for="passwordRepeated">Bekræft kodeord: </label> <br>
                            <input type="password" id="passwordRepeated" name="passwordRepeated" minlength="4"
                                   oninput="check(this)" required> <br> <br>

                            <button class="btn btn-info"
                                    onclick="return confirm('Er du sikker på du vil ændre dit kodeord?')">Bekræft
                            </button>

                            <script type='text/javascript'>
                                function check(input) {
                                    if (input.value != document.getElementById('newPassword').value) {
                                        input.setCustomValidity('Koderne skal være ens.');
                                    } else {
                                        // input is valid -- reset the error message
                                        input.setCustomValidity('');
                                    }
                                }
                            </script>
                            <br/><br/>
                        </form>

                        <br>
                        <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#exampleModal2">Gå Tilbage
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal til ændring af telefonnr-->
        <div class="modal fade" id="exampleModal5" tabindex="-1" aria-labelledby="exampleModalLabel5"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Rediger dit telefonnr.</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="fc/profile">
                            <input type="hidden" name="command" value="profile">
                            <label for="newPhoneNumber">Indtast nyt telefonnr.: </label> <br>
                            <input type="tel" id="newPhoneNumber" placeholder="40404040" name="newPhoneNumber"
                                   pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}" required> <br> <br>
                            <button class="btn btn-info"
                                    onclick="return confirm('Er du sikker på du vil ændre dit telefonnr?')">Bekræft
                            </button>
                        </form>
                        <br>
                        <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#exampleModal2">Gå Tilbage
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <br> <br>
        <c:if test="${carportRequestByUser.isEmpty()}">
            Du har ingen forespørgsler.
            <a href="${pageContext.request.contextPath}/fc/customerLogin?command=customerLogin" class="link-primary">Klik
                her</a> for at komme igang med at bygge din helt egen carport.
        </c:if>

        <c:if test="${!carportRequestByUser.isEmpty()}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Carport nummer</th>
                    <th>Carport størrelse <br> bredde * længde * højde</th>
                    <th>Redskabsrum + størrelse?</th>
                    <th>Oprettet</th>
                    <th>Stykliste</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="carport" items="${carportRequestByUser}">
                    <form>

                        <tr>
                            <td>${carport.getCarportId()}</td>
                            <td>${carport.getWidth()}x${carport.getLength()}x${carport.getHeight()}</td>
                            <td><c:if test="${carport.getHasShed() == false}">Intet redskabsrum valgt</c:if>
                                <c:if test="${carport.getHasShed() == true}">${carport.getShed().getWidth()}x${carport.getShed().getLength()}, ${carport.getShed().getPlacement()}</c:if></td>
                            <td>d. ${carport.getCreated()}</td>

                            <td><c:if test="${carport.isConfirmed()}">Din carport er igang med at blive pakket.
                                <br> Ring og aftal levering.
                                <br>

                                <div class="container-itemList-and-drawing-buttons-profile">
                                    <form action="fc/itemList">
                                        <input type="hidden" name="command" value="itemList">
                                        <button class="btn btn-primary" style="margin: 2px">
                                            Se stykliste
                                        </button>
                                        <input id="getCarportId0" name="getCarportId0" value="${carport.getCarportId()}"
                                               hidden>
                                    </form>

                                    <form action="fc/viewDrawing">
                                        <input type="hidden" name="command" value="viewDrawing">
                                        <button class="btn btn-primary" style="margin: 2px">
                                            Se skitse
                                        </button>
                                        <input class="getCarportId1" name="getCarportId1"
                                               value="${carport.getCarportId()}" hidden>
                                    </form>
                                </div>

                            </c:if>

                                <c:if test="${carport.isConfirmed() == false}">Afventer bekræftelse <br>
                                    <form action="fc/viewDrawing">
                                        <input type="hidden" name="command" value="viewDrawing">
                                        <button class="btn btn-primary">
                                            Se skitse
                                        </button>
                                        <input class="getCarportId1" name="getCarportId1"
                                               value="${carport.getCarportId()}" hidden>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </form>
                </c:forEach>
                </tbody>
            </table>

        </c:if>

    </jsp:body>

</t:pagetemplate>