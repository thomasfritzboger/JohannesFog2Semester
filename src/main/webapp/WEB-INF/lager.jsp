<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Lagerstyring
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <a href="${pageContext.request.contextPath}/fc/kunder?command=kunder" class="btn btn-primary" >Kunder</a>
        <a href="${pageContext.request.contextPath}/fc/forespoergsler?command=forespoergsler" class="btn btn-primary" >Forespørgsler</a>
        <a href="${pageContext.request.contextPath}/fc/ordre?command=ordre" class="btn btn-primary" >Ordre</a>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Product scale</th>
                    <th>Product Description</th>
                    <th>Unit Price</th>

                </tr>

            </thead>
            <tbody>
<%--            TODO Lave joins i database mellem product og productvariant så fylde dem i tablellen. --%>
<%--            <c:forEach var="product" items="${}"> </c:forEach>--%>

            </tbody>

        </table>


    </jsp:body>

</t:pagetemplate>