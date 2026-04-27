//水嶋、西嶋
package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {

	private int entYear; // 入学年度
	private String studentNo; // 学生番号
	private String studentName;// 学生名
	private String classNum; // クラス番号
	private Map<Integer, Integer> points = new HashMap<>(); // 回数と点数のマップ

	public TestListSubject() {
	}

	// Getter / Setter
	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Map<Integer, Integer> getPoints() {
		return points;
	}

	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	public String getPoint(int key) {
		Integer point = points.get(key);
		if (point == null) {
			return "-";
		}
		return String.valueOf(point);
	}

	public void putPoint(int key, int value) {
		this.points.put(key, value);
	}
}