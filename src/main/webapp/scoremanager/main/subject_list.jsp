<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        科目一覧
    </c:param>

    <c:param name="content">
        <section class="me-4">

            <!-- 画面タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目管理
            </h2>

            <!-- 新規登録リンク-->
            <div class="px-4 mb-3 text-end">
                <a href="SubjectCreate.action" class="new">新規登録</a>
            </div>

            <!-- 科目一覧テーブル -->
            <div class="px-4">
                <table class="table table-bordered align-middle">
                    <thead class="table-light">
                        <tr>
                            <th style="width: 40%;">科目コード</th>
                            <th style="width: 40%;">科目名</th>
                            <th style="width: 10%;"></th>
                            <th style="width: 10%;"></th>
                        </tr>
                    </thead>

                    <tbody>
                        <!-- 科目がある場合 -->
						<c:forEach var="subject" items="${subjects}">
						    <tr>
						        <td>${subject.cd}</td>
						        <td>${subject.name}</td>
						        <td>
						            <a href="SubjectUpdate.action?cd=${subject.cd}">変更</a>
						        </td>
						        <td>
						            <a href="SubjectDelete.action?cd=${subject.cd}">削除</a>
						        </td>
						    </tr>
						</c:forEach>

                        <!-- 科目が無い場合 -->
                    </tbody>
                </table>
            </div>

        </section>
    </c:param>
</c:import>