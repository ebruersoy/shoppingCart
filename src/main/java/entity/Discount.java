package entity;

/**
 * @author Ebru Göksal
 */
public class Discount {
    private DiscountType type;
    private double amount;

    public Discount(DiscountType type, double amount) {
        this.type = type;
        this.amount = amount;
    }


    public double applyDiscount(double totalAmount) {
        if(DiscountType.AMOUNT.equals(this.getType())){
            return totalAmount - this.getAmount();
        }
        if(DiscountType.RATE.equals(this.getType())){
            return totalAmount - totalAmount * this.getAmount() / 100;
        }
        return 0.00;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
