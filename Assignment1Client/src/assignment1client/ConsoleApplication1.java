package assignment1client;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/**
 *
 * A console application through which 
 * users can access and modify BookEntity data.
 * 
 * @author carl + grace + frede
 * @version 1.0
 * @since 05-29-2019
 */
public class ConsoleApplication1 {

    static String url = "http://localhost:8080/Library/webresources/";
    public static void main(String[] args) throws Exception {

        System.out.println("Welcome to Assignment 1 - the console application");
        System.out.println("Fot help, type: help");
        System.out.println("________");
        String option = "start";
        while (!"quit".equals(option)) {
            System.out.println("What would you like to do?");
            getHelp();
            option = new Scanner(System.in).nextLine();
            switch (option) {
                case "help":
                    getHelp();
                    break;
                case "1":
                    get();
                    break;
                case "2":
                    displayItem();
                    break;
                case "3":
                    post();
                    break;
                case "4":
                    updateItem();
                    break;
                case "5":
                    deleteItem();
                    break;
                case "6":
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println(option + " is not a valid command");
                    break;
            }
        }
    }

    public static void getHelp() {
        System.out.println("________");
        System.out.println("1 - List all books");
        System.out.println("2 - Display a single book");
        System.out.println("3 - Add a book");
        System.out.println("4 - Update a book");
        System.out.println("5 - Delete a book");
        System.out.println("6 - Quit");
        System.out.println("________");
    }

    public static void get() throws Exception {
        HttpClientConsole http = new HttpClientConsole();
        http.sendGet(url + "books/");
    }

    public static void post() throws Exception {
        HttpClientConsole http = new HttpClientConsole();
        String title, description, isbn, author, publisher;
        System.out.println("Enter book title");
        String sptitle = new Scanner(System.in).nextLine();
        title = sptitle.replaceAll(" ", "%20");
        System.out.println("Enter book description");
        String spdescription = new Scanner(System.in).nextLine();
        description = spdescription.replaceAll(" ", "%20");
        System.out.println("Enter book isbn");
        isbn = new Scanner(System.in).nextLine();
        System.out.println("Enter book author");
        String spauthor = new Scanner(System.in).nextLine();
        author = spauthor.replaceAll(" ", "%20");
        System.out.println("Enter book publisher");
        String sppublisher = new Scanner(System.in).nextLine();
        publisher = sppublisher.replaceAll(" ", "%20");
        http.sendPost(url + "books/",title,description, isbn, author, publisher);
    }

    /**
     *
     * @throws Exception
     */
    public static void displayItem() throws Exception {
        HttpClientConsole http = new HttpClientConsole();
        String id;
        System.out.println("Which book id would you like to display?");
        id = new Scanner(System.in).nextLine();
        if (id != null) {
            http.sendGet(url + "books/" + id);
        } else {

        }
    }

    public static void updateItem() throws Exception {
        String id;
        String nTitle;
        String nDescription;
        String nISBN;
        String nAuthor;
        String nPublisher;
                System.out.println("Enter book key");
                id = new Scanner(System.in).nextLine();

                        System.out.println("Title?");
                        String title = new Scanner(System.in).nextLine();
                        if(title.isEmpty()){
                            nTitle = null;
                        }
                        else{
                            nTitle = title;
                        }
                        
                        System.out.println("Description?");
                        String description = new Scanner(System.in).nextLine();
                        if(description.isEmpty()){
                            nDescription = null;
                        }
                        else {
                            nDescription = description;
                        }
                          System.out.println("ISBN?");
                        String isbn = new Scanner(System.in).nextLine();
                        if(isbn.isEmpty()){
                            nISBN = null;
                        }
                        else{
                            nISBN = isbn;
                        }
                        
                        System.out.println("Author?");
                        String author = new Scanner(System.in).nextLine();
                      
                        if(author.isEmpty()){
                            nAuthor = null;
                        }
                        else {
                            nAuthor = author;
                        }
                          System.out.println("Publisher?");
                        String publisher = new Scanner(System.in).nextLine();
                        if(publisher.isEmpty()){
                            nPublisher = null;
                        }
                        else{
                            nPublisher = publisher;
                            
                        }              
            
        HttpClientConsole http = new HttpClientConsole();
        String encodedTitle = URLEncoder.encode(nTitle,StandardCharsets.UTF_8.toString());
        String encodedDescription = URLEncoder.encode(nDescription,StandardCharsets.UTF_8.toString());
        String encodedISBN = URLEncoder.encode(nISBN,StandardCharsets.UTF_8.toString());
        String encodedAuthor = URLEncoder.encode(nAuthor,StandardCharsets.UTF_8.toString());
        String encodedPublisher = URLEncoder.encode(nPublisher,StandardCharsets.UTF_8.toString());
        http.sendPut(url + "books/" +id,id,encodedTitle, encodedDescription, encodedISBN, encodedAuthor, encodedPublisher);
    }

    public static void deleteItem() throws IOException {
        String id;
        System.out.println("What is the ID of the book you would like to delete?");
        id = new Scanner(System.in).nextLine();

        HttpClientConsole http = new HttpClientConsole();
        try {
           http.sendDelete(url + "books/"+ id);
            System.out.println("Book deleted");
        } catch (IOException e) {
            System.out.print(e.getCause());
            System.out.println(" - ERR");
        }
    }

}
