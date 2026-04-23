<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="content">
        <section class="me-4">
            <%-- 画面タイトル --%>
            <h2 class="h2 mb-3">成績一覧（学生）</h2>

            <%-- 検索フォームエリア（既存の設計を維持） --%>
            <div class="card p-4 mb-4">
				<%-- 科目情報検索フォーム --%>
				<form action="TestListSubjectExecute.action" method="get">
					<div class="row align-items-center">
						<%-- rowを追加して横並びにし、上下中央揃えに --%>

						<%-- 左側：見出し (横幅2つ分を使用) --%>
						<div class="col-md-2">
							<p class="fw-bold mb-0">科目情報</p>
							<%-- border-bottom と pb-2 を消しました --%>
						</div>

						<%-- 右側：入力項目 (横幅10個分を使用) --%>
						<div class="col-md-10">
							<div class="row g-3 align-items-end">
								<%-- 入学年度 --%>
								<div class="col-md-3">
									<label class="form-label">入学年度</label> <select name="f1"
										class="form-select">
										<option value="">--------</option>
										<c:forEach var="year" items="${ent_year_list}">
											<option value="${year}"
												<c:if test="${year == f1}">selected</c:if>>${year}</option>
										</c:forEach>
									</select>
								</div>

								<%-- クラス --%>
								<div class="col-md-3">
									<label class="form-label">クラス</label> <select name="f2"
										class="form-select">
										<option value="">--------</option>
										<c:forEach var="cNum" items="${class_list}">
											<option value="${cNum}"
												<c:if test="${cNum == f2}">selected</c:if>>${cNum}</option>
										</c:forEach>
									</select>
								</div>

								<%-- 科目 --%>
								<div class="col-md-4">
									<label class="form-label">科目</label> <select name="f3"
										class="form-select">
										<option value="">--------</option>
										<c:forEach var="subject" items="${subject_list}">
											<option value="${subject.cd}"
												<c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
										</c:forEach>
									</select>
								</div>

								<%-- 検索ボタン --%>
								<div class="col-md-2">
									<input type="hidden" name="f" value="sj">
									<button type="submit" class="btn btn-secondary w-100">検索</button>
								</div>
							</div>
						</div>

					</div>
				</form>

				<hr class="my-4">

				<%-- 学生情報検索フォーム --%>
				<form action="TestListStudentExecute.action" method="get">
					<div class="row align-items-center">
						<%-- rowを追加して横並びに --%>

						<%-- 左側：見出し (科目側と同じ col-md-2 にして幅を揃える) --%>
						<div class="col-md-2">
							<p class="fw-bold mb-0">学生情報</p>
							<%-- 線を消して、余白(mb-0)を調整 --%>
						</div>

						<%-- 右側：入力項目とボタン --%>
						<div class="col-md-10">
							<div class="row g-3 align-items-end">
								<%-- 学生番号入力欄 --%>
								<div class="col-md-10">
									<label class="form-label">学生番号</label> <input type="text"
										name="f4" class="form-control" placeholder="学生番号を入力してください"
										value="${f4}" maxlength="10" required>
								</div>

								<%-- 検索ボタン --%>
								<div class="col-md-2">
									<input type="hidden" name="f" value="st">
									<button type="submit" class="btn btn-secondary w-100">検索</button>
								</div>
							</div>
						</div>

					</div>
				</form>


			</div>

            <%-- 成績一覧表示エリア（設計書 No.①〜⑩に基づき作成） --%>
            
            
            <c:if test="${not empty tests}">
                <div class="mt-4">
                    <%-- No.① 氏名：名前 (学生番号) --%>
                    <p class="fw-bold">氏名：${student.name} (${student.no})</p>
                    
                    <%-- No.② 成績テーブル --%>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>科目名</th>   <%-- No.③ --%>
                                <th>科目コード</th> <%-- No.④ --%>
                                <th>回数</th>     <%-- No.⑤ --%>
                                <th>点数</th>     <%-- No.⑥ --%>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${tests}">
                                <tr>
                                    <td>${test.subjectName}</td> <%-- No.⑦ --%>
                                    <td>${test.subjectCd}</td>   <%-- No.⑧ --%>
                                    <td>${test.num}</td>         <%-- No.⑨ --%>
                                    <td>${test.point}</td>       <%-- No.⑩ --%>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            
            <%-- メッセージ表示（学生が見つからない場合など） --%>
            <c:if test="${not empty notfoundmessage}">
                <div class="mt-4">
            	<%-- 学生が存在する場合（成績なし）のみ、氏名を表示 --%>
            		<c:if test="${not empty student}">
                		<p class="fw-bold">氏名：${student.name} (${student.no})</p>
            		</c:if>
            		<p>${notfoundmessage}</p>
        		</div>
            </c:if>
            
            <c:if test="${empty tests and empty notfoundmessage}">
                <p class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
            </c:if>
            
            
        </section>
    </c:param>
</c:import>