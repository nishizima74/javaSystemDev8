//山本
package scoremanager.main;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// セッション取得
		HttpSession session = req.getSession();

		// ログイン中の教員を取得
		Teacher teacher = (Teacher) session.getAttribute("user");

		req.setAttribute("teacher", teacher);

		// 科目登録画面へフォワード
		req.getRequestDispatcher("subject_create.jsp").forward(req, res);
	}
}