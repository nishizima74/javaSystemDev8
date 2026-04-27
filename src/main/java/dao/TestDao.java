//水嶋、西嶋

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	//クラス図の設計に基づき基本SQLを定義

	private String baseSql = "select "
			+ "st.no, st.name, st.ent_year, st.class_num, st.is_attend, "
			+ "t.subject_cd, t.no as test_no, t.point "
			+ "from student st "
			+ "left join test t on st.no = t.student_no "
			+ "and t.subject_cd = ? and t.no = ? and t.school_cd = ? "
			+ "where st.ent_year = ? and st.class_num = ? and st.school_cd = ?";

	// 指定された条件で成績リストを取得する

	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement(baseSql);
			st.setString(1, subject.getCd());
			st.setInt(2, num);
			st.setString(3, school.getCd());
			st.setInt(4, entYear);
			st.setString(5, classNum);
			st.setString(6, school.getCd());

			ResultSet rs = st.executeQuery();
			// postFilter を呼び出し
			list = postFilter(rs, school);

		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null)
				st.close();
			if (connection != null)
				connection.close();
		}
		return list;
	}

	// ResultSetからTestリストを生成する

	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		while (rSet.next()) {
			Test test = new Test();
			Student student = new Student();

			// Student情報のセット
			student.setNo(rSet.getString("no"));
			student.setName(rSet.getString("name"));
			student.setEntYear(rSet.getInt("ent_year"));
			student.setClassNum(rSet.getString("class_num"));

			// Test情報のセット
			test.setStudent(student);
			test.setSubject(null);
			test.setNo(rSet.getInt("test_no"));
			test.setPoint(rSet.getInt("point"));
			test.setClassNum(rSet.getString("class_num"));
			test.setSchool(school);

			if (rSet.wasNull()) {
				test.setPoint(-1);
			}
			list.add(test);
		}
		return list;
	}

	// 成績データリストを一括保存する

	public boolean save(List<Test> list) throws Exception {
		Connection connection = getConnection();
		boolean result = true;
		try {
			connection.setAutoCommit(false); // トランザクション開始

			for (Test test : list) {
				// 1件ずつ非公開のsaveメソッドを呼び出す
				if (!save(test, connection)) {
					result = false;
					break;
				}
			}

			if (result) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (Exception e) {
			connection.rollback();
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}
		return result;
	}

	// 1件の成績データを保存する

	private boolean save(Test test, Connection connection) throws Exception {
		PreparedStatement st = null;
		int count = 0;

		try {
			// 削除処理
			if (test.getPoint() < 0) {

				st = connection.prepareStatement(
						"delete from test where student_no=? and subject_cd=? and school_cd=? and no=?");

				st.setString(1, test.getStudent().getNo());
				st.setString(2, test.getSubject().getCd());
				st.setString(3, test.getSchool().getCd());
				st.setInt(4, test.getNo());

			} else {
				// 登録・更新
				st = connection.prepareStatement(
						"merge into test (student_no, subject_cd, school_cd, no, point, class_num) "
								+ "key(student_no, subject_cd, school_cd, no) values (?, ?, ?, ?, ?, ?)");

				st.setString(1, test.getStudent().getNo());
				st.setString(2, test.getSubject().getCd());
				st.setString(3, test.getSchool().getCd());
				st.setInt(4, test.getNo());
				st.setInt(5, test.getPoint());
				st.setString(6, test.getClassNum());
			}

			count = st.executeUpdate();

		} finally {
			if (st != null)
				st.close();
		}

		return count > 0;
	}
}