<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: Sign-Up ::</title>
    
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
        <div class="row justify-content-center" style="margin-bottom: 100px;">
            
            <div class="col-lg-4 col-md-6">
                <h3 class="my-3 py-3 bg-light text-primary text-center rounded-2">Sign-Up</h3>

                <form action="signup.do" method="post" class="m-auto p-5 border rounded needs-validation form_style" novalidate>
                    <div class="mb-3">
                        <label for="full_name" class="form-label">Full Name</label>
                        <input type="text" class="form-control" id="full_name" name="full_name" required>
                        <!-- <div class="form-text">Please Enter Your Full Name</div> -->
                        <div class="invalid-feedback">Please Enter Your Full Name</div>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="text" class="form-control" id="email" name="email" pattern="^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$">
                        <div class="invalid-feedback" id="email_error_feedback">Please Enter Valid Email</div>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                        <div class="invalid-feedback">Please Enter Valid Password</div>
                    </div>
                    <div class="mb-3">
                        <label for="country" class="form-label">Country</label>
                        <select id="country_list" class="form-select" id="country" name="country" required>
                            <option value="0">Select</option>
                            <c:forEach var="country" items="${countries}">
                                <option value="${country.countryId}">${country.name}</option>
                            </c:forEach>                           
                        </select>
                        <div class="invalid-feedback">Enter Phone Without Country Code</div>
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Contact Number</label>
                        <input type="number" class="form-control" id="phone" name="phone" required>
                        <div class="invalid-feedback" id="phone_error_feedback">Enter Valid Phone Number</div>
                    </div>

                    <input type="button" value="Send OTP" class="btn btn-sm btn-warning" id="send_otp">
                    
                    <div class="mb-3 d-none" id="check_otp_box">
                        <label for="otp" class="form-label">Enter OTP Sent to your Contact Number</label>
                        <input type="number" id="otp" class="form-control">
                        <div class="invalid-feedback" id="otp_error_feedback">Incorrect OTP... Please Enter correct OTP</div>
                        <input type="button" value="Check OTP" id="check_otp" class="mt-2 btn btn-primary btn-sm">
                    </div>

                    <div id="next_step" class="d-none">
                        <div class="g-recaptcha mb-3" data-sitekey="6LeDNhUpAAAAAFN6P3iDlLhyA_JZeSXN4Ql5orCF"></div>
    
                        <input type="submit" value="SignUp" class="btn btn-primary">
                    </div>
                </form>
            </div>
            
        </div>
        
        <!-- ############################### -- Footer -- ###################################-->
        <c:import url="footer.jsp" />
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        let next_step = document.querySelector('#next_step');

        //---------------------- Contact ------------------------------------ start

        let phone = document.querySelector('#phone');
        let phone_error_feedback = document.querySelector('#phone_error_feedback');
        let send_otp = document.querySelector('#send_otp');
        let check_otp_box = document.querySelector('#check_otp_box');
        let otp = document.querySelector('#otp');
        let check_otp = document.querySelector('#check_otp');

        let contactPattern = /^[6-9][0-9]{9}$/;
        let checkDuplicatePhone = false;

        let checkPhoneExists = async (phone) => {
            let response = await fetch('check_phone_exists.do?phone='+phone);
            let result = await response.text();

            return result;
        };

        let phoneFocusFunc = () => {
            checkDuplicatePhone = false;
            phone.classList.remove('is-invalid');
            phone.classList.remove('is-valid');
            phone_error_feedback.innerText = 'Enter Valid Phone Number';
            send_otp.classList.remove('d-none');
            send_otp.classList.add('d-inline');
            check_otp_box.classList.remove('d-block');
            check_otp_box.classList.add('d-none');           
        };

        phone.addEventListener('focus', phoneFocusFunc);

        phone.addEventListener('blur', (event) => {
            if(contactPattern.test(phone.value)) {
                checkPhoneExists(phone.value).then((data)=>{
                    if(data == 'true') {
                        checkDuplicatePhone = true;
                        phone.classList.add('is-invalid');
                        phone_error_feedback.innerText = 'Phone Number Already Exists...'; 
                    } else {
                        phone.classList.add('is-valid');
                    }
                }).catch((error)=>{
                    console.log(error);
                });
            } else {
                phone.classList.add('is-invalid');
            }
        });

        // ~~~~~~~~~ OTP ~~~~~~~~~~~~~ start
        
        let generateOTP = async () => {
            let response = await fetch('generate_otp.do?phone='+phone.value);
            let result = await response.text();

            return result;
        };

        send_otp.addEventListener('click', () => {
            generateOTP().then((data)=>{
                if(data == 'true') {
                    check_otp_box.classList.replace('d-none', 'd-block');
                    send_otp.classList.add('d-none');
                }
            }).catch((err)=>{
                console.log(err);
            });
        });

        otp.addEventListener('focus', () => {
            otp.classList.remove('is-invalid');
        });

        let checkOTP = async () => {
            let response = await fetch('check_otp.do?otp='+otp.value);
            let result = await response.text();
            
            return result;
        };

        check_otp.addEventListener('click', () => {
            checkOTP().then(data=>{
                if(data == 'true') {
                    check_otp_box.classList.replace('d-block', 'd-none');
                    next_step.classList.replace('d-none', 'd-block');
                    phone.readOnly = true;
                    phone.removeEventListener('focus', phoneFocusFunc);
                } else {
                    otp.classList.add('is-invalid');
                }
            }).catch(err => {
                console.log(err);
            });
        });

        // ~~~~~~~~~ OTP ~~~~~~~~~~~~~ end
        //---------------------- Contact ------------------------------------ end

        //---------------------- Email ------------------------------------ start
        let email = document.querySelector('#email');  
        let email_error_feedback = document.querySelector('#email_error_feedback'); 
        let emailDuplicateTest = false; 

        let emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
        
        let checkEmailExists = async (email) => {
            console.log('check_email_exist.do?email=' + email);
            let response = await fetch('check_email_exist.do?email=' + email);
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
                    if(data=='true'){
                        emailDuplicateTest = true;
                        email.classList.add('is-invalid');
                        email_error_feedback.innerText = 'Email Already Exists...';
                    } else {
                        email.classList.add('is-valid');
                    }    
                }).catch((error)=>{
                    console.log(error);
                });
            } else {
                email.classList.add('is-invalid');
            }
        });
        //---------------------- Email ------------------------------------ end
    </script>

    <script>
        (() => {
            'use strict'

            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            const forms = document.querySelectorAll('.needs-validation')

            // Loop over them and prevent submission
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                
                if (!form.checkValidity() || emailDuplicateTest || checkDuplicatePhone) {
                    event.preventDefault()
                    event.stopPropagation()
                }    

                form.classList.add('was-validated')
                }, false)
            })
        })()
    </script>
</body>
</html>