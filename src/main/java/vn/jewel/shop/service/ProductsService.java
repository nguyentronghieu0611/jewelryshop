package vn.jewel.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import vn.jewel.shop.model.Product;
import vn.jewel.shop.repository.ProductsRepository;

import java.util.List;

@Service
public class ProductsService extends AbstractService<Product, Long>{
    @Autowired
    ProductsRepository productsRepository;

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        return productsRepository;
    }

    public Object getProducts(int id, int pageNumber, int pageSize){
        if(id==0){
            if(pageNumber <= 0) {
                return productsRepository.findAll();
            }
            if(pageSize <= 0) {
                pageSize = PAGE_SIZE;
            }
            PageRequest pageRequest =  PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.ASC, "id");
            return productsRepository.findAll(pageRequest);
        }
        else {
            if(pageNumber <= 0) {
                return productsRepository.findByCategory_id(Long.valueOf(id));
            }
            if(pageSize <= 0) {
                pageSize = PAGE_SIZE;
            }
            PageRequest pageRequest =  PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.ASC, "id");
            return productsRepository.findByCategory_id(Long.valueOf(id),pageRequest);
        }
    }
}
