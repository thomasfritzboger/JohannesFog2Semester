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
    <style> <%@include file="/css/style.css" %> </style>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Titillium+Web:wght@200&display=swap" rel="stylesheet">
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
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
                        <a class="nav-item nav-link" style="pointer-events: none; cursor: default; font-style: italic">Velkommen: ${fn:toLowerCase(sessionScope.user.email)}</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/index.jsp">Forside</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/faq?command=faq">FAQ</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/profile2?command=profile2">Profil</a>
                    </c:if>

                    <!--Navigationsbaren hvis admin er logget IND-->
                    <c:if test="${sessionScope.user.role.equals('admin')}">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/requestList?command=requestList">Forespørgsler</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/order?command=order">Ordre</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/customer?command=customer">Kunder</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/stock?command=stock">Lager</a>
                        <a class="nav-item nav-link" style="pointer-events: none; cursor: default; font-style: italic">Admin: ${fn:toLowerCase(sessionScope.user.email)}</a>
                    </c:if>

                    <c:if test="${sessionScope.user != null}">
                        <form name="logout" action="fc/" method="post">
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

                    <form action="fc/login" method="post">
                        <input type="hidden" name="command" value="login"/>

                        <label for="email">Email: </label>
                        <br>
                        <input type="email" id="email" name="email" placeholder="Indtast email" required style="margin-bottom: 10px"/>
                        <br>
                        <label for="password">Kodeord: </label>
                        <br>
                        <input type="password" id="password" name="password"  placeholder="Indtast kode" minlength="4"
                               required/>
                        <br>
                        <!-- Checkbox -->
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                            <label class="form-check-label" for="form2Example31"> Husk mig </label>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary" >
                            Log ind
                        </button>
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
<footer class="text-center text-black mt-auto" style="background-color: rgba(128, 128, 128, 5%); margin: 25px 5px;">
    <div class="container" style="padding: 20px 0px;">

        <div class="row">
            <div class="col-sm">
                <div>
                    <a href="mailto: carport@fog.com">Send os en email</a><br/>
                    <a href="tel: 70707070">Ring til os</a>
                </div>
            </div>

            <div class="col-sm">
                <a class="btn btn-outline-dark btn-floating m-1" href="#!" role="button"
                ><i class="fa fa-instagram"></i
                ></a>

                <a class="btn btn-outline-dark btn-floating m-1" href="#!" role="button"
                ><i class="fa fa-facebook"></i
                ></a>

                <a class="btn btn-outline-dark btn-floating m-1" href="#!" role="button"
                ><i class="fa fa-linkedin"></i
                ></a>
            </div>

            <div class="col-sm">
                <div>
                    Peter Knudsens Vej<br/>
                    Cvr. 22334455
                </div>
            </div>
        </div>
    </div>
</footer>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>