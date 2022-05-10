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

        <!--Denne div er til valg af tag og taghældni-->
        <div></div>



        <a href="${pageContext.request.contextPath}/fc/skitse?command=skitse" class="btn btn-primary" >Se skitse</a>

    </jsp:body>

</t:pagetemplate>