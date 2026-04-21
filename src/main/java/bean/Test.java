package bean;

import java.io.Serializable;

public class Test implements Serializable {
    private Student student;   // 学生
    private String classNum;   // クラス番号
    private Subject subject;   // 科目
    private School school;     // 学校
    private int no;            // 回数
    private int point;         // 点数

    // Getter
    public Student getStudent() { return student; }
    public String getClassNum() { return classNum; }
    public Subject getSubject() { return subject; }
    public School getSchool() { return school; }
    public int getNo() { return no; }
    public int getPoint() { return point; }

    // Setter
    public void setStudent(Student student) { this.student = student; }
    public void setClassNum(String classNum) { this.classNum = classNum; }
    public void setSubject(Subject subject) { this.subject = subject; }
    public void setSchool(School school) { this.school = school; }
    public void setNo(int no) { this.no = no; }
    public void setPoint(int point) { this.point = point; }
}