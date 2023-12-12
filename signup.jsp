<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: TDF Home ::</title>

    <link rel="icon" href="static/images/tdf_logo.ico">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    
    <link rel="stylesheet" href="static/css/form.css">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
    <div class="container-fluid">
        <!-- ############################### -- Header -- ###################################-->
        <c:import url="header.jsp" />
        
        <!-- ############################### -- Page Body -- ###################################-->
        <div class="row mb-5" style="margin-bottom: 100px;">
            <div class="col-1"></div>
            <div class="col-md-10">
                <h3 class="text-primary my-3 text-center py-3 bg-light" >Sign-Up</h3>

                <form action="signup.do" method="post" class="w-50 m-auto p-5 border rounded needs-validation form_style" style="background-color:#ddd;" novalidate>
                    <div class="mb-3">
                        <label class="form-label" for="nm">Name</label>
                        <input class="form-control" id="nm" type="text" name="name">
                        <!-- <div class="form-text">Please enter your name</div> -->
                        <div class="invalid-feedback">Please Enter Your Full Name</div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="text" class="form-control" id="email" name="email">
                        <div class="invalid-feedback" id="email_error_feedback">Please Enter Valid Email</div>
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
                        <div class="invalid-feedback">Please enter a valid phone number</div>
                    </div>
                    <div class="mb-3">
                        <input type="button" class="d-block btn btn-outline-secondary" value="Send OTP" id="btn">
                    </div>
                    <div class="mb-3 otpbox d-none" >
                        <label for="otpfield" class="form-label">Enter the otp sent to your mobile number</label>
                        <input type="number"  id="otpfield" class="check-otp-box form-control ">
                        <input type="button" value="Check OTP" class="btn btn-outline-secondary mt-3" id="checkotpbutton">
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
        //------------ PHONE ----------------------
        let phone = document.querySelector('#phn');
        let contactPattern = /^[6-9][0-9]{9}$/;
        
        let checkPhoneExists = async (phone)=>{
            console.log(phone);
            let response = await fetch('check_phone_exists.do?phone='+phone);
            let result = await response.text();
            return result;
        }
        phone.addEventListener('focus',()=>{
            
            phone.classList.remove('is-invalid');
            phone.classList.remove('is-valid');
        });

        phone.addEventListener('blur',()=>{
            if(contactPattern.test(phone.value)){

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
            }else{
                phone.classList.add('is-invalid');
            }
        });
        //------------ PHONE ----------------------
        
        //------------ OTP ----------------------
        let checkotpbutton = document.querySelector('#checkotpbutton');
        let send_otp = document.querySelector('#btn');
        let otpbox = document.querySelector('.otpbox');
        let otpfield = document.querySelector('#otpfield');

        let generateOTP = async (phone)=>{
            console.log("entered phone no. is:"+phone);
            let response = await fetch('generate_otp.do?phone='+phone);
            let result = await response.text();
            return result;
        }
        send_otp.addEventListener('click',()=>{
            generateOTP(phone.value).then((data)=>{
                if(data == 'true'){
                    otpbox.classList.replace('d-none','d-block');
                    send_otp.classList.replace('d-block','d-none');
                }
            }).catch((error)=>{
                console.log(error);
            });
        });


        let checkOTP = async (otp)=>{
            console.log(otp);
            let response = await fetch('check_otp.do?otp='+otp);
            let result = await response.text();
            return result;
        };
        checkotpbutton.addEventListener('click',()=>{
            checkOTP(otpfield.value).then((data)=>{
                if(data == 'true'){
                    otpbox.classList.replace('d-block','d-none');

                    console.log("OTP verified");
                    phone.readOnly= true;
                    isVerified = true;
                }else{
                    console.log("Incorrect OTP");
                }
            }).catch((error)=>{
                console.log(error)
            });
        });

        //------------ EMAIL ----------------------
        let email = document.querySelector('#email');  
        let email_error_feedback = document.querySelector('#email_error_feedback'); 
        let emailDuplicateTest = false; 

        let emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
        
        let checkEmailExists = async (email) => {
            console.log('Entered value of email is: ' + email);
            let response = await fetch('check_email_exists.do?email=' + email);
            let result = await response.text();

            return result;
        };

        email.addEventListener('focus', () => {
            emailDuplicateTest = false;
            email.classList.remove('is-invalid');
            email.classList.remove('is-valid');
            email_error_feedback.innerText = 'Please Enter Valid Email';
        });

        email.addEventListener('blur', (event) => {
            if(emailPattern.test(event.target.value)) {
                checkEmailExists(event.target.value).then((data)=>{
                    console.log(data);
                    if(data=='true'){
                        emailDuplicateTest = true;
                        email.classList.add('is-invalid');
                       //email.classList.add('invalid');
                        email_error_feedback.innerText = 'Email Already Exists...';
                    } else {
                        email.classList.add('is-valid');
                    }    
                }).catch((error)=>{
                    console.log(error);
                });
            } else {
                email.classList.add('is-invalid');
                email_error_feedback.innerText = 'Email Pattern Mismatch';
            }
        });
        //------------ EMAIL ----------------------
        
        (()=>{
            'use strict'

            //Fetch all the forms we want to apply custom Bootstrap validation styles to 
            const forms = document.querySelectorAll('.needs-validation')

            //Loop over them and prevent submission
            Array.from(forms).forEach(form => {
                form.addEventListener('submit',event => {
                    if(!form.checkValidity() || emailDuplicateTest){
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated')
                },false)
            })
        })()
    </script>
</body>
</html>