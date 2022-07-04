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

    @Test
    public void shouldAllProducts() {
        ProductManager manager = new ProductManager(new ProductRepository());
        manager.save(item1);
        manager.save(item2);
        manager.save(item3);
        manager.save(item4);
        manager.save(item5);
        manager.save(item6);

        Product[] expected = {item1, item2, item3,item4,item5,item6};
        Product[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAllProductsAndRemove() {
        ProductManager manager = new ProductManager(new ProductRepository());
        manager.save(item1);
        manager.save(item2);
        manager.save(item3);
        manager.removeById(item2.getId());

        Product[] expected = {item1, item3};
        Product[] actual = manager.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBy() {
        ProductManager manager = new ProductManager(new ProductRepository());
        manager.save(item1);
        manager.save(item2);
        manager.save(item3);
        manager.save(item4);
        manager.save(item5);
        manager.save(item6);
        Product[] expected = {item3};
        Product[] actual = manager.searchBy("Идиот");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldNotSearchBy() {
        ProductManager manager = new ProductManager(new ProductRepository());

        Product[] expected = {};
        Product[] actual = manager.searchBy("Идиот");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchedNotPresent () {
        ProductManager manager = new ProductManager(new ProductRepository());
        manager.save(item1);
        manager.save(item2);
        manager.save(item3);
        manager.save(item4);
        manager.save(item5);
        manager.save(item6);
        Product [] expected = {};
        Product [] actual = manager.searchBy("Осень");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearched () {
        ProductManager manager = new ProductManager(new ProductRepository());
        manager.save(item4);
        manager.save(item2);
        manager.save(item3);
        manager.save(item4);
        manager.save(item5);
        manager.save(item6);
        Product [] expected = {item4,item4};
        Product [] actual = manager.searchBy("Galaxy A13");

        Assertions.assertArrayEquals(expected, actual);
    }
}

