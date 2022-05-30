<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <h1 style="text-align: center">🛠️ Carport factory! 🛠️</h1>

        <h2 id="choose-carport-type-heading">Vælg carport type:</h2>

        <div class="container-chosen-carport">
            <div>
                <p>Carport med fladt tag</p>
                <a href="${pageContext.request.contextPath}/fc/carportDesigner?command=carportDesigner">
                    <img src="${pageContext.request.contextPath}/images/fladt_tag.png" alt="carport med fladt tag">
                </a>
            </div>

            <div>
                <p>Carport med rejsning</p>
                <a href="${pageContext.request.contextPath}/fc/carportDesigner?command=carportDesigner">
                    <img src="${pageContext.request.contextPath}/images/carport_rejsning.png"
                         alt="carport med rejsning">
                </a>
            </div>
        </div>

    </jsp:body>

</t:pagetemplate>