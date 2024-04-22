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

							<h3 class="mb-5">Регистрация</h3>

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
									<label for="city" class="form-label">Город</label>
									<input type="text" class="form-control" id="city" name="city" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Страна</label> <select
										class="form-select" name="country" required>
										<option selected disabled value="">Выберите страну...</option>
										<option value="russia">Россия</option>
										<option value="usa">США</option>
										<option value="germany">Германия</option>
										<option value="france">Франция</option>
									</select>
								</div>
								<div class="col-md-12">
									<label class="form-label">Номер телефона</label> <input
										type="text" class="form-control" name="phone" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Пароль</label> <input type="password"
										class="form-control" name="password" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Подтверждение пароля</label> <input
										type="password" class="form-control"
										name="password_confirmation" required>
								</div>
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