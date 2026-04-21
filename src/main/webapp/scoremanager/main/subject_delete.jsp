<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">科目情報削除</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
            <div class="px-4">
                <p class="text-danger">以下の科目を削除します。よろしいですか？</p>
                <div class="mb-3">
                    <strong>科目コード：</strong> ${subject.cd}<br>
                    <strong>科目名：</strong> ${subject.name}
                </div>
                <form action="SubjectDeleteExecute.action" method="post">
                    <input type="hidden" name="cd" value="${subject.cd}">
                    <div class="mb-3">
                        <input type="submit" class="btn btn-danger" value="削除">
                    </div>
                </form>
                <div><a href="SubjectList.action">キャンセル（一覧へ戻る）</a></div>
            </div>
        </section>
    </c:param>
</c:import>