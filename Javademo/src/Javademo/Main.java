package Javademo;

public class Main implements intDemo{
	public void add() {
		System.out.println("From Add");
	}
	public void subtract() {
		System.out.println("From subtract");
	}
	public static void main(String[] args) {
		System.out.println("Hello World");
		Main m = new Main();
		m.add();
		m.subtract();
		m.printMessage();
		intDemo.staticMessage();
	}
}
