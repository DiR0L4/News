<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	<section class="vh-100" style="background-color: #508bfc;">
		<div class="container py-6 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12 col-md-8 col-lg-6 col-xl-5">
					<div class="card shadow-2-strong" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">

							<h3 class="mb-3">Регистрация</h3>
							<c:if test="${not (param.regError eq null)}">
								<span class="text-danger"><c:out value="${param.regError}"/></span>
							</c:if>
							<form class="row g-3 needs-validation" action="MyController"
								method="post" novalidate>

								<input type="hidden" name="command" value="do_registration"/>
								
								<div class="col-md-6">
									<label for="surname" class="form-label">Фамилия</label>
									<input type="text" class="form-control" id="surname" name="surname" required>
								</div>
								<div class="col-md-6">
									<label for="name" class="form-label">Имя</label>
									<input type="text" class="form-control" id="name" name="name" required>
								</div>
								<div class="col-md-12">
									<label for="login" class="form-label">Логин</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="inputGroupPrepend">@</span>
										<input type="text" class="form-control" id="login" name="login"
											aria-describedby="inputGroupPrepend" required>
									</div>
								</div>
								<div class="col-md-6">
									<label for="email" class="form-label">Почта</label>
									<input type="email" class="form-control" id="email" name="email" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Страна</label> <select
										class="form-select" name="country" id="country" required>
										<option selected disabled value="">Выберите страну...</option>
										<option value="Russia">Россия</option>
										<option value="Belarus">Беларусь</option>
										<option value="Ukraine">Украина</option>
										<option value="Kazakhstan">Казахстан</option>
									</select>
								</div>
								<div class="col-md-12">
									<label class="form-label">Номер телефона</label> <input
										type="text" class="form-control" name="phone" id="phone" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Пароль</label> <input placeholder="От 8 символов, хотя бы одна заглавная, строчная буквы и цифра" type="password"
										class="form-control" name="password" id="password" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Подтверждение пароля</label> <input
										type="password" class="form-control"
										name="password_confirmation" id="password_confirmation" required>
								</div>
								<div class="col-md-3"></div>
								<div class="col-md-6">
									<label class="form-label">Роль</label> <select
										class="form-select" name="role" id="role" required>
									<option selected disabled value="">Выберите роль...</option>
									<option value="3">Читатель</option>
									<option value="2">Автор</option>
								</select>
								</div>
								<div class="col-md-3"></div>
								<div class="col-12">
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value=""
											name="invalidCheck" required> <label
											class="form-check-label"> Согласен
											с условиями... </label>
									</div>
								</div>
								<div class="col-12">
									<button data-mdb-button-init data-mdb-ripple-init
										class="btn btn-primary btn-lg btn-block" type="submit">Зарегистрироваться</button>
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