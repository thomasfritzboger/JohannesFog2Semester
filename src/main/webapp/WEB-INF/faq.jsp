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
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                                Spørgsmål 1
                            </button>
                        </h2>
                        <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus, accusantium cumque cupiditate deserunt doloribus dolorum eos
                                exercitationem facilis harum hic, illo labore laborum magni maiores mollitia nesciunt nihil odio omnis quis quo ratione rerum sequi tempore velit veniam, vero voluptatem?</div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingTwo">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                                Spørgsmål 2
                            </button>
                        </h2>
                        <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis labore molestias nobis. Dicta eius in incidunt minima optio! Excepturi exercitationem iste itaque repellat temporibus! Accusamus atque blanditiis consectetur delectus, doloribus eligendi enim exercitationem laudantium mollitia omnis, quas ratione tempora veritatis?</div>
                        </div>
                    </div>
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="flush-headingThree">
                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                                Spørgsmål 3
                            </button>
                        </h2>
                        <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                            <div class="accordion-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid aperiam aut deleniti deserunt doloribus eius explicabo illo inventore ipsa ipsam laborum laudantium necessitatibus neque numquam obcaecati perferendis perspiciatis porro quam quia quo quos, ratione recusandae reiciendis repellat repellendus reprehenderit soluta!</div>
                        </div>
                    </div>
                </div>
            </div>


            <div id="info-box-faq">
                <h3 style="color: white;">Nyttige links</h3>
                <p>Korrekt opdateret lovgivning kan altid findes i bygningsreglementet</p>
                <button class="btn btn-primary"><i class="fa fa-info-circle"></i> Gå til bygningsreglement</button>
            </div>

        </div>
    </jsp:body>
</t:pagetemplate>