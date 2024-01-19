package andreamarchica.U5W2L5.repositories;

import andreamarchica.U5W2L5.entities.Device;
import andreamarchica.U5W2L5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DevicesRepository extends JpaRepository <Device, UUID> {
    List<Device> findByUser(User user);
}
