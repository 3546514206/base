package Javaassist;

interface Byte01 {

}

public class Emp {

	private int empno;
	private String ename;

	public Emp() {
		super();
	}

	public Emp(int empno, String ename) {
		super();
		this.empno = empno;
		this.ename = ename;
	}

	public void sayHello(int a) {
		System.out.println("hello world" + a);
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

}
