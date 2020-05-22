package vn.jewel.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import vn.jewel.shop.model.User;
import vn.jewel.shop.repository.UserRepository;

@Service
public class UserService extends AbstractService<User, Long>{
    @Autowired
    UserRepository userRepository;


    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    public Object getListUser(Integer pageNumber, Integer pageSize) {
        if(pageNumber <= 0) {
            return userRepository.findAll();
        }
        if(pageSize <= 0) {
            pageSize = PAGE_SIZE;
        }
        PageRequest pageRequest =  PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.ASC, "id");
        return userRepository.findAll(pageRequest);
    }
}
