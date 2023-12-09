<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  </head>
  <body>
    <div class="container-fluid ">
        <!-- ############################ HEADER ######################## -->
        <c:import url="header.jsp" />

        <div class="row mb-5" >
            <div class="col-1"></div>
            <div class="col-10">
                <h3 class="my-3 py-3 bg-light text-primary ps-3 rounded-2">All Topics</h3>
                <table class="table border border-secondary table-hover" style="font-size:1.3rem">
                  <thead class="bg-secondary text-light">
                    <tr>
                      <th>Sr.</th>
                  <th class="table-active text-light">Topics</th>
                      <th>All Posts</th>
                      <th>Open Posts</th>
                      <th>Last Post</th>
                      <th>Active Users</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="topic" items="${topics}" varStatus = "counter">
                      <tr>
                        <td>${counter.count}.</td>
                        <th class="table-active"><a href="#" style="color: #555" class="text-decoration-none">${topic.name}</a>
                      </th>
                        <td>${topic.allPosts}</td>
                        <td>${topic.openPosts}</td>
                        <td>${topic.lastPost}</td>
                        <td>${topic.activeUsers}</td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
            </div>
            <div class="col-1"></div>
        </div>

        <!-- ############################### FOOTER ############################## -->
        <c:import url="footer.jsp" />
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  </body>
</html>