<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>

    <jsp:body>

        <h1>Ønsket dimensioner for carport</h1>

        <!--to funktioner til at tjekke om bredde og længde er valgt-->
        <script type="text/javascript">
            function checkChosen() {
                //hvis man vil have label til at poppe up
                /*if(document.getElementById("carportbredde").value > 0 && document.getElementById("carportlængde").value > 0) {
                    document.getElementById("ifYes").style.display = "block";
                }*/

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
                    document.getElementById("redskabsrumPlacering").disabled = false;
                    document.getElementById("reskabsrumBredde").disabled = false;
                    document.getElementById("redskabsrumLaengde").disabled = false;

                    //tjek om deres værdier alle 3 er indtastet, hvis ja så enable se skitse knap ellers nej
                    if((document.getElementById("redskabsrumPlacering").value === "midt"
                            || document.getElementById("redskabsrumPlacering").value === "venstre"
                            || document.getElementById("redskabsrumPlacering").value === "højre")
                        && document.getElementById("reskabsrumBredde").value > 0
                        && document.getElementById("redskabsrumLaengde").value > 0) {
                        document.getElementById("seSkitse").disabled = false;
                    } else {
                        document.getElementById("seSkitse").disabled = true;
                    }
                }

                if(!document.getElementById("redskabsrumValgt").checked) {
                    document.getElementById("redskabsrumPlacering").disabled = true;
                    document.getElementById("reskabsrumBredde").disabled = true;
                    document.getElementById("redskabsrumLaengde").disabled = true;
                    document.getElementById("seSkitse").disabled = false;
                }
            }
        </script>

        <!--Denne div er til valg af carport bredde og længde-->
        <div>
            <label for="carportbredde">Carport bredde:</label> <br>
            <select name="carportbredde" id="carportbredde" onchange="checkChosen()">
                <option value="" disabled selected>Vælg bredde</option>
                <c:forEach items="${sessionScope.carportWidthList}" var="width">
                    <option id="breddevaerdi" value="${width}">${width/100} m</option>
                </c:forEach>
            </select>

            <br>

            <label for="carportlængde">Carport længde:</label> <br>
            <select name="carportlængde" id="carportlængde" onchange="checkChosen()">
                <option value="" disabled selected>Vælg længde</option>
                    <c:forEach items="${sessionScope.carportLengthList}" var="length">
                        <option id="laengdevaerdi" value="${length}">${length/100} m</option>
                    </c:forEach>
            </select>

            <br>

            <label for="carporthøjde">Carport højde:</label> <br>
            <select name="carporthøjde" id="carporthøjde" onchange="checkChosen()">
                <option value="" disabled selected>Vælg højde</option>
                <c:forEach items="${sessionScope.carportHeightList}" var="height">
                    <option id="højdeværdi" value="${height}">${height/100} m</option>
                </c:forEach>
            </select>

        </div>

        <!--Denne div er til valg af tag og taghældning????-->
        <div>
            <label for="tagtype">Tagtype:</label> <br>
            <select name="tagtype" id="tagtype">
                <!--<option value="" disabled selected>Valg tagtype/farve</option>-->
                <option value="-">Plasttrapeztag</option>
                    <%--
                    <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                        <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                    </c:forEach>
                     --%>
            </select>

            <br>

            <label for="taghaeldning">Taghældning:</label> <br>
            <select name="taghaeldning" id="taghaeldning">
                <option value="" disabled selected>0 grader</option>
                    <%--
                    <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                        <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                    </c:forEach>
                     --%>
            </select>
        </div>



        <!--<div id="ifYes" style="display: none;">
            <label for="redskabsrumValgt">Tilføj redskabsrum? </label>
            <input type="checkbox" id="redskabsrumValgt" name="redskabsrumValgt" value="redskabsrum">
        </div>-->


        <label id="checkBoxRedskabsrum" for="redskabsrumValgt" style="opacity: 0.6;">Tilføj redskabsrum? </label>
        <input type="checkbox" id="redskabsrumValgt" name="redskabsrumValgt"
               value="redskabsrum" onclick="redskabsRumValgt()" disabled>
        

        <br><br>

        <!--Hvis redskabsrum tilvælges og alt information er tastet ind mht. dimensioner fremvises
        følgende muligheder til redskabsrummet-->

        
        <div>
            <label for="redskabsrumPlacering">Redskabsrum placering: </label> <br>
            <select name="redskabsrumPlacering" id="redskabsrumPlacering" disabled onchange="redskabsRumValgt()">
                <option value="" disabled selected>Valg placering</option>
                    <c:forEach items="${sessionScope.carportRoomPlacements}" var="placements">
                        <option value="${placements}">${placements}</option>
                    </c:forEach>
            </select>

            <br>

            <label for="reskabsrumBredde">Redskabsrum bredde:</label> <br>
            <select name="reskabsrumBredde" id="reskabsrumBredde" disabled onchange="redskabsRumValgt()">
                <option value="" disabled selected>Vælg bredde</option>
                    <c:forEach items="${sessionScope.carportRoomWidthList}" var="roomWidths">
                        <option value="${roomWidths}">${roomWidths}</option>
                    </c:forEach>
            </select>

            <br>

            <label for="redskabsrumLaengde">Redskabsrum længde:</label> <br>
            <select name="redskabsrumLaengde" id="redskabsrumLaengde" disabled onchange="redskabsRumValgt()">
                <option value="" disabled selected>Vælg længde</option>
                    <c:forEach items="${sessionScope.carportRoomLengthList}" var="roomlengths">
                        <option value="${roomlengths}">${roomlengths}</option>
                    </c:forEach>
            </select>
        </div>

        <br><br>

        <div>
            <form action="fc/skitse" method="post">
                <button id="seSkitse" class="btn btn-primary" disabled>
                    Se skitse
                </button>
            </form>
        </div>






    </jsp:body>

</t:pagetemplate>