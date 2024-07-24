<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <c:if test="${param.action eq null}">
                            <h3 class="mb-5"><fmt:message key="add.news.create"/></h3>
                        </c:if>
                        <c:if test="${not (param.action eq null)}">
                            <h3 class="mb-5"><fmt:message key="add.news.update"/></h3>
                        </c:if>
                        <form action="MyController" method="post">
                            <c:if test="${param.action eq null}">
                                <input type="hidden" name="command" value="do_add_news"/>
                            </c:if>
                            <c:if test="${not (param.action eq null)}">
                                <input type="hidden" name="command" value="do_update_news"/>
                                <input type="hidden" name="newsId" value="${param.newsId}"/>
                            </c:if>
                            <c:if test="${not (param.newsError eq null)}">
                                <span class="text-danger"><c:out value="${param.newsError}"/></span>
                            </c:if>
                            <c:if test="${not (param.newsMessage eq null)}">
                                <span class="text-success"><c:out value="${param.newsMessage}"/></span>
                            </c:if>
                            <div class="input-group mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="titleSpan"><fmt:message key="add.news.title"/></span>
                                </div>
                                <input type="text" class="form-control" value="${param.title}" name="title" id="title" placeholder="Title" aria-describedby="titleSpan">
                            </div>

                            <div class="input-group mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="imageSpan"><fmt:message key="add.news.image"/></span>
                                </div>
                                <input type="text" class="form-control" value="${param.image}" name="image" id="image" placeholder="Link" aria-describedby="imageSpan">
                            </div>

                            <div class="input-group mb-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="briefSpan"><fmt:message key="add.news.brief"/></span>
                                </div>
                                <input type="text" class="form-control" value="${param.brief}" name="brief" id="brief" placeholder="Brief" aria-describedby="briefSpan">
                            </div>

                            <div class="input-group mb-4">
                                <div class="input-group-text">
                                    <span class="input-group" id="infoSpan"><fmt:message key="add.news.info"/></span>
                                </div>
                                <textarea type="text" class="form-control" name="info" id="info" placeholder="Info" aria-describedby="infoSpan">${param.info}</textarea>
                            </div>
                            <select class="form-select mb-4" name="tag" id="tag" required>
                            <option selected disabled value="${param.tagId}"><fmt:message key="add.news.choose.category"/></option>
                            <c:forEach var="tags" items="${sessionScope.tags}">
                                <option value="${tags.id}">${tags.title}</option>
                            </c:forEach>
                        </select>

                            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block mb-2" type="submit">
                                <c:if test="${param.action eq null}">
                                    <fmt:message key="add.news.docreate"/>
                                </c:if>
                                <c:if test="${not (param.action eq null)}">
                                    <fmt:message key="add.news.doupdate"/>
                                </c:if>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>