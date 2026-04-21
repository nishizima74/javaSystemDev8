<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目登録完了</c:param>

    <c:param name="content">
        <section class="me-4">

            <!-- 画面タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目登録完了
            </h2>

            <div class="px-4">

                <!-- 完了メッセージ -->
                <p class="mb-4">
                    科目情報を登録しました。
                </p>

<!-- 科目登録画面へ戻る -->
<div class="mb-2">
    <a href="SubjectCreate.action">別の科目を登録する</a>
</div>

<!-- 科目一覧画面へ戻る -->
<div>
    <a href="SubjectList.action">科目管理一覧へ戻る</a>
</div>
            </div>
        </section>
    </c:param>
</c:import>