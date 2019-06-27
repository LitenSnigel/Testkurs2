package Labb2;

import java.util.Date;

public interface IPurchaseStore {
    /**
     * Get purchaseList from startDate to endDate.
     * @param startDate
     * @param endDate
     * @return Purchases.
     */
    Purchase[] getPurchases(Date startDate, Date endDate);
    /**
     * Get purchaseList from startDate to endDate for category catId.
     * @param startDate
     * @param endDate
     * @param catId
     * @return Purchases.
     */
    Purchase[] getPurchasesByCategory(Date startDate, Date endDate, int catId);
    /**
     * Get all categories.
     * @return Categories.
     */
    Category[] getAllCategories();
}