package kodlamaio.hrms.core.utilities.adapters;

import org.springframework.stereotype.Service;

import emailConfirmationService.EmailConfirmationManager;
import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class EmailConfirmationServiceAdapter implements EmailConfirmationService {

	@Override
	public Result checkEmailConfirmation(String email) {
		EmailConfirmationManager emailConfirmationManager = new EmailConfirmationManager();
		if(emailConfirmationManager.isEmailConfirmed(email)) {
			return new SuccessResult("E-posta doğrulaması başarılı.");
		}else {
			return new ErrorResult("E-posta doğrulaması başarısız.");
		}
	}

}
