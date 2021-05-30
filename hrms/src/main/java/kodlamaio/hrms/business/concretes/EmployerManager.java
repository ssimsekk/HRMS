package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.adapters.EmailConfirmationService;
import kodlamaio.hrms.core.utilities.result.DataResult;
import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private EmailConfirmationService emailConfirmationService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, EmailConfirmationService emailConfirmationService) {
		super();
		this.employerDao = employerDao;
		this.emailConfirmationService = emailConfirmationService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "İşverenler listelendi.");
	}

	@Override
	public Result register(Employer employer) {
		Result checkAllField = checkAllField(employer);
		if (checkAllField.isSuccess()) {
			Result checkEmailConfirmation = emailConfirmationService.checkEmailConfirmation(employer.getEmail());
			if (checkEmailConfirmation.isSuccess()) {
				this.employerDao.save(employer);
				return new SuccessResult("Doğrulama tamamlandı. Sisteme kayıt başarılı.");
			} else {
				return checkEmailConfirmation;
			}

		} else {
			return checkAllField;
		}

	}

	public Result checkAllField(Employer employer) {
		if (!Strings.isNullOrEmpty(employer.getCompanyName()) && !Strings.isNullOrEmpty(employer.getEmail())
				&& !Strings.isNullOrEmpty(employer.getPassword()) && !Strings.isNullOrEmpty(employer.getPhoneNumber())
				&& !Strings.isNullOrEmpty(employer.getWebsite())) {

			if (checkEmail(employer.getEmail()) == false) {
				return new ErrorResult("Email sistemde kayıtlı.");
			}
			if (checkEmployeeConfirmation(employer) == false) {
				return new ErrorResult("HRMS personeli onaylamadı.");
			} else {
				return new SuccessResult();
			}
		} else {
			return new ErrorResult("Tüm alanlar zorunludur.");
		}

	}

	public boolean checkEmail(String email) {
		List<Employer> employers = getAll().getData();

		for (Employer employer : employers) {
			if (employer.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkEmployeeConfirmation(Employer employer) {
		return true;
	}

}
