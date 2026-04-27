//水嶋
package scoremanager.main;

import java.util.Calendar;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		//  検索条件に使うデータの準備
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();

		// ログイン講師の学校に紐づくクラス一覧と科目一覧を取得
		List<String> class_list = cNumDao.filter(teacher.getSchool());
		List<bean.Subject> subject_list = sDao.filter(teacher.getSchool());

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		List<Integer> ent_year_list = new java.util.ArrayList<>();

		// 10年前から現在の年までを順番にリストに追加
		for (int i = year - 10; i <= year; i++) {
			ent_year_list.add(i);
		}

		//  リクエスト属性にセット
		request.setAttribute("f1", request.getParameter("f1"));
		request.setAttribute("f2", request.getParameter("f2"));
		request.setAttribute("f3", request.getParameter("f3"));
		request.setAttribute("f4", request.getParameter("f4"));

		request.setAttribute("class_list", class_list);
		request.setAttribute("subject_list", subject_list);
		request.setAttribute("ent_year_list", ent_year_list);

		// 成績参照画面（JSP）へフォワード
		request.getRequestDispatcher("test_list.jsp").forward(request, response);
	}
}