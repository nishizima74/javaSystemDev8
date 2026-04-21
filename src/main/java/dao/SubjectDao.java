package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    /** get: 科目コード＋学校で1件取得 */
    public Subject get(String cd, School school) throws Exception {

        Subject subject = null;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(
                     "select * from subject where cd=? and school_cd=?")) {

            statement.setString(1, cd);
            statement.setString(2, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {
                if (rSet.next()) {
                    subject = new Subject();
                    subject.setCd(rSet.getString("cd"));
                    subject.setName(rSet.getString("name"));
                    subject.setSchool(school);
                }
            }
        }
        return subject;
    }

    /** filter: 学校に紐づく科目一覧取得 */
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(
                     "select * from subject where school_cd=? order by cd asc")) {

            statement.setString(1, school.getCd());

            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                    Subject subject = new Subject();
                    subject.setCd(rSet.getString("cd"));
                    subject.setName(rSet.getString("name"));
                    subject.setSchool(school);
                    list.add(subject);
                }
            }
        }
        return list;
    }

    /** save: 新規登録・更新 */
    public boolean save(Subject subject) throws Exception {

        int count;

        try (Connection connection = getConnection()) {

            Subject existing = get(subject.getCd(), subject.getSchool());

            if (existing == null) {
                try (PreparedStatement statement =
                     connection.prepareStatement(
                         "insert into subject (cd, name, school_cd) values (?, ?, ?)")) {

                    statement.setString(1, subject.getCd());
                    statement.setString(2, subject.getName());
                    statement.setString(3, subject.getSchool().getCd());
                    count = statement.executeUpdate();
                }
            } else {
                try (PreparedStatement statement =
                     connection.prepareStatement(
                         "update subject set name=? where cd=? and school_cd=?")) {

                    statement.setString(1, subject.getName());
                    statement.setString(2, subject.getCd());
                    statement.setString(3, subject.getSchool().getCd());
                    count = statement.executeUpdate();
                }
            }
        }
        return count > 0;
    }

    /** delete: 削除 */
    public boolean delete(Subject subject) throws Exception {

        int count;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                 connection.prepareStatement(
                     "delete from subject where cd=? and school_cd=?")) {

            statement.setString(1, subject.getCd());
            statement.setString(2, subject.getSchool().getCd());
            count = statement.executeUpdate();
        }
        return count > 0;
    }
}