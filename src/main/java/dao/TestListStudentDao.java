package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    // クラス図にある基本となるSQL文
	private String baseSql = "select sub.name, t.subject_cd, t.no, t.point "
            + "from test t "
            + "left join subject sub on t.subject_cd = sub.cd and t.school_cd = sub.school_cd "
            + "where t.student_no LIKE ? and t.school_cd = ?";

    /**
     * ResultSetからリストへの変換処理（共通化メソッド）
     */
    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent res = new TestListStudent();
            // クラス図のフィールド名に合わせてセット
            res.setSubjectName(rSet.getString("name"));
            res.setSubjectCd(rSet.getString("subject_cd"));
            res.setNum(rSet.getInt("no"));
            res.setPoint(rSet.getInt("point"));
            list.add(res);
        }
        return list;
    }

    /**
     * 学生情報から成績一覧を取得する
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        String sql = baseSql + " order by t.subject_cd asc, t.no asc";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // 1番目の?に学生番号をセット
            statement.setString(1, student.getNo().trim());
            // 2番目の?に学校コードをセット（studentオブジェクトから取得）
            statement.setString(2, student.getSchool().getCd());

            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet);
            }
        }
        // デバッグ用にここにも出力しておくと安心です
        System.out.println("検索する学生No: " + student.getNo());
        System.out.println("検索する学校CD: " + student.getSchool().getCd());
        System.out.println("DAO取得件数: " + list.size());
        
        return list;
    }
}