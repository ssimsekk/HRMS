package kodlamaio.hrms.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employers_email_verification")
@AllArgsConstructor
@NoArgsConstructor
public class EmployerEmailVerification {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "token_id")
	private int tokenId;
	
	@JoinColumn(name = "employer_id")
	private int employerId;
	
	@Column(name = "is_verified")
	private boolean isVerified;

	@Column(name = "verification_date")
	private Date verificationDate;
}
