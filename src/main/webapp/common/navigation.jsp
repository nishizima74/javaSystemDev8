<%-- サイドバー共通JSP --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="user" value="${sessionScope.user}" />

<div class="d-flex">

    <!-- サイドバー -->
    <div style="width: 200px; min-height: 100vh;">
        
        <nav>
            <ul class="list-unstyled ps-3 mt-3">

                <!-- メニュー -->
                <li class="mb-2">
                    <a href="${pageContext.request.contextPath}/scoremanager/Menu.action">メニュー</a>
                </li>

                <!-- 学生管理 -->
                <li class="mb-2">
                    <a href="${pageContext.request.contextPath}/scoremanager/main/StudentList.action" class="text-decoration-none">
                        学生管理
                    </a>
                </li>

                <!-- 成績管理 -->
                <li class="mb-2">
                    <span>成績管理</span>
                    <ul class="list-unstyled ps-3">
                        <li>
                            <a href="${pageContext.request.contextPath}/scoremanager/main/TestRegist.action" class="text-decoration-none">
                                成績登録
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/scoremanager/main/TestList.action" class="text-decoration-none">
                                成績参照
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- 科目管理 -->
                <li class="mb-2">
                    <a href="${pageContext.request.contextPath}/scoremanager/main/SubjectList.action" class="text-decoration-none">
                        科目管理
                    </a>
                </li>

            </ul>
        </nav>
    </div>

    <!-- メインコンテンツ -->
    <div class="flex-grow-1 p-3">
        <jsp:include page="${contentPage}" />
    </div>

</div>