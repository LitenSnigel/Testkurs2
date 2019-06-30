package Labb2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mainProgram {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    Category cheese = new Category(1, "A category for different cheese purchaseList");
    Purchase attempt = new Purchase(1, new Date(), 10, "Herrgårdsost", 1);

        System.out.println(attempt.toString());
        System.out.println();
        System.out.println(cheese.toString());

        PurchaseStore store = new PurchaseStore();
        PurchaseManager ps = new PurchaseManager(store);
        float[] svar = ps.monthlyAverage(2019);
        for (float s: svar
             ) {
            System.out.println(s);

        }

    }


}


