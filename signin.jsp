<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: Sign-In ::</title>
    
    <link rel="icon" href="static/images/tdf_logo.ico">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <link rel="stylesheet" href="static/css/form.css">  

    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
    <!-- ############################### -- Forgot Password Modal -- start ###################################-->
    <div class="modal fade" id="forgot_password">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title">Forgot Password</h2>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="alert d-none my-2" id="reset_message"></div>
                    
                    <div class="mb-3">
                        <label for="email" class="form-label">Enter Your Email</label>
                        <input type="text" class="form-control" id="reset_email">
                        <div class="form-text">We will send you a password reset link...</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="reset_password_email_btn">
                        <span id="loader" class="spinner-border spinner-border-sm me-2 d-none"></span>Send Email</button>
                </div>
            </div>
        </div>
    </div>
    <!-- ############################### -- Forgot Password Modal -- end ###################################-->


    <div class="container-fluid">
        <!-- ############################### -- Header -- ###################################-->
        <c:import url="header.jsp" />

        <c:if test="${signin_err_msg != null}">
            <div class="row justify-content-center">
                <div class="col-lg-8 alert alert-danger">
                    <c:out value="${signin_err_msg}" />
                </div>
            </div>
        </c:if>
        
        <!-- ############################### -- Page Body -- ###################################-->
        <div class="row justify-content-center" style="margin-bottom: 100px;">
            <div class="col-lg-4 col-md-6">
                <h3 class="my-3 py-3 bg-light text-primary text-center rounded-2">Sign-In</h3>

                <form action="signin.do" method="post" class="m-auto p-5 border rounded form_style" style="background-color: #ddd;">
                    <input type="hidden" name="next_url" value="${param.next_url}">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email">
                        <div class="form-text">Please Enter Valid Email</div>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                        <div class="form-text">Please Enter Valid Password</div>
                    </div>
                    
                    
                    <!-- <div class="g-recaptcha mb-3" data-sitekey="6LeDNhUpAAAAAFN6P3iDlLhyA_JZeSXN4Ql5orCF"></div> -->
                    <div>
                        <input type="submit" value="SignIn" class="btn btn-primary">
                        <button type="button" class="btn btn-secondary float-end" data-bs-toggle="modal" data-bs-target="#forgot_password">Forgot Password</button>
                    </div>

                    <div class="d-grid gap-2 mt-4">
                        <span class="text-danger text-help ps-2" style="font-size: .9em">Or, Create New Account...</span>
                        <a href="signup.do" class="btn btn-success">SignUp</a>
                    </div>
                </form>
            </div>
        </div>
        
        <!-- ############################### -- Footer -- ###################################-->
        <c:import url="footer.jsp" />
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        let reset_password_email_btn = document.querySelector('#reset_password_email_btn');
        let reset_email = document.querySelector('#reset_email');
        let reset_message = document.querySelector('#reset_message');
        let loader = document.querySelector('#loader');

        let sendResetPasswordMail = async () => {
            let response = await fetch('send_reset_password_mail.do?email=' + reset_email.value);
            let result = await response.text();

            return result;
        };

        reset_password_email_btn.addEventListener('click', () => {
            loader.classList.replace('d-none', 'd-inline-block');

            sendResetPasswordMail().then(data => {
                if(data == 'true') {
                    reset_message.innerText = 'Email for reset password sent...';
                    reset_message.classList.replace('d-none', 'd-block');
                    reset_message.classList.add('alert-success');
                    loader.classList.replace('d-inline-block', 'd-none');
                } else {
                    reset_message.innerText = 'No such email found ...';
                    reset_message.classList.replace('d-none', 'd-block');
                    reset_message.classList.add('alert-danger');
                }
            }).catch(err => {
                console.log(err);
            });
        });
    </script>
</body>
</html>