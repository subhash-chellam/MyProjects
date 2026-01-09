package Javademo;

public interface intDemo {
	default void printMessage() {
		System.out.println("Print Message ");
	}
	void add();
	void subtract();
	static void staticMessage() {
		System.out.println("Static message");
	}
}
