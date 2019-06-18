package BookDemo;


class Window{
	Window(int marker){
		System.out.println("Window("+marker+")");
	}
}
class Dog {
	Window w4 = new Window(4);
}
class House{
	Window w1 = new Window(1);
	//w1 = new Window(5);不懂
	Window w3=new Window(333);
	Window w2 = new Window(2);
	void fo() {System.out.println("f()");}
	
	House(){
		System.out.println("House()");
		w3 = new Window(33);
	}
	//w3 = new Window(3);表示不懂
}

public class Demo1 {
	public static void main(String[] args) {
		House house = new House();
		house.fo();
	}
	
}
