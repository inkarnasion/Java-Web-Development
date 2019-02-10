<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="metubev1.domain.models.views.TubeDetailsViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<%TubeDetailsViewModel tubeDetailsViewModel = (TubeDetailsViewModel) request.getAttribute("tubeDetailsViewModel");%>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>
                        <%= tubeDetailsViewModel.getName()%>
                    </h1>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>
                        <%= tubeDetailsViewModel.getDescription()%>
                    </h3>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="<%= tubeDetailsViewModel.getYouTubeLink()%>">Link To Video</a>
                </div>
                <div class="col col-md-6 d-flex justify-content-center">
                    <p><%= tubeDetailsViewModel.getUploader()%>
                    </p>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="/">Back to Home page</a>
                </div>
            </div>
        </div>
    </main>
    <footer><c:import url="templates/footer.jsp"/></footer>
</div>
</body>
</html>
