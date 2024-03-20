import com.betacom.common.valueObjects.DeliveryAddress;
import com.betacom.common.valueObjects.Money;
import com.betacom.common.valueObjects.Product;
import com.betacom.coreDomain.entity.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class OrderTest {
    @Test
    public void testValidOrder(){
       Order order = new Order(getCustomerNumber(),
               getProdustList(),
               getDeliveryAddress(),
               orderGrossAmount(), orderNetAmount(), orderDiscount());

        System.out.println(order.toString());
    }



    private String getCustomerNumber(){
        return "321681V";
    }

    private List<Product> getProdustList(){
        Product product1 = Product.builder()
                .productCode("AAAA001")
                .quantity(10)
                .netValue(new Money(500.50, "LKR"))
                .grossValue(new Money(540, "LKR"))
                .discountValue(new Money(39.50, "LKR"))
                .amountOfEachQuantity(new Money(54, "LKR"))
                .build();

        Product product2 = Product.builder()
                .productCode("AAAA002")
                .quantity(10)
                .netValue(new Money(470.50, "LKR"))
                .grossValue(new Money(500, "LKR"))
                .discountValue(new Money(29.50, "LKR"))
                .amountOfEachQuantity(new Money(50, "LKR"))
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        return productList;
    }

    private DeliveryAddress getDeliveryAddress(){
        return DeliveryAddress.builder()
                .buildingNumber("131/2")
                .streetAddress("Magalegoda")
                .city("Veyangoda")
                .postalCode("111000")
                .build();
    }

    private Money orderGrossAmount(){
        return new Money(1040, "LKR");
    }

    private Money orderNetAmount(){
        return new Money(971, "LKR");
    }

    private Money orderDiscount(){
        return new Money(69, "LKR");
    }
}
