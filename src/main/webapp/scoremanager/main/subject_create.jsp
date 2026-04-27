<%--杉本 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">科目情報登録</c:param>

	<c:param name="content">
		<section class="me-4">

			<%-- 画面タイトル --%>
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				科目情報登録</h2>

			<div class="px-4">

				<%-- 登録フォーム --%>
				<form action="SubjectCreateExecute.action" method="post">

					<%-- 科目コード --%>
					<div class="mb-3">
						<label class="form-label">科目コード</label> <input type="text"
							name="cd" value="${cd}" class="form-control" maxlength="3"
							required placeholder="科目コードを入力してください">

						<%-- エラーメッセージはここに出す --%>
						<c:if test="${not empty errors}">
							<div class="text-warning small mt-1">${errors}</div>
						</c:if>
					</div>

					<%-- 科目名 --%>
					<div class="mb-3">
						<label class="form-label">科目名</label> <input type="text"
							name="name" value="${name}" class="form-control" maxlength="20"
							required placeholder="科目名を入力してください">
					</div>

					<%-- 登録ボタン --%>
					<div class="mb-3">
						<input type="submit" class="btn btn-primary" value="登録">
					</div>

				</form>

				<%-- 戻るリンク --%>
				<div>
					<a href="SubjectList.action">戻る</a>
				</div>

			</div>
		</section>
	</c:param>
</c:import>