//水嶋、西嶋

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

	public Teacher login(String id, String password) throws Exception {
		Teacher teacher = null;
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			// SQL文：IDとパスワードが一致する先生を探す
			statement = connection.prepareStatement("select * from teacher where id=? and password=?");
			statement.setString(1, id);
			statement.setString(2, password);

			// 実行
			ResultSet rSet = statement.executeQuery();

			// 結果があればTeacherインスタンスを作成
			if (rSet.next()) {
				teacher = new Teacher();
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));

				// 学校コード（school_cd）をSchoolBeanとしてセットする場合
				School school = new School();
				school.setCd(rSet.getString("school_cd"));
				teacher.setSchool(school);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		return teacher; // 見つからなければnull、見つかれば先生データが返る
	}
}