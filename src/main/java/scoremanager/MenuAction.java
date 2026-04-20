package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class MenuAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // メニュー表示に必要な処理（お知らせの取得など）があればここに書く
        
        // 最終的に menu.jsp を表示する
        request.getRequestDispatcher("main/menu.jsp").forward(request, response);
    }
}