<%--西嶋 --%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
        得点管理システム
    </c:param>

	<c:param name="content">
		<section class="me-4">

			<!--  タイトル -->
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				ログアウト</h2>

			<!--  メッセージ -->
			<div class="alert alert-success mx-4">ログアウトしました</div>

			<!--  ログインリンク -->
			<div class="px-4">
				<a href="Login.action">ログイン</a>
			</div>

		</section>
	</c:param>

</c:import>