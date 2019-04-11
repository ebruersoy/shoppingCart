package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ebru Göksal
 */

public class Product {
    private String title;
    private double price;
    private double TotalDiscount;
    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Campaign> findAllCampaigns(){
        List<Campaign> campaigns = new ArrayList<>();
        Category currentCategory = this.category;
        while(currentCategory != null){
            currentCategory.getCampaignList().stream().collect(Collectors.toCollection(() -> campaigns));
            currentCategory = currentCategory.getParentCategory();
        }
        return campaigns;
    }

    public double getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        TotalDiscount = totalDiscount;
    }
}
