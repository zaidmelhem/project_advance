package logger;
import java.util.Calendar;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import logIn.logIn;

public final  class logger {
		private static final logger instance = new logger();
		public String logname = "simplelog";
		protected String env = System.getProperty("user.dir");
		private static File logFile;

		public static logger getInstance(){
			return instance;
		}

		public static logger getInstance(String withName){
			instance.logname = withName;
			instance.createLogFile();
			return instance;
		}

		public void createLogFile(){
			//Determine if a logs directory exists or not.
			File logsFolder = new File(env + '/' + "logs");
			if(!logsFolder.exists()){
				//Create the directory 
				System.err.println("INFO: Creating new logs directory in " + env);
				logsFolder.mkdir();
				
			}

			//Get the current date and time
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   	Calendar cal = Calendar.getInstance();
		   	
		   	//Create the name of the file from the path and current time
			logname =  logname + '-' +  dateFormat.format(cal.getTime()) + ".log";
			logger.logFile = new File(logsFolder.getName(),logname);
			try{
				if(logFile.createNewFile()){
					//New file made
					System.err.println("INFO: Creating new log file");	
				}
			}catch(IOException e){
				System.err.println("ERROR: Cannot create log file");
				System.exit(1);
			}
		}

		private logger(){
			if (instance != null){
				//Prevent Reflection
				throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
			}
			this.createLogFile();
		}

		public static void logInfo(String message){
			try{
				Date date = new Date(); 
				FileWriter out = new FileWriter(logger.logFile, true);
				out.write("[User ID "+logIn.inpID+"] [Info] ( "+date.toString()+" )  "+message+"\n");
				out.close();
			}catch(IOException e){
				System.err.println("ERROR: Could not write to log file");
			}
		}
		
		public static void logError(String message){
			try{
				Date date = new Date();
				FileWriter out = new FileWriter(logger.logFile, true);
				out.write("[User ID "+logIn.inpID+"] [Error] ( "+date.toString()+" )  "+message+"\n");
				out.close();
			}catch(IOException e){
				System.err.println("ERROR: Could not write to log file");
			}
		}
		
		public static void logDebug(String message){
			try{
				Date date = new Date();
				FileWriter out = new FileWriter(logger.logFile, true);
				out.write("[User ID "+logIn.inpID+"] [Debug] ( "+date.toString()+" )  "+message+"\n");
				out.close();
			}catch(IOException e){
				System.err.println("ERROR: Could not write to log file");
			}
		}
		
		public static void logWarning(String message){
			try{
				Date date = new Date();
				FileWriter out = new FileWriter(logger.logFile, true);
				out.write("[User ID "+logIn.inpID+"] [Warning] ( " +date.toString()+" )  "+message+"\n");
				out.close();
			}catch(IOException e){
				System.err.println("ERROR: Could not write to log file");
			}
		}
		
		


	}
	

