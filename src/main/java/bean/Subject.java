package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    // フィールド（クラス図の上の段）
    private String cd;      // 科目コード
    private String name;    // 科目名
    private School school;  // 学校（Schoolクラスのインスタンス）

    // コンストラクタ
    public Subject() {
    }

    // メソッド（クラス図の下の段：getter/setter）

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}