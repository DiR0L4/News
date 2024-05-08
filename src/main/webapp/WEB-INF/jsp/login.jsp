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
<section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">Вход</h3>
            <form action="MyController" method="post">
              <input type="hidden" name="command" value="do_auth"/>
              <div data-mdb-input-init class="form-outline mb-4">
              <input type="email" id="authEmail" name="authEmail" class="form-control form-control-lg" />
              <label class="form-label" for="authEmail">Почта</label>
            </div>

            <div data-mdb-input-init class="form-outline mb-2">
              <input type="password" id="authPassword" name="authPassword" class="form-control form-control-lg" />
              <label class="form-label" for="authPassword">Пароль</label>
            </div>

            <!-- Checkbox -->
            <div class="form-check d-flex mb-2">
              <input class="form-check-input" type="checkbox" value="remember-me" name="remember-me" id="remember-me" />
              <label class="form-check-label" for="remember-me"> Запомнить пароль </label>
            </div>

            <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg btn-block mb-2" type="submit">Войти</button>
            <p>Еще не зарегистрированы? <a href="MyController?command=go_to_registration_page">Зарегистрироваться</a></p>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>