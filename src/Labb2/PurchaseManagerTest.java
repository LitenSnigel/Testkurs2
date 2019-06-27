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


    @Test
    void sumOfMonth() {
        stub.addPurchase(3, new Date(2019,8,10), 8f, "something", 3);
        stub.addPurchase(2, new Date(2019,8,10), 8f, "something", 3);
        stub.addPurchase(1, new Date(2016,4,2), 8f, "somethingElse", 2);
        stub.addPurchase(1, new Date(2016,4,2), 12f, "somethingElse", 2);

        assertEquals(20f, cut.sumOfMonth(2016, 4));
    }

    @Test
    void monthlyAverage(){
        float [] array = {0,0,1,0,0,0,0,0,0,0,0,0};

        stub.addPurchase(4, new Date(2019,3,4), 31, "monthlyAverage", 4);



        assertArrayEquals(array, cut.monthlyAverage(2019));     //jämför expected med avg för 2019

}

    @Test
    void yearlyAverageByCategoryTest() {
        float[] testArray = {30};

        stub.addCategory(1, "Star Wars");

        stub.addPurchase(1, new Date(2019,8,10), 10, "something", 1);
        stub.addPurchase(2, new Date(2019,7,10), 30, "something", 1);
        stub.addPurchase(3, new Date(2019,4,2), 50, "somethingElse", 1);

        assertArrayEquals(testArray, cut.yearlyAveragePerCategory(2019));
    }

    @Test
     void sumOfMonthMock() {
    PurchaseStore store = mock(PurchaseStore.class);
    PurchaseManager man = new PurchaseManager(store);

    when(store.getPurchases(new Date(2019,1,1), new Date(2019,1,31))).thenReturn(new Purchase[]{
            new Purchase(1, new Date(2019,1,4), 100, "Star Wars Merch", 1),
            new Purchase(1, new Date(2019,1,6), 200, "Star Wars Merch", 1),
            new Purchase(1, new Date(2019,1,8), 300, "Star Wars Merch", 1)
    });
    assertEquals(600f, man.sumOfMonth(2019,1));
}
    @Test
    void monthlyAverageMock () {
        PurchaseStore store = mock(PurchaseStore.class);
        PurchaseManager man = new PurchaseManager(store);

        float[] testArray2 = {0,0,0,20,0,30,0,0,0,0,0,0};

        when(store.getPurchases(new Date(2019,1,1), new Date(2019,12,31))).thenReturn(new Purchase[]{
                new Purchase(1, new Date(2019,2,4), 10f, "Star Wars Merch", 1),
                new Purchase(1, new Date(2019,4,6), 20f, "Star Wars Merch", 1),
                new Purchase(1, new Date(2019,5,8), 30f, "Star Wars Merch", 1)
        });

        assertArrayEquals(testArray2, man.monthlyAverage(2019));

    }

    }
