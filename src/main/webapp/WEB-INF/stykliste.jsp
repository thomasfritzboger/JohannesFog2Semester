<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Stykliste
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <div>
            <button class="btn btn-primary" onclick="history.back()">
                Gå tilbage
            </button>
        </div>


        <table class="table table-striped">
            <thead>
             <tr>
                <th>Produktbeskrivelse</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Brugsbeskrivelse</th>
             </tr>
            </thead>
            <tbody>
            <c:forEach var="materialline" items="${sessionScope.materiallinelist}">
                <tr>
                    <td>${materialline.productDescription}</td>
                    <td>${materialline.length}</td>
                    <td>${materialline.amount}</td>
                    <td>${materialline.unitScale}</td>
                    <td>${materialline.usementDescription}</td>
                </tr>
            </c:forEach>

            </tbody>

        </table>

    </jsp:body>

</t:pagetemplate>