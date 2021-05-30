package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.result.DataResult;
import kodlamaio.hrms.core.utilities.result.ErrorDataResult;
import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{

	private JobPositionDao jobPositionDao;
	
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}


	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "İş pozisyonları listelendi.") ;
	}


	@Override
	public Result add(JobPosition jobPosition) {
		if(isJobPositionExist(jobPosition.getPositionName())) {
			this.jobPositionDao.save(jobPosition);
			return new SuccessResult("İş pozisyonu eklendi.");
		}else {
			return new ErrorResult("İş pozisyonu daha önce oluşturulmuş.");
		}
		
	}
	
	public boolean isJobPositionExist(String jobPositionName) {
		List<JobPosition> jobPositions = getAll().getData();
		for (JobPosition jobPosition : jobPositions) {
			if(jobPositionName.equals(jobPosition.getPositionName())) {
				return false;
			}
		}
		return true;
	}

}
