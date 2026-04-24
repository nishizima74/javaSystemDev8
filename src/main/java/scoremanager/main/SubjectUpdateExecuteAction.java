package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        SubjectDao sDao = new SubjectDao();
        // 更新前に存在チェック（画像3の「変更中に削除された場合」の対応）
        Subject existing = sDao.get(cd, teacher.getSchool());

        if (existing != null) {
            // 存在する場合は更新
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(teacher.getSchool());
            sDao.save(subject);
            
            req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
        } else {
            // 存在しない場合はエラーをセットして戻る（画像3の再現）
            Map<String, String> errors = new HashMap<>();
            errors.put("not_found", "科目が存在していません");
            req.setAttribute("errors", errors);
            
            // 入力画面で値を保持するためにセット
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            req.setAttribute("subject", subject);
            
            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
        }
    }
}