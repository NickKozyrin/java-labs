import java.util.List;
import java.util.Scanner;
import library.*;

public class Main {
    private static Library library = new Library("Центральная городская библиотека");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSampleData();

        while (true) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Поиск специальных палиндромов");
            System.out.println("2. Проверка надежности пароля");
            System.out.println("3. Управление библиотекой");
            System.out.println("4. Выход");
            System.out.print("Выберите задание: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    runPalindromeFinder();
                    break;
                case 2:
                    runPasswordValidator();
                    break;
                case 3:
                    runLibraryManagement();
                    break;
                case 4:
                    System.out.println("Выход из программы...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void initializeSampleData() {
        // Добавляем sample книги
        library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch",
                "Addison-Wesley", 2018, "Programming", 5));
        library.addBook(new Book("978-1617293290", "Spring in Action", "Craig Walls",
                "Manning", 2020, "Programming", 3));
        library.addBook(new Book("978-1491950357", "Programming Python", "Mark Lutz",
                "O'Reilly", 2011, "Programming", 2));
        library.addBook(new Book("978-0451524935", "1984", "George Orwell",
                "Signet Classic", 1949, "Fiction", 8));

        // Добавляем sample посетителей
        Visitor visitor1 = new Visitor("CARD001", "Иван Иванов", "+79161234567", "ivan@mail.com");
        Visitor visitor2 = new Visitor("CARD002", "Мария Петрова", "+79167654321", "maria@mail.com");

        library.registerVisitor(visitor1);
        library.registerVisitor(visitor2);
    }

    private static void runPalindromeFinder() {
        // ... существующий код ...
    }

    private static void runPasswordValidator() {
        // ... существующий код ...
    }

    private static void runLibraryManagement() {
        while (true) {
            System.out.println("\n=== УПРАВЛЕНИЕ БИБЛИОТЕКОЙ ===");
            System.out.println("1. Показать все книги");
            System.out.println("2. Поиск книг");
            System.out.println("3. Сортировка книг");
            System.out.println("4. Показать посетителей");
            System.out.println("5. Выдать книгу");
            System.out.println("6. Вернуть книгу");
            System.out.println("7. Активные займы");
            System.out.println("8. Просроченные займы");
            System.out.println("9. Назад в главное меню");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAllBooks();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    sortBooks();
                    break;
                case 4:
                    showAllVisitors();
                    break;
                case 5:
                    loanBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    showActiveLoans();
                    break;
                case 8:
                    showOverdueLoans();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showAllBooks() {
        System.out.println("\n=== ВСЕ КНИГИ ===");
        library.getAllBooks().forEach(System.out::println);
    }

    private static void searchBooks() {
        System.out.println("\n=== ПОИСК КНИГ ===");
        System.out.println("1. По названию");
        System.out.println("2. По автору");
        System.out.println("3. По жанру");
        System.out.println("4. По издателю");
        System.out.println("5. По году");
        System.out.println("6. По диапазону лет");
        System.out.print("Выберите тип поиска: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Введите название: ");
                String title = scanner.nextLine();
                library.searchByTitle(title).forEach(System.out::println);
                break;
            case 2:
                System.out.print("Введите автора: ");
                String author = scanner.nextLine();
                library.searchByAuthor(author).forEach(System.out::println);
                break;
            case 3:
                System.out.print("Введите жанр: ");
                String genre = scanner.nextLine();
                library.searchByGenre(genre).forEach(System.out::println);
                break;
            case 4:
                System.out.print("Введите издателя: ");
                String publisher = scanner.nextLine();
                library.searchByPublisher(publisher).forEach(System.out::println);
                break;
            case 5:
                System.out.print("Введите год: ");
                int year = scanner.nextInt();
                library.searchByYear(year).forEach(System.out::println);
                break;
            case 6:
                System.out.print("Введите начальный год: ");
                int startYear = scanner.nextInt();
                System.out.print("Введите конечный год: ");
                int endYear = scanner.nextInt();
                library.searchByYearRange(startYear, endYear).forEach(System.out::println);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void sortBooks() {
        System.out.println("\n=== СОРТИРОВКА КНИГ ===");
        System.out.println("1. По названию");
        System.out.println("2. По автору");
        System.out.println("3. По году (возрастание)");
        System.out.println("4. По году (убывание)");
        System.out.println("5. По жанру");
        System.out.print("Выберите тип сортировки: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Book> sortedBooks;
        switch (choice) {
            case 1:
                sortedBooks = library.sortByTitle();
                break;
            case 2:
                sortedBooks = library.sortByAuthor();
                break;
            case 3:
                sortedBooks = library.sortByYear();
                break;
            case 4:
                sortedBooks = library.sortByYearDesc();
                break;
            case 5:
                sortedBooks = library.sortByGenre();
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }

        sortedBooks.forEach(System.out::println);
    }

    private static void showAllVisitors() {
        System.out.println("\n=== ВСЕ ПОСЕТИТЕЛИ ===");
        library.getAllVisitors().forEach(System.out::println);
    }

    private static void loanBook() {
        System.out.println("\n=== ВЫДАЧА КНИГИ ===");
        System.out.print("Введите ISBN книги: ");
        String isbn = scanner.nextLine();
        System.out.print("Введите номер читательского билета: ");
        String cardNumber = scanner.nextLine();

        if (library.loanBook(isbn, cardNumber)) {
            System.out.println("Книга успешно выдана!");
        } else {
            System.out.println("Ошибка: книга не найдена или недоступна, либо неверный номер билета.");
        }
    }

    private static void returnBook() {
        System.out.println("\n=== ВОЗВРАТ КНИГИ ===");
        showActiveLoans();
        System.out.print("Введите ID записи о выдаче: ");
        String recordId = scanner.nextLine();

        if (library.returnBook(recordId)) {
            System.out.println("Книга успешно возвращена!");
        } else {
            System.out.println("Ошибка: запись не найдена или книга уже возвращена.");
        }
    }

    private static void showActiveLoans() {
        System.out.println("\n=== АКТИВНЫЕ ЗАЙМЫ ===");
        List<LoanRecord> activeLoans = library.getActiveLoans();
        if (activeLoans.isEmpty()) {
            System.out.println("Нет активных займов.");
        } else {
            activeLoans.forEach(System.out::println);
        }
    }

    private static void showOverdueLoans() {
        System.out.println("\n=== ПРОСРОЧЕННЫЕ ЗАЙМЫ ===");
        List<LoanRecord> overdueLoans = library.getOverdueLoans();
        if (overdueLoans.isEmpty()) {
            System.out.println("Нет просроченных займов.");
        } else {
            overdueLoans.forEach(System.out::println);
        }
    }
}