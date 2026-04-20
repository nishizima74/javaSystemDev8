package scoremanager.main;

import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        // teacherからschoolを取得
        School school = teacher.getSchool(); 

//        リクエストパラメーター
        String studentNo = request.getParameter("f4");

        TestListStudentDao tsDao = new TestListStudentDao();
        StudentDao sDao = new StudentDao();
        SubjectDao subDao = new SubjectDao(); // 追加
        List<TestListStudent> tests = null;

        if (studentNo != null && !studentNo.isEmpty()) {
            // リクエストパラメータから学生情報を取得
            Student student = sDao.get(studentNo); 
            
            // 自校の学生であることを確認して検索実行
            // そもそも学生が存在しない（または他校の学生）場合
            if (student == null || !student.getSchool().getCd().equals(school.getCd())) {
                request.setAttribute("notfoundmessage", "学生情報が存在しませんでした");
            } else {
                // 学生は存在するので、JSP表示用にセット
                request.setAttribute("student", student);
                
                // 成績情報を検索
                tests = tsDao.filter(student);
                
                // ② 学生は存在するが、成績情報が存在しない場合
                if (tests == null || tests.isEmpty()) {
                    request.setAttribute("notfoundmessage", "成績情報が存在しませんでした");
                } 
                // ③ 学生も成績も存在する場合は、tests がそのまま JSP へ渡る
            }
        }
        
        
        // 3. 次画面（JSP）で使うデータをリクエストにセット
        request.setAttribute("f", "st"); // 識別コード
        request.setAttribute("f4", studentNo); // 入力された学生番号を保持
        request.setAttribute("tests", tests); // 検索結果リスト
        
        
     // 4. プルダウン用の共通データを再セット（TestListActionの内容を手動で行う）
        ClassNumDao cNumDao = new ClassNumDao();

        request.setAttribute("class_list", cNumDao.filter(school));
        request.setAttribute("subject_list", subDao.filter(school)); // 追加：科目リスト

        // 入学年度リストの生成（TestListActionと同じロジック）
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        List<Integer> ent_year_list = new java.util.ArrayList<>();
        for (int i = year - 5; i <= year + 5; i++) {
            ent_year_list.add(i);
        }
        
        request.setAttribute("ent_year_list", ent_year_list); // 追加：リクエストにセット

        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);    }
}