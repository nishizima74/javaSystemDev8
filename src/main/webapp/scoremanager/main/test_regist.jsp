<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/common/base.jsp">
	<c:param name="content">

		<section class="me-4">

			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
				成績管理</h2>

			<%-- ================= 検索フォーム ================= --%>
			<div class="card p-4 mb-4">
				<form action="TestRegist.action" method="get" class="px-4 mb-4">
					<div class="row g-3 align-items-end">
						<%-- 入学年度 --%>
						<div class="col-md-2">
							<label class="form-label">入学年度</label> <select name="f1"
								class="form-select">
								<option value="">--------</option>
								<c:forEach var="y" items="${ent_year_set}">
									<%-- 変数名を修正 --%>
									<option value="${y}" <c:if test="${y == f1}">selected</c:if>>${y}</option>
								</c:forEach>
							</select>
						</div>

						<%-- クラス --%>
						<div class="col-md-2">
							<label class="form-label">クラス</label> <select name="f2"
								class="form-select">
								<option value="">--------</option>
								<c:forEach var="c" items="${class_num_set}">
									<%-- 変数名を修正 --%>
									<option value="${c}" <c:if test="${c == f2}">selected</c:if>>${c}</option>
								</c:forEach>
							</select>
						</div>

						<%-- 科目 --%>
						<div class="col-md-3">
							<label class="form-label">科目</label> <select name="f3"
								class="form-select">
								<option value="">--------</option>
								<c:forEach var="s" items="${subjects}">
									<%-- 変数名を修正 --%>
									<option value="${s.cd}"
										<c:if test="${s.cd == f3}">selected</c:if>>${s.name}</option>
								</c:forEach>
							</select>
						</div>

						<%-- 回数 (追加) --%>
						<div class="col-md-2">
							<label class="form-label">回数</label> <select name="f4"
								class="form-select">
								<option value="">--------</option>
								<c:forEach var="n" items="${num_set}">
									<option value="${n}" <c:if test="${n == f4}">selected</c:if>>${n}</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-md-2">
							<button type="submit" class="btn btn-secondary w-100">検索</button>
						</div>
					</div>

					<%-- エラーメッセージの表示 --%>
					<c:if test="${not empty message}">

						<div class="col-12">
							<p class="text-warning mb-0 small" style="margin-top: 10px;">${message}</p>
						</div>

					</c:if>

				</form>
			</div>

			<%-- ================= 学生一覧 + 登録フォーム ================= --%>
			<c:if test="${not empty tests}">
				<%-- tests がある場合に表示 --%>
				<form action="TestRegistExecute.action" method="post" class="px-4">
					<%-- 条件を維持するための隠しパラメータ --%>
					<input type="hidden" name="f1" value="${f1}"> <input
						type="hidden" name="f2" value="${f2}"> <input
						type="hidden" name="f3" value="${f3}"> <input
						type="hidden" name="f4" value="${f4}">

					<p>科目：${subject.name}（${f4}回）</p>
					<%-- 何を登録中か表示 --%>

					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>
						</tr>
						<c:forEach var="t" items="${tests}">
							<tr>
								<td>${t.student.entYear}</td>
								<td>${t.classNum}</td>
								<td>${t.student.no}</td>
								<td>${t.student.name}</td>
								<td>
									<%-- 点数があれば初期値として表示 --%> <input type="number"
									name="point_${t.student.no}"
									value="${t.point >= 0 ? t.point : ''}" class="form-control">
									<c:if test="${fn:contains(errorStudentNos, t.student.no)}">
										<small class="text-warning">0〜100の範囲で入力してください</small>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
					<button type="submit" class="btn btn-secondary">登録して終了</button>
				</form>
			</c:if>

			<%-- 学生が存在しないメッセージはここ（フォームの外・テーブルの位置） --%>
			<c:if test="${not empty message2}">
				<div class="px-4">
					<p>${message2}</p>
				</div>
			</c:if>

		</section>

	</c:param>
</c:import>