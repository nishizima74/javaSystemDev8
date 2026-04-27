//山本、杉本
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// セレクトボックス用のクラスデータを取得
		ClassNumDao cNumDao = new ClassNumDao();
		List<String> class_list = cNumDao.filter(teacher.getSchool());

		// 現在の年から入学年度のリストを作成
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// リクエスト属性にセットしてJSPへ
		req.setAttribute("class_num_set", class_list);
		req.setAttribute("ent_year_set", entYearSet);
		req.getRequestDispatcher("student_create.jsp").forward(req, res);
	}
}