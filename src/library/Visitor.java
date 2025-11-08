package library;

import java.util.Objects;

public class Visitor {
    private String cardNumber;
    private String fullName;
    private String phone;
    private String email;

    public Visitor(String cardNumber, String fullName, String phone, String email) {
        this.cardNumber = cardNumber;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visitor visitor = (Visitor) o;
        return Objects.equals(cardNumber, visitor.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    @Override
    public String toString() {
        return String.format("Card: %s, Name: %s, Phone: %s, Email: %s",
                cardNumber, fullName, phone, email);
    }
}