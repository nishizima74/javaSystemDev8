//水嶋
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();

		// リクエストパラメータの取得
		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String subjectCd = request.getParameter("f3");
		String numStr = request.getParameter("f4");

		// DAOの初期化
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();
		TestDao tDao = new TestDao();

		List<Integer> entYearSet = new ArrayList<>();
		int year = LocalDate.now().getYear();
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}
		// クラス一覧
		List<String> classNumSet = cNumDao.filter(school);
		// 科目一覧
		List<Subject> subjects = subDao.filter(school);
		// テスト回数（2回固定）
		List<Integer> numSet = new ArrayList<>();
		numSet.add(1);
		numSet.add(2);

		//  検索処理
		// すべての項目が選択されているかチェック
		if (entYearStr != null && classNum != null && subjectCd != null && numStr != null &&
				!entYearStr.equals("0") && !entYearStr.equals("") &&
				!classNum.equals("") && !subjectCd.equals("") && !numStr.equals("")) {

			int entYear = Integer.parseInt(entYearStr);
			int num = Integer.parseInt(numStr);

			// 科目コードからSubject型に変換
			Subject subject = subDao.get(subjectCd, school);

			// 成績データ取得
			List<Test> tests = tDao.filter(entYear, classNum, subject, num, school);

			// 検索結果をリクエストにセット
			request.setAttribute("tests", tests);
			request.setAttribute("subject", subject);

			// 0件だった場合の処理
			if (tests == null || tests.isEmpty()) {
				request.setAttribute("message2", "学生情報が存在しませんでした");
			}

		} else {

			if (entYearStr != null || classNum != null || subjectCd != null || numStr != null) {
				request.setAttribute("message", "入学年度とクラスと科目と回数を選択してください");
			}
		}

		// JSPへデータを渡す
		request.setAttribute("f1", entYearStr);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", subjectCd);
		request.setAttribute("f4", numStr);

		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", classNumSet);
		request.setAttribute("subjects", subjects);
		request.setAttribute("num_set", numSet);

		// フォワード
		request.getRequestDispatcher("test_regist.jsp").forward(request, response);
	}
}