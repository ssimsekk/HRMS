package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.VerificationTokenService;
import kodlamaio.hrms.core.utilities.result.DataResult;
import kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.VerificationTokenDao;
import kodlamaio.hrms.entities.concretes.VerificationToken;

@Service
public class VerificationTokenManager implements VerificationTokenService {

	private VerificationTokenDao verificationTokenDao;
	
	@Autowired
	public VerificationTokenManager(VerificationTokenDao verificationTokenDao) {
		super();
		this.verificationTokenDao = verificationTokenDao;
	}

	@Override
	public DataResult<List<VerificationToken>> getAll() {
		return new SuccessDataResult<List<VerificationToken>>(this.verificationTokenDao.findAll(), "Tokenler listelendi.");
	}

}
