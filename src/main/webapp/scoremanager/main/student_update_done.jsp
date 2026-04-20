<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム - 学生情報変更
    </c:param>

    <c:param name="content">
        <section class="me-4">

            <!-- ① タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生情報変更
            </h2>

            <!-- ② 緑の完了メッセージ -->
            <div class="mx-4 mb-4">
                <div class="bg-success bg-opacity-50 text-center py-2 rounded">
                    変更が完了しました
                </div>
            </div>

            <!-- ③ 学生一覧リンク -->
            <div class="px-4">
                <a href="StudentList.action">学生一覧</a>
            </div>

        </section>
    </c:param>
</c:import>