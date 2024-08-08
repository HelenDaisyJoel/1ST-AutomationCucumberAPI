package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.CreateBooking;
import pojo.ForeignIdentifier;


public class VideoBooking {
	
	public CreateBooking createBooking(String Parent, String SourceName, String ChannelName, String StartTime, String EndTime, String foreignIDType, String foreignIDValue) {
		CreateBooking createBooking = new CreateBooking();
		createBooking.setParent(Parent);
		createBooking.setVideo_source_name(SourceName);
		createBooking.setVideo_channel_name(ChannelName);
		createBooking.setStart_time(StartTime);
		createBooking.setEnd_time(EndTime);
		ForeignIdentifier foreignIdentifier = new ForeignIdentifier();
        foreignIdentifier.setType(foreignIDType);
        List<String> foreignIDValues = new ArrayList<>();
        foreignIDValues.add(foreignIDValue);
        
        foreignIdentifier.setValues(foreignIDValues);
		
		
		return createBooking;
		
		

	
	
	

}
}
