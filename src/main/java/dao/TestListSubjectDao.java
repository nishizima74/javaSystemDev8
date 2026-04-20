package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    // クラス図にある基本となるSQL文
    private String baseSql = "select st.ent_year, st.no as student_no, st.name as student_name, st.class_num, t.no, t.point " +
                             "from test t " +
                             "join student st on t.student_no = st.no " +
                             "where st.ent_year = ? and st.class_num = ? and TRIM(t.subject_cd) = ? and t.school_cd = ?";

    /**
     * ResultSetからリストへの変換処理
     * 学生ごとにMapへ点数を集約する
     */
    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        TestListSubject current = null;
        String lastStudentNo = "";

        while (rSet.next()) {
            String studentNo = rSet.getString("student_no");

            // 前のレコードと学生番号が異なる場合、新しいBeanを作成
            if (!studentNo.equals(lastStudentNo)) {
                current = new TestListSubject();
                current.setEntYear(rSet.getInt("ent_year"));
                current.setStudentNo(studentNo);
                current.setStudentName(rSet.getString("student_name"));
                current.setClassNum(rSet.getString("class_num"));
                list.add(current);
                lastStudentNo = studentNo;
            }

            // 同一学生の「回数」と「点数」をMapに追加
            int no = rSet.getInt("no");
            int point = rSet.getInt("point");
            current.putPoint(no, point);
        }
        System.out.println("取得件数: " + list.size() + " 件");
        return list;
    }

    /**
     * 科目情報から成績一覧を取得する
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        
    	System.out.println("検索する学校CD: " + school.getCd());
    	
    	List<TestListSubject> list = new ArrayList<>();
        // 同一学生が並ぶようにソート
        String sql = baseSql + " order by st.no asc, t.no asc";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd().trim());
            statement.setString(4, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet);
            }
        }
        return list;
    }
}