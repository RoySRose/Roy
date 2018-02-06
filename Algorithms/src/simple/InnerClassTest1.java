package simple;

public class InnerClassTest1 {

	public static void main(String [] args) throws Exception{
				InnerClass innerClass = new InnerClass();
				System.out.println("Inner Class Name : " + innerClass.name);
	}
	
	static class InnerClass{
		public String name = "Jungho";
	}
}
