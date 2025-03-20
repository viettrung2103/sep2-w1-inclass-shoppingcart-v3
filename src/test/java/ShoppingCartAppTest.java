import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartAppTest {


    @BeforeEach
    void setUp(){
        ShoppingCartApp.totalCost = 0.0;
    }

    @Test
    void calculateCost() {
        double result = ShoppingCartApp.calculateCost(5,10.0);
        assertEquals(result,50.0,0.0001);
    }

    @Test
    void totalCost() {
        ShoppingCartApp.totalCost+= ShoppingCartApp.calculateCost(5,10.0);
        ShoppingCartApp.totalCost+= ShoppingCartApp.calculateCost(5,10.0);
        ShoppingCartApp.totalCost+=  ShoppingCartApp.calculateCost(5,10.0);
        double result = ShoppingCartApp.getTotalCost();
        assertEquals(result,150.0,0.0001);
    }
}