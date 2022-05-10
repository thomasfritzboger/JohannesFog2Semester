<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Carport Designer
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <a href="${pageContext.request.contextPath}/fc/skitse?command=skitse" class="btn btn-primary" >Se skitse</a>

    </jsp:body>

</t:pagetemplate>