<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム - 学生登録
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>

            <%-- エラーメッセージの表示（学生番号重複時など） --%>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger mx-4">
                    ${errors}
                </div>
            </c:if>

            <form action="StudentCreateExecute.action" method="post" class="px-4">
                <div class="mb-3">
                    <label class="form-label" for="ent-year-select">入学年度</label>
                    <select class="form-select w-50" id="ent-year-select" name="ent_year" required>
                        <option value="">選択してください</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label" for="student-no-input">学籍番号</label>
                    <input class="form-control w-50" type="text" id="student-no-input" name="no" 
                           placeholder="学籍番号を入力してください" maxlength="10" required>
                </div>

                <div class="mb-3">
                    <label class="form-label" for="student-name-input">氏名</label>
                    <input class="form-control w-50" type="text" id="student-name-input" name="name" 
                           placeholder="氏名を入力してください" maxlength="10" required>
                </div>

                <div class="mb-4">
                    <label class="form-label" for="class-num-select">クラス</label>
                    <select class="form-select w-50" id="class-num-select" name="class_num" required>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}">${num}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-primary me-2">登録</button>
                    <a href="StudentList.action" class="btn btn-secondary">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>