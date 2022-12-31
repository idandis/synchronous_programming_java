
public class Studenti extends Thread {
	
 Laboratorio L; 
 int Tipologia;
 


 public Studenti (int t, Laboratorio L, int id) {
 this.Tipologia=t;
 this.L=L;
 this.setName("Studente" + id);
}
 public void run () {
	 
	 while (true) {
		 
		 try {	 
		 L.richiedePostazione(Tipologia);
		 // simulo l'utilizzo della postazione di studio
		 sleep((int)(Math.random()*5000));
		 } catch(InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		 L.rilasciaPostazione(Tipologia);
	 }
			 
 }

	
}

 
 

