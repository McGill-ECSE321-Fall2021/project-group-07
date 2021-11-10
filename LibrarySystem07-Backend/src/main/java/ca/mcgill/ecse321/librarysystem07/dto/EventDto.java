package ca.mcgill.ecse321.librarysystem07.dto;

<<<<<<< HEAD
import ca.mcgill.ecse321.librarysystem07.model.Visitor;

=======
>>>>>>> 7534830a97ff1725b9a931b0e11703729988dd7c
public class EventDto {
    
    private String name;
	private int eventID;
	private Visitor visitor;

    public EventDto(String aName, int aEventID, Visitor aVisitor) {
        name = aName;
        eventID = aEventID;
        visitor = aVisitor;
<<<<<<< HEAD
    }
    
=======
}
>>>>>>> 7534830a97ff1725b9a931b0e11703729988dd7c
    public String getName() {
        return name;
    }

<<<<<<< HEAD
=======

>>>>>>> 7534830a97ff1725b9a931b0e11703729988dd7c
	public int getEventID() {
		return eventID;
	}

	public Visitor getVisitor() {
		return visitor;
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 7534830a97ff1725b9a931b0e11703729988dd7c
