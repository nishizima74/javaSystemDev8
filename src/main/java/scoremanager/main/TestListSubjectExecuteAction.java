package scoremanager.main;

import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 1. JSPからの検索パラメータを受け取る
        // f1: 入学年度, f2: クラス, f3: 科目コード
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        // 2. 検索実行
        TestListSubjectDao tsDao = new TestListSubjectDao();
        SubjectDao sDao = new SubjectDao();
        List<TestListSubject> tests = null;

        if (entYearStr != null && classNum != null && subjectCd != null && 
            !entYearStr.equals("") && !classNum.equals("") && !subjectCd.equals("")) {
            
            int entYear = Integer.parseInt(entYearStr);
            // 科目コードからSubjectインスタンスを取得
            Subject subject = sDao.get(subjectCd, school);
            request.setAttribute("subject", subject);

            
            // DAOのfilterメソッドを呼び出し
            tests = tsDao.filter(entYear, classNum, subject, school);
            
         // 0件だった場合のメッセージ設定
            if (tests == null || tests.isEmpty()) {
                request.setAttribute("message2", "学生情報が存在しませんでした");
            }
        } else {
            // 未入力があった場合
            request.setAttribute("message", "入学年度とクラスと科目を選択してください");
        }

     // 4. プルダウン用の共通データを再セット（TestListActionの内容を手動で行う）
        ClassNumDao cNumDao = new ClassNumDao();

        request.setAttribute("class_list", cNumDao.filter(school));
        request.setAttribute("subject_list", sDao.filter(school));

        // 入学年度リストの生成（TestListActionと同じロジック）
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        List<Integer> ent_year_list = new java.util.ArrayList<>();
        for (int i = year - 5; i <= year + 5; i++) {
            ent_year_list.add(i);
        }
        request.setAttribute("ent_year_list", ent_year_list);
        
     // ★ここを追加：JSPに検索結果のリストを渡す
        request.setAttribute("tests", tests);
        
        
        // 検索条件を保持したい場合は、入力された値もセットしておくと親切です
        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectCd);
        
        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
        
    }
}