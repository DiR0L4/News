<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Вход</title>
</head>
<body>
<header class="d-flex justify-content-center py-3 border-bottom">
        <span class="fs-4 col-md-3">
            <c:if test="${not (sessionScope.user eq null)}">
                <c:out value="${sessionScope.user.getLogin()}"/>
            </c:if>
            <c:if test="${sessionScope.user eq null}">
                Гость
            </c:if>
        </span>
        <ul class="nav nav-pills col-md-a3">
            <li class="nav-item"><a href="MyController?command=go_to_main_page" class="nav-link">Главная</a></li>
            <li class="nav-item"><a href="#" class="nav-link">Новости</a></li>
            <li class="nav-item"><a href="#" class="nav-link">Профиль</a></li>
            <li class="nav-item"><a href="MyController?command=do_logout" class="nav-link">Выход</a></li>
        </ul>
        <div class="col-md-3 text-end">
            <c:if test="${sessionScope.user eq null}">
                <a href="MyController?command=go_to_auth" class="nav-link">Вход</a>
                <a href="MyController?command=go_to_registration_page" class="nav-link">Регистрация</a>
            </c:if>
        </div>
</header>
<section class="vh-200" style="background-color: #508bfc;">
    <div class="container py-5 h-200">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-12">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <c:if test="${not (sessionScope.user eq null)}">
                            <h2 class="mb-5">Рады приветствовать Вас на нашем портале, <c:out value="${sessionScope.user.getLogin()}"/></h2>
                        </c:if>
                        <h2 class="mb-5">Главное к этому часу</h2>
                        <div class="row justify-content-center">
                            <c:forEach var="news" items="${requestScope.lastNews}">
                                <div onclick="location.href=''" class="news-item col-md-5 mx-4 mb-5 border border-3 rounded">
                                    <img src="${news.imgPath}"
                                         alt="News image" class="img-thumbnail mb-3">
                                    <h4 class="news-title mb-2">${news.title}</h4>
                                    <p class="news-brief fs-8 text-muted text-start">${news.brief}</p>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>