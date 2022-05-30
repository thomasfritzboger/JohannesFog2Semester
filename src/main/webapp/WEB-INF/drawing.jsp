<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Se skitse
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <!--SVG SKITSEN LAVES HER-->

        <script type="text/javascript">
            function onClick() {
                document.getElementById('confirmRequest').value = "send";
            }

        </script>

        <!--hidden div med alle dimensioner hentet-->
        <div hidden>
            <input type="text" id="carportWidth" name="carportWidth" value="${carportWidth}" hidden>
            <input type="text" id="carportLength" name="carportLength" value="${carportLength}" hidden>
            <input type="text" id="carportHeight" name="carportHeight" value="${carportHeight}" hidden>
            <input type="text" id="shedChosen" name="shedChosen" value="${shedChosen}" hidden>
            <input type="text" id="shedPlacement" name="shedPlacement" value="${shedPlacement}" hidden>
            <input type="text" id="shedLength" name="shedLength" value="${shedLength}" hidden>
            <input type="text" id="shedWidth" name="shedWidth" value="${shedWidth}" hidden>
        </div>

        <div class="container-buttons-drawing-page">
            <div>
                <form action="fc/carportDesigner">
                    <input type="hidden" name="command" value="carportDesigner">
                    <button class="btn btn-primary" style="margin-right: 5px;">
                        Gå tilbage
                    </button>
                </form>
            </div>
            <div>
                <form action="fc/profile" method="post">
                    <input type="hidden" name="command" value="profile"/>
                    <button id="sendRequest" type="submit" class="btn btn-primary" onclick="onClick();">
                        Send forespørgsel
                    </button>
                </form>
            </div>
        </div>

        <div class="container-carport-dimensions-page">
            <h2 style="color: white">Din carport:</h2>
            <p>bredde: ${carportWidth} cm, længde: ${carportLength} cm, højde: ${carportHeight} cm <br>
                Forventet pris: ${carportPrice} kr.</p>
        </div>

        <div>
                ${sessionScope.svgDrawing}
        </div>


    </jsp:body>

</t:pagetemplate>