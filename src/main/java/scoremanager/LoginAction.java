package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class LoginAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ビジネスロジックは何もしない（ログイン画面を表示するだけ）
        
        // JSPへフォワード
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}