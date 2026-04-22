<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        エラーページ
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <div class="px-4 mt-4">
                <p>
                    <span class="text-danger fw-bold"></span> エラーが発生しました
                </p>
            </div>
        </section>
    </c:param>
</c:import>