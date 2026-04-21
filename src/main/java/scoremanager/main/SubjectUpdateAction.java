package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // URLパラメータから科目コードを取得
        String cd = req.getParameter("cd");
        SubjectDao sDao = new SubjectDao();
        
        // 更新対象のデータを取得
        Subject subject = sDao.get(cd, teacher.getSchool());

        req.setAttribute("subject", subject);
        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}