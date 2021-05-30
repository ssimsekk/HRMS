package kodlamaio.hrms.core.utilities.adapters;

import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.entities.concretes.Candidate;

public interface EmailConfirmationService {
	public Result checkEmailConfirmation(String email);
}
