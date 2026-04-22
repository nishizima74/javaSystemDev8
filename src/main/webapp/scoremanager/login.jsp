<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="scripts">
		<script>
			function togglePassword() {
				const pw = document.getElementById("password");
				const chk = document.getElementById("chk_d_ps");
				pw.type = chk.checked ? "text" : "password";
			}
		</script>
	</c:param>
	<c:param name="content">

		<section class="container">
			<div class="row justify-content-center mt-5">
				<div class="col-md-15">
					<div class="card shadow-sm">
						<!-- ① 画面タイトル（h2） -->
						<div class="card-header bg-secondary bg-opacity-10 py-3">
							<h2 class="h4 text-center mb-0">ログイン</h2>
						</div>
						<div class="card-body p-5">

							<!-- エラーメッセージ -->
							<c:if test="${not empty errors}">
								<ul class="mb-4 small"">
									<c:forEach var="error" items="${errors}">
										<li>${error}</li>
									</c:forEach>
								</ul>

							</c:if>


							<form action="LoginExecute.action" method="post">

								<%-- ID入力欄（フローティングラベル） --%>
								<div class="form-floating mb-3">
									<input type="text" name="id" class="form-control"
										id="floatingId" placeholder="I D" value="${id}" required>
									<label for="floatingId" class="text-secondary">I D</label>
								</div>

								<%-- パスワード入力欄（フローティングラベル） --%>
								<div class="form-floating mb-3">
									<input type="password" name="password" class="form-control"
										id="password" placeholder="パスワード" required> <label
										for="password" class="text-secondary">パスワード</label>
								</div>
								<!-- ④ チェックボックス -->
								<div class="mb-4 d-flex justify-content-center align-items-center">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="chk_d_ps"
										name="chk_d_ps" onclick="togglePassword()">

									<!-- ⑤ ラベル -->
									<label class="form-check-label small" for="chk_d_ps">
										パスワードを表示 </label>
								</div>
								</div>


								<!-- ⑥ ログインボタン -->
								<div class="text-center">
									<button type="submit" name="login" value="ログイン"
										class="btn btn-primary w-50">ログイン</button>
								</div>

							</form>


						</div>
					</div>
				</div>
			</div>
		</section>

	</c:param>
</c:import>