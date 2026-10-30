import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    Ticket ticket1 = new Ticket("LED", "SVO", 9000, 10, 14);
    Ticket ticket2 = new Ticket("LED", "SVO", 5000, 10, 16);
    Ticket ticket3 = new Ticket("LED", "SVO", 7000, 8, 10);
    Ticket ticket4 = new Ticket("KZN", "VKO", 1000, 1, 2);
    Ticket ticket5 = new Ticket("KZN", "VKO", 5000, 3, 4);

    private AviaSouls createManager() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        return manager;
    }

    @Test
    public void shouldReturnNegativeWhenPriceIsLess() {
        assertTrue(ticket2.compareTo(ticket1) < 0);
    }

    @Test
    public void shouldReturnPositiveWhenPriceIsGreater() {
        assertTrue(ticket1.compareTo(ticket2) > 0);
    }

    @Test
    public void shouldReturnZeroWhenPricesAreEqual() {
        assertEquals(0, ticket2.compareTo(ticket5));
    }

    @Test
    public void shouldSearchAndSortByPrice() {
        AviaSouls manager = createManager();

        Ticket[] expected = {ticket2, ticket3, ticket1};
        Ticket[] actual = manager.search("LED", "SVO");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchOneTicket() {
        AviaSouls manager = createManager();

        Ticket[] expected = {ticket4};
        Ticket[] actual = manager.search("KZN", "VKO");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayWhenNothingFound() {
        AviaSouls manager = createManager();

        Ticket[] expected = {};
        Ticket[] actual = manager.search("LED", "VKO");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnNegativeWhenFlightTimeIsLess() {
        TickettimeComparator comparator = new TickettimeComparator();

        assertTrue(comparator.compare(ticket3, ticket1) < 0);
    }

    @Test
    public void shouldReturnPositiveWhenFlightTimeIsGreater() {
        TickettimeComparator comparator = new TickettimeComparator();

        assertTrue(comparator.compare(ticket2, ticket1) > 0);
    }

    @Test
    public void shouldReturnZeroWhenFlightTimesAreEqual() {
        TickettimeComparator comparator = new TickettimeComparator();


        assertEquals(0, comparator.compare(ticket4, ticket5));
    }

    @Test
    public void shouldSearchAndSortByFlightTime() {
        AviaSouls manager = createManager();
        TickettimeComparator comparator = new TickettimeComparator();

        Ticket[] expected = {ticket3, ticket1, ticket2};
        Ticket[] actual = manager.searchAndSortBy("LED", "SVO", comparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTimeWhenNothingFound() {
        AviaSouls manager = createManager();
        TickettimeComparator comparator = new TickettimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = manager.searchAndSortBy("LED", "VKO", comparator);

        assertArrayEquals(expected, actual);
    }
}
