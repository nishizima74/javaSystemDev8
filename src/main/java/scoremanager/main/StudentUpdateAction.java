//西嶋
package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// セッションからログインユーザ取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// パラメータ取得（学生番号）
		String no = req.getParameter("no");

		// 学生データ取得
		StudentDao sDao = new StudentDao();
		Student student = sDao.get(no);

		// クラス一覧取得
		ClassNumDao cDao = new ClassNumDao();
		List<String> classList = cDao.filter(teacher.getSchool());

		// JSPへ渡す
		req.setAttribute("student", student);
		req.setAttribute("class_num_set", classList);

		// 画面遷移
		req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}
}