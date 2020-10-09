package com.application.backEnd;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
    private SimpleLongProperty id = new SimpleLongProperty();
    private SimpleStringProperty vendorCode = new SimpleStringProperty();
    private SimpleIntegerProperty monthsNumber = new SimpleIntegerProperty();
    private SimpleIntegerProperty quantity = new SimpleIntegerProperty();

    public Book() {
        id.set(0L);
        vendorCode.set("");
        monthsNumber.set(1);
        quantity.set(0);
    }

    public Book(SimpleLongProperty id, SimpleStringProperty vendorCode, SimpleIntegerProperty monthsNumber, SimpleIntegerProperty quantity) {
        this.id = id;
        this.vendorCode = vendorCode;
        this.monthsNumber = monthsNumber;
        this.quantity = quantity;
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getVendorCode() {
        return vendorCode.get();
    }

    public SimpleStringProperty vendorCodeProperty() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode.set(vendorCode);
    }

    public int getMonthsNumber() {
        return monthsNumber.get();
    }

    public SimpleIntegerProperty monthsNumberProperty() {
        return monthsNumber;
    }

    public void setMonthsNumber(int monthsNumber) {

        if(monthsNumber >= 1 && monthsNumber <= 12){
            this.monthsNumber.set(monthsNumber);
        }
        else{
            throw new IllegalArgumentException("Month can\'t be less than 1 and more than 12");
        }

    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
}
