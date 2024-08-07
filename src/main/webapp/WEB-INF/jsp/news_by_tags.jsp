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
    <div class="container py-5 h-200">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-12">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <h2 class="mb-5"><fmt:message key="news.by.tag.message"/></h2>
                        <form action="MyController" class="row mb-3 justify-content-center" method="post">
                            <input type="hidden" name="command" value="do_news_by_tag"/>
                           <div class="mb-4 col-md-4 justify-content-center">
                               <select class="form-select" name="tag" id="tag" required>
                                   <option selected value="1"><fmt:message key="add.news.choose.category"/></option>
                                   <c:forEach var="tags" items="${sessionScope.tags}">
                                       <option value="${tags.id}">${tags.title}</option>
                                   </c:forEach>
                               </select>
                           </div>
                            <div class="mb-4 col-md-4">
                                <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message
                                        key="news.by.tag.search"/></button>
                            </div>
                        </form>
                        <div class="row justify-content-center">
                            <c:forEach var="news" items="${requestScope.tagNews}">
                                <div onclick="location.href='MyController?command=go_to_full_news_page&newsId=${news.id}'" class="news-item col-md-5 mx-4 mb-5 border border-3 rounded">
                                    <img src="${news.imgPath}"
                                         alt="News image" class="img-thumbnail mb-3">
                                    <h4 class="news-title mb-2">${news.title}</h4>
                                    <p class="news-brief fs-8 text-muted text-start">${news.brief}</p>
                                </div>
                            </c:forEach>
                            <c:if test="${requestScope.tagNews.isEmpty() eq true}">
                                <h3><fmt:message key="news.by.tag.null"/></h3>
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