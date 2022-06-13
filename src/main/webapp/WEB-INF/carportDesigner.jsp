<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>

    <jsp:body>

        <h1 style="text-align: center">Ønsket dimensioner for carport</h1>

        <script type="text/javascript">

            <!--Funktion som tjekker om carport længde, bredde og højde er valgt-->
            function checkChosen() {
                document.getElementById("shedChosen").checked = false;
                document.getElementById("shedPlacement").disabled = true;
                document.getElementById("shedLength").disabled = true;

                //tjek om carport bredde, længde og højde er valgt
                if (document.getElementById("carportWidth").value > 0
                    && document.getElementById("carportLength").value > 0
                    && document.getElementById("carportHeight").value > 0) {
                    document.getElementById("shedChosen").disabled = false; //gør det muligt at tilføje redskabsrum
                    document.getElementById("checkBoxShed").style.opacity = 1; //gør den fuld synlig
                    document.getElementById("viewDrawing").disabled = false; //man kan se skitsen, hvis redskabsrum IKKE er valgt
                }
            }

            <!--Er redskabsrummet valgt skal værdierne opdateres for redskabsrummet-->
            function updateShedValues() {
                if (document.getElementById("shedChosen").checked) {
                    document.getElementById("shedChosen").value = "y";
                    document.getElementById("shedPlacement").disabled = false; //vis nu redskabsrumsplacering dropdown
                    document.getElementById("shedLength").disabled = false; //vis nu redskabsrumslængde dropdown

                    //tjek om bredden af vores carport er mindre end 3.8, hvis ja, så kan man kun have redskabsrumplaceringen
                    // i midten
                    if (document.getElementById("carportWidth").value < 380) {
                        document.getElementById("left").disabled = true;
                        document.getElementById("right").disabled = true;
                        document.getElementById("shedPlacement").value = "center";
                    } else {
                        document.getElementById("left").disabled = false;
                        document.getElementById("right").disabled = false;
                    }

                    //tjek om deres værdier alle 3 er indtastet, hvis ja så enable se skitse knap ellers nej
                    if ((document.getElementById("shedPlacement").value === "center"
                            || document.getElementById("shedPlacement").value === "left"
                            || document.getElementById("shedPlacement").value === "right")
                        && document.getElementById("shedLength").value > 0) {
                        document.getElementById("viewDrawing").disabled = false; //'se skitse' knap kan klikkes
                    } else {
                        document.getElementById("viewDrawing").disabled = true; //'se skitse' knap kan IKKE klikkes
                    }
                }

                //Der skal være specifikke længder til redskabsrummet alt efter om denne er sat i venstre/højre eller midten
                let carportWidth = parseInt(document.getElementById("carportWidth").value);
                let carportLength = parseInt(document.getElementById("carportLength").value);

                //udregn spærantal
                let spaerantal = Math.ceil(carportLength / 59.0);

                //udregn afstand mellem spær
                let afstandMellemSpaer = Math.ceil(carportLength / spaerantal);
                document.getElementById("afstandMellemSpaer").value = afstandMellemSpaer;

                let options = [];

                //fjern alle elementer fra en liste
                function removeOptions(selectElement) {
                    let i, L = selectElement.options.length - 1;
                    for (i = L; i > 0; i--) {
                        selectElement.remove(i);
                    }
                }

                if (document.getElementById("shedPlacement").value === "left"
                    || document.getElementById("shedPlacement").value === "right") {
                    //sæt redskabsrummets bredde - halvdelen af carporten minus udhæng
                    document.getElementById("shedWidth").value = ((carportWidth / 2) - 35) / 100; //udregn bredden og sæt ind

                    //fjern nuværende elementer i længde dropdown
                    let selectLength = document.getElementById("shedLength");
                    removeOptions(selectLength);

                    //indsætter nye elementer i vores liste
                    //Baseret på vores antagelser: stolperne sidder fast i noget..
                    //3 er minimum for alle skure, da den skal være minimum én spærlængde længere end døren, og døren er altid 2
                    //5 er maksimum for halve skure, da det er det vi har besluttes
                    options = [(3 * afstandMellemSpaer),
                        (4 * afstandMellemSpaer),
                        (5 * afstandMellemSpaer)];

                    //indsætter nu listen tilbage i dropdown
                    for (let i = 0; i < options.length; i++) {
                        let opt = options[i];
                        let el = document.createElement("option");
                        el.text = (opt / 100) + " m";
                        el.value = opt + "";
                        selectLength.add(el);
                    }
                }

                if (document.getElementById("shedPlacement").value === "center") {
                    //sæt redskabsrummets bredde - carporten bredde minus udhæng fra begge sider
                    document.getElementById("shedWidth").value = (carportWidth - 70) / 100; //udregn bredden og sæt ind

                    //fjern nuværende elementer i længde dropdown
                    let selectLength = document.getElementById("shedLength");
                    removeOptions(selectLength);

                    //itereres til 10, da denne giver den største skurlængde under 10 meter sammen med parkeringsareal
                    for (let i = 3; i <= 10; i++) {
                        //må ikke overstige 10 meter i længde
                        if ((i * afstandMellemSpaer) + parseInt(document.getElementById("carportLength").value) > 1000) {
                            break;
                        }
                        options.push(i * afstandMellemSpaer);
                    }

                    for (let i = 0; i < options.length; i++) {
                        let opt = options[i];
                        let el = document.createElement("option");
                        el.text = (opt / 100) + " m";
                        el.value = opt + "";
                        selectLength.add(el);
                    }
                }

                if (!document.getElementById("shedChosen").checked) {
                    document.getElementById("shedChosen").value = "n";
                    document.getElementById("shedPlacement").disabled = true;
                    document.getElementById("shedLength").disabled = true;
                    document.getElementById("viewDrawing").disabled = false;
                }
            }

            function checkIfAllIsSelected() {
                //tjek om redskabsrummets værdier er indtastet, hvis ja så enable 'se skitse' knap
                if ((document.getElementById("shedPlacement").value === "center"
                        || document.getElementById("shedPlacement").value === "left"
                        || document.getElementById("shedPlacement").value === "right")
                    && document.getElementById("shedLength").value > 0) {
                    document.getElementById("viewDrawing").disabled = false;
                    //spærlængder for skuret sendes videre til næste side
                    document.getElementById("shedSize").value = (document.getElementById("shedLength").value / document.getElementById("afstandMellemSpaer").value);
                } else {
                    document.getElementById("viewDrawing").disabled = true;
                }
            }
        </script>

        <div id="container-good-to-knows">
            <h2 style="color: white; font-weight: bold; text-align: center">Good to knows:</h2>
            <ul>
                <li>Et redskabsrum kan tilvælges efter valg af carportens bredde, højde og længde.</li>
                <li>Carport bredde skal være minimum <b>3.8 meter</b> før et redskabsrum kan tilføjes i venstre
                    eller højre hjørne.
                </li>
                <li>Redskabsrummets bredde er fastlagt baseret på ens valg af placering.</li>
            </ul>
        </div>

        <div class="container-carportdesigner">

            <form action="fc/drawing" method="post">
                <!--Denne div er til valg af carport bredde og længde-->
                <div class="container-width-length-height">
                    <div class="dimensions-items">
                        <label for="carportWidth">Carport bredde:</label> <br>
                        <select name="carportWidth" id="carportWidth" onchange="checkChosen()">
                            <option value="" disabled selected>Vælg bredde</option>
                            <c:forEach items="${sessionScope.carportWidthList}" var="width">
                                <option id="widthValue" value="${width}">${width/100} m</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="dimensions-items">
                        <label for="carportLength">Carport længde:</label> <br>
                        <select name="carportLength" id="carportLength" onchange="checkChosen()">
                            <option value="" disabled selected>Vælg længde</option>
                            <c:forEach items="${sessionScope.carportLengthList}" var="length">
                                <option id="lengthValue" value="${length}">${length/100} m</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="dimensions-items">
                        <label for="carportHeight">Carport højde:</label> <br>
                        <select name="carportHeight" id="carportHeight" onchange="checkChosen()">
                            <option value="" disabled selected>Vælg højde</option>
                            <c:forEach items="${sessionScope.carportHeightList}" var="height">
                                <option id="heightValue" value="${height}">${height/100} m</option>
                            </c:forEach>
                        </select>
                    </div>

                </div>

                <!--Denne div er til valg af tag og taghældning????-->
                <div class="container-roofMaterial-roofAngle">
                    <div class="dimensions-items">
                        <label for="roofMaterial">Tagtype:</label> <br>
                        <select name="roofMaterial" id="roofMaterial">
                            <!--<option value="" disabled selected>Valg tagtype/farve</option>-->
                            <option value="p">Plasttrapez tag</option>
                            <option value="c">Cembrit tag</option>
                        </select>
                    </div>

                    <div class="dimensions-items">
                        <label for="roofAngle">Taghældning:</label> <br>
                        <select name="roofAngle" id="roofAngle">
                            <option value="" disabled selected>0 grader</option>
                        </select>
                    </div>
                </div>

                <div class="container-shed-chosen">
                    <div class="dimensions-items">
                        <label id="checkBoxShed" for="shedChosen" style="opacity: 0.6;">Tilføj redskabsrum? </label>
                        <input type="checkbox" id="shedChosen" name="shedChosen"
                               value="n" onclick="updateShedValues()" disabled>
                    </div>
                </div>

                <!--Hvis redskabsrum tilvælges og alt information er tastet ind mht. dimensioner fremvises
                følgende muligheder til redskabsrummet-->
                <div class="container-shed-dimensions">
                    <div class="dimensions-items">
                        <label for="shedPlacement">Redskabsrum placering: </label> <br>
                        <select name="shedPlacement" id="shedPlacement" disabled onchange="updateShedValues()">
                            <option value="" disabled selected>Vælg placering</option>
                            <option id="left" value="left">venstre</option>
                            <option id="center" value="center">fuld længde</option>
                            <option id="right" value="right">højre</option>
                        </select>
                    </div>

                    <div class="dimensions-items">
                        <label for="shedWidth">Redskabsrum bredde:</label> <br>
                        <input type="text" id="shedWidth" style="width: 75px" readonly> m
                    </div>

                    <div class="dimensions-items">
                        <label for="shedLength">Redskabsrum længde:</label> <br>
                        <select name="shedLength" id="shedLength" disabled onchange="checkIfAllIsSelected();">
                            <option value="" disabled selected>Vælg længde</option>
                        </select>
                    </div>

                    <input id="afstandMellemSpaer" type="text" hidden value="">
                    <input name="shedSize" id="shedSize" type="number" hidden value="0">
                </div>

                <div class="container-drawing-button">
                    <input type="hidden" name="command" value="drawing"/>
                    <button id="viewDrawing" class="btn btn-primary" disabled>
                        Se skitse
                    </button>
                </div>
            </form>
        </div>

    </jsp:body>
</t:pagetemplate>