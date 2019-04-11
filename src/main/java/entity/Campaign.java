package entity;

/**
 * @author Ebru Göksal
 */

public class Campaign {
    private Category category;
    private int minimumQuantityForDiscount;
    private Discount discount;

    public Campaign(Category category, double amount, DiscountType discountType, int minimumQuantityForDiscount) {
        this.category = category;
        this.minimumQuantityForDiscount = minimumQuantityForDiscount;
        this.discount = new Discount(discountType,amount);
        this.category.getCampaignList().add(this);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getMinimumQuantityForDiscount() {
        return minimumQuantityForDiscount;
    }

    public void setMinimumQuantityForDiscount(int minimumQuantityForDiscount) {
        this.minimumQuantityForDiscount = minimumQuantityForDiscount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
