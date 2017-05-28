package me.vincentlin.bookstore.mock;

import com.github.javafaker.Faker;
import me.vincentlin.bookstore.dao.*;
import me.vincentlin.bookstore.model.*;
import me.vincentlin.bookstore.service.CartService;
import me.vincentlin.bookstore.service.OrderService;
import me.vincentlin.bookstore.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SeederRunner implements ApplicationRunner {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderEntryRepository orderEntryRepository;
    @Autowired
    private OrderService orderService;

    private Faker faker;

    public SeederRunner() {
        faker = new Faker();
    }

    public void run(ApplicationArguments args) {
        seedRoles();
        seedUsers(20);
        seedAuthors(15);
        seedGenres(5);
        seedBooks(50);
        seedOrders(20);
        seedCart();
        storeFileToGridFs();
    }

    private void seedCart() {
        User user = userRepository.findByUsername("admin");
        List<Book> books = bookRepository.findAll();
        cartService.setItem(user, randomElement(books), 5L);
    }

    private void seedUsers(int n) {
        User superUser = createUser("admin", "admin", roleRepository.findAll());
        userRepository.save(superUser);
        List<User> users = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            users.add(randomUser());
        }
        userRepository.save(users);
    }

    private void seedRoles() {
        roleRepository.save(new Role("ROLE_USER"));
        roleRepository.save(new Role("ROLE_ADMIN"));
    }

    private void seedAuthors(int n) {
        List<Author> authors = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Author author = new Author();
            author.setName(faker.superhero().name());
            authors.add(author);
        }
        authorRepository.save(authors);
    }

    private void seedGenres(int n) {
        List<Genre> genres = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Genre genre = new Genre();
            genre.setName(faker.book().genre());
            genres.add(genre);
        }
        genreRepository.save(genres);
    }

    private User randomUser() {
        Role role = roleRepository.findByName("ROLE_USER");
        String username = faker.artist().name();
        String password = username;
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return createUser(username, password, roles);
    }

    private User createUser(String username, String password, List<Role> roles) {
        User user = new User();
        user.setEmail(faker.internet().safeEmailAddress());
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRoles(roles);
        return user;
    }

    private void seedBooks(int n) {
        List<Genre> genres = genreRepository.findAll();
        List<Author> authors = authorRepository.findAll();
        List<Book> books = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Book book = new Book();
            book.setGenre(randomElement(genres));
            book.setName(faker.book().title());
            book.setIsbn(faker.number().digits(13));
            book.setAuthors(randomElements(authors, 2));
            book.setInventory(faker.number().randomNumber());
            book.setPrice(new BigDecimal(faker.number().randomDouble(2, 10, 100)));
            book.setImage("http://lorempixel.com/200/400");
            book.setDescription(faker.shakespeare().kingRichardIIIQuote());
            books.add(book);
        }
        bookRepository.save(books);
    }

    private void seedOrders(int n) {
        List<User> users = userRepository.findAll();
        List<Book> books = bookRepository.findAll();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            User user = randomElement(users);
            List<Book> booksToBuy = randomElements(books, faker.random().nextInt(10) + 1);
            for(Book book:booksToBuy){
                cartService.setItem(user,book, faker.random().nextLong(30) + 1);
            }
            Order order= orderService.checkout(user,cartService.getItems(user));
            cartService.removeAll(user);
            orders.add(order);
        }
        orderRepository.save(orders);
    }

    private static <T> T randomElement(List<T> elements) {
        int index = new Random().nextInt(elements.size());
        return elements.get(index);
    }

    private static <T> List<T> randomElements(List<T> elements, int n) {
        List<T> subset = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = new Random().nextInt(elements.size());
            subset.add(elements.get(index));
        }
        return subset;
    }

    @Autowired
    GridFsOperations operations;

    public void storeFileToGridFs() {
        // populate metadata
        Resource file = new FileSystemResource("readme.md");
        // lookup File or Resource
        try {
            operations.store(file.getInputStream(), "filename.txt");
            Resource resource = operations.getResource("filename.txt");
            InputStream stream = resource.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            int i= reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}