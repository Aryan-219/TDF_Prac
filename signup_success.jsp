<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: TDF Home ::</title>
    
    <link rel="icon" href="static/images/tdf_logo.ico">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container-fluid">
        <!-- ############################### -- Header -- ###################################-->
        <c:import url="header.jsp" />
        
        <!-- ############################### -- Page Body -- ###################################-->
        <div class="row">
            <div class="col-1"></div>
            <div class="col-10">
                <h3 class="my-3 py-3 bg-light text-primary ps-3 rounded-2">Congratulations!!</h3>
                <p class="lead">Your Account is created ... Please Check Email and Verify Your Account</p>
            </div>
            <div class="col-1"></div>
        </div>
        
        <!-- ############################### -- Footer -- ###################################-->
        <c:import url="footer.jsp" />
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>