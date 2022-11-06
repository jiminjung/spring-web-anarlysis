package kr.ac.jbnu.se.awp.gitplay4.model;
import java.util.ArrayList;
import java.util.HashSet;

public class Attribute {
	
	private ArrayList<String> data;
	private String name;
	private HashSet<Integer> na_array;
	
	public Attribute() {
		data = new ArrayList<String>();
		na_array = new HashSet<Integer>();
	}
	
	public void addData(String data) {
		this.data.add(data);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addNa_array(int na) {
		na_array.add(na);
	}
	
	public String getData(int i) {
		return data.get(i);
	}	
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return this.data.size();
	}

//	public Integer getNa_array(String ) {
//		� ������ ��ȯ���� .. 
// 		�˻��ϴ� ��쵵 ����
// 		������ ��ȯ�� �� ��� �ؾ�����..
//	} 
	
}
