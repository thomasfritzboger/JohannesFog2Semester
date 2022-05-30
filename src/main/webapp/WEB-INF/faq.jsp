<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Ofte stillede spørgsmål
    </jsp:attribute>

    <jsp:attribute name="footer">
            About
    </jsp:attribute>

    <jsp:body>

        <div class="container-faq">
            <div class="div-around-accordion">
                <div class="accordion accordion-flush" id="accordionFlushExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingOne">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#flush-collapseOne" aria-expanded="false"
                                    aria-controls="flush-collapseOne">
                                Hvorfor kan man ikke vælge sin helt specifikke skurstørrelse?
                            </button>
                        </h2>
                        <div id="flush-collapseOne" class="accordion-collapse collapse"
                             aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Skur størrelsen er valgt så den passer med en spærafstand, denne
                                varierer derfor afhængig af, hvor lang din carport er. <br>
                                Der er endvidere lagt begrænsning på størrelsen af dit skur, hvis du vælger et skur i
                                halv carportbredde, som er mindre end et skur i hel bredde.<br>
                                Dit carport inklusive skur kan ikke bestilles længere end 10m tilsammen.
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingTwo">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#flush-collapseTwo" aria-expanded="false"
                                    aria-controls="flush-collapseTwo">
                                Hvorfor kan jeg ikke bestille en carport, der er bredere end 6m?
                            </button>
                        </h2>
                        <div id="flush-collapseTwo" class="accordion-collapse collapse"
                             aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Der kan ikke vælges carporte, der er bredere end 6m, da vi ikke
                                kan garantere at tagkonstruktionen kan holde i tilfælde af eksempelvis kraftigt snefald.
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingThree">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#flush-collapseThree" aria-expanded="false"
                                    aria-controls="flush-collapseThree">
                                Jeg ønsker ikke selv at bygge carporten, hvad kan jeg gøre?
                            </button>
                        </h2>
                        <div id="flush-collapseThree" class="accordion-collapse collapse"
                             aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Fog har kontakt til en række fagfolk, som vi står inde for.
                                Disse dygtige håndværkere har bygget carporte for mange af vores kunder.<br>
                                Du skal dog selv forhandle pris med pågældende entreprenør, men du kan ringe til os, og
                                modtage kontaktinformation på disse.
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="info-box-faq">
                <h3 style="color: white; font-weight: bold; text-align: center">Nyttige links</h3>
                <p>Korrekt opdateret lovgivning kan altid findes i bygningsreglementet. Klik på linket for at læse
                    mere.</p>
                <div id="box-around-bygningsreglement">
                    <a class="btn btn-primary"
                       href="https://bygningsreglementet.dk/Administrative-bestemmelser/BRV/Sekundaer-bebyggelse?Layout=ShowAll"
                       target="_blank"
                       role="button"><i class="fa fa-info-circle"></i> Gå til bygningsreglement</a>
                </div>
            </div>

        </div>
    </jsp:body>
</t:pagetemplate>