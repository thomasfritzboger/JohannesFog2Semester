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
                let redskabsRumBredde = document.getElementById("redskabsrumBreddeSkitse").value;
                let redskabsRumLaengde = document.getElementById("redskabsrumLængdeSkitse").value;

                let text = "Send forespørgsel med følgende dimensioner?\n\n" +
                    "Carport: " + carportBredde + "m (bredde), " + carportLaengde + "m (længde), " + carportHoejde + "m (højde) \n\n"
                    + carportRum +
                    "\nRedskabsrum: " + redskabsRumPlacering + "(placering), "
                    + redskabsRumBredde + "m (bredde), "
                    + redskabsRumLaengde + "m (længde)";

                confirm(text);
            }

        </script>

        <!--hidden div med alle dimensioner hentet-->
        <div hidden>
            <input type="text" id="carportBreddeSkitseSide" value="4.00" hidden>
            <input type="text" id="carportLængdeSkitseSide" value="7.00" hidden>
            <input type="text" id="carportHøjdeSkitseSide" value="3.0" hidden>
            <input type="text" id="carportValgtRedskabsrumSkitseSide" value="" hidden>
            <input type="text" id="redskabsrumPlacering" value="midt" hidden>
            <input type="text" id="redskabsrumBreddeSkitse" value="2.5" hidden>
            <input type="text" id="redskabsrumLængdeSkitse" value="2.6" hidden>

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


        <div style="border: 2px solid black">
                ${sessionScope.svgdrawing}
        </div>


    </jsp:body>

</t:pagetemplate>