<%-- JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="alert alert-danger mx-4">

    <c:choose>
        <c:when test="${not empty errorMessage}">
            ${errorMessage}
        </c:when>
        <c:otherwise>
            エラーが発生しました。
        </c:otherwise>
    </c:choose>

</div>