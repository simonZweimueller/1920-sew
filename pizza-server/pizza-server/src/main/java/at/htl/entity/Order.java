package at.htl.entity;

import java.util.List;

public class Order {

    String firstname;
    String lastname;
    String email;
    String street;
    String city;
    String phonenr;
    List<CartItem> cartItemList;

    public Order(String firstname, String lastname, String email, String street, String city, String phonenr, List<CartItem> cartItemList) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.street = street;
        this.city = city;
        this.phonenr = phonenr;
        this.cartItemList = cartItemList;
    }

    public Order() {
    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public void setPhonenr(String phonenr) {
        this.phonenr = phonenr;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", phonenr='" + phonenr + '\'' +
                ", cartItemList=" + cartItemList +
                '}';
    }
}
