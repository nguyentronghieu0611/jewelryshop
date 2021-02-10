package vn.jewel.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.jewel.shop.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
