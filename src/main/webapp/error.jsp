<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             En fejl er opstået
    </jsp:attribute>

    <jsp:attribute name="footer">
            Error page
    </jsp:attribute>

    <jsp:body>

        <c:if test="${pageContext.errorData.statusCode == 404 }">
            <p><b>Fejl kode:</b> ${pageContext.errorData.statusCode}</p>
        </c:if>

        <c:if test="${pageContext.errorData.statusCode == 500 }">
            <p>En seriøs fejl opstod i serveren. <b>Fejl kode:</b> ${pageContext.errorData.statusCode}.</p>
        </c:if>


        <c:if test="${requestScope.errormessage != null}">
            <p>${requestScope.errormessage}</p>
        </c:if>

        <c:if test="${requestScope.errormessage  == null}">
            <p>Forlad skibet! Vi har ingen idé om hvordan du endte her.</p>
        </c:if>

        <p>Hop tilbage til <a href="${pageContext.request.contextPath}/index.jsp">forsiden</a>,
            eller prøv at logge ind igen fra hovedmenuen.</p>

    </jsp:body>
</t:pagetemplate>