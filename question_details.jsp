<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" uri="http://localhost:8080/tdf" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: Question Detail ::</title>
    <link rel="icon" href="static/images/tdf_logo.ico">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

    <style>
        .ctrl_opt {
            cursor: pointer;
        }

        .title_style {
            text-decoration: none;
        }
    </style>
</head>
<body>  
    <!-- ---------------- POST QUESTION MODAL ----------------------- -->
    <div class="modal fade" id="post_reply_box">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title">Post Question</h2>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <h3 class="text-success d-none" id="post_question_report">Your Question Is Posted Successfully...</h3>
                    <div class="mb-3">
                        <label for="question_title" class="form-label">Question Title</label>
                        <input type="text" id="question_title" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="question_content" class="form-label">Question</label>
                        <textarea id="question_content" rows="7" class="form-control"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="post_question_btn">Post Question</button>
                    <button type="button" data-bs-dismiss="modal" class="btn btn-secondary">Close</button>
                </div>
            </div> 
        </div>
    </div>
    <!-- ---------------- POST QUESTION MODAL ----------------------- END -->
    

    <input type="hidden" id="is_loggedin" value="${user}">

    <div class="container-fluid">
        <!-- ############################### -- Header -- ###################################-->
        <c:import url="header.jsp" />
        
        <!-- ############################### -- breadcrumb -- ###################################-->
        <div class="row mt-4">
            <div class="col-1"></div>
            <div class="col-10">
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.do">Topics</a></li>
                        <li class="breadcrumb-item"><a href="topic_questions.do?topic_id=${question.topic.topicId}&topic_name=${question.topic.name}">All Questions</a></li>
                        <li class="breadcrumb-item active">The-Question</li>
                    </ol>
                </nav>
            </div>
            <div class="col-1"></div>
        </div>

        <!-- ############################### -- Page Body -- ###################################-->
        <div class="row mb-5">
            <div class="col-1"></div>
            <div class="col-10">

                <h3 class="my-3 py-3 bg-light text-primary ps-3 rounded-2">
                    <span>${question.topic.name}<h6 class="d-inline-block ms-3">(Topic)</h6></span>
                    <button type="button" id="reply_form_btn" class="btn btn-primary float-end me-3" data-bs-target="#post_reply_box">Reply</button>
                </h3>
                
                <div class="border border-secondary rounded-3 p-4 mb-4">
                    <h3 class="pb-2"><a href="question_details.do?question_id=${question.questionId}" class="title_style">${question.title}</a></h3>
                    <hr>
                    <p class="mt-3">
                        <span class="text-muted me-5"><b>Posted By:</b> ${question.user.name}</span>
                        <span class="text-muted float-end"><b>Post Date:</b> ${a:formatDate(question.postedOn)}</span>
                    </p>                    
                    <p class="lead">
                        ${question.post}
                    </p>   
                    <hr>
                    <p class="pb-1">
                        <span class="float-start">
                            <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;" title="Share">share</span>
                            <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;" title="Add To Wishlist">favorite</span>
                            <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;" title="Like">thumb_up</span>
                            <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;" title="Dislike">thumb_down</span>
                            <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;" title="Reply">reply</span>                            
                        </span>
                        <span class="float-end">                            
                            <c:if test="${not empty user}">
                                <c:choose>
                                    <c:when test="${question.user.userId == user.userId}">
                                        <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;" title="Edit">edit</span>
                                        <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;color:red" title="Delete">delete</span>
                                    </c:when>
                                    <c:when test="${not a:checkModerator(user.userId,question.topic.topicId)}">
                                        <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;" title="Spam">warning</span>
                                    </c:when>
                                </c:choose>
                                                                    
                                <c:if test="${user.userType.userTypeId==2 and a:checkModerator(user.userId,param.topic_id)}">
                                    <span class="material-symbols-outlined pe-3 ctrl_opt position-relative" title="Spam" style="font-size:1.7rem;">
                                        warning
                                        <span style="font-size:1rem;" class="position-absolute top-0 start-75 translate-middle badge rounded-circle bg-danger">
                                            ${question.spams}
                                            <span class="visually-hidden">unread messages</span>
                                            </span>
                                    </span>
                                    <span class="material-symbols-outlined pe-3 ctrl_opt" style="font-size:1.7rem;" title="Block">block</span>
                                </c:if>
                            </c:if>
                        </span>
                    </p>
                </div>                
            </div>
            <div class="col-1"></div>
        </div>
        
        <!-- ############################### -- Footer -- ###################################-->
        <c:import url="footer.jsp" />
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        let reply_form_btn = document.querySelector('#reply_form_btn');

        //--------- open modal conditionally -------------- start
        const reply_modal_object = new bootstrap.Modal('#post_reply_box', {
            keyboard: false
        });
        
        reply_form_btn.addEventListener('click', () => {
            if(is_loggedin.value != '')
                reply_modal_object.show(post_reply_box);
            else
                window.location = 'signin.do?next_url=' + encodeURIComponent(window.location.href);
        });
        //--------- open modal conditionally -------------- end
    </script>
</body>
</html>

