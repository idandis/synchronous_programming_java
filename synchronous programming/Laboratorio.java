
public class Laboratorio {
	
	int PostazioneLavoro;
	int codaS; // coda per gli studenti cge richiedono 1 stazione di lavoro 
	int codaT; // coda studenti che richiedono 2 stazioni di lavoro

	public Laboratorio (int PostazioneLavoro) {
		this.PostazioneLavoro = PostazioneLavoro;
		this.codaS = 0;
		this.codaT = 0;
	}	
	// Tipologia studenti:
	// tipologia 0 - postazione singola
	// tipologia 1 - postazione doppia 
	
  public synchronized void richiedePostazione (int Tipologia) {

	if (Tipologia==0) {
	System.out.println(Thread.currentThread().getName()+ ": Ho bisogno di una sola postazione");

            while (PostazioneLavoro<2 || codaT>0 ) {
    	       System.out.println(Thread.currentThread().getName()+ ": devo aspettare!");
	          codaS++;
	         	try {
	        		wait();
	        	} catch (InterruptedException e) {
	        		e.printStackTrace();
	        	  }
            } // end while 
       
       System.out.println(Thread.currentThread().getName()+ " occupo una postazione di studio");
       if (codaS>0) {
    	   codaS--; 
       }
           PostazioneLavoro--;

           System.out.println("|postazioni libere:" + PostazioneLavoro +
        		   "| studenti Tipologia(1) in attesa: " + codaT +
        		   "| studenti Tipologia(0) in attesa: " + codaS);

    } // end if

	else if (Tipologia==1) {
	      System.out.println(Thread.currentThread().getName()+ " Ho bisogno di due postazioni di studio");
	
		 while (PostazioneLavoro<2) {
			codaT++;
			System.out.println(Thread.currentThread().getName()+ " devo aspettare!");
			System.out.println("|postazioni libere:" + PostazioneLavoro +
					"| studenti Tipologia(1) in attesa: " + codaT +
					"| studenti Tipologia(0) in attesa: " + codaS);
		       try {
			       wait();
		       } catch (InterruptedException e) {
			e.printStackTrace();
		       }
        } // end while 	
		System.out.println(Thread.currentThread().getName()+ " occupo due postazione di studio");
		if (codaT>0) {
			codaT--;
		}
			PostazioneLavoro = PostazioneLavoro - 2;
			System.out.println("|postazioni libere:" + PostazioneLavoro +
			"| studenti Tipologia(1) in attesa: " + codaT +
			"| studenti Tipologia(0) in attesa: " + codaS);
	} //end else if
}
	
  public synchronized void rilasciaPostazione (int Tipologia) {
	  
	if (Tipologia==0) {
		System.out.println(Thread.currentThread().getName()+ " Ho finito di studiare, libero la postazione");
		PostazioneLavoro++;
		notifyAll();
		System.out.println("|postazioni libere:" + PostazioneLavoro + 
				"| studenti Tipologia(1) in attesa: " + codaT +
				"| studenti Tipologia(0) in attesa: " + codaS);
	
	} // end else if
	else if (Tipologia==1) {
	   System.out.println(Thread.currentThread().getName()+ " Ho finito di studiare, libero le due postazioni");
	   PostazioneLavoro = PostazioneLavoro + 2;
	   notifyAll();
	   System.out.println("|postazioni libere:" + PostazioneLavoro + 
			   "| studenti Tipologia(1) in attesa: " + codaT +
			   "| studenti Tipologia(0) in attesa: " + codaS);
	 
	}
  }
}
