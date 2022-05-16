<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Ordre
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <a href="${pageContext.request.contextPath}/fc/kunder?command=kunder" class="btn btn-primary" >Kunder</a>
        <a href="${pageContext.request.contextPath}/fc/forespoergsler?command=forespoergsler" class="btn btn-primary" >Forespørgsler</a>
        <a href="${pageContext.request.contextPath}/fc/ordre?command=ordre" class="btn btn-primary" >Ordre</a>

        <br>
        <br>
        <br>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Carport ID</th>
                <th>User ID</th>
                <th>Carport size</th>
                <th>Shed + shed size?</th>
                <th>Carport Created</th>
                <%--TODO: Flere informationer i listen?--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="carporte" items="${sessionScope.carportListe}">
                <tr>
                    <td>${carporte.carportId}</td>
                    <td>${carporte.userId}</td>
                    <td>${carporte.carportLength}x${carporte.carportWidth}</td>
                    <td>shed no (test)</td>
<%--                    <td>${carporte.hasShed} med ${carporte.shedLength}x${carporte.shedWidth}</td>--%>
                        <%-- ^ if hasShed == true så vi størrelse eller bare nej/false/null--%>
                    <td>Time created ?-?-?</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>

</t:pagetemplate>