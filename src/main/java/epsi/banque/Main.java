package epsi.banque;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		if(args.length >= 3) {
			Calculatrice test1 = new Calculatrice(args[0], args[2], args[1]);
			System.out.println("Result test1 : " +test1.result);
		}
		/*Calculatrice test2 = new Calculatrice("se", "2", "*");
		System.out.println("Result test2 : " +test2.result);
		Thread.sleep(1000);
		Calculatrice test3 = new Calculatrice("1", "2", "ADDICTION");		
		System.out.println("Result test3 : " +test3.result);
		Thread.sleep(1000);
		Calculatrice test4 = new Calculatrice("4", "2", "/");
		System.out.println("Result test4 : " +test4.result);*/
		
	}
	
	
	
}
