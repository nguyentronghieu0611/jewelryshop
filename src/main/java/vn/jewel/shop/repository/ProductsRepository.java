package vn.jewel.shop.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.jewel.shop.model.Product;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllById(Long id);

    @Query(value = "select a.* from product a where category_id=?", nativeQuery = true)
    List<Product> findByCategory_id(Long id);

    @Query(value = "select a.* from product a where category_id=?", nativeQuery = true)
    List<Product> findByCategory_id(Long id,PageRequest request);

    List<Product> findAllById(PageRequest request);
}
