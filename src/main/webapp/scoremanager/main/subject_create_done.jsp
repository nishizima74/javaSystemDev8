<%--杉本 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目情報登録</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報登録
            </h2>

            <div class="px-4">
                <div class="alert alert-success text-center" role="alert">
                    登録が完了しました
                </div>

                <%--  戻るリンク --%>
            <div class="px-4 mt-4">
                <a href="SubjectCreate.action" class="me-4">戻る</a>

                <%--  一覧へ --%>
                <a href="SubjectList.action">科目一覧</a>
            </div>
            </div>
        </section>
    </c:param>
</c:import>