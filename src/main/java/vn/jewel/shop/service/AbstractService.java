package vn.jewel.shop.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.jewel.shop.model.AbstractModel;


public abstract class AbstractService<T extends AbstractModel<Long>, Long extends Serializable> {

    public static final int PAGE_SIZE = 20;
    protected abstract JpaRepository<T, Long> getRepository();

    public Page<T> getList(Integer pageNumber) {
        PageRequest pageRequest=(PageRequest.of(pageNumber - 1, PAGE_SIZE,Sort.Direction.DESC,"id"));

//        PageRequest pageRequest =
//                new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "id");

        return getRepository().findAll(pageRequest);
    }

    public Page<T> getList(Integer pageNumber, Integer pageSize) {
        if(pageSize <= 0) {
            pageSize = PAGE_SIZE;
        }

        PageRequest pageRequest=(PageRequest.of(pageNumber - 1, PAGE_SIZE,Sort.Direction.DESC,"id"));

        return getRepository().findAll(pageRequest);
    }

    public List<T> fillAll() {
        return getRepository().findAll();
    }

    public T save(T entity) {
        return getRepository().save(entity);
    }

    public T get(Long id) {
        //  T entity = getRepository().findOne(id);
        T entity = getRepository().getOne(id);
        return entity;
    }

    public void delete(Long id) {
        try {
            // getRepository().delete(id);
            getRepository().deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }

    public void update(T entity) {
        //   T getEntity = getRepository().findOne(entity.getId());
        T getEntity = getRepository().getOne(entity.getId());
        //getRepository().save(entity);

        getRepository().save(getEntity);
    }

}
