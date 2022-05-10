<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>

    <jsp:body>

        <h1>Ønsket dimensioner for carport</h1>

        <!--Denne div er til valg af carport bredde og længde-->
        <div>
            <label for="carportbredde">Carport bredde:</label> <br>
            <select name="carportbredde" id="carportbredde">
                <option value="" disabled selected>Vælg bredde</option>
                <option value="-">-</option>
                <%--
                <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                    <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                </c:forEach>
                 --%>
            </select>

            <br>

            <label for="carportlængde">Carport længde:</label> <br>
            <select name="carportlængde" id="carportlængde">
                <option value="" disabled selected>Vælg længde</option>
                <option value="-">-</option>
                    <%--
                    <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                        <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                    </c:forEach>
                     --%>
            </select>
        </div>

        <!--Denne div er til valg af tag og taghældning????-->
        <div>
            <label for="tagtype">Tagtype:</label> <br>
            <select name="tagtype" id="tagtype">
                <option value="" disabled selected>Valg tagtype/farve</option>
                <option value="-">-</option>
                    <%--
                    <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                        <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                    </c:forEach>
                     --%>
            </select>

            <br>

            <label for="taghaeldning">Taghældning:</label> <br>
            <select name="taghaeldning" id="taghaeldning">
                <option value="" disabled selected>15 grader</option>
                <option value="-">-</option>
                    <%--
                    <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                        <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                    </c:forEach>
                     --%>
            </select>
        </div>



        <label for="redskabsrumValgt">Tilføj redskabsrum? </label>
        <input type="checkbox" id="redskabsrumValgt" name="redskabsrumValgt" value="redskabsrum">

        <br><br>

        <!--Hvis redskabsrum tilvælges og alt information er tastet ind mht. dimensioner fremvises
        følgende muligheder til redskabsrummet-->

        <div>
            <label for="redskabsrumPlacering">Redskabsrum placering: </label> <br>
            <select name="redskabsrumPlacering" id="redskabsrumPlacering">
                <option value="" disabled selected>Valg placering</option>
                <option value="-">-</option>
                    <%--
                    <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                        <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                    </c:forEach>
                     --%>
            </select>

            <br>

            <label for="reskabsrumBredde">Redskabsrum bredde:</label> <br>
            <select name="reskabsrumBredde" id="reskabsrumBredde">
                <option value="" disabled selected>Vælg bredde</option>
                <option value="-">-</option>
                    <%--
                    <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                        <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                    </c:forEach>
                     --%>
            </select>

            <br>

            <label for="reskabsrumLængde">Redskabsrum længde:</label> <br>
            <select name="reskabsrumLængde" id="reskabsrumLængde">
                <option value="" disabled selected>Vælg længde</option>
                <option value="-">-</option>
                    <%--
                    <c:forEach items="${applicationScope.bottomlist}" var="bottoms">
                        <option value="${bottoms.bottomId}">${bottoms.name} (${bottoms.price},-)</option>
                    </c:forEach>
                     --%>
            </select>

        </div>


        <a href="${pageContext.request.contextPath}/fc/skitse?command=skitse" class="btn btn-primary" >Se skitse</a>

    </jsp:body>

</t:pagetemplate>