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
        <div class="row mb-5">
            <div class="col-1"></div>
            <div class="col-10">
                <h3 class="text-primary mt-3 text-center pt-3 bg-light" >Sign-Up</h3>
                <form action="signup.do" method="post" class="w-50 m-auto">
                    <div class="mb-3">
                        <label class="form-label" for="nm">Name</label>
                        <input class="form-control" id="nm" type="text" name="name">
                        <div class="form-text">Please enter your name</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="eml">Email</label>
                        <input class="form-control" type="email" name="email" id="eml">
                        <div class="form-text">Please enter your email</div>
                    </div>
                    <div class="mb-3">
                        <label for="pwd" class="form-label">Password</label>
                        <input type="password" class="form-control" id="pwd" name="password">
                        <div class="form-text">Please Enter Valid Password</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="country">Country</label>
                        <select class="form-select" name="country" id="country">
                            <c:forEach var="country" items="${countries}" >
                                <option value="${country.countryId}">${country.name}</option>
                            </c:forEach>
                        </select>
                        <div class="form-text">Select your country</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="phn">Phone number</label>
                        <input class="form-control" type="number" name="phone" id="phn">
                        <div class="form-text">Please enter your mobile number without country code</div>
                    </div>

                    <div class="g-recaptcha mb-3" data-sitekey="6Lc5sSopAAAAAEoDTF_P9Pu1h3vt1IwrONV73YSm"></div>

                    <input type="submit" value="SignUp" class="btn btn-primary mb-3">
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