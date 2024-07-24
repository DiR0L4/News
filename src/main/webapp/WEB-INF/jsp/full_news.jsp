<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="locale" value="${sessionScope.locale}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>

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
<header class="d-flex justify-content-center py-3 fs-6 border-bottom">
        <span class="fs-4 col-md-2">
            <c:if test="${not (sessionScope.user eq null)}">
                <c:out value="${sessionScope.user.getLogin()}"/>
            </c:if>
            <c:if test="${sessionScope.user eq null}">
                <fmt:message
                        key="header.role.guest"/>
            </c:if>
        </span>
    <div class="language-selector col-md-1">
        <a href="MyController?command=do_change_locale&lang=en" class="row"><fmt:message
                key="header.locale_en"/></a>
        <a href="MyController?command=do_change_locale&lang=ru" class="row"><fmt:message
                key="header.locale_ru"/></a>
    </div>
    <ul class="nav nav-pills col-md-4 justify-content-center">
        <li class="nav-item"><a href="MyController?command=go_to_main_page" class="nav-link"><fmt:message
                key="header.link.main"/></a></li>
        <li class="nav-item"><a href="MyController?command=go_to_news_by_tags_page" class="nav-link"><fmt:message
                key="header.link.news"/></a></li>
        <li class="nav-item"><a href="MyController?command=go_to_profile_page" class="nav-link"><fmt:message
                key="header.link.profile"/></a></li>
        <c:if test="${sessionScope.user.getRoleId() eq 1 || sessionScope.user.getRoleId() eq 2}">
            <li class="nav-item"><a href="MyController?command=go_to_add_news_page" class="nav-link"><fmt:message
                    key="header.link.addnews"/></a></li>
        </c:if>
        <li class="nav-item"><a href="MyController?command=do_logout" class="nav-link"><fmt:message
                key="header.link.exit"/></a></li>
    </ul>
    <div class="col-md-3 text-end">
        <c:if test="${sessionScope.user eq null}">
            <a href="MyController?command=go_to_auth" class="nav-link"><fmt:message
                    key="header.link.auth"/></a>
            <a href="MyController?command=go_to_registration_page" class="nav-link"><fmt:message
                    key="header.link.reg"/></a>
        </c:if>
    </div>
</header>
<section class="vh-200" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center w-70 h-100">
            <div class="col-12 col-md-12">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center justify-content-center">
                        <h2 class="mb-5">${requestScope.news.title}</h2>
                        <img src="${requestScope.news.imgPath}" alt="News image" class="img-thumbnail mb-5">
                        <p class="news-brief fs-4 text-start mb-4">${requestScope.news.info}</p>
                        <c:if test="${sessionScope.user.roleId eq 1}">
                            <div class="row col-12 justify-content-center">
                                <form action="MyController" class="col-md-3" method="post">
                                    <input type="hidden" name="command" value="go_to_update_news_page"/>
                                    <input type="hidden" name="action" value="update"/>
                                    <input type="hidden" name="newsId" value="${requestScope.news.id}"/>
                                    <input type="hidden" name="title" value="${requestScope.news.title}"/>
                                    <input type="hidden" name="image" value="${requestScope.news.imgPath}"/>
                                    <input type="hidden" name="brief" value="${requestScope.news.brief}"/>
                                    <input type="hidden" name="info" value="${requestScope.news.info}"/>
                                    <input type="hidden" name="tag" value="${requestScope.news.tagId}"/>
                                    <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block mb-2">
                                        <fmt:message key="news.full.update"/></button>
                                </form>
                                <form action="MyController" class="col-md-3" method="post">
                                    <input type="hidden" name="command" value="do_delete_news"/>
                                    <input type="hidden" name="action" value="delete"/>
                                    <input type="hidden" name="newsId" value="${requestScope.news.id}"/>
                                    <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block mb-2">
                                        <fmt:message key="news.full.delete"/></button>
                                </form>
                            </div>
                        </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>