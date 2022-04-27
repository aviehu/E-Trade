package Domain.Users.Users;

public class SupplyAddress {
    private String city;
    private String street;
    private int streetNum;
    private int apartmentNum;

    public SupplyAddress(String city, String street, int streetNum, int apartmentNum) {
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
        this.apartmentNum = apartmentNum;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public int getApartmentNum() {
        return apartmentNum;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public void setApartmentNum(int apartmentNum) {
        this.apartmentNum = apartmentNum;
    }
}
