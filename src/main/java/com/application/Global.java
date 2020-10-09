package com.application;

import com.application.backEnd.Book;
import com.application.backEnd.Books;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Global {

    public static final String extension = "*.db";
    public static String path;
    public static Books books;

    public static void errorReport(Exception e) {
        e.printStackTrace();
        TextArea output = new TextArea();
        {
            StringBuilder string = new StringBuilder();
            var i = 1;
            for (var el : e.getStackTrace()) {
                string.append(i).append(") ").append(el).append("\n\n");
                i++;
            }
            output.setText(string.toString());
        }
        output.setWrapText(true);

        BorderPane pane = new BorderPane();
        pane.setTop(new Label("Errors report"));
        pane.setCenter(output);
        pane.setBottom(new Label("Short description of error: " + e.getMessage()));
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setWidth(400);
        stage.setHeight(200);
//        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void fromListToFile() {
        StringBuilder content = new StringBuilder();
        for (var el : books.getBooks()) {
            content .append(String.format("id=%d;\n", el.getId()))
                    .append(String.format("vendorCode=%s;\n", el.getVendorCode()))
                    .append(String.format("monthsNumber=%d;\n", el.getMonthsNumber()))
                    .append(String.format("quantity=%d;\n", el.getQuantity()));
        }
        try (FileWriter writer = new FileWriter(path)) {
            writer.append(content);
            writer.flush();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void fromFileToList() {
        String[] content = new String[0];
        try {
            content = Files
                    .readString(Paths.get(path))
                    .replaceAll("\n", "")
                    .replaceAll("\r", "")
                    .split(";");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorReport(e);
        }

        books = new Books();
        var j = 0;
        for (var i = 0; i < content.length / 4; i++) {
            Book book = new Book();
            book.setId(Long.parseLong(content[j].substring((content[j].indexOf("=")) + 1)));
            j++;
            book.setVendorCode(content[j].substring((content[j].indexOf("=") + 1)));
            j++;
            book.setMonthsNumber(Integer.parseInt(content[j].substring((content[j].indexOf("=")) + 1)));
            j++;
            book.setQuantity(Integer.parseInt(content[j].substring((content[j].indexOf("=")) + 1)));
            j++;
            books.add(book);
        }
        System.out.println(Arrays.toString(books.getBooks().toArray()));
    }

    public static void newSource() {
        books = new Books(new ArrayList<Book>());
//        EditWindowController.addToTable(books);
    }

}
