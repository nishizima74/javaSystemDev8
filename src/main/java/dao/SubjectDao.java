package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * 基本となるSQL文
     */
    private String baseSql = "select * from subject where school_cd=? ";

    /**
     * 科目コード＋学校で1件取得
     */
    public Subject get(String cd, School school) throws Exception {

        Subject subject = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from subject where cd=? and school_cd=?"
            );
            statement.setString(1, cd);
            statement.setString(2, school.getCd());

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
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

        return subject;
    }

    /**
     * ResultSet を Subject リストに変換（StudentDao の postFilter と同系）
     */
    private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        while (rSet.next()) {
            Subject subject = new Subject();
            subject.setCd(rSet.getString("cd"));
            subject.setName(rSet.getString("name"));
            subject.setSchool(school);
            list.add(subject);
        }

        return list;
    }

    /**
     * 学校に紐づく科目一覧取得
     */
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(baseSql + "order by cd")) {

            statement.setString(1, school.getCd());

            ResultSet rSet = statement.executeQuery();
            list = postFilter(rSet, school);

        } catch (Exception e) {
            throw e;
        }

        return list;
    }

    /**
     * 登録（存在すれば更新、なければ登録）
     */
    public boolean save(Subject subject) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Subject old = get(subject.getCd(), subject.getSchool());

            if (old == null) {
                // 新規登録
                statement = connection.prepareStatement(
                    "insert into subject (school_cd, cd, name) values (?, ?, ?)"
                );
                statement.setString(1, subject.getSchool().getCd());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getName());
            } else {
                // 更新
                statement = connection.prepareStatement(
                    "update subject set name=? where cd=? and school_cd=?"
                );
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getSchool().getCd());
            }

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

        return count > 0;
    }

    /**
     * 削除
     */
    public boolean delete(Subject subject) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                "delete from subject where cd=? and school_cd=?"
            );
            statement.setString(1, subject.getCd());
            statement.setString(2, subject.getSchool().getCd());

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

        return count > 0;
    }
}