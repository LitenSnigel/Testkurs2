package Labb2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class PurchaseManager implements IPurchaseManager {

    public PurchaseStore store;

    public PurchaseManager(PurchaseStore p) {
        store = p;
    }

    public int numberOfDays(int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else if (month == 2) {
            return 28;
        }
        return 0;
    }

    @Override
    public float sumOfMonth(int year, int month) {
        Purchase[] sumOfMonth;
        int endDay = numberOfDays(month);
        sumOfMonth = store.getPurchases(new Date(year, month, 1), new Date(year, month, endDay));
        float sumTotal = 0;
        for (int i = 0; i < sumOfMonth.length; i++) {
            sumTotal += sumOfMonth[i].getAmount();
        }
        return sumTotal;
    }

    @Override
    public float[] monthlyAverage(int year) {

        int counter = 1;
        float[] monthAverage = new float[12];
        for (int i = 0; i < monthAverage.length; i++) {
            float sum = sumOfMonth(year, counter);
            float average = sum/numberOfDays(counter);
            monthAverage[i] = average;
            counter++;
        }
        return monthAverage;
    }

    @Override
    public float[] yearlyAveragePerCategory(int year) {
        Category[] categories = store.getAllCategories();
        ArrayList<Float> fakeYearlyAverage = new ArrayList<>();

        for (int i = 0; i < categories.length; i++) {
            int categoryID = categories[i].getId();
            Purchase[] purchasePerCategory = store.getPurchasesByCategory(new Date(year, 1,1), new Date(year,12,31),categoryID);
            int numberOfPurchase = purchasePerCategory.length;
            float amount = 0;

            for (int j = 0; j < purchasePerCategory.length; j++) {
                amount += purchasePerCategory[j].getAmount();
            }
            float categoryAverage;
            if (numberOfPurchase != 0) {
                categoryAverage = amount/numberOfPurchase;
            } else {
                categoryAverage = 0;
            }

            fakeYearlyAverage.add(categoryAverage);
        }

        float[] yearlyAverage = new float[fakeYearlyAverage.size()];

        for (int i = 0; i < yearlyAverage.length; i++) {
            yearlyAverage[i] = fakeYearlyAverage.get(i);
        }

        return yearlyAverage;
    }
}
