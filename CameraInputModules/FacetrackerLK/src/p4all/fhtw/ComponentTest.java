package p4all.fhtw;

import p4all.fhtw.ICallbackListener;

public class ComponentTest implements ICallbackListener {

	public void callbackListener(final int point1_x,
            final int point1_y, final int point2_x, final int point2_y)
	{
		System.out.println("Facetracker reports NoseX="+point1_x+ " "+"NoseY="+point1_y
				            + " "+"ChinX="+point2_x+ " "+"ChinY="+point2_y);
	}
	
	/**
	 * @param args
	 */ 
	public static void main(String[] args) {

		   System.out.println("creating FacetrackerLK Plugin!"); 
		   FacetrackerLKStandalone fk=new FacetrackerLKStandalone();
 
		   System.out.println("adding callback listener."); 
		   fk.addListener(new ComponentTest());
		   
		   System.out.println("Setting Property"); 
		   fk.setRuntimePropertyValue("cameraDisplayUpdate","100");

		   System.out.println("starting plugin."); 
		   fk.start();
		   
		   for (int i=0; i<10;i++)
		   {
			   try { 
				   Thread.sleep(1000);
				   System.out.println("sending init event!"); 
				   fk.getEventListenerPort("init").receiveEvent(" ");
			   }
			   catch (InterruptedException e) {};
		   }
		   
		   System.out.println("Stopping FacetrackerLK Plugin!"); 
		   fk.stop();
	}
}
