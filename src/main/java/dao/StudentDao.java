package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

	private String baseSql = "select * from student where school_cd=?";

	public Student get(String no) throws Exception {

		Student student = new Student();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(
					"select * from student where no=?");

			statement.setString(1, no);

			ResultSet rSet = statement.executeQuery();

			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));

				student.setSchool(
						schoolDao.get(rSet.getString("school_cd")));
			} else {
				student = null;
			}

		} catch (Exception e) {
			throw e;

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return student;
	}

	private List<Student> postFilter(ResultSet rSet, School school) throws Exception {

		List<Student> list = new ArrayList<>();

		try {
			while (rSet.next()) {

				Student student = new Student();

				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				student.setSchool(school);

				list.add(student);
			}

		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

		// リストを初期化
		List<Student> list = new ArrayList<>();

		// コネクションを確立
		Connection connection = getConnection();

		// プリペアードステートメント
		PreparedStatement statement = null;

		// リザルトセット
		ResultSet rSet = null;

		// SQL文の条件
		String condition = " and ent_year=? and class_num=?";

		// SQL文のソート
		String order = " order by no asc";

		// SQL文で在学フラグ条件
		String conditionIsAttend = "";

		// 在学フラグがtrueの場合
		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);

			// パラメータをバインド
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);

			// SQL実行
			rSet = statement.executeQuery();

			// リストへ格納
			list = postFilter(rSet, school);

		} catch (Exception e) {
			throw e;

		} finally {

			// ステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

		// リストを初期化
		List<Student> list = new ArrayList<>();

		// コネクションを確立
		Connection connection = getConnection();

		// プリペアードステートメント
		PreparedStatement statement = null;

		// リザルトセット
		ResultSet rSet = null;

		// SQL文の条件
		String condition = " and ent_year=?";

		// SQL文のソート
		String order = " order by no asc";

		// 在学フラグ条件
		String conditionIsAttend = "";

		// 在学フラグがtrueの場合
		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}

		try {
			// SQLセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);

			// パラメータをバインド
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);

			// SQL実行
			rSet = statement.executeQuery();

			// リストに変換
			list = postFilter(rSet, school);

		} catch (Exception e) {
			throw e;

		} finally {

			// ステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	public List<Student> filter(School school, boolean isAttend) throws Exception {

		// リストを初期化
		List<Student> list = new ArrayList<>();

		// コネクションを確立
		Connection connection = getConnection();

		// プリペアードステートメント
		PreparedStatement statement = null;

		// リザルトセット
		ResultSet rSet = null;

		// SQL文のソート
		String order = " order by no asc";

		// 在学フラグ条件
		String conditionIsAttend = "";

		// 在学フラグがtrueの場合
		if (isAttend) {
			conditionIsAttend = " and is_attend=true";
		}

		try {
			// SQLをセット
			statement = connection.prepareStatement(baseSql + conditionIsAttend + order);

			// パラメータをバインド
			statement.setString(1, school.getCd());

			// SQL実行
			rSet = statement.executeQuery();

			// リストへ格納
			list = postFilter(rSet, school);

		} catch (Exception e) {
			throw e;

		} finally {

			// ステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	public boolean save(Student student) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;

		try {
			// データベースから学生を取得
			Student old = get(student.getNo());

			if (old == null) {
				// 学生が存在しなかった場合
				statement = connection.prepareStatement(
						"insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)");

				statement.setString(1, student.getNo());
				statement.setString(2, student.getName());
				statement.setInt(3, student.getEntYear());
				statement.setString(4, student.getClassNum());
				statement.setBoolean(5, student.isAttend());
				statement.setString(6, student.getSchool().getCd());

			} else {
				// 学生が存在した場合
				statement = connection.prepareStatement(
						"update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");

				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.isAttend());
				statement.setString(5, student.getNo());
			}

			// 実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;

		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		// 結果判定
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
}