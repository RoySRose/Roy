package simple;

public class InnerClassTest2 {

	public static void main(String [] args) throws Exception{
				InnerClassTest2 innerClassTest1 = new InnerClassTest2();
				InnerClass innerClass = innerClassTest1.new InnerClass();
				System.out.println("Inner Class Name : " + innerClass.name);
	}
	
	static class InnerClass{
		public String name = "Jungho";
	}
}
