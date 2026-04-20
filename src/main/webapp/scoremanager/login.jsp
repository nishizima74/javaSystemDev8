<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp"><c:param name="title">得点管理システム</c:param><c:param name="scripts">
<script>
function togglePassword() {
    const pw = document.getElementById("password");
    const chk = document.getElementById("chk_d_ps");
    pw.type = chk.checked ? "text" : "password";
}
</script>
</c:param><c:param name="content">

<section class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-5">

            <!-- ① 画面タイトル（h2） -->
            <h2 class="text-center mb-4">ログイン</h2>

            <div class="card">
                <div class="card-body">

                    <form action="LoginExecute.action" method="post">

                        <!-- ② ログインID -->
                        <div class="mb-3">
                            <label class="form-label">ログインID</label>
                            <input type="text"
                                   name="id"
                                   class="form-control"
                                   value="${id}"
                                   maxlength="10"
                                   inputmode="latin"
                                   placeholder="半角でご入力ください"
                                   required>
                        </div>

                        <!-- ③ パスワード -->
                        <div class="mb-3">
                            <label class="form-label">パスワード</label>
                            <input type="password"
                                   name="password"
                                   id="password"
                                   class="form-control"
                                   maxlength="30"
                                   inputmode="latin"
                                   placeholder="30文字以内の半角英数字でご入力ください"
                                   required>
                        </div>

                        <!-- ④ チェックボックス -->
                        <div class="form-check mb-3 text-center">
                            <input class="form-check-input"
                                   type="checkbox"
                                   id="chk_d_ps"
                                   name="chk_d_ps"
                                   onclick="togglePassword()">
                            
                            <!-- ⑤ ラベル -->
                            <label class="form-check-label" for="chk_d_ps">
                                パスワードを表示
                            </label>
                        </div>

                        <!-- エラーメッセージ -->
                        <div class="text-danger text-center mb-2">
                            ${error}
                        </div>

                        <!-- ⑥ ログインボタン -->
                        <div class="text-center">
                            <button type="submit"
                                    name="login"
                                    value="ログイン"
                                    class="btn btn-primary w-50">
                                ログイン
                            </button>
                        </div>

                    </form>

                </div>
            </div>

        </div>
    </div>
</section>

</c:param></c:import>