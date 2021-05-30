package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.result.DataResult;
import kodlamaio.hrms.entities.concretes.VerificationToken;

public interface VerificationTokenService {

	DataResult<List<VerificationToken>> getAll();
}
