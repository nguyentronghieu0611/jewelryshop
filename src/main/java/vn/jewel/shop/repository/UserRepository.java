package vn.jewel.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.jewel.shop.model.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where username=?1",nativeQuery = true)
    User getUserByUserName(String username);

    @Query(value = "select * from user where username=?1 and enabled=1",nativeQuery = true)
    User getUserByUserActiveName(String username);

    User findByEmail(String email);

    List<User> findAll();

    @Query(value = "update user set first_name=?, last name=? where id=?",nativeQuery = true)
    void updateUser(String firstName, String lastName, Long id);
}
