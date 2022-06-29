package mainPackage;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import mainPackage.entities.product.Product;
import mainPackage.services.ProductService;
import mainPackage.services.UserService;

@SpringBootTest
@ComponentScan("mainPackage.services")
class ClTask042DbApplicationTests {

	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	
	
	@Test
	void testRepos() {
		
		Product productA = new Product("tisch", 20);
		Product productB = new Product("stuhl", 20);	
		this.productService.getProductRepo().saveAll(Arrays.asList(productA, productB));	
		
	
		System.out.println(productService.findAllProducts());
		
		
		System.out.println(userService.findAllUsers());
		
		
		
		System.out.println("\nTest finished");
	}

}
