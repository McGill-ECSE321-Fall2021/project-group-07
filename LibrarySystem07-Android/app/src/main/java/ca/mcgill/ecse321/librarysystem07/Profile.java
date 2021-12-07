package ca.mcgill.ecse321.librarysystem07;

public class Profile {

    // ATTRIBUTES
    private float balance;
    private int demeritPoints;
    private String address;
    private	String name;
    private String username;
    private int libraryCardId;
    private String typeOfAccount;

    public Profile(String aTypeOfAccount, String aName, String aUsername, String aAddress, int aLibraryCardID) {

        name = aName;
        username = aUsername;
        address = aAddress;
        libraryCardId = aLibraryCardID;
        typeOfAccount = aTypeOfAccount;
        demeritPoints = 0;
        balance = 0;

    }

    public int getLibraryCardId() {
        return libraryCardId;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getDemeritPoints() {
        return demeritPoints;
    }

    public float getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
