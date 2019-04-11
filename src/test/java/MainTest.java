import entity.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Ebru Göksal
 */

public class MainTest {
    private static List<Product> productList = new ArrayList<>();
    private static List<Category> categoryList = new ArrayList<>();
    private static List<Campaign> campaignList = new ArrayList<>();
    private static List<Discount> discountList = new ArrayList<>();
    private static Coupon coupon = null ;

    @BeforeClass
    public static void createData(){
        createDiscounts();
        createCoupon();
        createCategories();
        createCampaigns();
        createProducts();
    }

    private static void createCoupon() {
        coupon = new Coupon(discountList.get(2).getType(),discountList.get(2).getAmount(),100.00);
    }

    private static void createDiscounts() {
        Discount discount = new Discount(DiscountType.RATE,50.00);
        discountList.add(discount);
        discount = new Discount(DiscountType.RATE,40.00);
        discountList.add(discount);
        discount = new Discount(DiscountType.RATE,10.00);
        discountList.add(discount);
    }

    private static void createProducts(){
        Product product = new Product();
        product.setTitle("Product 1");
        product.setPrice(150.00);
        product.setCategory(categoryList.get(1));
        productList.add(product);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        product2.setPrice(100.00);
        product2.setCategory(categoryList.get(2));
        productList.add(product2);

        Product product3 = new Product();
        product3.setTitle("Product 3");
        product3.setPrice(200.00);
        product3.setCategory(categoryList.get(3));
        productList.add(product3);
    }

    private static void createCategories(){
        Category parentCategory = new Category();
        parentCategory.setTitle("Parent 1");
        parentCategory.setParentCategory(null);
        categoryList.add(parentCategory);
        Category category = new Category();
        category.setTitle("Category 1");
        category.setParentCategory(parentCategory);
        categoryList.add(category);
        Category category2 = new Category();
        category2.setTitle("Category 2");
        category2.setParentCategory(parentCategory);
        categoryList.add(category2);
        Category parentCategory2 = new Category();
        parentCategory2.setTitle("Parent 2");
        parentCategory2.setParentCategory(null);
        categoryList.add(parentCategory2);
    }

    private static void createCampaigns(){
        Campaign campaign = new Campaign(categoryList.get(0),discountList.get(1).getAmount(),
                discountList.get(1).getType(),1);
        campaignList.add(campaign);
        campaign = new Campaign(categoryList.get(1),discountList.get(0).getAmount(),
                discountList.get(0).getType(),5);
        campaignList.add(campaign);
    }


    @Test
    public void should_insert_cart(){
        Cart shoppingCart = new Cart();
        shoppingCart.addItem(productList.get(0),5);
        shoppingCart.addItem(productList.get(1),2);
        shoppingCart.addItem(productList.get(2),2);
        shoppingCart.applyCampaigns();
        shoppingCart.applyCoupon(coupon);

        shoppingCart.setDeliveryCostCalculator(new DeliveryCostCalculator(10.00,5.00,3.00));
        double deliveryCost = shoppingCart.getDeliveryCostCalculator().calculate(shoppingCart);
        double totalPrice = shoppingCart.getLastAmount() + deliveryCost;
        System.out.println("getTotalAmountAfterDiscounts: " + shoppingCart.getTotalAmountAfterDiscounts());
        System.out.println("getCouponDiscount: " + shoppingCart.getCouponDiscount());
        System.out.println("getCampaignDiscount: " + shoppingCart.getCampaignDiscount());
        System.out.println("getDeliveryCost: " + shoppingCart.getDeliveryCost());
        shoppingCart.print();

        assertTrue(totalPrice > 0);
    }

}
