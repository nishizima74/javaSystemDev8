//水嶋
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();

		// リクエストパラメータを取得
		int entYear = Integer.parseInt(request.getParameter("f1"));
		String classNum = request.getParameter("f2");
		String subjectCd = request.getParameter("f3");
		int num = Integer.parseInt(request.getParameter("f4"));

		// 登録に必要なDAOとデータの準備
		TestDao tDao = new TestDao();
		SubjectDao sDao = new SubjectDao();
		Subject subject = sDao.get(subjectCd, school);

		// 現在の検索条件に一致する学生リストを取得
		List<Test> tests = tDao.filter(entYear, classNum, subject, num, school);
		List<Test> updateList = new ArrayList<>();
		List<String> errors = new ArrayList<>();

		List<String> errorStudentNos = new ArrayList<>(); // エラーがあった学生番号を保存する用

		// 入力値のチェックとリストへの格納
		for (Test test : tests) {
			String pointStr = request.getParameter("point_" + test.getStudent().getNo());

			// 未入力（空文字）のチェック
			if (pointStr == null || pointStr.isEmpty()) {

				if (test.getPoint() >= 0) {
					test.setSubject(subject);
					test.setNo(num);
					test.setPoint(-1);
					updateList.add(test);
				}

				continue;
			}

			try {
				int point = Integer.parseInt(pointStr);
				if (point < 0 || point > 100) {
					// エラーを具体的にする
					errors.add(test.getStudent().getName() + "さんは 0〜100 の範囲で入力してください");
					errorStudentNos.add(test.getStudent().getNo());
				} else {
					test.setSubject(subject);
					test.setNo(num);
					test.setPoint(point);
					updateList.add(test);
				}
			} catch (NumberFormatException e) {
				errors.add(test.getStudent().getName() + "さんの点数に不正な文字が含まれています");
				errorStudentNos.add(test.getStudent().getNo());
			}
		}

		// 1つでもエラーがあれば登録せずに戻る
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			// 元の条件で再検索した結果を出すため、Actionを呼び出す
			request.setAttribute("errorStudentNos", errorStudentNos);
			request.getRequestDispatcher("TestRegist.action").forward(request, response);
			return;
		}

		// DBへの保存実行
		tDao.save(updateList);

		// 完了画面へフォワード
		request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
	}
}