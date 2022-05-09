<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/fog_logo_banner.png" width="400px;" class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">

                    <!--Navigationsbaren hvis INGEN er logget ind endnu-->
                    <c:if test="${sessionScope.user == null}">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/index.jsp">Forside</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/faq?command=faq">FAQ</a>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#exampleModal">
                            Log ind
                        </button>
                    </c:if>

                    <!--Navigationsbaren hvis kunden er logget IND-->
                    <c:if test="${sessionScope.user.role.equals('kunde')}">
                        <a class="nav-item nav-link" style="pointer-events: none; cursor: default;">Velkommen: ${fn:toLowerCase(sessionScope.user.email)}</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/forside?command=forside">Forside</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/index.jsp">Forside</a> <!--???-->
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/faq?command=faq">FAQ</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/profil?command=profil">Profil</a>
                    </c:if>

                    <!--Navigationsbaren hvis admin er logget IND-->
                    <c:if test="${sessionScope.user.role.equals('admin')}">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/forespoergsler?command=forespoergsler">Forespørgsler</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/ordre?command=ordre">Ordre</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/kunder?command=kunder">Kunder</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/lager?command=lager">Lager</a>
                        <a class="nav-item nav-link" style="pointer-events: none; cursor: default;">Admin: ${fn:toLowerCase(sessionScope.user.email)}</a>
                    </c:if>

                    <c:if test="${sessionScope.user != null}">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="logout"/>
                            <button type="submit" class="btn btn-secondary">
                                Log ud <img src="${pageContext.request.contextPath}/images/log_out.png" style="width: 20px" alt="">
                            </button>
                        </form>

                    </c:if>
                </div>
            </div>
        </div>
    </nav>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Du kan logge ind her</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                            aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="login"/>
                        <label for="email">Email: </label>
                        <br>
                        <input type="email" id="email" name="email" required/>
                        <br>
                        <label for="password">Kodeord: </label>
                        <br>
                        <input type="password" id="password" name="password" minlength="4"
                               required/>
                        <br>
                        <br>
                        <input type="submit" value="Log ind"/>
                        <br>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#modal2" aria-label="Close"
                            data-dismiss="#exampleModal" style="margin-right: 25%;">
                        Ikke medlem endnu? Klik her.
                    </button>
                </div>
            </div>
        </div>
    </div>


</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <h1><jsp:invoke fragment="header"/></h1>
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container mt-3">
    <hr/>
    <div class="row mt-4">
        <div class="col">
            Nørgaardsvej 30<br/>
            2800 Lyngby
        </div>
        <div class="col">
            <jsp:invoke fragment="footer"/><br/>
            <p>&copy; 2022 Cphbusiness</p>
        </div>
        <div class="col">
            Datamatikeruddannelsen<br/>
            2. semester forår 2022
        </div>
    </div>

</div>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>