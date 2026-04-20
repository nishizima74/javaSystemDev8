package bean;

import java.io.Serializable;

public class ClassNum implements Serializable {

    /** クラス名:String */
    private String class_num;

    /** 学校:School */
    private School school;

    /** ゲッター・セッター */
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getClassnum() {
        return class_num;
    }

	public void setClassNum(String string) {
		// TODO 自動生成されたメソッド・スタブ
		
	}


}