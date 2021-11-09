package ca.mcgill.ecse321.librarysystem07.dto;

public class EventDto {
    
    private String name;
	private int eventID;
	private Visitor visitor;

    public EventDto(String aName, int aEventID, Visitor aVisitor) {
        name = aName;
        eventID = aEventID;
        visitor = aVisitor;
}
    public String getName() {
        return name;
    }


	public int getEventID() {
		return eventID;
	}

	public Visitor getVisitor() {
		return visitor;
	}
}
