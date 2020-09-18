package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import domain.Article;
import domain.Purchase;
import domain.PurchasedArticle;

public class PurchaseTest {
	private Purchase basket=new Purchase();
	@Test
	public void testAddBasket1() {
		int stock=5;
		float price= (float) 2.5;
		int quantity=2;
		//1. expected value
		double expectedResult = 0;
		//2. invoke the method and get the result
		basket = new Purchase();
		Article art=new Article("1", "16Gb pendrive", 0, false, stock);
		double result = basket.addBasket(art,quantity);
		//3. check
		assertEquals(expectedResult,result);
	}
	@Test
	public void testAddBasket2() {
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
	public void testAddBasket3DateNotNull() {
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
		//3. check
		assertEquals(expectedResult,result);
	}
	@Test
	public void testAddBasket4PurchasedArticleNotNull() {
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
		//3. check
		assertEquals(expectedResult,result);
	}
	@Test
	public void testAddBasket5NotEnoughStock() {
		int stock=2;
		float price= (float) 2.5;
		int quantity=10;
		//1. expected value
		double expectedResult = price * quantity;
		//2. invoke the method and get the result
		basket = new Purchase();		
		Article art=new Article("1","16Gb pendrive",price, false, stock);
		double result = basket.addBasket(art,quantity);
		//3. check
		assertEquals(expectedResult,result);
	}
}
