package mainPackage.entities.product;

import org.springframework.data.jpa.repository.JpaRepository;

import mainPackage.entities.product.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
