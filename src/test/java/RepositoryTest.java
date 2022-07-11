import Manager.NotFoundException;
import Manager.ProductRepository;
import Product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import Product.Book;
import Product.Smartphone;

public class RepositoryTest {
    Product item1 = new Book(1, "Преступление и наказание", 2_000, "Достоевский");
    Product item2 = new Book(2, "Война и Мир", 1_500, "Толстой");
    Product item3 = new Book(3, "Идиот", 2_200, "Достоевский");
    Product item4 = new Smartphone(4, "Galaxy A13", 20_200, "Samsung");
    Product item5 = new Smartphone(5, "P Smart 2021", 25_200, "Huawei");
    Product item6 = new Smartphone(6, "Redmi Note 11", 27_200, "Xiaomi");
    ProductRepository repo = new ProductRepository();

    @Test
    public void deletNotExistent() {

        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);
        repo.add(item6);
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(10);
        });
    }

    @Test
    public void deletExisting() {
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);
        repo.add(item5);
        repo.add(item6);
        repo.removeById(item2.getId());

        Product[] expected = {item1, item3,item4,item5,item6};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}

