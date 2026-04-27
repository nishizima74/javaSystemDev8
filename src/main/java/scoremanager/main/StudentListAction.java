package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// セッションからログインユーザー取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// ログインしていない場合はログイン画面へ
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}

		// パラメータ取得
		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String isAttendStr = request.getParameter("f3");

		int entYear = 0;
		boolean isAttend = false;

		// 在学フラグの判定を先にやる！
		if (isAttendStr != null) {
			isAttend = true;
			request.setAttribute("f3", isAttendStr);
		}

		if (entYearStr != null && !entYearStr.isEmpty()) {
			entYear = Integer.parseInt(entYearStr);
		}

		// 現在の年から入学年度セット作成
		LocalDate today = LocalDate.now();
		int year = today.getYear();

		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}

		// DAO
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();

		// クラス番号一覧
		List<String> classNumList = cNumDao.filter(teacher.getSchool());

		// 学生リスト
		List<Student> students = null;

		// 絞り込み条件
		Map<String, String> errors = new HashMap<>();

		if (entYear != 0 && classNum != null && !classNum.equals("0")) {

			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);

		} else if (entYear != 0 && classNum != null && classNum.equals("0")) {

			students = sDao.filter(teacher.getSchool(), entYear, isAttend);

		} else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {

			students = sDao.filter(teacher.getSchool(), isAttend);

		} else {

			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			request.setAttribute("errors", errors);

			students = sDao.filter(teacher.getSchool(), isAttend);
		}

		// 在学フラグ
		if (isAttendStr != null) {
			isAttend = true;
			request.setAttribute("f3", isAttendStr);
		}

		// JSP へ値を渡す
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		request.setAttribute("students", students);
		request.setAttribute("class_num_set", classNumList);
		request.setAttribute("ent_year_set", entYearSet);

		// JSP へフォワード
		request.getRequestDispatcher("student_list.jsp").forward(request, response);
	}
}
