package project;

import java.util.ArrayList;
import java.util.Date;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;

public class Access {

	
	public Access() {
		Event even = new Event();
		
		even.setSummary("Appoitment");
		even.setLocation("Somewhere");
		
		
		ArrayList<EventAttendee> attendee = new  ArrayList<EventAttendee>();
		attendee.add(new EventAttendee().setEmail("attendeeEmail"));
		Date date=new Date();
		DateTime start = new DateTime(date);
	
	}
	public void setUp(){
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jacksonFactory=new JacksonFactory();
	}
}
