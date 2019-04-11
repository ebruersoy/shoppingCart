package entity;

/**
 * @author Ebru Göksal
 */

public class Coupon {
    private Discount discount;
    private double minimumCartAmount;

    public Coupon(DiscountType discountType, double amount, double minimumCartAmount) {
        this.discount = new Discount(discountType,amount);
        this.minimumCartAmount = minimumCartAmount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getMinimumCartAmount() {
        return minimumCartAmount;
    }

    public void setMinimumCartAmount(double minimumCartAmount) {
        this.minimumCartAmount = minimumCartAmount;
    }
}
