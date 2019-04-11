package entity;


/**
 * @author Ebru Göksal
 */
public class DeliveryCostCalculator {
    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }

    public double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public double getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(double fixedCost) {
        this.fixedCost = fixedCost;
    }

    public double calculate(Cart cart){
        int numberOfDeliviries = cart.getCategoryListMap().size();
        int numberOfProducts = cart.getProductQuantityMap().size();
        double deliveryCost = (costPerDelivery * numberOfDeliviries) + (costPerProduct + numberOfProducts) + fixedCost;
        cart.setDeliveryCost(deliveryCost);
        cart.setLastAmount(cart.getLastAmount()+deliveryCost);
        return deliveryCost;
    }
}
