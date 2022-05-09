<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the frontpage
    </jsp:attribute>

    <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

    <jsp:body>


            <input type="image" src="${pageContext.request.contextPath}/images/carport_bygselv.png" name="carport" class="btTxt submit" id="123" data-bs-toggle="modal" data-bs-target="#exampleModal" />


        <c:if test="${sessionScope.user == null}">
            <div class="d-flex justify-content-center" style="padding-bottom: 30px;">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Start din rejse!
                </button>
            </div>
        </c:if>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Du kan logge ind her</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="login" method="post">
                            <label for="email">Email: </label>
                            <br>
                            <input type="email" id="email" name="email" required/>
                            <br>
                            <label for="password">Kodeord: </label>
                            <br>
                            <input type="password" id="password" name="password" minlength="4" required/>
                            <br>
                            <br>
                            <button type="submit" class="btn btn-primary">
                                Log ind
                            </button>
                            <br>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal2"
                                aria-label="Close" data-dismiss="#exampleModal">
                            Ikke medlem endnu? Klik her.
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Opret login</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        <form action="createuser" method="post">

                            <label for="navn">Navn: </label> <br>
                            <input type="text" id="navn" name="navn" pattern="(?!^[aA][dD][mM][iI][nN]([iI][sS][tT][rR][aA][tT][oO][rR])?$)(?!^root$).{3,20}" required> <br> <br>
                            <label for="telefonnr">Telefonnr: </label> <br>
                            <input type="tel" id="telefonnr" placeholder="40404040" name="telefonnr" pattern="[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}" required> <br> <br>

                            <label for="emailny">Email: </label> <br>
                            <input type="email" id="emailny" name="emailny" required> <br> <br>

                            <label for="passwordny">Kodeord: </label> <br>
                            <input type="password" id="passwordny" name="passwordny" minlength="4" required> <br> <br>

                            <label for="passwordRepeated">Gentag kodeord: </label> <br>
                            <input type="password" id="passwordRepeated" name="passwordRepeated" minlength="4" required> <br> <br>

                            <button type="submit" class="btn btn-primary" formaction="createuser">
                                Bliv medlem
                            </button>

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