

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CI Demo Game Store</title>
        <!-- Default Stylesheets -->
        <link rel="shortcut icon" href="images/logo/ico.ico"/>

        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/text.css"/>
        <link rel="stylesheet" type="text/css" href="css/960_16.css"/>
        <link rel="stylesheet" type="text/css" href="css/styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/product.css"  />

        <script type="text/javascript" src="js/jquery.js"></script>

        <script type="text/javascript" src="js/myScript.js"></script>

    </head>
    <body>

        <%
            if (session.getAttribute("src/main/java/user") == null) {// THen new user, show join now
        %>
        <jsp:include page="includesPage/_joinNow.jsp"></jsp:include>
        <%        } else {
        %>
        <jsp:include page="includesPage/_logout.jsp"></jsp:include>
        <%            }
        %>


        <div class="container_16">
            <div id="whiteBox" style="padding: 10px;">
                <br/>
                <h1>About the CI Demo Game Store</h1>
                <hr/>
            </div>
        </div>

        <div class="container_16">
            <div id="whiteBox" class="grid_16" style="padding: 10px;">
                <div class="push_1">
                    <p>This application exists to demonstrate how Continuous Integration and Continuous Deployment (CI/CD) can include the database tier. </p>
                </div> 
                <div class="push_1">
                    <p>Version 0.1</p>
                </div> 
            </div>
        </div>
        <jsp:include page="includesPage/_footer.jsp"></jsp:include>
    </body>
</html>
