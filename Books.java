//Books.java
package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Books {
    private ArrayList<Book> books = new ArrayList<Book>();
    private String fileName = "BookInfo.txt";
    protected Integer numBooks = setOriginalNumBooks();

    public int setOriginalNumBooks() {

        Integer numBooks = 0;

        // reads first number in file which indicates # of books on file
        try (Scanner scan = new Scanner(new File(fileName))) {
            numBooks = Integer.parseInt(scan.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return numBooks;

    }

    public void setNumBooks(int num) {
        this.numBooks = num;
    }

    public void uploadBooks() throws FileNotFoundException {
        try (Scanner scan = new Scanner(new File(fileName))) {
            books.clear();

            // Read and skip the first line (number of books)
            scan.nextLine();

            for (int i = 0; i < numBooks; i++) {
                try {
                    // Skip any blank lines
                    String line = scan.nextLine().trim();
                    while (line.isEmpty() && scan.hasNextLine()) {
                        line = scan.nextLine().trim();
                    }

                    // Parse ID from the non-empty line we found
                    Integer ID = Integer.parseInt(line);

                    // Read strings
                    String isbn = scan.nextLine().trim();
                    String title = scan.nextLine().trim();
                    String author = scan.nextLine().trim();
                    String condition = scan.nextLine().trim();
                    String category = scan.nextLine().trim();

                    // Read prices
                    Double originalPrice = Double.parseDouble(scan.nextLine().trim());
                    Double generatedPrice = Double.parseDouble(scan.nextLine().trim());

                    // Read seller and buyer
                    String seller = scan.nextLine().trim();
                    String buyer = scan.nextLine().trim();

                    // Read sold status
                    Boolean sold = Boolean.parseBoolean(scan.nextLine().trim());

                    Book loadBook = new Book(ID, isbn, title, author, condition,
                            category, originalPrice, generatedPrice,
                            seller, buyer, sold);
                    books.add(loadBook);

                    System.out.println("Successfully loaded book: " + title);

                } catch (Exception e) {
                    System.out.println("Error reading book #" + (i + 1));
                    System.out.println("Specific error: " + e.getMessage());
                    e.printStackTrace();
                    if (scan.hasNextLine()) {
                        System.out.println("Next line in file: " + scan.nextLine());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            throw e;
        }
    }

    // allows the system to search for book based on ID number
    public Book searchBookById(Integer searchKey) {
        Book noFoundBook = new Book();

        for (int i = 0; i < numBooks; i++) {
            if (searchKey.equals(books.get(i).ID)) {
                return books.get(i);
            }
        }

        return noFoundBook;
    }

    //returns arraylist of books
    public ArrayList<Book> getBooks(){
        return books;
    }

    public void addBook(String isbn, String title, String author, String condition,
            String category, Double originalPrice, Double generatedPrice,
            String seller, String buyer, boolean sold) {

        // Generate new ID (could be based on current size + 1 or other logic)
        Integer newId = books.size() + 1;

        Book addedBook = new Book(newId, isbn, title, author, condition,
                category, originalPrice, generatedPrice,
                seller, buyer, sold);

        // Add to ArrayList
        books.add(addedBook);

        // Update number of books
        this.setNumBooks(numBooks + 1);

        // Update the file
        updateBookFile();
    }

    public void deleteBook(Integer ID) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(ID)) {
                books.remove(i);
                this.setNumBooks(numBooks - 1);
                updateBookFile();
                System.out.println("Book deleted.");
                return;
            }
        }
        System.out.println("No such book found.");
    }

    public void printBooks() {
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).title);
        }
    }

    // Add helper method to update the file
    private void updateBookFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write number of books as first line
            writer.write(numBooks.toString());
            writer.newLine();

            // Write each book's data
            for (Book book : books) {
                writer.write(book.getId().toString());
                writer.newLine();
                writer.write(book.getIsbn());
                writer.newLine();
                writer.write(book.getTitle());
                writer.newLine();
                writer.write(book.getAuthor());
                writer.newLine();
                writer.write(book.getCondition());
                writer.newLine();
                writer.write(book.getCategory());
                writer.newLine();
                writer.write(book.getOriginalPrice().toString());
                writer.newLine();
                writer.write(book.getGeneratedPrice().toString());
                writer.newLine();
                writer.write(book.getSeller());
                writer.newLine();
                writer.write(book.getBuyer() != null ? book.getBuyer() : "");
                writer.newLine();
                writer.write(book.isSold().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
