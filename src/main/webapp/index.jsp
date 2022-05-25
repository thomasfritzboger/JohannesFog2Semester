<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>
<div class="container-frontpage">
        <!--Open modal if user not logged in-->
        <c:if test="${sessionScope.user == null}">
            <input type="image" src="${pageContext.request.contextPath}/images/carport_bygselv.png" name="carport" class="btTxt submit" id="123" data-bs-toggle="modal" data-bs-target="#exampleModal" />
        </c:if>

        <!--If logged in only link to carport factory TODO-->
        <c:if test="${sessionScope.user != null}">
            <a href="${pageContext.request.contextPath}/fc/kundeLogin?command=kundeLogin">
                <img src="${pageContext.request.contextPath}/images/carport_bygselv.png">
            </a>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <div class="d-flex justify-content-center" style="padding-top: 30px;">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Start din rejse!
                </button>
            </div>
        </c:if>
</div>


        <div class="modal fade" id="modal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Opret login</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="fc/lavbruger" method="post">
                            <input type="hidden" name="command" value="lavbruger"/>

                            <label for="emailny">Email: </label> <br>
                            <input type="email" id="emailny" name="emailny" placeholder="Indtast email" required> <br> <br>

                            <label for="telefonnr">Telefonnr: </label> <br>
                            <input type="tel" id="telefonnr" placeholder="Indtast telefonnummer" name="telefonnr" pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}" required> <br> <br>

                            <label for="addresse">Addresse: </label> <br>
                            <input type="text" id="addresse" name="addresse" placeholder="Indtast addresse" minlength="4" required>
                            <br> <br>

                            <label for="postnr">Postnr.: </label> <br>
                            <input type="text" id="postnr" name="postnr" pattern="[0-9]{4}" placeholder="Fire cifre" required> <br><br>

                            <label for="passwordny">Kodeord: </label> <br>
                            <input type="password" id="passwordny" name="passwordny" minlength="4" placeholder="Indtast kode" required> <br> <br>

                            <label for="passwordRepeated">Bekræft kodeord: </label> <br>
                            <input type="password" id="passwordRepeated" name="passwordRepeated" placeholder="Gentag kode" minlength="4" oninput="check(this)" required> <br> <br>

                            <script type='text/javascript'>
                                function check(input) {
                                    if (input.value != document.getElementById('passwordny').value) {
                                        input.setCustomValidity('Koderne skal være ens.');
                                    } else {
                                        // input is valid -- reset the error message
                                        input.setCustomValidity('');
                                    }
                                }
                            </script>


                            <button type="submit" class="btn btn-primary" >
                                Bliv medlem
                            </button>

                            <br> <br>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"
                                        aria-label="Close" data-dismiss="#exampleModal">
                                    Allerede medlem? Klik her.
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </jsp:body>

</t:pagetemplate>