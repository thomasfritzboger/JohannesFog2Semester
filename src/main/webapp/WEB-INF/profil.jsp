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

        <h3>Dine forespørgsler</h3>

        <button>
            Rediger dine oplysninger
        </button>

        <c:if test="${carportRequestByUser.isEmpty()}">
            Du har ingen forespørgsler.
            <a href="${pageContext.request.contextPath}/fc/kundeLogin?command=kundeLogin" class="link-primary" >Klik her</a> for at komme igang med at bygge din helt egen carport.
        </c:if>

        <c:if test="${!carportRequestByUser.isEmpty()}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Carport nummer</th>
                <th>Carport størrelse <br> bredde * længde * højde</th>
                <th>Redskabsrum + størrelse?</th>
                <th>Oprettet</th>
                <th>Stykliste</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="carport" items="${carportRequestByUser}">
                <form action="fc/seSkitse" method="post">
                    <input type="hidden" name="command" value="seSkitse">
                    <tr>
                        <td>${carport.getCarportId()}</td>
                        <td>${carport.getWidth()}x${carport.getLength()}x${carport.getHeight()}</td>
                        <td><c:if test="${carport.getHasShed() == false}">Intet redskabsrum valgt</c:if>
                        <c:if test="${carport.getHasShed() == true}">${carport.getShed().getWidth()}x${carport.getShed().getLength()}, ${carport.getShed().getPlacement()}</c:if>    </td>
                        <td>d. ${carport.getCreated()}</td>

                        <td><c:if test="${carport.isConfirmed()}">Din carport er igang med at blive pakket. <br> Ring og aftal levering.
                            <br><a href="${pageContext.request.contextPath}/fc/stykliste?command=stykliste" class="btn btn-primary" >Se stykliste</a>
                            <a href="${pageContext.request.contextPath}/fc/stykliste?command=seSkitse" class="btn btn-primary" >Se skitse</a></c:if>

                        <c:if test="${carport.isConfirmed() == false}">Afventer bekræftelse <br>
                            <a href="${pageContext.request.contextPath}/fc/stykliste?command=seSkitse" class="btn btn-primary" >Se skitse</a></c:if>
                        </td>
                        <input hidden id="carportId" name="carportId" value="${carport.getCarportId()}">
                    </tr>
                </form>
            </c:forEach>
            </tbody>
        </table>
        </c:if>

    </jsp:body>

</t:pagetemplate>