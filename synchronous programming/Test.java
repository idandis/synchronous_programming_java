import prog.io.ConsoleInputManager;

public class Test {

  public static void main(String[] args) {
		
   
    ConsoleInputManager input = new ConsoleInputManager();
	int numero = input.readInt("inserisci il numero di stazioni di lavoro nel laboratorio: ");
	
	Laboratorio L = new Laboratorio (numero);
    for (int i=0; i<15; i++) {
	    int t = (int)(Math.random()*2);
	    Thread Studenti = new Studenti (t, L, i);
	    Studenti.start();
    }
   }
}
