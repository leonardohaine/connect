package connectLab;

public class Test {

		public static void main(String args[]) {
			 String cpf ="09551130401";
			    cpf = cpf.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3-$4");
			    System.out.println(cpf);
			    
			 String cnpj = "01.234.567/8945-61";
			 
			 System.out.println("CNPJ:" + cnpj.replaceAll("[^0-9]", ""));
			 
			 System.out.println(Integer.toBinaryString(-20));
			 System.out.println(Integer.parseInt("10100", 8));
			    
		}
	
}
