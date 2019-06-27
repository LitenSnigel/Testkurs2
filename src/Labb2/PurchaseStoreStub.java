package Labb2;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class PurchaseStoreStub extends PurchaseStore {

    ArrayList<Purchase> purchaseList;
    ArrayList<Category> categoryList;

    public PurchaseStoreStub () {
        purchaseList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    public Purchase[] getPurchases(Date startDate, Date endDate) {

        ArrayList<Purchase> getPurchasesArray = new ArrayList<>();
        // jämför datumen med compareto, -1 blir resultat om datumet är innan det man jämfört med
        for (int i = 0; i < purchaseList.size(); i++) {
            if (purchaseList.get(i).getDate().compareTo(startDate) >= 0 && purchaseList.get(i).getDate().compareTo(endDate) <= 0) {
                getPurchasesArray.add(purchaseList.get(i));
            }
        }
            // skapa ny arraylist och fyll den med de purchase-objekt som skall returneras
            Purchase[] purchasesArray = new Purchase[getPurchasesArray.size()];
            getPurchasesArray.toArray(purchasesArray);
            return purchasesArray;
        }

    public Purchase[] getPurchasesByCategory(Date startDate, Date endDate, int catId) {
        // kalla på tidigare metod och jämför sedan mot kategori-id
        Purchase[] purchasesArray = getPurchases(startDate, endDate);
        ArrayList<Purchase> tempArray = new ArrayList<>();
        for (int i = 0; i < purchasesArray.length; i++) {
            if (purchasesArray[i].getId() == catId) {
                tempArray.add(purchasesArray[i]);
            }
        }
        //lägg över värderna från temparraylist till den returnerbara arrayen
        Purchase[] getPurchasesArray = new Purchase[tempArray.size()];
        tempArray.toArray(getPurchasesArray);
        return getPurchasesArray;
    }

    public Category[] getAllCategories() {
        Category[] categories = new Category[categoryList.size()];
        categoryList.toArray(categories);
        return categories;
    }

    public void addPurchase (int Id, Date date, float Amount, String comment, int CategoryId) {
        Purchase onePurchase = new Purchase(Id,date,Amount,comment,CategoryId);
        purchaseList.add(onePurchase);
    }

    public void addCategory (int id, String description){
        Category oneCategory = new Category(id, description);
        categoryList.add(oneCategory);
    }
}