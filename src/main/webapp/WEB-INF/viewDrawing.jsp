<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Her er en skitse af din carport
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <div>
            <button class="btn btn-primary" onclick="history.back()">
                Gå tilbage
            </button>
        </div>

        <div>
                ${sessionScope.svgDrawing}
        </div>

    </jsp:body>

</t:pagetemplate>