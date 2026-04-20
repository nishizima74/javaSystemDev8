<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="content">
        <section class="me-4">
            <h2 class="h2 mb-3">成績一覧（科目）</h2>

            <%-- 検索フォーム部分は以前の test_list.jsp と同じものを再利用 --%>
            <div class="card p-4 mb-4">
                <%-- 科目情報検索フォーム --%>
                <form action="TestListSubjectExecute.action" method="get">
                    <%-- No.2 科目情報 --%>
                    <p class="fw-bold border-bottom pb-2">科目情報</p>
                    
                    <div class="row g-3 align-items-end">
                        <%-- No.3 & 6 入学年度 --%>
                        <div class="col-md-3">
                            <label class="form-label">入学年度</label>
                            <select name="f1" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="year" items="${ent_year_list}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- No.4 & 7 クラス --%>
                        <div class="col-md-3">
                            <label class="form-label">クラス</label>
                            <select name="f2" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="cNum" items="${class_list}">
                                    <option value="${cNum}" <c:if test="${cNum == f2}">selected</c:if>>${cNum}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- No.5 & 8 科目 --%>
                        <div class="col-md-4">
                            <label class="form-label">科目</label>
                            <select name="f3" class="form-select">
                                <option value="">--------</option>
                                <c:forEach var="subject" items="${subject_list}">
                                    <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <%-- No.9 検索ボタン & No.15 識別コード(sj) --%>
                        <div class="col-md-2">
                            <input type="hidden" name="f" value="sj">
                            <button type="submit" class="btn btn-secondary w-100" id="search-btn-subject">検索</button>
                        </div>
                    </div>
                    
                    <%-- ★ ここに追加！科目情報フォームの内側にメッセージを置く --%>
    					<c:if test="${not empty message}">
        					<p class="text-warning mt-2 fw-bold"> ${message}</p>
    					</c:if>
    					
                </form>

                <hr class="my-4">

                <%-- 学生情報検索フォーム --%>
                <form action="TestListStudentExecute.action" method="get">
                    <%-- No.10 学生情報 --%>
                    <p class="fw-bold border-bottom pb-2">学生情報</p>
                    
                    <div class="row g-3 align-items-end">
                        <%-- No.11 & 12 学生番号 --%>
                        <div class="col-md-10">
                            <label class="form-label">学生番号</label>
                            <%-- Value属性に ${f4} を反映 --%>
                            <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください" value="${f4}" maxlength="10">
                        </div>

                        <%-- No.13 検索ボタン & No.16 識別コード(st) --%>
                        <div class="col-md-2">
                            <input type="hidden" name="f" value="st">
                            <button type="submit" class="btn btn-secondary w-100" id="search-btn-student">検索</button>
                        </div>
                    </div>
                    
                    
                    
                </form>
            </div>
            
			<c:if test="${not empty message2 }">
    			<p class="mt-3">${message2}</p>
			</c:if>

            <%-- 成績一覧表示テーブル --%>
            <c:if test="${not empty tests}">
                <div class="mt-4">
                    <p>科目：${subject.name} </p>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>1回</th>
                                <th>2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${tests}">
                                <tr>
                                    <td>${test.entYear}</td>
                                    <td>${test.classNum}</td>
                                    <td>${test.studentNo}</td>
                                    <td>${test.studentName}</td>
                                    <%-- Map(points)から1回目と2回目の点数を取得 --%>
                                    <td>${test.getPoint(1)}</td>
                                    <td>${test.getPoint(2)}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            
            <c:if test="${empty tests and empty message2}">
                <p class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
            </c:if>
            
        </section>
    </c:param>
</c:import>