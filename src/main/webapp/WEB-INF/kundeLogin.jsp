<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        KundeLogin
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <a href="${pageContext.request.contextPath}/fc/carportDesigner?command=carportDesigner" class="btn btn-primary" >Carport designer</a>

    </jsp:body>

</t:pagetemplate>