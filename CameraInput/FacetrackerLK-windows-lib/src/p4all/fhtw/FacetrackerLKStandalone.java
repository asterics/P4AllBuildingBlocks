package p4all.fhtw;

import java.awt.Dimension;
import java.awt.Point;

import eu.asterics.component.sensor.facetrackerLK.FacetrackerLKInstance;
import eu.asterics.mw.services.AstericsErrorHandling;
import p4all.fhtw.ICallbackListener;


public class FacetrackerLKStandalone extends FacetrackerLKInstance {

	private ICallbackListener cl=null;
	
	public void addListener (ICallbackListener listener)
	{
		cl = listener; 
	} 
	
	@Override
    public void newCoordinates_callback(final int point1_x,
            final int point1_y, final int point2_x, final int point2_y)
        {
			cl.callbackListener (point1_x,point1_y,point2_x,point2_y);
        }             
	
	@Override
	public void start() {
        if (bridge.activate() == 0)
        {
     	    AstericsErrorHandling.instance.reportError(this, "Could not init Webcam");
        } 
     	else
     	{
     	   AstericsErrorHandling.instance.reportDebugInfo(this, "Webcam Facetracker activated");
		    //  if (propCameraProfile != "")
			//   bridge.loadCameraProfile(propCameraProfile+".yml");

     	   Point pos = new Point(0,0);
     	   Dimension d = new Dimension(200, 200);
		   // System.out.println("LK window position:"+ pos.x +"/"+ pos.y+" Size:"+d.width+"/"+d.height);  
		   bridge.setDisplayPosition(pos.x,pos.y,d.width,d.height);
     	} 		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

}
