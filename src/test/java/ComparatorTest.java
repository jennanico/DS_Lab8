import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComparatorTest {
	
	PriorityQueue<Customer> pQueue;
	
	Customer customerA;
	Customer customerB;
	Customer customerC;
	Customer customerD;
	
	Customer.WorthComparator worthC;
	Customer.LoyaltyComparator loyalC;
	Customer.WorthPoliteComparator politeC;

	@BeforeEach
	void setUp() throws Exception {
		customerA = new Customer(10, 0, 0);
		customerB = new Customer(0, 10, 0);
		customerC = new Customer(0, 0, 10);
		customerD = new Customer(5, 5, 5);
		
		worthC = new Customer.WorthComparator();
		loyalC = new Customer.LoyaltyComparator();
		politeC = new Customer.WorthPoliteComparator();
	}

	@Test
	void testWorthComp() {
		assertTrue(worthC.compare(customerA, customerB) > 0);
		assertTrue(worthC.compare(customerB, customerA) < 0);
		assertTrue(worthC.compare(customerB, customerC) == 0);
	}
	
	@Test
	void testLoyaltyComp() {
		assertTrue(loyalC.compare(customerB, customerA) > 0);
		assertTrue(loyalC.compare(customerA, customerB) < 0);
		assertTrue(loyalC.compare(customerA, customerC) == 0);
	}
	
	@Test
	void testPoliteComp() {
		assertTrue(politeC.compare(customerA, customerB) > 0);
		assertTrue(politeC.compare(customerB, customerA) < 0);
		assertTrue(politeC.compare(customerB, customerC) < 0);
	}
	
	@Test
	void testPushEmpty() {
		pQueue = new PriorityQueue<Customer>(worthC);
		
		pQueue.push(customerA);
		assertEquals(customerA, pQueue.pop());
		
		assertThrows(QueueUnderFlowException.class, () -> {
			pQueue.pop();
		});
	}
	
	@Test
	void testWorthPushLessThan() {
		pQueue = new PriorityQueue<Customer>(worthC);
		
		pQueue.push(customerA);
		pQueue.push(customerB);
		assertEquals(customerA, pQueue.pop());
		assertEquals(customerB, pQueue.pop());
	}
	
	@Test
	void testWorthPushInsert() {
		pQueue = new PriorityQueue<Customer>(worthC);
		
		pQueue.push(customerA);
		pQueue.push(customerB);
		pQueue.push(customerD);
		assertEquals(customerA, pQueue.pop());
		assertEquals(customerD, pQueue.pop());
		assertEquals(customerB, pQueue.pop());
	}
	
	@Test
	void testWorthPushGreaterThan() {
		pQueue = new PriorityQueue<Customer>(worthC);
		
		pQueue.push(customerD);
		pQueue.push(customerB);
		pQueue.push(customerA);
		assertEquals(customerA, pQueue.pop());
		assertEquals(customerD, pQueue.pop());
		assertEquals(customerB, pQueue.pop());
	}
	
	@Test
	void testLoyalPushLessThan() {
		pQueue = new PriorityQueue<Customer>(loyalC);
		
		pQueue.push(customerB);
		pQueue.push(customerA);
		assertEquals(customerB, pQueue.pop());
		assertEquals(customerA, pQueue.pop());
	}
	
	@Test
	void testLoyalPushInsert() {
		pQueue = new PriorityQueue<Customer>(loyalC);
		
		pQueue.push(customerB);
		pQueue.push(customerA);
		pQueue.push(customerD);
		assertEquals(customerB, pQueue.pop());
		assertEquals(customerD, pQueue.pop());
		assertEquals(customerA, pQueue.pop());
	}
	
	@Test
	void testLoyalPushGreaterThan() {
		pQueue = new PriorityQueue<Customer>(loyalC);
		
		pQueue.push(customerD);
		pQueue.push(customerA);
		pQueue.push(customerB);
		assertEquals(customerB, pQueue.pop());
		assertEquals(customerD, pQueue.pop());
		assertEquals(customerA, pQueue.pop());
	}
	
	@Test
	void testPolitePushLessThan() {
		pQueue = new PriorityQueue<Customer>(politeC);
		
		pQueue.push(customerA);
		pQueue.push(customerB);
		assertEquals(customerA, pQueue.pop());
		assertEquals(customerB, pQueue.pop());
	}
	
	@Test
	void testPolitePushInsert() {
		pQueue = new PriorityQueue<Customer>(politeC);
		
		pQueue.push(customerA);
		pQueue.push(customerB);
		pQueue.push(customerD);
		assertEquals(customerA, pQueue.pop());
		assertEquals(customerD, pQueue.pop());
		assertEquals(customerB, pQueue.pop());
	}
	
	@Test
	void testPolitePushGreaterThan() {
		pQueue = new PriorityQueue<Customer>(politeC);
		
		pQueue.push(customerD);
		pQueue.push(customerB);
		pQueue.push(customerA);
		assertEquals(customerA, pQueue.pop());
		assertEquals(customerD, pQueue.pop());
		assertEquals(customerB, pQueue.pop());
	}
	
	@Test
	void testPoliteTie() {
		pQueue = new PriorityQueue<Customer>(politeC);

		pQueue.push(customerB);
		pQueue.push(customerC);
		assertEquals(customerC, pQueue.pop());
		assertEquals(customerB, pQueue.pop());
	}
	

}
