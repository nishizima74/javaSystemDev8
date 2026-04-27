//山本
package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String cd = req.getParameter("cd");

		Subject subject = new Subject();
		subject.setCd(cd);
		subject.setSchool(teacher.getSchool());

		SubjectDao sDao = new SubjectDao();
		sDao.delete(subject);

		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}