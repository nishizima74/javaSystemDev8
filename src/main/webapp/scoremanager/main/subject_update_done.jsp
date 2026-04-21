<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">科目変更完了</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目変更完了</h2>
            <div class="px-4">
                <p class="mb-4">科目情報を変更しました。</p>
                <a href="SubjectList.action">科目管理一覧へ戻る</a>
            </div>
        </section>
    </c:param>
</c:import>