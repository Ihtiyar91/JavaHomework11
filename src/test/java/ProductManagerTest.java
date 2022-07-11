import Manager.ProductManager;
import Manager.ProductRepository;
import Product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Product.Book;
import Product.Smartphone;

public class ProductManagerTest {
    Product item1 = new Book(1, "Преступление и наказание", 2_000, "Достоевский");
    Product item2 = new Book(2, "Война и Мир", 1_500, "Толстой");
    Product item3 = new Book(3, "Идиот", 2_200, "Достоевский");
    Product item4 = new Smartphone(4, "Galaxy A13", 20_200, "Samsung");
    Product item5 = new Smartphone(5, "P Smart 2021", 25_200, "Huawei");
    Product item6 = new Smartphone(6, "Redmi Note 11", 27_200, "Xiaomi");
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    @Test
    public void shouldAllProducts() {

        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);
        manager.add(item5);
        manager.add(item6);

        Product[] expected = {item1, item2, item3, item4, item5, item6};
        Product[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAllProductsAndRemove() {

        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.removeById(item2.getId());

        Product[] expected = {item1, item3};
        Product[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBy() {

        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);
        manager.add(item5);
        manager.add(item6);
        Product[] expected = {item3};
        Product[] actual = manager.searchBy("Идиот");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchBy() {


        Product[] expected = {};
        Product[] actual = manager.searchBy("Идиот");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchedNotPresent() {

        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);
        manager.add(item5);
        manager.add(item6);
        Product[] expected = {};
        Product[] actual = manager.searchBy("Осень");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearched() {

        manager.add(item4);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);
        manager.add(item5);
        manager.add(item6);
        Product[] expected = {item4, item4};
        Product[] actual = manager.searchBy("Galaxy A13");

        Assertions.assertArrayEquals(expected, actual);
    }
}

