package mainPackage.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mainPackage.entities.product.Product;
import mainPackage.entities.product.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private final ProductRepository productRepo;
	
	public ProductService(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}
	
	
	public List<Product> findAllProducts(){
		return productRepo.findAll();
	}


	public ProductRepository getProductRepo() {
		return productRepo;
	}
	
}
