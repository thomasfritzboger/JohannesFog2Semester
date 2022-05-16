<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Forespørgsler
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <a href="${pageContext.request.contextPath}/fc/kunder?command=kunder" class="btn btn-primary" >Kunder</a>
        <a href="${pageContext.request.contextPath}/fc/lager?command=lager" class="btn btn-primary" >Lager</a>
        <a href="${pageContext.request.contextPath}/fc/ordre?command=ordre" class="btn btn-primary" >Ordre</a>

    </jsp:body>

</t:pagetemplate>