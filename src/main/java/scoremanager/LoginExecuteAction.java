package scoremanager;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // --- 1. 入力データの取得 ---
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        // エラーメッセージ格納用
        List<String> errors = new ArrayList<>();
        
        // --- 2. 未入力チェック (シーケンス図の最初の alt ブロック) ---
        if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
            errors.add("IDとパスワードを入力してください");
            request.setAttribute("errors", errors);
            // ログイン画面へ戻る
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // --- 3. 認証処理 (TeacherDaoを起動) ---
        TeacherDao tDao = new TeacherDao();
        // IDとPWを元に認証・データ取得
        Teacher teacher = tDao.login(id, password);

        // --- 4. 判定 (シーケンス図の後半の alt ブロック) ---
        if (teacher != null) {
            // 【認証成功】
            // セッションを開始し、ユーザーデータを保存
            HttpSession session = request.getSession();
            session.setAttribute("user", teacher);
            
         // JSPへ直接フォワードするのではなく、MenuActionを呼び出す
            // FrontControllerのルールに合わせて ".action" をつけてリダイレクトします
            response.sendRedirect("Menu.action");
        } else {
            // 【認証失敗：IDかPWが正しくない場合】
            errors.add("認証に失敗しました。IDまたはパスワードが正しくありません");
            request.setAttribute("errors", errors);
            // ログイン画面へ戻る
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}