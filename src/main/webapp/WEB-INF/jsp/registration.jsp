<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="locale" value="${sessionScope.locale}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Registration</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
	<ul class="nav nav-pills col-md-3">
		<li class="nav-item"><a href="MyController?command=go_to_main_page" class="nav-link"><fmt:message
				key="header.link.main"/></a></li>
		<li class="nav-item"><a href="#" class="nav-link"><fmt:message
				key="header.link.news"/></a></li>
		<li class="nav-item"><a href="#" class="nav-link"><fmt:message
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
		<div class="container py-6 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card shadow-2-strong" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">

							<h3 class="mb-3"><fmt:message key="reg.reg"/></h3>
							<c:if test="${not (param.regError eq null)}">
								<span class="text-danger"><c:out value="${param.regError}"/></span>
							</c:if>
							<form class="row g-3 needs-validation" action="MyController"
								method="post" novalidate>

								<input type="hidden" name="command" value="do_registration"/>
								
								<div class="col-md-6">
									<label for="surname" class="form-label"><fmt:message key="reg.surname"/></label>
									<input type="text" class="form-control" id="surname" name="surname" required>
								</div>
								<div class="col-md-6">
									<label for="name" class="form-label"><fmt:message key="reg.name"/></label>
									<input type="text" class="form-control" id="name" name="name" required>
								</div>
								<div class="col-md-12">
									<label for="login" class="form-label"><fmt:message key="reg.login"/></label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="inputGroupPrepend">@</span>
										<input type="text" class="form-control" id="login" name="login"
											aria-describedby="inputGroupPrepend" required>
									</div>
								</div>
								<div class="col-md-6">
									<label for="email" class="form-label"><fmt:message key="reg.email"/></label>
									<input type="email" class="form-control" id="email" name="email" required>
								</div>
								<div class="col-md-6">
									<label class="form-label"><fmt:message key="reg.country"/></label> <select
										class="form-select" name="country" id="country" required>
										<option selected disabled value=""><fmt:message key="reg.choose.country"/></option>
										<option value="Russia"><fmt:message key="reg.choose.country.rus"/></option>
										<option value="Belarus"><fmt:message key="reg.choose.country.bel"/></option>
										<option value="Ukraine"><fmt:message key="reg.choose.country.ukr"/></option>
										<option value="Kazakhstan"><fmt:message key="reg.choose.country.kz"/></option>
									</select>
								</div>
								<div class="col-md-12">
									<label class="form-label"><fmt:message key="reg.phone"/></label> <input
										type="text" class="form-control" name="phone" id="phone" required>
								</div>
								<div class="col-md-6">
									<label class="form-label"><fmt:message key="reg.password"/></label> <input type="password"
										class="form-control" name="password" id="password" required>
								</div>
								<div class="col-md-6">
									<label class="form-label"><fmt:message key="reg.confirmpassword"/></label> <input
										type="password" class="form-control"
										name="password_confirmation" id="password_confirmation" required>
								</div>
								<div class="col-md-3"></div>
								<div class="col-md-6">
									<label class="form-label"><fmt:message key="reg.role"/></label> <select
										class="form-select" name="role" id="role" required>
									<option selected disabled value=""><fmt:message key="reg.choose.role"/></option>
									<option value="3"><fmt:message key="reg.choose.role.reader"/></option>
									<option value="2"><fmt:message key="reg.choose.role.author"/></option>
								</select>
								</div>
								<div class="col-md-3"></div>
								<div class="col-12">
									<button data-mdb-button-init data-mdb-ripple-init
										class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="reg.doreg"/></button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>