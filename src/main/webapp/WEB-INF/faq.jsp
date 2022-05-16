<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             FAQ
    </jsp:attribute>

    <jsp:attribute name="footer">
            About
    </jsp:attribute>

    <jsp:body>

        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus animi, aut commodi cupiditate doloremque
            esse excepturi impedit laboriosam molestias nostrum nulla officiis praesentium quas quidem rem similique
            voluptatum. Distinctio eum ipsa nemo odit quod ratione soluta ut? Asperiores autem, corporis deleniti ea
            eligendi esse eveniet fugit id iste magni minus nostrum odio omnis quas quidem similique sunt, tempora
            tenetur totam velit vitae voluptas voluptatum. Accusamus impedit in optio, perferendis placeat quia
            sapiente. Aliquam aliquid beatae cupiditate deserunt dolorem dolores doloribus eos est et ex impedit ipsa
            libero maxime provident quidem quis quo, repudiandae suscipit, ullam veritatis voluptas voluptate
            voluptatem, voluptates.</p>


        <div style="background-color: grey; width: 450px; height: 200px;">
            <p>Nyttige links</p>
            <p>Korrekt opdateret lovgivning kan altid findes i bygningsreglementet</p>
            <button class="btn btn-primary"><i class="fa fa-info-circle"></i> Gå til bygningsreglement</button>
        </div>


        <p><a href="index.jsp">Til forsiden</a></p>

    </jsp:body>
</t:pagetemplate>