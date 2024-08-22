<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />

<div class="row">
    <div class="col">
        <nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">
          <div class="container-fluid">
            <a class="navbar-brand" href="index.do">
              <img src="static/images/tdf_logo.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top mx-3">
              TDF
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                  <a class="nav-link active" aria-current="page" href="index.do">Home</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#">Link</a>
                </li>                          
              </ul>
              <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
              </form>
              <ul class="navbar-nav mb-2 mb-lg-0 ms-5">
                <c:choose>
                  <c:when test="${user == null}">
                    <li class="nav-item me-2">
                      <a class="nav-link btn btn-outline-primary py-1" href="signin.do">Sign-In</a>
                    </li>                          
                    <li class="nav-item me-2">
                      <a class="nav-link btn btn-outline-primary py-1" href="signup.do">Sign-Up</a>
                    </li>
                  </c:when>
                  <c:otherwise>
                    <div class="dropdown">
                      <!-- <span class="material-symbols-outlined dropdown-toggle" data-bs-toggle="dropdown">settings</span> -->
                      <img class="dropdown-toggle" data-bs-toggle="dropdown" src="show_pic.do" height="28">
                      <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="profile.do">${user.name}</a></li>
                        <li><a class="dropdown-item" href="#">Questions</a></li>
                        <li><a class="dropdown-item" href="#">Wishlist</a></li>
                        <li><a class="dropdown-item" href="logout.do">Logout</a></li>
                      </ul>
                    </div>
                      
                      
                      <!-- <a class="nav-link btn btn-outline-primary py-1" href="logout.do">Logout</a> -->
                    
                  </c:otherwise>
                </c:choose>
                  
              </ul>
            </div>
          </div>
        </nav>
    </div>
</div>