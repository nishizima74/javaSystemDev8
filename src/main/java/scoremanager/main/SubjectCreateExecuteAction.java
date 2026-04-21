package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログインユーザーを取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータ取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // 入力値保持（エラー時に元画面へ戻すため）
        req.setAttribute("cd", cd);
        req.setAttribute("name", name);

        // 未入力チェック
        if (cd == null || cd.isEmpty() || name == null || name.isEmpty()) {
            req.setAttribute("errors", "このフィールドを入力してください");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // 科目コード文字数チェック（3文字）
        if (cd.length() != 3) {
            req.setAttribute("errors", "科目コードは3文字で入力してください");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // DAO 初期化
        SubjectDao sDao = new SubjectDao();

        // 科目コード重複チェック
        Subject existing = sDao.get(cd, teacher.getSchool());
        if (existing != null) {
            req.setAttribute("errors", "科目コードが重複しています");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        // Subject インスタンス生成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        // DBへ保存
        boolean isSuccess = sDao.save(subject);

        if (isSuccess) {
            // 登録成功 → 完了画面へ
            req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
        } else {
            // 登録失敗
            req.setAttribute("errors", "登録に失敗しました。管理者に連絡してください。");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
        }
    }
}