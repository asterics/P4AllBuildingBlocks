/*
 *    AsTeRICS - Assistive Technology Rapid Integration and Construction Set
 *
 *
 *        d8888      88888888888       8888888b.  8888888 .d8888b.   .d8888b.
 *       d88888          888           888   Y88b   888  d88P  Y88b d88P  Y88b
 *      d88P888          888           888    888   888  888    888 Y88b.    
 *     d88P 888 .d8888b  888   .d88b.  888   d88P   888  888         "Y888b. 
 *    d88P  888 88K      888  d8P  Y8b 8888888P"    888  888            "Y88b.
 *   d88P   888 "Y8888b. 888  88888888 888 T88b     888  888    888       "888
 *  d8888888888      X88 888  Y8b.     888  T88b    888  Y88b  d88P Y88b  d88P
 * d88P     888  88888P' 888   "Y8888  888   T88b 8888888 "Y8888P"   "Y8888P"
 *
 *
 *                    homepage: http://www.asterics.org
 *
 *     This project has been partly funded by the European Commission,
 *                      Grant Agreement Number 247730
 * 
 * 
 *    License: GPL v3.0 (GNU General Public License Version 3.0)
 *                 http://www.gnu.org/licenses/gpl.html
 *
 */

package eu.asterics.mw.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import eu.asterics.mw.model.runtime.IRuntimeComponentInstance;




/*
 *    AsTeRICS - Assistive Technology Rapid Integration and Construction Set
 *
 *
 *        d8888      88888888888       8888888b.  8888888 .d8888b.   .d8888b.
 *       d88888          888           888   Y88b   888  d88P  Y88b d88P  Y88b
 *      d88P888          888           888    888   888  888    888 Y88b.    
 *     d88P 888 .d8888b  888   .d88b.  888   d88P   888  888         "Y888b. 
 *    d88P  888 88K      888  d8P  Y8b 8888888P"    888  888            "Y88b.
 *   d88P   888 "Y8888b. 888  88888888 888 T88b     888  888    888       "888
 *  d8888888888      X88 888  Y8b.     888  T88b    888  Y88b  d88P Y88b  d88P
 * d88P     888  88888P' 888   "Y8888  888   T88b 8888888 "Y8888P"   "Y8888P"
 *
 *
 *                    homepage: http://www.asterics.org
 *
 *     This project has been partly funded by the European Commission,
 *                      Grant Agreement Number 247730
 * 
 * 
 *    License: GPL v3.0 (GNU General Public License Version 3.0)
 *                 http://www.gnu.org/licenses/gpl.html
 *
 */


/**
 * This class provides Error Handling functionality the ARE and the components 
 * 
 *         Date: Aug 25, 2010
 *         Time: 11:35:35 AM
 */


public class AstericsErrorHandling implements IAstericsErrorHandling{

	public static final AstericsErrorHandling instance = 
			new AstericsErrorHandling();
	static String LOGGER_OPTIONS = ".logger";

	private static int statusObjectIndex=0;
	private static Logger logger = null; 

	private AstericsErrorHandling()
	{
		super();
	}


	/**
	 * This method is used by the components to report an error. It logs the error in "warning" logger
	 *  and sets the status of the ARE to "ERROR" to denote that an error has occurred 
	 * @param component the component instance that reports the error
	 * @param errorMsg the error message
	 */
	public void reportError(IRuntimeComponentInstance component, 
			final String errorMsg) 
	{
		System.out.println("Error:"+errorMsg);
	}

	/**
	 * This method is used by the components to report informative messages. It logs 
	 * the message in "info" logger.
	 * @param component the component instance that reports the informative message
	 * @param info the informative message
	 */
	public void reportInfo(IRuntimeComponentInstance component, String info) 
	{
		System.out.println("Info:"+info);

	}


	/**
	 * This method is used by the components to report.. Ok
	 */
	public void reportOk(IRuntimeComponentInstance component, String info) 
	{
		System.out.println("Ok:"+info);
	}


