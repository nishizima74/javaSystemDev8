package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから教員取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータ取得
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        int entYear = Integer.parseInt(req.getParameter("ent_year"));
        String classNum = req.getParameter("class_num");

        // チェックボックス（null対策）
        boolean isAttend = req.getParameter("is_attend") != null;

        // Student作成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool());

        // DB更新
        StudentDao sDao = new StudentDao();
        boolean result = sDao.save(student);

        if (result) {
            // 成功
            req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
        } else {
            // 失敗
            req.setAttribute("errors", "更新に失敗しました");
            req.getRequestDispatcher("StudentUpdate.action?no=" + no).forward(req, res);
        }
    }
}