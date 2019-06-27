package Labb2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase {

      /*  public String formatDate(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatDate.format(date);
        return strDate;
        } */

    private int id;
    private Date date;
    private float amount;
    private String comment;
    private int categoryID;

    public Purchase() {}

    public Purchase (int id, Date date, float amount, String comment, int categoryID) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.comment = comment;
        this.categoryID = categoryID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return ("Id: " + id + "\nDate: " + date + "\nAmount: " + amount + "\nComment: " + comment + "\nCategory: " + categoryID);
    }
}
