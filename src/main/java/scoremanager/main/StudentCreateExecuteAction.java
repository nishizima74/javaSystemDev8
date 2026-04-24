package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

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
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメーターの取得
        String entYearStr = req.getParameter("ent_year");
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String classNum = req.getParameter("class_num");

        // エラーリストの初期化
        Map<String, String> errors = new HashMap<>();
        StudentDao sDao = new StudentDao();

        // 1. 学生番号の重複チェック（画像5の対応）
        Student existingStudent = sDao.get(no);
        if (existingStudent != null) {
            errors.put("no", "学生番号が重複しています");
        }

        // エラーがある場合は入力画面に戻る
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            // 入力値を保持させる
            req.setAttribute("ent_year", entYearStr);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.setAttribute("class_num", classNum);
            
            // StudentCreateActionを経由せず直接JSPへ（初期化データを再取得するため本来はActionへ転送が望ましいですが、
            // 簡易的にStudentCreateActionの初期化処理をここに呼ぶか、JSPへ戻します）
            req.getRequestDispatcher("StudentCreate.action").forward(req, res);
            return;
        }

        // 2. 保存処理
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        sDao.save(student);
        req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
    }
}