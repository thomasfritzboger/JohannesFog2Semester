<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Profil
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Carport Number</th>
                <th>Buyer</th>
                <th>Carport size</th>
                <th>Shed + shed size?</th>
                <th>Carport Created</th>
                <%--TODO: Flere informationer i listen?--%>
                <th>Stykliste</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="carporte" items="${sessionScope.carportListe}">
                <tr>
                    <td>${carporte.carportId}</td>
                    <td>${carporte.userId}</td>
                    <td>${carporte.carportLength}x${carporte.carportWidth}</td>
                    <td>shed no (test)</td>
                    <td>Time created ?-?-?</td>
                    <td><a href="${pageContext.request.contextPath}/fc/stykliste?command=stykliste" class="btn btn-primary" >Se stykliste</a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/fc/stykliste?command=stykliste" class="btn btn-primary" >Se stykliste</a>

    </jsp:body>

</t:pagetemplate>