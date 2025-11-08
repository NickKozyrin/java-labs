package library;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private String name;
    private Map<String, Book> books; // ISBN -> Book
    private Map<String, Visitor> visitors; // Card Number -> Visitor
    private Map<String, LibraryCard> libraryCards; // Card Number -> LibraryCard
    private Map<String, LoanRecord> loanRecords; // Record ID -> LoanRecord
    private int loanDurationDays;

    public Library(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.visitors = new HashMap<>();
        this.libraryCards = new HashMap<>();
        this.loanRecords = new HashMap<>();
        this.loanDurationDays = 30;
    }

    // Book Management
    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    public void updateBookCopies(String isbn, int newTotalCopies) {
        Book book = books.get(isbn);
        if (book != null) {
            int currentAvailable = book.getAvailableCopies();
            int difference = newTotalCopies - book.getTotalCopies();
            book.setTotalCopies(newTotalCopies);
            book.setAvailableCopies(currentAvailable + difference);
        }
    }

    // Visitor Management
    public void registerVisitor(Visitor visitor) {
        visitors.put(visitor.getCardNumber(), visitor);
    }

    public void issueLibraryCard(LibraryCard card) {
        libraryCards.put(card.getCardNumber(), card);
    }

    public void deactivateLibraryCard(String cardNumber) {
        LibraryCard card = libraryCards.get(cardNumber);
        if (card != null) {
            card.setActive(false);
        }
    }

    // Loan Management
    public boolean loanBook(String isbn, String cardNumber) {
        Book book = books.get(isbn);
        LibraryCard card = libraryCards.get(cardNumber);

        if (book == null || card == null || !card.isActive() || card.isExpired()) {
            return false;
        }

        if (book.getAvailableCopies() > 0) {
            book.decreaseAvailableCopies();

            String recordId = UUID.randomUUID().toString();
            LocalDate loanDate = LocalDate.now();
            LocalDate dueDate = loanDate.plusDays(loanDurationDays);

            LoanRecord record = new LoanRecord(recordId, book, card, loanDate, dueDate);
            loanRecords.put(recordId, record);

            return true;
        }

        return false;
    }

    public boolean returnBook(String recordId) {
        LoanRecord record = loanRecords.get(recordId);
        if (record != null && !record.isReturned()) {
            record.setReturned(true);
            record.setReturnDate(LocalDate.now());
            record.getBook().increaseAvailableCopies();
            return true;
        }
        return false;
    }

    // Search Methods
    public List<Book> searchByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByGenre(String genre) {
        return books.values().stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Book> searchByPublisher(String publisher) {
        return books.values().stream()
                .filter(book -> book.getPublisher().toLowerCase().contains(publisher.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByYear(int year) {
        return books.values().stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    public List<Book> searchByYearRange(int startYear, int endYear) {
        return books.values().stream()
                .filter(book -> book.getPublicationYear() >= startYear && book.getPublicationYear() <= endYear)
                .collect(Collectors.toList());
    }

    public List<Book> searchAvailableBooks() {
        return books.values().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .collect(Collectors.toList());
    }

    // Sort Methods
    public List<Book> sortByTitle() {
        return books.values().stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    public List<Book> sortByAuthor() {
        return books.values().stream()
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
    }

    public List<Book> sortByYear() {
        return books.values().stream()
                .sorted(Comparator.comparingInt(Book::getPublicationYear))
                .collect(Collectors.toList());
    }

    public List<Book> sortByYearDesc() {
        return books.values().stream()
                .sorted(Comparator.comparingInt(Book::getPublicationYear).reversed())
                .collect(Collectors.toList());
    }

    public List<Book> sortByGenre() {
        return books.values().stream()
                .sorted(Comparator.comparing(Book::getGenre))
                .collect(Collectors.toList());
    }

    // Reports
    public List<LoanRecord> getActiveLoans() {
        return loanRecords.values().stream()
                .filter(record -> !record.isReturned())
                .collect(Collectors.toList());
    }

    public List<LoanRecord> getOverdueLoans() {
        return loanRecords.values().stream()
                .filter(LoanRecord::isOverdue)
                .collect(Collectors.toList());
    }

    public List<LoanRecord> getVisitorLoans(String cardNumber) {
        return loanRecords.values().stream()
                .filter(record -> record.getLibraryCard().getCardNumber().equals(cardNumber))
                .collect(Collectors.toList());
    }

    // Getters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Collection<Book> getAllBooks() { return books.values(); }
    public Collection<Visitor> getAllVisitors() { return visitors.values(); }
    public Collection<LibraryCard> getAllLibraryCards() { return libraryCards.values(); }
    public Collection<LoanRecord> getAllLoanRecords() { return loanRecords.values(); }

    public int getTotalBooksCount() { return books.size(); }
    public int getTotalVisitorsCount() { return visitors.size(); }
    public int getActiveLoansCount() { return getActiveLoans().size(); }

    public void setLoanDurationDays(int days) { this.loanDurationDays = days; }
    public int getLoanDurationDays() { return loanDurationDays; }

    @Override
    public String toString() {
        return String.format("Library: %s, Books: %d, Visitors: %d, Active Loans: %d",
                name, getTotalBooksCount(), getTotalVisitorsCount(), getActiveLoansCount());
    }
}