	/**
	 * This method is used by the components to report debugging messages. It logs 
	 * the message in "fine" logger. 
	 * @param component the component instance that reports the debugging message
	 * @param info the debugging message
	 */
	public void reportDebugInfo(IRuntimeComponentInstance component, 
			String info) 
	{
		System.out.println("Debug:"+info);
	}



	/**
	 * This method creates the logger. Actually there are 4 types of loggers: 
	 * "severe": logs fatal errors i.e. errors that prevent the ARE from functioning
	 * "warning": logs errors other than fatal e.g. component errors
	 * "info": logs informative messages
	 * "fine": logs debugging messages
	 * 
	 * Each logger by default also logs all messages with severity level higher than its own. 
	 * E.g. the warning logger logs warning and severe messages, the info logger logs info, 
	 * warning and severe messages etc. The same applies to the consoleHandler, i.e. by 
	 * setting its level to info, the console will also print severe and warning messages 
	 * along with info messages
	 */
	public Logger getLogger()
	{
		if (logger==null)
		{	
			logger = Logger.getLogger("AstericsLogger");


			FileHandler warningFileHandler, severeFileHandler, infoFileHandler, 
			fineFileHandler;
			ConsoleHandler consoleHandler;
			try {
				//cleanup before starting:
				logger.setUseParentHandlers(false);

				// remove and handlers that will be replaced
				Handler[] handlers = logger.getHandlers();
				for(Handler handler : handlers)
				{
				        if(handler.getClass() == ConsoleHandler.class)
				            logger.removeHandler(handler);
				}
				

				//Create handlers
				severeFileHandler =
						new FileHandler("asterics_logger_severe.log", true);
				warningFileHandler =
						new FileHandler("asterics_logger_warning.log", true);
				infoFileHandler = new FileHandler("asterics_logger.log", true);
				fineFileHandler = new FileHandler("asterics_logger_fine.log", true);
				consoleHandler = new ConsoleHandler ();

				//Set report level of handlers
				severeFileHandler.setLevel(Level.SEVERE);
				warningFileHandler.setLevel(Level.WARNING);
				infoFileHandler.setLevel(Level.INFO);
				fineFileHandler.setLevel(Level.FINE);

				//The consoleHandler prints log messaged to the console. Its 
				//severety level can be adjusted accordingly. 
				String level = getLoggerLevel();
				switch (level) {
				case "INFO":
					consoleHandler.setLevel(Level.INFO);
					break;
				case "WARNING":
					consoleHandler.setLevel(Level.WARNING);
					break;
				case "FINE":
					consoleHandler.setLevel(Level.FINE);
					break;
				case "SEVERE":
					consoleHandler.setLevel(Level.SEVERE);
					break;

				default:
					consoleHandler.setLevel(Level.INFO);
					break;
				}



				//Add handlers to the logger
				logger.addHandler(warningFileHandler);
				logger.addHandler(severeFileHandler);
				logger.addHandler(infoFileHandler);
				logger.addHandler(fineFileHandler);
				logger.addHandler(consoleHandler);


				//Create formatters for the handlers (optional)
				severeFileHandler.setFormatter(new SimpleFormatter());
				warningFileHandler.setFormatter(new SimpleFormatter());
				infoFileHandler.setFormatter(new SimpleFormatter());
				fineFileHandler.setFormatter(new SimpleFormatter());
				consoleHandler.setFormatter(new SimpleFormatter());

				logger.setLevel(Level.ALL); 
				logger.setUseParentHandlers(false);

			} catch (SecurityException e) {
				System.out.println(AstericsErrorHandling.class.getName()+
						": Error creating AstericsLogger: "+e.getMessage());
			} catch (IOException e) {
				//logger.warning(this.getClass().getName()+
				//	": Error creating AstericsLogger: "+e.getMessage());
				System.out.println(AstericsErrorHandling.class.getName()+
						": Error creating AstericsLogger: "+e.getMessage());
			}
		}

		return logger;
	}


	private String getLoggerLevel() {
		return "FINE";
	}

	private void notifyAREEventListeners(String methodName, String msg) 
	{
	}


}
