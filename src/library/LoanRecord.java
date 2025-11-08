package library;

import java.time.LocalDate;
import java.util.Objects;

public class LoanRecord {
    private String recordId;
    private Book book;
    private LibraryCard libraryCard;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public LoanRecord(String recordId, Book book, LibraryCard libraryCard, LocalDate loanDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.book = book;
        this.libraryCard = libraryCard;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.isReturned = false;
    }

    // Getters and Setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public LibraryCard getLibraryCard() { return libraryCard; }
    public void setLibraryCard(LibraryCard libraryCard) { this.libraryCard = libraryCard; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public boolean isReturned() { return isReturned; }
    public void setReturned(boolean returned) { isReturned = returned; }

    public boolean isOverdue() {
        return !isReturned && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRecord that = (LoanRecord) o;
        return Objects.equals(recordId, that.recordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId);
    }

    @Override
    public String toString() {
        return String.format("Record: %s, Book: %s, Visitor: %s, Loan: %s, Due: %s, Returned: %s",
                recordId, book.getTitle(), libraryCard.getVisitor().getFullName(),
                loanDate, dueDate, isReturned ? returnDate.toString() : "Not returned");
    }
}