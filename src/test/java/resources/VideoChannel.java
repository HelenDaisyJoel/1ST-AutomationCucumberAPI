package resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pojo.CreateChannel;
import pojo.CreateDecoder;
import pojo.DisplayName;
import pojo.ForeignIdentifier;

public class VideoChannel {
	public CreateChannel CreatePhenixChannel(String Parent,String DisplayName,String Vendor,String vendorChannelId, 
			String AutoAngle,String description,boolean createReplay,String ForeignIDType,String ForeignIDValue) {
CreateChannel channel = new CreateChannel();
channel.setParent(Parent);
DisplayName name=new DisplayName();
name.setValue(DisplayName);
channel.setDisplay_name(name);
channel.setVendor(Vendor);
channel.setVendor_channel_identifier(vendorChannelId);
channel.setAuto_angle_name(AutoAngle);
channel.setDescription(description);
channel.setCreate_replay(createReplay);
channel.setForeign_ids(generateForeignIds(ForeignIDType, ForeignIDValue));
return channel;
}

	
// Create Dolby Channel
public CreateChannel CreateDolbyChannel(String parent, String displayName, String vendor, String autoAngle,
		String connectorID, String clusterName, String height, String frameRate, String description,
		String foreignIDType, String foreignIDValue, boolean createReplay) {
	 System.out.println("Inside CreateDolbyChannel");
CreateChannel channel = new CreateChannel();
channel.setParent(parent);
DisplayName name=new DisplayName();
name.setValue(displayName);
channel.setDisplay_name(name);
channel.setVendor(vendor);
channel.setConnector_id(connectorID);
channel.setCluster_name(clusterName);
channel.setHeight(height);
channel.setFrame_rate(frameRate);
channel.setAuto_angle_name(autoAngle);
channel.setDescription(description);
channel.setCreate_replay(createReplay);
channel.setForeign_ids(generateForeignIds(foreignIDType, foreignIDValue));
return channel;
}

// Helper method to generate Foreign IDs
private static List<ForeignIdentifier> generateForeignIds(String type, String value) {
ForeignIdentifier foreignIdentifier = new ForeignIdentifier();
foreignIdentifier.setType(type);
List<String> values = new ArrayList<>();
values.add(value);

foreignIdentifier.setValues(values);
List<ForeignIdentifier> foreignIdentifiers = new ArrayList<>();
foreignIdentifiers.add(foreignIdentifier);
return foreignIdentifiers;
}
	 public CreateChannel CreateNoneChannel(String parent, String displayName, String vendor, String autoAngle,
				String decoder, String description, String foreignIdType, String foreignIdValue) 
	 {
	    	CreateChannel channel = new CreateChannel();
	    	channel.setParent(parent);

	    	DisplayName name=new DisplayName();
	    	name.setValue(displayName);
	    	channel.setDisplay_name(name);
	    	channel.setVendor(vendor);
	    	channel.setAuto_angle_name(autoAngle);
	    	channel.setDescription(description);
	    	channel.setDecoder_name(decoder);
	    	channel.setDescription(description);
//	    	channel.setState(state);
	    	
	    	ForeignIdentifier foreignIdentifier = new ForeignIdentifier();
	        foreignIdentifier.setType(foreignIdType);
	        List<String> foreignIDValues = new ArrayList<>();
	        foreignIDValues.add(foreignIdValue);
	        
	        foreignIdentifier.setValues(foreignIDValues);
	        
	        List<ForeignIdentifier> foreignIdentifiers = new ArrayList<>();
	        foreignIdentifiers.add(foreignIdentifier);
//	        foreignIdentifiers.add(foreignIdentifier1);
	        channel.setForeign_ids(foreignIdentifiers);
	    	
	    	
	        return channel;
	    }
	 
public CreateChannel UpdatePhenixChannel(String updatedDisplayName, String updatedvendorChannelId, 
	    		String UpdatedAutoAngle, String updatedDescription,String UpdatedForeignIdType,String updatedForeignIdValue, boolean createReplay) throws IOException {
	    	CreateChannel channel = new CreateChannel();
	    	DisplayName name=new DisplayName();
	    	name.setValue(updatedDisplayName);
	    	channel.setDisplay_name(name);	    	
	    	channel.setVendor_channel_identifier(updatedvendorChannelId);
	    	channel.setAuto_angle_name(UpdatedAutoAngle);
	    	channel.setDescription(updatedDescription);
	    	channel.setCreate_replay(createReplay);
	    	ForeignIdentifier foreignIdentifier = new ForeignIdentifier();
	        foreignIdentifier.setType(UpdatedForeignIdType);
	        List<String> foreignIDValues = new ArrayList<>();
	        foreignIDValues.add(updatedForeignIdValue);
	        
	        foreignIdentifier.setValues(foreignIDValues);
	        return channel;


	    }

public CreateChannel UpdateNoneChannel(String newDisplayName,String updatedAutoAngle,String updatedDecoder, 
		String updatedDescription,String updatedForeignIdType,String updatedForeignIdValue) throws IOException {
	CreateChannel channel = new CreateChannel();
	        DisplayName name = new DisplayName();
	        name.setValue(newDisplayName);
	        channel.setDisplay_name(name);
	        channel.setAuto_angle_name(updatedAutoAngle);
	        channel.setDecoder_name(updatedDecoder);
	        channel.setDescription(updatedDescription);
	        
	        ForeignIdentifier foreignIdentifier = new ForeignIdentifier();
	        foreignIdentifier.setType(updatedForeignIdType);
	        List<String> foreignIDValues = new ArrayList<>();
	        foreignIDValues.add(updatedForeignIdValue);
	        foreignIDValues.add("update");
	        foreignIdentifier.setValues(foreignIDValues);
	        return channel;
	    }
public CreateChannel UpdateDolbyChannel(String updatedVendorChannelIdentifier, String Vendor,
        String updatedAutoAngleName, String updatedConnectorID,String updatedClusterName, String updatedHeight,String updatedFrameRate, 
        String updatedDescription,String updatedForeignIdType,String updatedForeignIdValue,boolean updatedCreateReplay) throws IOException {
	CreateChannel channel = new CreateChannel();
	
	channel.setAuto_angle_name(updatedAutoAngleName);
//	channel.setVendor_channel_identifier(updatedVendorChannelIdentifier);
	channel.setConnector_id(updatedConnectorID);
	channel.setCluster_name(updatedClusterName);
	channel.setVendor_channel_identifier(updatedVendorChannelIdentifier);
	channel.setHeight(updatedHeight);
	channel.setVendor(Vendor);
	channel.setFrame_rate(updatedFrameRate);
	channel.setDescription(updatedDescription);	
	channel.setCreate_replay(updatedCreateReplay);
	ForeignIdentifier foreignIdentifier = new ForeignIdentifier();
    foreignIdentifier.setType(updatedForeignIdType);
    List<String> foreignIDValues = new ArrayList<>();
    foreignIDValues.add(updatedForeignIdValue);
    foreignIDValues.add("update");
    foreignIdentifier.setValues(foreignIDValues);
    
    return channel;


}

public CreateChannel CreateNoneChannel(String parent, String displayName, String vendor, String autoAngle,
		String connectorID, String clusterName, String height, String frameRate, String description,
		String foreignIDType, String foreignIDValue, boolean createReplay) {
	// TODO Auto-generated method stub
	return null;
}
	 
	 

}