package HCL_ASS_2_COREJAVA_2300320130287_YAKSHMITTAL;


import java.util.*;
import java.io.*;
import java.time.Year;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static LibraryService service = new LibraryService();

    public static void main(String[] args) {
        service.addItem(new Book("Clean Code", 2008,
                "Robert Martin", "ISBN101"));
        service.addItem(new Magazine("Science Today", 2022, 5));
        service.addItem(new DVD("Java Tutorial", 2020, 120));

        service.addMember(new Member("Rahul", "rahul@gmail.com"));
        service.addMember(new Member("Priya", "priya@gmail.com"));

        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Item");
            System.out.println("2. Register Member");
            System.out.println("3. Borrow Item");
            System.out.println("4. Return Item");
            System.out.println("5. Join Waitlist");
            System.out.println("6. List Items");
            System.out.println("7. Search Item");
            System.out.println("8. Export Report");
            System.out.println("9. List Members");
            System.out.println("10. Recent Searches");
            System.out.println("11. Sort Items by Year");
            System.out.println("12. Item Statistics");
            System.out.println("0. Exit");

            int choice = readInt("Choice: ");

            try {
                switch (choice) {
                    case 1:
                        addItemMenu();
                        break;

                    case 2:
                        String name = readString("Name: ");
                        String email = readString("Email: ");
                        Member m = new Member(name, email);
                        service.addMember(m);
                        System.out.println("Registered: " + m);
                        break;

                    case 3:
                        service.borrowItem(
                                readInt("Item ID: "),
                                readInt("Member ID: "));
                        System.out.println("Borrow successful");
                        break;

                    case 4:
                        double fee = service.returnItem(
                                readInt("Item ID: "),
                                readInt("Days late: "));
                        System.out.println("Late fee: Rs." + fee);
                        break;

                    case 5:
                        service.joinWaitlist(
                                readInt("Item ID: "),
                                readInt("Member ID: "));
                        break;

                    case 6:
                        service.listItems().forEach(System.out::println);
                        break;

                    case 7:
                        String query = readString("Search title: ");
                        service.search(query).forEach(System.out::println);
                        break;

                    case 8:
                        service.exportReport("library-report.txt");
                        System.out.println("Report exported successfully");
                        break;

                    case 9:
                        service.listMembers().forEach(System.out::println);
                        break;

                    case 10:
                        service.showRecentSearches();
                        break;

                    case 11:
                        service.sortByYear().forEach(System.out::println);
                        break;

                    case 12:
                        service.showStatistics();
                        break;

                    case 0:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice");
                }
            } catch (LibraryException | IllegalArgumentException
                     | IllegalStateException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    static void addItemMenu() {
        System.out.println("1. Book  2. Magazine  3. DVD");

        int type = readInt("Type: ");
        String title = readString("Title: ");
        int year = readInt("Year: ");

        LibraryItem item;

        switch (type) {
            case 1:
                item = new Book(title, year,
                        readString("Author: "),
                        readString("ISBN: "));
                break;

            case 2:
                item = new Magazine(title, year,
                        readInt("Issue Number: "));
                break;

            case 3:
                item = new DVD(title, year,
                        readInt("Duration (minutes): "));
                break;

            default:
                System.out.println("Invalid type");
                return;
        }

        service.addItem(item);
        System.out.println("Added: " + item);
    }

    static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    static String readString(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }
}

// ================= INTERFACE =================

interface Borrowable {
    boolean isAvailable();
    void borrow();
    void returnItem();
}

// ================= ABSTRACT CLASS =================

abstract class LibraryItem
        implements Borrowable, Comparable<LibraryItem> {

    private static int counter = 1001;

    private final int id;
    private String title;
    private int year;
    private boolean available = true;
    private Integer borrowedBy;

    public LibraryItem(String title, int year) {
        id = counter++;
        setTitle(title);
        setYear(year);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Integer getBorrowedBy() {
        return borrowedBy;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Invalid title");

        this.title = title.trim();
    }

    public void setYear(int year) {
        if (year < 1500 || year > Year.now().getValue())
            throw new IllegalArgumentException("Invalid year");

        this.year = year;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void borrow() {
        if (!available)
            throw new ItemNotAvailableException(
                    "Item already borrowed");

        available = false;
    }

    public void borrowBy(int memberId) {
        borrow();
        borrowedBy = memberId;
    }

    @Override
    public void returnItem() {
        if (available)
            throw new ItemAlreadyIssuedException(
                    "Item is not borrowed");

        available = true;
        borrowedBy = null;
    }

    public abstract double lateFee(int daysLate);

    @Override
    public int compareTo(LibraryItem other) {
        return title.compareToIgnoreCase(other.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LibraryItem)) return false;
        return id == ((LibraryItem) obj).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ID=" + id +
                ", Title=" + title +
                ", Year=" + year +
                ", Available=" + available;
    }
}

// ================= BOOK =================

class Book extends LibraryItem {

    private String author;
    private final String isbn;

    public Book(String title, int year,
                String author, String isbn) {
        super(title, year);

        if (author == null || author.trim().isEmpty())
            throw new IllegalArgumentException("Invalid author");

        if (isbn == null || isbn.trim().isEmpty())
            throw new IllegalArgumentException("Invalid ISBN");

        this.author = author.trim();
        this.isbn = isbn.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public double lateFee(int daysLate) {
        return Math.max(0, daysLate) * 2.0;
    }

    @Override
    public String toString() {
        return "Book [" + super.toString() +
                ", Author=" + author +
                ", ISBN=" + isbn + "]";
    }
}

// ================= MAGAZINE =================

class Magazine extends LibraryItem {

    private int issueNumber;

    public Magazine(String title, int year, int issueNumber) {
        super(title, year);

        if (issueNumber <= 0)
            throw new IllegalArgumentException("Invalid issue number");

        this.issueNumber = issueNumber;
    }

    @Override
    public double lateFee(int daysLate) {
        return Math.max(0, daysLate) * 1.0;
    }

    @Override
    public String toString() {
        return "Magazine [" + super.toString() +
                ", Issue=" + issueNumber + "]";
    }
}

// ================= DVD =================

class DVD extends LibraryItem {

    private int durationMinutes;

    public DVD(String title, int year, int durationMinutes) {
        super(title, year);

        if (durationMinutes <= 0)
            throw new IllegalArgumentException("Invalid duration");

        this.durationMinutes = durationMinutes;
    }

    @Override
    public double lateFee(int daysLate) {
        return Math.max(0, daysLate) * 5.0;
    }

    @Override
    public String toString() {
        return "DVD [" + super.toString() +
                ", Duration=" + durationMinutes + " min]";
    }
}

// ================= MEMBER =================

class Member {

    private static int counter = 1001;

    private final int id;
    private String name;
    private String email;

    public Member(String name, String email) {
        id = counter++;
        setName(name);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Invalid name");

        this.name = name.trim();
    }

    public void setEmail(String email) {
        if (email == null ||
                !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new IllegalArgumentException("Invalid email");

        this.email = email.trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Member)) return false;
        return id == ((Member) obj).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Member [ID=" + id +
                ", Name=" + name +
                ", Email=" + email + "]";
    }
}

// ================= CUSTOM EXCEPTIONS =================

class LibraryException extends RuntimeException {
    public LibraryException(String message) {
        super(message);
    }

    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}

class ItemNotFoundException extends LibraryException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

class MemberNotFoundException extends LibraryException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}

class ItemNotAvailableException extends LibraryException {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}

class ItemAlreadyIssuedException extends LibraryException {
    public ItemAlreadyIssuedException(String message) {
        super(message);
    }
}

class BorrowLimitExceededException extends LibraryException {
    public BorrowLimitExceededException(String message) {
        super(message);
    }
}

// ================= GENERIC REPOSITORY =================

interface Repository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    boolean deleteById(ID id);
}

class InMemoryRepository<T, ID> implements Repository<T, ID> {

    private final Map<ID, T> data = new HashMap<>();
    private final java.util.function.Function<T, ID> idExtractor;

    public InMemoryRepository(
            java.util.function.Function<T, ID> idExtractor) {
        this.idExtractor = Objects.requireNonNull(idExtractor);
    }

    @Override
    public void save(T entity) {
        Objects.requireNonNull(entity);
        data.put(idExtractor.apply(entity), entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public boolean deleteById(ID id) {
        return data.remove(id) != null;
    }
}

// ================= GENERIC UTILITIES =================

class CollectionUtils {

    public static <T extends Comparable<T>> void sortAndPrint(
            List<T> list) {
        Collections.sort(list);
        list.forEach(System.out::println);
    }

    public static <T extends Comparable<T>> T maxOf(
            List<T> list) {
        if (list == null || list.isEmpty())
            throw new IllegalArgumentException("Empty list");

        return Collections.max(list);
    }
}

// ================= LRU CACHE =================

class SearchCache extends LinkedHashMap<String, String> {

    private static final int CAPACITY = 5;

    public SearchCache() {
        super(8, 0.75f, true);
    }

    @Override
    protected boolean removeEldestEntry(
            Map.Entry<String, String> eldest) {
        return size() > CAPACITY;
    }
}

// ================= LIBRARY SERVICE =================

class LibraryService {

    private final Repository<LibraryItem, Integer> items =
            new InMemoryRepository<>(LibraryItem::getId);

    private final Repository<Member, Integer> members =
            new InMemoryRepository<>(Member::getId);

    private final Set<String> isbnSet = new HashSet<>();

    private final Map<Integer, Queue<Integer>> waitlists =
            new HashMap<>();

    private final Map<Integer, Integer> borrowCount =
            new HashMap<>();

    private final SearchCache cache = new SearchCache();

    private static final int MAX_BORROW = 3;

    public void addItem(LibraryItem item) {

        if (item instanceof Book) {
            String isbn = ((Book) item).getIsbn()
                    .toLowerCase(Locale.ROOT);

            if (!isbnSet.add(isbn))
                throw new IllegalArgumentException(
                        "Duplicate ISBN");
        }

        items.save(item);
    }

    public void addMember(Member member) {
        members.save(member);
    }

    public List<LibraryItem> listItems() {
        return items.findAll();
    }

    public List<Member> listMembers() {
        return members.findAll();
    }

    private LibraryItem findItem(int id) {
        return items.findById(id)
                .orElseThrow(() ->
                        new ItemNotFoundException(
                                "Item " + id + " not found"));
    }

    private Member findMember(int id) {
        return members.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException(
                                "Member " + id + " not found"));
    }

    public void borrowItem(int itemId, int memberId) {

        LibraryItem item = findItem(itemId);
        findMember(memberId);

        if (!item.isAvailable())
            throw new ItemNotAvailableException(
                    "Item already borrowed");

        int count = borrowCount.getOrDefault(memberId, 0);

        if (count >= MAX_BORROW)
            throw new BorrowLimitExceededException(
                    "Maximum 3 items allowed");

        item.borrowBy(memberId);
        borrowCount.put(memberId, count + 1);
    }

    public double returnItem(int itemId, int daysLate) {

        LibraryItem item = findItem(itemId);

        if (item.isAvailable())
            throw new ItemAlreadyIssuedException(
                    "Item is not borrowed");

        double fee = item.lateFee(daysLate);
        Integer memberId = item.getBorrowedBy();

        item.returnItem();

        if (memberId != null) {
            int count = borrowCount.getOrDefault(memberId, 0);

            if (count <= 1)
                borrowCount.remove(memberId);
            else
                borrowCount.put(memberId, count - 1);
        }

        Queue<Integer> queue = waitlists.get(itemId);

        if (queue != null && !queue.isEmpty()) {
            int nextMember = queue.poll();

            System.out.println(
                    "WAITLIST NOTIFICATION: Member " +
                            nextMember + " is next for " +
                            item.getTitle());

            if (queue.isEmpty())
                waitlists.remove(itemId);
        }

        return fee;
    }

    public void joinWaitlist(int itemId, int memberId) {

        LibraryItem item = findItem(itemId);
        findMember(memberId);

        if (item.isAvailable()) {
            System.out.println("Item available. Borrow directly.");
            return;
        }

        Queue<Integer> queue = waitlists.computeIfAbsent(
                itemId, k -> new ArrayDeque<>());

        if (queue.contains(memberId)) {
            System.out.println("Already on waitlist");
            return;
        }

        queue.offer(memberId);
        System.out.println("Added to waitlist");
    }

    public List<LibraryItem> search(String title) {

        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Invalid search");

        List<LibraryItem> result = new ArrayList<>();

        for (LibraryItem item : items.findAll()) {
            if (item.getTitle().toLowerCase(Locale.ROOT)
                    .contains(title.toLowerCase(Locale.ROOT))) {
                result.add(item);
            }
        }

        Collections.sort(result);

        cache.put(title, "Found " + result.size() + " items");

        return result;
    }

    public void showRecentSearches() {
        cache.forEach((key, value) ->
                System.out.println(key + " => " + value));
    }

    public List<LibraryItem> sortByYear() {

        List<LibraryItem> result = items.findAll();

        result.sort(
                Comparator.comparingInt(LibraryItem::getYear)
                        .reversed()
                        .thenComparing(LibraryItem::getTitle)
        );

        return result;
    }

    public void showStatistics() {

        Map<String, Integer> stats = new HashMap<>();

        for (LibraryItem item : items.findAll()) {
            String type = item.getClass().getSimpleName();

            stats.merge(type, 1, Integer::sum);
        }

        stats.forEach((type, count) ->
                System.out.println(type + ": " + count));
    }

    public void exportReport(String filename) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filename))) {

            writer.write("===== LIBRARY REPORT =====");
            writer.newLine();

            writer.write("Total Items: " + items.findAll().size());
            writer.newLine();

            writer.write("Total Members: " + members.findAll().size());
            writer.newLine();

            writer.write("\nITEMS");
            writer.newLine();

            for (LibraryItem item : items.findAll()) {
                writer.write(item.toString());
                writer.newLine();
            }

            writer.write("\nMEMBERS");
            writer.newLine();

            for (Member member : members.findAll()) {
                writer.write(member.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new LibraryException(
                    "Report export failed", e);
        }
    }
}
