<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>

    <jsp:body>

        <h1 style="text-align: center">Ønsket dimensioner for carport</h1>

        <div id="container-good-to-knows" style="background-color: grey; width: 400px; height: 120px;">
            <h2>Good to knows:</h2>
            <ul>
                <li>Carport bredde skal være minimum <b>3.8 meter</b> før et redskabsrum kan tilføjes i venstre
                    eller højre hjørne.</li>
            </ul>
        </div>

        <!--to funktioner til at tjekke om bredde og længde er valgt-->
        <script type="text/javascript">
            function checkChosen() {

                document.getElementById("redskabsrumValgt").checked = false;
                document.getElementById("redskabsrumPlacering").disabled = true;
                document.getElementById("redskabsrumLængde").disabled = true;


                //hvis man vil skifte enabled på checkbox
                if(document.getElementById("carportbredde").value > 0
                    && document.getElementById("carportlængde").value > 0
                    && document.getElementById("carporthøjde").value > 0) {
                    document.getElementById("redskabsrumValgt").disabled = false;
                    document.getElementById("checkBoxRedskabsrum").style.opacity = 1;
                    document.getElementById("seSkitse").disabled = false;
                }

            }

            function redskabsRumValgt() {
                if(document.getElementById("redskabsrumValgt").checked) {
                    document.getElementById("redskabsrumValgt").value ="y";
                    document.getElementById("redskabsrumPlacering").disabled = false;
                    document.getElementById("redskabsrumLængde").disabled = false;


                    //tjek om bredden af vores carport er mindre end 3.8, hvis ja, så kan man kun have redskabsrumplaceringen
                    // i midten
                    if(document.getElementById("carportbredde").value < 380) {
                        document.getElementById("venstre").disabled = true;
                        document.getElementById("højre").disabled = true;
                        document.getElementById("redskabsrumPlacering").value = "midt";
                    } else {
                        document.getElementById("venstre").disabled = false;
                        document.getElementById("højre").disabled = false;
                    }


                    //tjek om deres værdier alle 3 er indtastet, hvis ja så enable se skitse knap ellers nej
                    if((document.getElementById("redskabsrumPlacering").value === "midt"
                            || document.getElementById("redskabsrumPlacering").value === "venstre"
                            || document.getElementById("redskabsrumPlacering").value === "højre")
                        && document.getElementById("redskabsrumLængde").value > 0) {
                        document.getElementById("seSkitse").disabled = false;
                    } else {
                        document.getElementById("seSkitse").disabled = true;
                    }
                }

                let carportWidth = parseInt(document.getElementById("carportbredde").value);
                let carportLength = parseInt(document.getElementById("carportlængde").value);
                //console.log(carportWidth + ", " + carportLength);
                //udregn spærantal
                let spaerantal = Math.ceil(carportLength/59.0);

                //udregn afstand mellem spær
                let afstandMellemSpaer = Math.ceil(carportLength/spaerantal);
                document.getElementById("afstandMellemSpaer").value = afstandMellemSpaer;

                let options = [];

                if(document.getElementById("redskabsrumPlacering").value === "venstre"
                    || document.getElementById("redskabsrumPlacering").value === "højre") {
                    document.getElementById("redskabsrumBredde").value = ((carportWidth / 2)-35) / 100;

                    //fjern nuværende elementer i længde dropdown
                    let selectLaengde = document.getElementById("redskabsrumLængde");
                    removeOptions(selectLaengde);

                    //indsætter array i options
                    options = [(3*afstandMellemSpaer),
                        (4*afstandMellemSpaer),
                        (5*afstandMellemSpaer)];

                    for(let i = 0; i < options.length; i++) {
                        let opt = options[i];
                        let el = document.createElement("option");
                        el.text = (opt/100)+" m";
                        el.value = opt+"";
                        selectLaengde.add(el);
                    }
                }

                if(document.getElementById("redskabsrumPlacering").value === "midt") {
                    document.getElementById("redskabsrumBredde").value = (carportWidth-70)/100;

                    //fjern nuværende elementer i længde dropdown
                    let selectLaengde = document.getElementById("redskabsrumLængde");
                    removeOptions(selectLaengde);

                    for(let i = 3; i <= 10; i++) {
                        if ((i * afstandMellemSpaer) + parseInt(document.getElementById("carportlængde").value) > 1000) {
                            break;
                        }
                        options.push(i*afstandMellemSpaer);
                    }

                    for(let i = 0; i < options.length; i++) {
                        let opt = options[i];
                        let el = document.createElement("option");
                        el.text = (opt/100)+" m";
                        el.value = opt+"";
                        selectLaengde.add(el);
                    }

                }

                if(!document.getElementById("redskabsrumValgt").checked) {
                    document.getElementById("redskabsrumValgt").value = "n";
                    document.getElementById("redskabsrumPlacering").disabled = true;
                    document.getElementById("redskabsrumLængde").disabled = true;
                    document.getElementById("seSkitse").disabled = false;
                }
            }

            function removeOptions(selectElement) {
                let i, L = selectElement.options.length - 1;
                for(i = L; i > 0; i--) {
                    selectElement.remove(i);
                }
            }

            function tjekOmAltErIndtastet() {
                //tjek om deres værdier alle 3 er indtastet, hvis ja så enable se skitse knap ellers nej
                if((document.getElementById("redskabsrumPlacering").value === "midt"
                        || document.getElementById("redskabsrumPlacering").value === "venstre"
                        || document.getElementById("redskabsrumPlacering").value === "højre")
                    && document.getElementById("redskabsrumLængde").value > 0) {
                    document.getElementById("seSkitse").disabled = false;
                    document.getElementById("skurSize").value = (document.getElementById("redskabsrumLængde").value / document.getElementById("afstandMellemSpaer").value);
                    console.log(document.getElementById("skurSize").value);
                } else {
                    document.getElementById("seSkitse").disabled = true;
                }

            }
        </script>

        <div class="container-carportdesigner">

        <form action="fc/skitse" method="post">
        <!--Denne div er til valg af carport bredde og længde-->
        <div class="container-width-length-height">
            <div class="dimensions-items">
                <label for="carportbredde">Carport bredde:</label> <br>
                <select name="carportbredde" id="carportbredde" onchange="checkChosen()">
                    <option value="" disabled selected>Vælg bredde</option>
                    <c:forEach items="${sessionScope.carportWidthList}" var="width">
                        <option id="breddevaerdi" value="${width}">${width/100} m</option>
                    </c:forEach>
                </select>
            </div>

            <div class="dimensions-items">
                <label for="carportlængde">Carport længde:</label> <br>
                <select name="carportlængde" id="carportlængde" onchange="checkChosen()">
                    <option value="" disabled selected>Vælg længde</option>
                        <c:forEach items="${sessionScope.carportLengthList}" var="length">
                            <option id="laengdevaerdi" value="${length}">${length/100} m</option>
                        </c:forEach>
                </select>
            </div>

            <div class="dimensions-items">
                <label for="carporthøjde">Carport højde:</label> <br>
                <select name="carporthøjde" id="carporthøjde" onchange="checkChosen()">
                    <option value="" disabled selected>Vælg højde</option>
                    <c:forEach items="${sessionScope.carportHeightList}" var="height">
                        <option id="højdeværdi" value="${height}">${height/100} m</option>
                    </c:forEach>
                </select>
            </div>

        </div>

        <!--Denne div er til valg af tag og taghældning????-->
        <div class="container-tagtype-haeldning">
            <div class="dimensions-items">
                <label for="tagtype">Tagtype:</label> <br>
                <select name="tagtype" id="tagtype">
                    <!--<option value="" disabled selected>Valg tagtype/farve</option>-->
                    <option value="p">Plasttrapez tag</option>
                    <option value="c">Cembrit tag</option>
                </select>
            </div>

            <div class="dimensions-items">
                <label for="taghaeldning">Taghældning:</label> <br>
                <select name="taghaeldning" id="taghaeldning">
                    <option value="" disabled selected>0 grader</option>
                </select>
            </div>
        </div>

        <div class="container-redskabsrum-tilvalg">
            <div class="dimensions-items">
                <label id="checkBoxRedskabsrum" for="redskabsrumValgt" style="opacity: 0.6;">Tilføj redskabsrum? </label>
                <input type="checkbox" id="redskabsrumValgt" name="redskabsrumValgt"
                       value="n" onclick="redskabsRumValgt()" disabled>
            </div>
        </div>

        <!--Hvis redskabsrum tilvælges og alt information er tastet ind mht. dimensioner fremvises
        følgende muligheder til redskabsrummet-->
        
        <div class="container-redskabsrum-dimensioner">
            <div class="dimensions-items">
                <label for="redskabsrumPlacering">Redskabsrum placering: </label> <br>
                <select name="redskabsrumPlacering" id="redskabsrumPlacering" disabled onchange="redskabsRumValgt()">
                    <option value="" disabled selected>Valg placering</option>
                    <option id="venstre" value="venstre">venstre</option>
                    <option id="midt" value="midt">midt</option>
                    <option id="højre" value="højre">højre</option>
                </select>
            </div>

            <div class="dimensions-items">
                <label for="redskabsrumBredde">Redskabsrum bredde:</label> <br>
                <input type="text" id="redskabsrumBredde" style="width: 75px" readonly> m (denne er fastlagt)
            </div>

            <div class="dimensions-items">
                <label for="redskabsrumLængde">Redskabsrum længde:</label> <br>
                <select name="redskabsrumLængde" id="redskabsrumLængde" disabled onchange="tjekOmAltErIndtastet();">
                    <option value="" disabled selected>Vælg længde</option>
                </select>
            </div>

            <input id="afstandMellemSpaer" type="text" hidden value="">
            <input name="skurSize" id="skurSize" type="number" hidden value="0">
        </div>

        <div class="container-skitse-knap">
                <input type="hidden" name="command" value="skitse"/>
                <button id="seSkitse" class="btn btn-primary" disabled>
                    Se skitse
                </button>
        </div>

        </form>

        </div>

    </jsp:body>

</t:pagetemplate>