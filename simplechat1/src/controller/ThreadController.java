package controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import entity.Store;
import mypackage.EchoServer;


public class ThreadController implements Runnable
{
	private int Flag_Date_1 = 0; 
	private int Flag_Date_2 = 0; 
	private int Flag_Date_3 = 0; 
	private int Flag_Date_4 = 0;
	
	private ArrayList<Store> Stores;
	private Vector<Object> Help_To_Transfer_Object_To_The_Data_Base;
	private int Number_Of_Quarter;
	public static int Flag_For_Thread = 0;
	
	private static String ago="2017-10-30 17:12:35.584"; //for 24 hours complaint & order status
	private final double ONE_MINUTE = 60 * 1000;
	
	@Override
	public void run() 
	{
		
		while(Flag_For_Thread == 0);             /* Wait Until We Connect To the DB Because If Not We Can't Go to the Method Of the EchoServer */
		
		while(true)
		{	
			/* Variables */
			LocalDate localDate = LocalDate.now();
	  		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	  		String Formatted_String = localDate.format(Formatter);
	  		String Formatted_String_2 = localDate.format(Formatter);
	  		Formatted_String = Formatted_String.substring(5,10);      /* This Is The Month And The Day */
	  		Formatted_String_2 = Formatted_String_2 .substring(0,5);  /* This Is The Year */
	  		
	  		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()); //currentTime
	  		Timestamp oneAgo = Timestamp.valueOf(ago); //cast to timestamp
	  		
	  		long diff = currentTimestamp.getTime() - oneAgo.getTime(); //check if pass 1 min
	  		if (diff>=ONE_MINUTE) 
	  		{
	  			EchoServer.getAllComplaintsPass24(EchoServerController.con);
	  			ago=currentTimestamp.toString();
	  		}
	  	
	  		    
			if(Formatted_String.compareTo("01-01") == 0 && Flag_Date_1 == 0)
			{
				Flag_Date_1 = 1;
				Number_Of_Quarter = 4;
				Stores = new ArrayList<Store>();
				
				Stores = EchoServer.Get_All_Stores_For_Thread_Controller(EchoServerController.con);
				Help_To_Transfer_Object_To_The_Data_Base = new Vector<Object>();
				Help_To_Transfer_Object_To_The_Data_Base.add(Stores);                      				   /* All The Store */
				Help_To_Transfer_Object_To_The_Data_Base.add(Number_Of_Quarter);           				   /* The Number Of Quarter */
				Help_To_Transfer_Object_To_The_Data_Base.add(Formatted_String_2 + "12-31"); 			   /* The Year Right Now + Month + Day */
				EchoServer.Insert_Report_To_DB_For_All_The_Store(Help_To_Transfer_Object_To_The_Data_Base, EchoServerController.con);
				
			}
			else if(Formatted_String.compareTo("04-01") == 0 && Flag_Date_2 == 0)
			{
				Flag_Date_2 = 1;
				Number_Of_Quarter = 1;
				Stores = new ArrayList<Store>();
				
				Stores = EchoServer.Get_All_Stores_For_Thread_Controller(EchoServerController.con);
				Help_To_Transfer_Object_To_The_Data_Base = new Vector<Object>();
				Help_To_Transfer_Object_To_The_Data_Base.add(Stores);                      			/* All The Store */
				Help_To_Transfer_Object_To_The_Data_Base.add(Number_Of_Quarter);           			/* The Number Of Quarter */
				Help_To_Transfer_Object_To_The_Data_Base.add(Formatted_String_2 + "03-31"); 	    /* The Year Right Now + Month + Day */
				EchoServer.Insert_Report_To_DB_For_All_The_Store(Help_To_Transfer_Object_To_The_Data_Base, EchoServerController.con);
			}
		    else if(Formatted_String.compareTo("07-01") == 0 && Flag_Date_3 == 0)
			{
				Flag_Date_3 = 1;
				Number_Of_Quarter = 2;
				Stores = new ArrayList<Store>();
				
				Stores = EchoServer.Get_All_Stores_For_Thread_Controller(EchoServerController.con);
				Help_To_Transfer_Object_To_The_Data_Base = new Vector<Object>();
				Help_To_Transfer_Object_To_The_Data_Base.add(Stores);                      			/* All The Store */
				Help_To_Transfer_Object_To_The_Data_Base.add(Number_Of_Quarter);          			/* The Number Of Quarter */
				Help_To_Transfer_Object_To_The_Data_Base.add(Formatted_String_2 + "06-30"); 	    /* The Year Right Now + Month + Day */
				EchoServer.Insert_Report_To_DB_For_All_The_Store(Help_To_Transfer_Object_To_The_Data_Base, EchoServerController.con);
					
			}
			else if(Formatted_String.compareTo("10-01") == 0 && Flag_Date_4 == 0)
			{
				Flag_Date_4 = 1;
				Number_Of_Quarter = 3;
				Stores = new ArrayList<Store>();
				
				Stores = EchoServer.Get_All_Stores_For_Thread_Controller(EchoServerController.con);
				Help_To_Transfer_Object_To_The_Data_Base = new Vector<Object>();
				Help_To_Transfer_Object_To_The_Data_Base.add(Stores);                      			/* All The Store */
				Help_To_Transfer_Object_To_The_Data_Base.add(Number_Of_Quarter);           			/* The Number Of Quarter */
				Help_To_Transfer_Object_To_The_Data_Base.add(Formatted_String_2 + "09-30"); 	    /* The Year Right Now + Month + Day */
				EchoServer.Insert_Report_To_DB_For_All_The_Store(Help_To_Transfer_Object_To_The_Data_Base, EchoServerController.con);
			}
			else
			{
				if(Formatted_String.compareTo("01-01") != 0 && Flag_Date_1 == 1)
				{
					Flag_Date_1 = 0;
				}
				else if(Formatted_String.compareTo("04-01") != 0 && Flag_Date_2 == 1)
				{
					Flag_Date_2 = 0;
				}
				else if(Formatted_String.compareTo("07-01") != 0 && Flag_Date_3 == 1)
				{
					Flag_Date_3 = 0;
				}
				else if(Formatted_String.compareTo("10-01") != 0 && Flag_Date_4 == 1)
				{
					Flag_Date_4 = 0;
				}
			 }
		}
		
	}
}
