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
        <div class="row mb-5" style="margin-bottom: 100px;">
            <!-- <div class="col-1"></div> -->
            <div class="col-md-10">
                <h3 class="text-primary my-3 text-center py-3 bg-light" >Sign-Up</h3>

                <form action="signup.do" method="post" class="w-50 m-auto p-5 border rounded needs-validation" style="background-color:#ddd;" novalidate>
                    <div class="mb-3">
                        <label class="form-label" for="nm">Name</label>
                        <input class="form-control" id="nm" type="text" name="name">
                        <!-- <div class="form-text">Please enter your name</div> -->
                        <div class="invalid-feedback">Please Enter Your Full Name</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="eml">Email</label>
                        <input class="form-control" type="text" name="email" id="eml" pattern="^/w+@[a-zA-Z]+?\.[a-zA-Z]{2,3}$">
                        <div class="invalid-feedback">Please enter valid Email</div>
                    </div>
                    <div class="mb-3">
                        <label for="pwd" class="form-label">Password</label>
                        <input type="password" class="form-control" id="pwd" name="password" required>
                        <div class="invalid-feedback">Please Enter Valid Password</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="country">Country</label>
                        <select class="form-select" name="country" id="country" required>
                            <option value="0">Select</option>
                            <c:forEach var="country" items="${countries}" >
                                <option value="${country.countryId}">${country.name}</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">Kindly select your country</div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="phn">Phone number</label>
                        <input class="form-control" type="number" name="phone" id="phn" required>
                        <div class="invalid-feedback">Enter correct phone number</div>
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

    <script>
        let email = document.querySelector('#eml');
        let phone = document.querySelector('#phn');
        
        //------------ PHONE ----------------------
        let checkPhoneExists = async (phone)=>{
            console.log(phone);
            let response = await fetch('check_phone_exists.do?phone='+phone);
            let result = await response.text();
            return result;
        }

        phone.addEventListener('blur',()=>{
            checkPhoneExists(phone.value).then((data)=>{
                console.log(data);
                if(data=='true'){
                    phone.classList.add('is-invalid');
                }else{
                    phone.classList.add('is-valid');
                }
            }).catch((error)=>{
                console.log(error);
            });
        });
        //------------ PHONE ----------------------

        //------------ EMAIL ----------------------
        let checkEmailExists = async (email) => {
            <!-- console.log("This is the email value "+ email); -->
            let response = await fetch('check_email_exists.do?email='+email);
            let result = await response.text();
            return result;
        }
        email.addEventListener('blur', ()=>{
            checkEmailExists(email.value).then((data)=>{
                console.log(data);
                if(data == 'true'){
                    email.classList.add('is-invalid');
                }else{
                    email.classList.add('is-valid');
                }
            }).catch((error)=>{
                console.log(error);
            });
        });
        //------------ EMAIL ----------------------

        (()=>{
            'use strict'

            //Fetch all the forms we want to apply custom Bootstrap validation styles to 
            const forms = document.querySelectorAll('.needs-validation')

            //Loop over them and prevent submission
            Array.from(forms).forEach(form => {
                form.addEventListener('submit',event => {
                    if(!form.checkValidity()){
                        event.preventDefault()
                        event.stopPropogation()
                    }

                    form.classList.add('was-validated')
                },false)
            })
        })()
    </script>
</body>
</html>