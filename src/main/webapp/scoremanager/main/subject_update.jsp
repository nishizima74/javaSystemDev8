<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">科目情報変更</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>

            <div class="px-4">
                <form action="SubjectUpdateExecute.action" method="post">
                    <div class="mb-3">
                        <label class="form-label d-block">科目コード</label>
                        <div class="py-2 px-1">
                            ${subject.cd}
                        </div>
                        <input type="hidden" name="cd" value="${subject.cd}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">科目名</label>
                        <input type="text" name="name" value="${subject.name}" 
                               class="form-control" maxlength="20" required 
                               placeholder="科目名を入力してください">
                    </div>

                    <div class="mb-3">
                        <input type="submit" class="btn btn-primary" value="変更">
                    </div>
                </form>


                <div>
                    <a href="SubjectList.action">戻る</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>