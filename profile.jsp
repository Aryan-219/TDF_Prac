<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${user == null}">
    <c:redirect url="signin.do" />
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: User Profile ::</title>
    
    <link rel="icon" href="static/images/tdf_logo.ico">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/dropzone@5/dist/min/dropzone.min.css" type="text/css" />

    <script src="https://unpkg.com/dropzone@5/dist/min/dropzone.min.js"></script>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    
</head>
<body>
    <!-- ############################### -- Pic Upload Modal -- start ###################################-->
    <div class="modal fade" id="pic_upload_modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3>Upload Your Pic</h3>
                </div>
                <div class="modal-body">
                    <form action="upload_pic.do" class="dropzone" id="pic-upload-form"></form>
                </div>
                <div class="modal-footer"></div>
            </div>
        </div>
    </div>
    <!-- ############################### -- Pic Upload Modal -- end ###################################-->


    <div class="container-fluid">
        <!-- ############################### -- Header -- ###################################-->
        <c:import url="header.jsp" />
        
        <!-- ############################### -- Page Body -- ###################################-->
        <div class="row">            
            <div class="col-10 offset-1">
                <h3 class="my-3 py-3 bg-light text-primary ps-3 rounded-2"><c:out value="${user.name}" /></h3>
                
                <div class="accordion accordion-flush" id="profile_parent_box">
                    <div class="accordion-item" style="border: 1px solid #ddd">
                        <h2 class="accordion-header">
                            <button type="button" class="accordion-button" data-bs-toggle="collapse" data-bs-target="#sub_section_1">
                                My Profile
                            </button>
                        </h2>
                        <div id="sub_section_1" class="accordion-collapse collapse show p-4" data-bs-parent="#profile_parent_box">
                            <div class="row">
                                <div class="col-md-4 justify-content-center d-flex">
                                    <div class="shadow-lg rounded-circle w-75 text-center">
                                        <img src="show_pic.do" id="my_pic" data-bs-toggle="modal" data-bs-target="#pic_upload_modal" class="rounded-circle" style="object-fit:cover" width="270">
                                    </div>
                                </div>
                                <div class="col-md-8 fs-5 ">
                                    <div class="row mb-2 mt-md-0 mt-5">
                                        <div class="col-sm-4 text-md-end">
                                            <b>Name :</b>
                                        </div>
                                        <div class="col-sm-8">
                                            <span><c:out value="${user.name}" /></span>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col-sm-4 text-md-end">
                                            <b>Email :</b>
                                        </div>
                                        <div class="col-sm-8">
                                            <span><c:out value="${user.email}" /></span>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col-sm-4 text-md-end">
                                            <b>Password :</b>
                                        </div>
                                        <div class="col-sm-8">
                                            <span>******</span>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col-sm-4 text-md-end">
                                            <b>Phone :</b>
                                        </div>
                                        <div class="col-sm-8">
                                            <span><c:out value="${user.phone}" /></span>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col-sm-4 text-md-end">
                                            <b>Country :</b>
                                        </div>
                                        <div class="col-sm-8">
                                            <span><c:out value="${user.country.name}" /></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="accordion-item" style="border: 1px solid #ddd">
                        <h2 class="accordion-header">
                            <button type="button" class="accordion-button" data-bs-toggle="collapse" data-bs-target="#sub_section_2">
                                Interest
                            </button>
                        </h2>
                        <div id="sub_section_2" class="accordion-collapse collapse p-4" data-bs-parent="#profile_parent_box">
                                                        
                            <div id="saved_interest">
                                <div class="text-end">
                                    <span class="material-symbols-outlined" id="edit_interest" style="cursor: pointer;">
                                        edit_square
                                    </span>
                                </div>
                                <p class="lead">
                                    ${user.interest}
                                </p>
                            </div>
                            <div id="interest_form">
                                <label for="interest" class="form-label lead"><b>Your Area of Interest:</b></label>
                                <textarea id="interest" cols="30" rows="7" class="form-control">${user.interest}</textarea>
                                <button type="button" id="interest_btn" class="btn btn-primary mt-3">Save</button>
                            </div>
                            
                            
                            <c:if test="${user.interest != null}">
                                <input type="hidden" id="uinterest" value="${user.interest}">
                            </c:if>
                            
                            
                        </div>
                    </div>
                    <div class="accordion-item" style="border: 1px solid #ddd">
                        <h2 class="accordion-header">
                            <button type="button" class="accordion-button" data-bs-toggle="collapse" data-bs-target="#sub_section_3">
                                Technologies Used
                            </button>
                        </h2>
                        <div id="sub_section_3" class="accordion-collapse collapse p-4" data-bs-parent="#profile_parent_box">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis corrupti architecto vel, repellendus earum, debitis eius placeat incidunt quaerat consectetur totam repudiandae molestiae ab necessitatibus explicabo? Impedit ipsa sapiente quod?
                            Dolor dolore eligendi animi qui pariatur dignissimos est dicta ut, impedit delectus quae quis error hic consequatur vitae voluptas quos recusandae iusto odit neque facilis incidunt. Ab ullam a quae.
                            Eaque reprehenderit illum architecto vero corporis dolore, dicta commodi fugiat qui. Dolore eius magni ullam quidem architecto, vero consequatur sit unde dignissimos numquam ipsa sint nesciunt illo rerum incidunt quasi.
                        </div>
                    </div>
                    <div class="accordion-item" style="border: 1px solid #ddd">
                        <h2 class="accordion-header">
                            <button type="button" class="accordion-button" data-bs-toggle="collapse" data-bs-target="#sub_section_4">
                                Occupation
                            </button>
                        </h2>
                        <div id="sub_section_4" class="accordion-collapse collapse p-4" data-bs-parent="#profile_parent_box">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Blanditiis corrupti architecto vel, repellendus earum, debitis eius placeat incidunt quaerat consectetur totam repudiandae molestiae ab necessitatibus explicabo? Impedit ipsa sapiente quod?
                            Dolor dolore eligendi animi qui pariatur dignissimos est dicta ut, impedit delectus quae quis error hic consequatur vitae voluptas quos recusandae iusto odit neque facilis incidunt. Ab ullam a quae.
                            Eaque reprehenderit illum architecto vero corporis dolore, dicta commodi fugiat qui. Dolore eius magni ullam quidem architecto, vero consequatur sit unde dignissimos numquam ipsa sint nesciunt illo rerum incidunt quasi.
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        
        <!-- ############################### -- Footer -- ###################################-->
        <c:import url="footer.jsp" />
    </div>

    <script>
        let my_pic = document.querySelector('#my_pic');
        let i = 0;

        Dropzone.options.picUploadForm = {
            init: function() {
                this.on('complete', () => {
                    my_pic.src = 'show_pic.do?param=' + i++;                   
                });
            }
        };
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        let interest = document.querySelector('#interest');
        let interest_btn = document.querySelector('#interest_btn');

        let saved_interest = document.querySelector('#saved_interest');
        let interest_form = document.querySelector('#interest_form');
        let uinterest = document.querySelector('#uinterest');
        let edit_interest = document.querySelector('#edit_interest');

        edit_interest.addEventListener('click', () => {
            interest_form.style.display = 'block';
            saved_interest.style.display = 'none';
        });

        if(uinterest) {
            interest_form.style.display = 'none';
            saved_interest.style.display = 'block';
        } else {            
            interest_form.style.display = 'block';
            saved_interest.style.display = 'none';
        }

        let saveInterest = async () => {
            let response = await fetch('save_interest.do?interest='+interest.value);
            let result = await response.text();

            return result;
        };

        interest_btn.addEventListener('click', () => {
            saveInterest().then((data)=>{
                if(data=='true') {
                    interest_form.style.display = 'none';
                    saved_interest.style.display = 'block';
                    saved_interest.children[1].innerText = interest_form.children[1].value;  
                }
            }).catch((err)=>{
                console.log(err);
            });
        });
    </script>
</body>
</html>