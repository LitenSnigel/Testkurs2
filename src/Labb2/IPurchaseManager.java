package Labb2;

public interface IPurchaseManager {
    /**
     * Calculate the sum of all purchaseList for a month.
     * @param year
     * @param month
     */
    float sumOfMonth(int year, int month);
    /**
     * For a specified year, calculate each months average purchaseList.
     * @param year
     * @return Array of averages per month.
     */
    float[] monthlyAverage(int year);
    /**
     * For a specified year, calculate each category's average purchaseList.
     * @param year
     * @return Array of averages per category.
     */
    float[] yearlyAveragePerCategory(int year);
}
