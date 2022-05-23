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
            //er du sikker alert box, når der klikkes på send forespørgsel
            function sendForespoergsel() {
                let carportBredde = document.getElementById("carportBreddeSkitseSide").value;
                let carportLaengde = document.getElementById("carportLængdeSkitseSide").value;
                let carportHoejde = document.getElementById("carportHøjdeSkitseSide").value;
                let carportRum = "Valgt redskabsrum: ☑️";
                let redskabsRumPlacering = document.getElementById("redskabsrumPlacering").value;

                let text = "Send forespørgsel med følgende dimensioner?\n\n" +
                    "Carport: " + carportBredde + "cm (bredde), " + carportLaengde + "cm (længde), " + carportHoejde + "cm (højde) \n\n"
                    + carportRum +
                    "\nRedskabsrum: " + redskabsRumPlacering + "(placering)";

                confirm(text);
            }

        </script>

        <!--hidden div med alle dimensioner hentet-->
        <div hidden>
            <input type="text" id="carportbredde" name="carportbredde" value="${carportbredde}" hidden>
            <input type="text" id="carportlængde" name="carportlængde" value="${carportlængde}" hidden>
            <input type="text" id="carporthøjde" name="carporthøjde" value="${carporthøjde}" hidden>
            <input type="text" id="redskabsrumValgt" name="redskabsrumValgt" value="${redskabsrumValgt}" hidden>
            <input type="text" id="redskabsrumPlacering" name="redskabsrumPlacering" value="${redskabsrumPlacering}" hidden>
            <input type="text" id="redskabsrumLængde" name="redskabsrumLængde" value="${redskabsrumLængde}" hidden>
            <input type="text" id="redskabsrumBredde" name="redskabsrumBredde" value="${redskabsrumBredde}" hidden>

        </div>

        <div>
            <button class="btn btn-primary" onclick="history.back()">
                Gå tilbage
            </button>

            <form action="fc/profil" method="post">
                <input type="hidden" name="command" value="profil"/>
                <button id="sendforespoergsel" class="btn btn-primary" onclick="sendForespoergsel()">
                    Send forespørgsel
                </button>
            </form>
        </div>


        <div>
                ${sessionScope.svgdrawing}
        </div>

        <div style="background-color: grey; width: 450px; height: 140px;">
            <h2>Din carport:</h2>
            <ul>
                <li>Carport bredde: ${carportbredde} cm</li>
                <li>Carport længde: ${carportlængde} cm</li>
                <li>Carport højde: ${carporthøjde} cm</li>
            </ul>
        </div>


    </jsp:body>

</t:pagetemplate>