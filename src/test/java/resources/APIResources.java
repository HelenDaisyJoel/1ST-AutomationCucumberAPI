package resources;

public enum APIResources {
	AddEncoderAPI("/prism/v1/video/videoEncoder"),
	GetEncoderAPI("/prism/v1/video/{name}"),
	DeleteEncoderAPI("/prism/v1/video/{name}"),
	ListEncoderAPI("/prism/v1/video/videoEncoders"),
	UpdateEncoderAPI("/prism/v1/video"),
//	DisableEncoderAPI("/prism/v1/video"),
//	ReenableEncoderAPI("/prism/v1/video"),
	DisableEncoderAPI("/prism/v1/video/{name}:disable"),
	ReenableEncoderAPI("/prism/v1/video/{name}:reenable"),
	AddDecoderAPI("/prism/v1/video/videoDecoder"),
	GetDecoderAPI("/prism/v1/video/{name}"),
	DeleteDecoderAPI("/prism/v1/video/{name}"),
	ListDecoderAPI("/prism/v1/video/videoDecoders"),
	UpdateDecoderAPI("/prism/v1/video"),
//	DisableDecoderAPI("/prism/v1/video/"),
//	ReenableDecoderAPI("/prism/v1/video/"),
	DisableDecoderAPI("/prism/v1/video/{name}:disable"),
	ReenableDecoderAPI("/prism/v1/video/{name}:reenable"),
	
	AddSourceAPI("/prism/v1/video/videoSource"),
	GetSourceAPI("/prism/v1/video/{name}"),
	ListSourceAPI("/prism/v1/video/videoSources"),
	UpdateSourceAPI("/prism/v1/video"),
//	DisableSourceAPI("/prism/v1/video/"),
//	ReenableSourceAPI("/prism/v1/video/"),
	DisableSourceAPI("/prism/v1/video/{name}:disable"),
	ReenableSourceAPI("/prism/v1/video/{name}:reenable"),
	
	
	AddChannelAPI("/prism/v1/video/videoChannel"),
	GetChannelAPI("/prism/v1/video/{name}"),
	DeleteChannelAPI("/prism/v1/video/{name}"),
	ListChannelAPI("/prism/v1/video/videoChannels"),
	UpdatePhenixChannelAPI("/prism/v1/video"),
	UpdateDolbyChannelAPI("/prism/v1/video"),
	UpdateNoneChannelAPI("/prism/v1/video"),
//	DisableChannelAPI("/prism/v1/video/"),
//	ReenableChannelAPI("/prism/v1/video/"),
	DisableChannelAPI("/prism/v1/video/{name}:disable"),
	ReenableChannelAPI("/prism/v1/video/{name}:reenable"),
	
	
	AddBookingAPI("/prism/v1/video/videoBooking"),
	GetBookingAPI("/prism/v1/video/{name}"),
	DeleteBookingAPI("/prism/v1/video/{name}"),
	ListBookingAPI("/prism/v1/video/videoBookings"),
	UpdateBookingAPI("/prism/v1/video"),
//	StartBookingAPI("/prism/v1/video"),
//	StopBookingAPI("/prism/v1/video"),
//	DisableBookingAPI("/prism/v1/video/"),
//	ReenableBookingAPI("/prism/v1/video/");
	StartBookingAPI("/prism/v1/video/booking/{name}:start"),
    StopBookingAPI("/prism/v1/video/booking/{name}:stop"),
    DisableBookingAPI("/prism/v1/video/booking/{name}:disable"),
    ReenableBookingAPI("/prism/v1/video/booking/{name}:reenable");
	
		
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	public String getResource()
	{
		return resource;
	}

}

