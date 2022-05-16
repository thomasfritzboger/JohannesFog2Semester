<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Carport factory!
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <h2>Vælg carport type:</h2>

        <div>
            <p>Carport med fladt tag</p>
            <a href="${pageContext.request.contextPath}/fc/carportDesigner?command=carportDesigner" >
                <img src="${pageContext.request.contextPath}/images/fladt_tag.png" alt="carport med fladt tag">
            </a>
        </div>

        <div>
            <p>Carport med rejsning</p>
            <a href="${pageContext.request.contextPath}/fc/carportDesigner?command=carportDesigner" >
                <img src="${pageContext.request.contextPath}/images/carport_rejsning.png" alt="carport med rejsning">
            </a>
        </div>



    </jsp:body>

</t:pagetemplate>