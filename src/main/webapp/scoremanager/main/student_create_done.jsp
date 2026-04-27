<%--山本、杉本 --%>
<%-- 学生登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
        得点管理システム - 登録完了
    </c:param>

	<c:param name="content">
		<section class="me-4">

			<%-- タイトル --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				学生情報登録</h2>

			<%-- 緑の完了メッセージ --%>
			<div class="alert alert-success text-center mx-4 py-2">
				登録が完了しました</div>

			<%-- リンク--%>
			<div class="mt-4 px-4">
				<a
					href="${pageContext.request.contextPath}/scoremanager/main/StudentCreate.action"
					class="me-4 text-primary text-decoration-underline"> 戻る </a> <a
					href="${pageContext.request.contextPath}/scoremanager/main/StudentList.action"
					class="text-primary text-decoration-underline"> 学生一覧 </a>
			</div>

		</section>
	</c:param>
</c:import>