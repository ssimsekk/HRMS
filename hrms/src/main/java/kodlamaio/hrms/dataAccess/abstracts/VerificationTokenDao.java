package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.VerificationToken;

public interface VerificationTokenDao extends JpaRepository<VerificationToken, Integer> {

}
