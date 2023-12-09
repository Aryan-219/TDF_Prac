<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: TDF Home ::</title>

    <link rel="icon" href="static/images/tdf_logo.ico">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

<body>
    <div class="container-fluid">
        <!-- ############################### -- Header -- ###################################-->
        <c:import url="header.jsp" />

        <!-- ############################### -- Page Body -- ###################################-->
        <div class="row">
            <div class="col-1"></div>
            <div class="col-10">
                <h3 class="text-center bg-light rounded-2 text-primary mt-3">SignIn</h3>

                <form action="signin.do" method="post" class="w-50 m-auto p-5  rounded-3" style="background-color: #ddd;">
                    <div class="mb-3">
                        <label class="form-label" for="eml">Email</label>
                        <input class="form-control" type="email" name="email" id="eml">
                        <div class="form-text">Enter your email</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="pwd">Password</label>
                        <input class="form-control" type="password" name="password" id="pwd">
                        <div class="form-text">Enter your password</div>
                    </div>
                    
                    <div class="mb-3">
                        <div class="g-recaptcha  " data-sitekey="6Lc5sSopAAAAAEoDTF_P9Pu1h3vt1IwrONV73YSm">
                            
                        </div>
                    </div>
                    <div class="mb-3">
                        <input class="btn btn-primary " type="submit" value="SignIn">
                    </div>
                </form>
            </div>
            <div class="col-1"></div>
        </div>

        <!-- ############################### -- Footer -- ###################################-->
        <c:import url="footer.jsp" />
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>