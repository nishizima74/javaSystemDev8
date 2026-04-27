<%--西嶋 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
	<c:param name="title">
        得点管理システム - 学生変更
    </c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

			<form action="StudentUpdateExecute.action" method="post" class="px-4">

				<%-- 入学年度 --%>
				<div class="mb-3">
					<label class="form-label">入学年度</label>
					<div>${student.entYear}</div>
					<input type="hidden" name="ent_year" value="${student.entYear}">
				</div>

				<%-- 学生番号 --%>
				<div class="mb-3">
					<label class="form-label">学生番号</label>
					<div>${student.no}</div>
					<input type="hidden" name="no" value="${student.no}">
				</div>

				<%-- 氏名 --%>
				<div class="mb-3">
					<label class="form-label">氏名</label> <input
						class="form-control w-50" type="text" name="name"
						value="${student.name}" required>
				</div>

				<%-- クラス --%>
				<div class="mb-3">
					<label class="form-label">クラス</label> <select
						class="form-select w-50" name="class_num">
						<c:forEach var="num" items="${class_num_set}">
							<option value="${num}"
								<c:if test="${num == student.classNum}">selected</c:if>>
								${num}</option>
						</c:forEach>
					</select>
				</div>

				<%-- 在学中 --%>
				<div class="mb-3 form-check">
					<input class="form-check-input" type="checkbox" name="is_attend"
						<c:if test="${student.attend}">checked</c:if>> <label
						class="form-check-label">在学中</label>
				</div>

				<%-- ボタン --%>
				<div class="mt-4">
					<button type="submit" class="btn btn-primary">変更</button>
				</div>

				<div class="mt-2">
					<a href="StudentList.action">戻る</a>
				</div>

			</form>
		</section>
	</c:param>
</c:import>