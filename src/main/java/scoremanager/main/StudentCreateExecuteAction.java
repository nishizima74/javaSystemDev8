package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメーターの取得
        int entYear = Integer.parseInt(req.getParameter("ent_year"));
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String classNum = req.getParameter("class_num");

        // Daoの初期化
        StudentDao sDao = new StudentDao();
        
        // 1. 学生番号の重複チェック
        Student existingStudent = sDao.get(no);
        if (existingStudent != null) {
            // 重複がある場合はエラーメッセージをセットして入力画面へ戻す
            req.setAttribute("errors", "学生番号が重複しています。");
            // 入力中の値を保持させるためにリクエストに戻す（オプション）
            req.getRequestDispatcher("StudentCreate.action").forward(req, res);
            return;
        }

        // 2. 学生インスタンスの作成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(true); // 新規登録時は在学中に設定
        student.setSchool(teacher.getSchool());

        // 3. データベースへ保存
        boolean isSuccess = sDao.save(student);

        if (isSuccess) {
            // 登録成功：完了画面へ
            req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
        } else {
            // 登録失敗（DBエラー等）：エラーを表示
            req.setAttribute("errors", "登録に失敗しました。管理者へ連絡してください。");
            req.getRequestDispatcher("StudentCreate.action").forward(req, res);
        }
    }
}