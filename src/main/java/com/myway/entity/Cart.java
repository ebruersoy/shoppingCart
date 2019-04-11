package com.myway.entity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author Ebru Göksal
 */
public class Cart {
    private DeliveryCostCalculator deliveryCostCalculator;

    private Map<Product,Integer> productQuantityMap = new HashMap<>();
    private double totalAmount;
    private double lastAmount;
    private double campaignDiscount;
    private double couponDiscount;
    private double deliveryCost;
    private Discount discount;
    private Coupon coupon;
    private Map<Category, List<Product>> categoryListMap = new HashMap<>();

    public void addItem(Product product, Integer count){
        Integer currentCount = productQuantityMap.get(product);
        if(currentCount != null ){
            productQuantityMap.put(product,count+currentCount);
        }else {
            productQuantityMap.put(product, count);
        }
        totalAmount += product.getPrice() * count;
        lastAmount = totalAmount;
        categoryListMap.clear();
        categoryListMap = productQuantityMap.keySet().stream()
                .collect(groupingBy(Product::getCategory));
    }

    public void applyDiscount(List<Discount> discountList, Product product, int quantity ){
        if(discountList.isEmpty()){
            return;
        }
        double minAmount = Double.MAX_VALUE;
        double totalAmountOfProduct = product.getPrice() * quantity;

        for (Discount var : discountList) {
            double appliedDiscount = var.applyDiscount(totalAmountOfProduct);
            if(appliedDiscount < minAmount){
                minAmount = appliedDiscount;
                this.discount = var;
            }
        }
        campaignDiscount += totalAmountOfProduct - minAmount;
        product.setTotalDiscount(totalAmountOfProduct - minAmount);
        lastAmount = totalAmount - campaignDiscount;
    }


    public void applyCoupon(Coupon coupon){
        //rule a göre uygula
        if(lastAmount >= coupon.getMinimumCartAmount()){
            this.coupon = coupon;
            double afterDiscount = coupon.getDiscount().applyDiscount(this.lastAmount);
            couponDiscount = this.lastAmount - afterDiscount;
            this.lastAmount = afterDiscount;
        }
    }

    public void applyCampaigns(){
        for (Product product : productQuantityMap.keySet()) {
            List<Campaign> campaignList = product.findAllCampaigns().stream()
                    .filter(x -> x.getMinimumQuantityForDiscount() <= productQuantityMap.get(product))
                    .collect(Collectors.toList());

            List<Discount> discountList = campaignList.stream().map(Campaign::getDiscount).collect(Collectors.toList());
            applyDiscount(discountList,product, productQuantityMap.get(product));

        }
    }

    public Map<Product, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Product, Integer> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Map<Category, List<Product>> getCategoryListMap() {
        return categoryListMap;
    }

    public void setCategoryListMap(Map<Category, List<Product>> categoryListMap) {
        this.categoryListMap = categoryListMap;
    }

    public double getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(double lastAmount) {
        this.lastAmount = lastAmount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public DeliveryCostCalculator getDeliveryCostCalculator() {
        return deliveryCostCalculator;
    }

    public void setDeliveryCostCalculator(DeliveryCostCalculator deliveryCostCalculator) {
        this.deliveryCostCalculator = deliveryCostCalculator;
    }

    public void setCouponDiscount(double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public double getTotalAmountAfterDiscounts() {
        return totalAmount - couponDiscount;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setCampaignDiscount(double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public void print() {
        for (Product item : productQuantityMap.keySet()) {
            System.out.println("****************");
            System.out.println("CategoryName: " + item.getCategory().getTitle());
            System.out.println("ProductName: " + item.getTitle());
            System.out.println("Quantity: " + productQuantityMap.get(item));
            System.out.println("UnitPrice: " + item.getPrice());
            System.out.println("TotalPrice: " + item.getPrice() * productQuantityMap.get(item));
            System.out.println("TotalDiscount: " + item.getTotalDiscount());
        }
        System.out.println("****************");
        System.out.println("TotalAmount with discounts: " + (this.totalAmount - this.campaignDiscount - this.couponDiscount));
        System.out.println("DeliveryCost: " + this.deliveryCost);
        System.out.println("TOTAL With Delivery Cost: " + this.lastAmount);
    }
}
