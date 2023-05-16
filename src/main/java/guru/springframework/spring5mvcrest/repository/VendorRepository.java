package guru.springframework.spring5mvcrest.repository;

import guru.springframework.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
