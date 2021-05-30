package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.adapters.EmailConfirmationService;
import kodlamaio.hrms.core.utilities.adapters.MernisService;
import kodlamaio.hrms.core.utilities.result.DataResult;
import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private MernisService mernisService;
	private EmailConfirmationService emailConfirmationService;

	@Autowired
	public CandidateManager(CandidateDao candidateDao, MernisService mernisService,
			EmailConfirmationService emailConfirmationService) {
		super();
		this.candidateDao = candidateDao;
		this.mernisService = mernisService;
		this.emailConfirmationService = emailConfirmationService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "İş arayanlar listelendi.");
	}

	@Override
	public Result register(Candidate candidate) {
		Result checkAllField = checkAllField(candidate);
		if (checkAllField.isSuccess()) {
			Result checkIfRealPerson = mernisService.checkIfRealPerson(candidate);
			if (checkIfRealPerson.isSuccess()) {
				Result checkEmailConfirmation = emailConfirmationService.checkEmailConfirmation(candidate.getEmail());
				if (checkEmailConfirmation.isSuccess()) {
					this.candidateDao.save(candidate);
					return new SuccessResult("Doğrulama tamamlandı. Sisteme kayıt başarılı.");
				} else {
					return checkEmailConfirmation;
				}
			} else {
				return checkIfRealPerson;
			}

		} else {
			return checkAllField;
		}

	}

	public Result checkAllField(Candidate candidate) {
		if (!Strings.isNullOrEmpty(candidate.getFirstName()) && !Strings.isNullOrEmpty(candidate.getLastName())
				&& !Strings.isNullOrEmpty(candidate.getDateOfBirth().toString())
				&& !Strings.isNullOrEmpty(candidate.getEmail()) && !Strings.isNullOrEmpty(candidate.getNationalityId())
				&& !Strings.isNullOrEmpty(candidate.getPassword())) {

			if (checkEmail(candidate.getEmail()) == false) {
				return new ErrorResult("Email sistemde kayıtlı.");

			} else {
				if (checkIdentity(candidate.getNationalityId()) == false) {
					return new ErrorResult("T.C. Kimlik No sistemde kayıtlı.");
				} else {
					return new SuccessResult();
				}
			}
		} else {
			return new ErrorResult("Tüm alanlar zorunludur.");
		}

	}

	public boolean checkEmail(String email) {
		List<Candidate> candidates = getAll().getData();

		for (Candidate candidate : candidates) {
			if (candidate.getEmail().equals(email)) {
				return false;
			}
		}

		return true;
	}

	public boolean checkIdentity(String nationalIdentity) {
		List<Candidate> candidates = getAll().getData();

		for (Candidate candidate : candidates) {
			if (candidate.getNationalityId().equals(nationalIdentity)) {
				return false;
			}
		}

		return true;
	}

}
