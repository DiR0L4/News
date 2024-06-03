<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Welcome page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
</head>
<body>
<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <h3 class="mb-5">Добро пожаловать</h3>
                        <form class="mb-5" action="MyController" method="post">
                            <input type="hidden" name="command" value="go_to_auth"/>
                            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block" type="submit">Начать</button>
                        </form>
                        <h4>News</h4>
                        <%--<div class="news-item">
                            <c:forEach var="news" items="${requestScope.mainNews}">
                                <img src="${news.imgPath}"
                                     alt="Базовый курс Java" class="img-fluid">
                                <div class="news-content">
                                    <h3 class="news-title">${news.title}</h3>
                                    <p class="news-text">${news.brief}</p>
                                    <p class="news-info">${news.info}</p>
                                </div>
                            </c:forEach>
                        </div>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>