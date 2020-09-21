package test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import domain.Article;
import domain.Purchase;

public class PurchaseTest {
	private Purchase basket=new Purchase();
	@Test
	public void testAddBasketNegativeQuantity() {
		int stock=5;
		float price= (float) 2.5;
		int quantity=-1;
		//1. expected value
		double expectedResult = 0;
		//2. invoke the method and get the result
		basket = new Purchase();
		Article art=new Article("1","16Gb pendrive",price, false, stock);
		double result = basket.addBasket(art,quantity);
		//3. check
		assertEquals(expectedResult,result);
	}
	
	@Test
	public void testAddBasketPurchasedArticleNotNull() {
		try {
			int stock=5;
			float price= (float) 2.5;
			int quantity=1;
			//1. expected value
			double expectedResult = price * quantity;
			//2. invoke the method and get the result
			basket = new Purchase();
			Article art=new Article("1","16Gb pendrive",price, false, stock);
			double result = basket.addBasket(art,quantity);
			double result1 = basket.addBasket(art,quantity);
		}catch (RuntimeException e) {
		//3. check
		assertTrue(true);
		}
	}
	@Test
	public void testAddBasketDateNotNullAndNotEnoughStock() {
		
		try{ 
			int stock=2;
			float price= (float) 2.5;
			int quantity=10;
			Date d = new Date (2015-03-02);
			//1. expected value
			double expectedResult = price * quantity;
			//2. invoke the method and get the result
			basket = new Purchase();
			basket.setPurchasedDate(d);
			
			Article art=new Article("1","16Gb pendrive",price, false, stock);
			double result = basket.addBasket(art,quantity);
			fail();
		}catch (RuntimeException e) {
			//3. check
			assertTrue(true);
		}		
	}
	//Test RemoveBasket
	@Test
	public void testRemoveBasketPurchaseDateNotNullAndNotEnoughStock() {
		try {
			int stock=2;
			float price= (float) 2.5;
			int quantity=10;
			Date d = new Date (2015-03-02);
			//1. expected value
			double expectedResult = price * quantity;
			//2. invoke the method and get the result
			basket = new Purchase();		
			basket.setPurchasedDate(d);
			Article art=new Article("1","16Gb pendrive",price, false, stock);
			double result = basket.removeBasket(art,quantity);
			double result2 = basket.removeBasket(art,quantity);
		} catch (RuntimeException e) {
			//3. check
			assertTrue(true);
		}
	}
	@Test
	public void testRemovePurchaseQuantityHandiago() {
		try{
			int stock=5;
			float price= (float) 2.5;
			int quantity=2;
			//1. expected value
			double expectedResult = 0;
			//2. invoke the method and get the result
			basket = new Purchase();
			Article art=new Article("1","16Gb pendrive",price, false, stock);
			double result = basket.addBasket(art,quantity);
			quantity = 7;
			double result1 = basket.removeBasket(art,quantity);
		}catch (RuntimeException e) {
			//3. check
			assertTrue(true);
		}
	}
	@Test
	public void testRemovePurchaseQuantityBerdin() {
		int stock=5;
		float price= (float) 2.5;
		int quantity=2;
		//1. expected value
		double expectedResult = price * quantity;
		//2. invoke the method and get the result
		basket = new Purchase();
		Article art=new Article("1","16Gb pendrive",price, false, stock);
		Article art2=new Article("4","32Gb pendrive",3, false, stock);
		double result = basket.addBasket(art,quantity);
		quantity = 2;
		double result1 = basket.addBasket(art,quantity);
		double result2 = basket.removeBasket(art,quantity);
		double result4 = basket.addBasket(art2,quantity);
		double result5 = basket.removeBasket(art2,quantity);
		//3. check
		assertEquals(expectedResult,result);
	}

	@Test
	public void testRemoveBasketPurchaseArticleNotNull() {
		try {
		int stock=2;
		float price= (float) 2.5;
		int quantity=10;
		//1. expected value
		double expectedResult = price * quantity;
		//2. invoke the method and get the result
		basket = new Purchase();

		Article art=new Article("1","16Gb pendrive",price, false, stock);
		double result = basket.addBasket(art,quantity);
		double result2 = basket.removeBasket(art,quantity);
		fail();
		}catch(RuntimeException e) {
			//3. check
			assertTrue(true);
		}
	}
}
