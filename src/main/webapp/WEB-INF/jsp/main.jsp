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
<header class="d-flex justify-content-center py-3">
    <ul class="nav nav-pills">
        <li class="nav-item"><a href="MyController?command=go_to_main_page" class="nav-link">Главная</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Новости</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Профиль</a></li>
        <li class="nav-item"><a href="#" class="nav-link">Выход</a></li>
    </ul>
</header>
<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-200">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-12">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <h3 class="mb-5">Рады приветствовать Вас на нашем портале, <c:out value="${sessionScope.user.name}"/></h3>
                        <a href="MyController?command=do_logout">Выйти</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>