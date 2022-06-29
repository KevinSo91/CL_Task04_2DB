package mainPackage;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mainPackage.entities.product.Product;
import mainPackage.entities.product.ProductRepository;
import mainPackage.entities.user.UserRepository;

@SpringBootTest
class ClTask042DbApplicationTests {

	@Autowired
	ProductRepository productRepo;
	@Autowired
	UserRepository userRepo;
	
	
	@Test
	void testRepos() {
		
		Product productA = new Product("tisch", 20);
		Product productB = new Product("stuhl", 20);	
		productRepo.saveAll(Arrays.asList(productA, productB));	
		
		//User user0 = new User("")
	}

}
