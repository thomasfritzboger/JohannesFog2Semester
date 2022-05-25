<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Stykliste
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <div class="container-knapper-stykliste">
            <div style="margin-right: 5px;">
                <button class="btn btn-primary" onclick="history.back()">
                    Gå tilbage
                </button>
            </div>

            <div>
                <button class="btn btn-primary" onclick="gemSomPDF()">
                    Gem som pdf
                </button>
            </div>

        </div>

        <br>

        <table id="styklistetabel" class="table table-striped">
            <thead>
             <tr>
                <th>Produktbeskrivelse</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Enhed</th>
                <th>Brugsbeskrivelse</th>
             </tr>
            </thead>
            <tbody>
            <c:forEach var="materialline" items="${sessionScope.materiallinelist}">
                <tr>
                    <td>${materialline.productDescription}</td>
                    <td>${materialline.length}</td>
                    <td>${materialline.amount}</td>
                    <td>${materialline.unitScale}</td>
                    <td>${materialline.usementDescription}</td>
                </tr>
            </c:forEach>

            </tbody>

        </table>

        <!--Gemmer som pdf ved brug af følgende https://www.aspsnippets.com/Articles/Convert-Export-HTML-Table-to-PDF-file-using-JavaScript.aspx-->
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
        <script type="text/javascript">
            function gemSomPDF() {
                html2canvas(document.getElementById('styklistetabel'), {
                    onrendered: function (canvas) {
                        var data = canvas.toDataURL();
                        var docDefinition = {
                            content: [{
                                image: data,
                                width: 500
                            }]
                        };
                        pdfMake.createPdf(docDefinition).download("din_stykliste_fog.pdf");
                    }
                });
            }
        </script>


    </jsp:body>

</t:pagetemplate>