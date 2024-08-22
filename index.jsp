<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>:: TDF Home ::</title>
    <link rel="icon" href="static/images/tdf_logo.ico">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container-fluid">
        <!-- ############################### -- Header -- ###################################-->
        <c:import url="header.jsp" />

        <div class="row">
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
                <c:forEach var="topic" items="${topics}" varStatus="counter">
                  <tr>
                    <td>${counter.count}.</td>
                    <th class="table-active">
                      <a href="topic_questions.do?topic_id=${topic.topicId}&topic_name=${topic.name}" style="color:#555" class="text-decoration-none">${topic.name}</a>
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

        <!-- ############################### -- Footer -- ###################################-->
        <c:import url="footer.jsp" />
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>