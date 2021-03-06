package Labb2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.Date;

public class PurchaseManagerTest {

    PurchaseStoreStub stub = new PurchaseStoreStub();
    PurchaseManager cut = new PurchaseManager(stub);

    //testar månadssummering
    @Test
    void sumOfMonth() {
        stub.addPurchase(3, new Date(2019,8,10), 8f, "something", 3);
        stub.addPurchase(2, new Date(2019,8,10), 8f, "something", 3);
        stub.addPurchase(1, new Date(2016,4,2), 8f, "somethingElse", 2);
        stub.addPurchase(1, new Date(2016,4,2), 12f, "somethingElse", 2);

        assertEquals(20f, cut.sumOfMonth(2016, 4));
    }

    //testar månadsmedeltal
    @Test
    void monthlyAverage(){
        float [] array = {10,0,2,0,0,0,0,0,0,0,0,0};

        stub.addPurchase(4, new Date(2019,3,4), 31, "monthlyAverage", 4);
        stub.addPurchase(1, new Date(2019,1,1), 310, "monthlyAverage", 4);
        stub.addPurchase(4, new Date(2019,3,4), 31, "monthlyAverage", 4);

        assertArrayEquals(array, cut.monthlyAverage(2019));

}

    // testar årsaverage
    @Test
    void yearlyAverageByCategoryTest() {
        float[] testArray = {30};

        stub.addCategory(1, "Star Wars");

        stub.addPurchase(1, new Date(2019,8,10), 10, "something", 1);
        stub.addPurchase(1, new Date(2019,7,10), 30, "something", 1);
        stub.addPurchase(1, new Date(2019,4,2), 50, "somethingElse", 1);

        assertArrayEquals(testArray, cut.yearlyAveragePerCategory(2019));
    }

    // mocktestar månadssummering
    @Test
     void sumOfMonthMock() {
    PurchaseStore store = mock(PurchaseStore.class);
    PurchaseManager man = new PurchaseManager(store);
    float testSUm = 600f;

    when(store.getPurchases(new Date(2019,1,1), new Date(2019,1,31))).thenReturn(new Purchase[]{
            new Purchase(1, new Date(2019,1,4), 100, "Star Wars Merch", 1),
            new Purchase(1, new Date(2019,1,6), 200, "Star Wars Merch", 1),
            new Purchase(1, new Date(2019,1,8), 300, "Star Wars Merch", 1)
    });
    assertEquals(testSUm, man.sumOfMonth(2019,1));
}

    // fungerar ej???
    @Test
    void monthlyAverageMock () {
        PurchaseStore store = mock(PurchaseStore.class);
        PurchaseManager man2 = new PurchaseManager(store);

        float[] testArray2 = {0,0,0,20,0,30,0,0,0,0,0,0};

        when(store.getPurchases(new Date(2019,1,1), new Date(2019,12,31))).thenReturn(new Purchase[]{
                new Purchase(1, new Date(2019,2,4), 10f, "Star Wars Merch", 1),
                new Purchase(1, new Date(2019,4,6), 20f, "Star Wars Merch", 1),
                new Purchase(1, new Date(2019,5,8), 30f, "Star Wars Merch", 1)
        });

        assertArrayEquals(testArray2, man2.monthlyAverage(2019));

    }

    @Test
    void yearlyAvgMock() {
        PurchaseStore store = mock(PurchaseStore.class);
        PurchaseManager man = new PurchaseManager(store);

        float[] testArray = {10,60,100};

        when(store.getPurchases(new Date(2019,1,1), new Date(2019,12,31))).thenReturn(new Purchase[]{
                new Purchase(13, new Date(2019, 11, 21), 5f, "CategoryPYearMock", 1),
                new Purchase(1, new Date(2019, 1, 19), 3f, "CategoryPYearMock", 1),
                new Purchase(1, new Date(2019, 1, 24), 2f, "CategoryPYearMock", 1),

                new Purchase(1, new Date(2019, 1, 26), 10f, "CategoryPYearMock", 2),
                new Purchase(1, new Date(2019, 1, 20), 50f, "CategoryPYearMock", 2),

                new Purchase(1, new Date(2019, 1, 19), 100f, "CategoryPYearMock", 3)});

        when(store.getAllCategories()).thenReturn(new Category[]{
                new Category(1,"Star Wars"),
                new Category(2,"Harry Potter"),
                new Category(3,"The Matrix")
        });

        assertArrayEquals(testArray, man.yearlyAveragePerCategory(2019));
    }


        // FILIP
    //klass: PurchaseManager
//Metod: yearlyAveragePerCategory
    @Test
    public float[] yearlyAveragePerCategory(int year) {
        PurchaseStore store = mock(PurchaseStore.class);
        PurchaseManager man = new PurchaseManager(store);

        Category[] categories = store.getAllCategories();
        Date date1 = new Date(year, 1, 1);
        Date date2 = new Date(year, 12, 31);
        Purchase[] purchases = store.getPurchases(date1, date2);
        float[] avg = new float[categories.length];
        int index = 0;
        int counter = 0;


        for (Category category : categories) {
            for (Purchase purchase : purchases) {
                if (purchase.getDate().compareTo(date1) >= 0 && purchase.getDate().compareTo(date2) <= 0 && purchase.getCategoryID() == category.getId()) {
                    avg[index] = avg[index] + purchase.getAmount();
                    counter++;
                }
            }
            avg[index] = avg[index] / counter;
            index++;
            counter = 0;

        }
        return avg;
    }


    //klass: PurchaseManagerTest
//Metod: yearlyAvgMock
    @Test
    void yearlyAvgMockk() {
        PurchaseStore store = mock(PurchaseStore.class);
        PurchaseManager man = new PurchaseManager(store);

        float[] testArray = {4,30,100};

        when(store.getPurchases(new Date(2019,1,1), new Date(2019,12,31))).thenReturn(new Purchase[]{
                new Purchase(13, new Date(2019, 10, 21), 5f, "CategoryPYearMock", 1),
                new Purchase(1, new Date(2019, 1, 19), 3f, "CategoryPYearMock", 1),
                new Purchase(1, new Date(2019, 1, 24), 4f, "CategoryPYearMock", 1),

                new Purchase(1, new Date(2019, 1, 26), 10f, "CategoryPYearMock", 2),
                new Purchase(1, new Date(2019, 1, 20), 50f, "CategoryPYearMock", 2),

                new Purchase(1, new Date(2019, 1, 19), 100f, "CategoryPYearMock", 3)});

        when(store.getAllCategories()).thenReturn(new Category[]{
                new Category(1,"Star Wars"),
                new Category(2,"Harry Potter"),
                new Category(3,"The Matrix")
        });

        assertArrayEquals(testArray, man.yearlyAveragePerCategory(2019));
    }


    }


