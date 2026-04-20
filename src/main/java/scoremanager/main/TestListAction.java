package scoremanager.main;

import java.util.Calendar;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. 検索条件に使うデータの準備
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        
        // ログイン講師の学校に紐づくクラス一覧と科目一覧を取得
        List<String> class_list = cNumDao.filter(teacher.getSchool());
        List<bean.Subject> subject_list = sDao.filter(teacher.getSchool());
        
        // 入学年度のリスト（現在から前後5年分など）を作成
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        List<Integer> ent_year_list = new java.util.ArrayList<>();
        for (int i = year - 5; i <= year + 5; i++) {
            ent_year_list.add(i);
        }

        // 2. リクエスト属性にセット
        
     // 既存のexecuteメソッド内に追加
     // 検索条件をリクエストから取得し、そのまま戻す（条件保持のため）
        request.setAttribute("f1", request.getParameter("f1"));
        request.setAttribute("f2", request.getParameter("f2"));
        request.setAttribute("f3", request.getParameter("f3"));
        request.setAttribute("f4", request.getParameter("f4"));
        
        request.setAttribute("class_list", class_list);
        request.setAttribute("subject_list", subject_list);
        request.setAttribute("ent_year_list", ent_year_list);

        // 3. 成績参照画面（JSP）へフォワード
        // フォルダ構成に合わせてパスを調整してください
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}