package com.fluidapi.csv.function.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.fluidapi.csv.function.LimitedQueue;

@Testable
public class TestFixedLengthQueue {
	
	@Test
	public void testNegativeSize() {
		assertThrows(Throwable.class, () -> new FixedLengthQueue<>(-1));
	}
	
	@Test
	public void testZeroSize() {
		assertThrows(Throwable.class, () -> new FixedLengthQueue<>(0));
	}
	
	@Test
	public void testUnitSize() {
		LimitedQueue<String> queue = new FixedLengthQueue<>(1);
		assertTrue(queue.isEmpty(), "queue.empty | before insert");
		assertFalse(queue.isFull(), "queue.full | before insert");

		String test = "test";
		assertTrue(queue.enqueue(test), "enqueue");
		assertFalse(queue.enqueue("false"), "enqueue overflow");
		assertFalse(queue.isEmpty(), "queue.empty | after enqueue");
		assertTrue(queue.isFull(), "queue.full | after enqueue");

		assertEquals(test, queue.dequeue(), "dequeued");
		assertTrue(queue.isEmpty(), "queue.empty | after dequeue");
		assertFalse(queue.isFull(), "queue.full | after dequeue");
		
		assertTrue(queue.enqueue(test), "re-enqueue");
		assertFalse(queue.enqueue("false"), "re-enqueue overflow");
		assertFalse(queue.isEmpty(), "queue.empty | after re-enqueue");
		assertTrue(queue.isFull(), "queue.full | after re-enqueue");

		assertEquals(test, queue.dequeue(), "dequeued");
		assertTrue(queue.isEmpty(), "queue.empty | after re-dequeue");
		assertFalse(queue.isFull(), "queue.full | after re-dequeue");
		
		assertNull(queue.dequeue(), "dequeue on empty");
	}
	
	@Test
	public void testLongSize() {
		final int length = 5;
		LimitedQueue<Integer> queue = new FixedLengthQueue<>(length);
		assertTrue(IntStream.rangeClosed(1, length).boxed().map(queue::enqueue)
				 .reduce((a, b) -> a && b)
				 .get(), "enqueued 5");
		
		assertFalse(queue.enqueue(11), "enqueue overflow");
		assertEquals(1, queue.dequeue());
		assertEquals(2, queue.dequeue());
		assertEquals(3, queue.dequeue());
		assertEquals(4, queue.dequeue());
		assertEquals(5, queue.dequeue());
		assertNull(queue.dequeue(), "phantom element");
	}
}
