package library;

import java.time.LocalDate;
import java.util.Objects;

public class LibraryCard {
    private String cardNumber;
    private Visitor visitor;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private boolean isActive;

    public LibraryCard(String cardNumber, Visitor visitor, LocalDate issueDate, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.visitor = visitor;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.isActive = true;
    }

    // Getters and Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public Visitor getVisitor() { return visitor; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryCard that = (LibraryCard) o;
        return Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    @Override
    public String toString() {
        return String.format("Card: %s, Visitor: %s, Active: %s, Expires: %s",
                cardNumber, visitor.getFullName(), isActive ? "Yes" : "No", expirationDate);
    }
}