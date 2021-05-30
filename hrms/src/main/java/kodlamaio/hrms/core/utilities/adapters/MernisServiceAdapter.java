package kodlamaio.hrms.core.utilities.adapters;

import java.rmi.RemoteException;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.utilities.result.ErrorResult;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.entities.concretes.Candidate;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements MernisService {

	@Override
	public Result checkIfRealPerson(Candidate candidate) {
		boolean result = false;

		
		/*KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		boolean result = true;
		
	
			try {
				result = kpsPublicSoapProxy.TCKimlikNoDogrula(
						Long.parseLong(candidate.getNationalityId()),
						candidate.getFirstName().toUpperCase(),
						candidate.getLastName().toUpperCase(), 
						candidate.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()
						);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

			if(result==true) {
				return new SuccessResult("Mernis Kontrolü Başarılı");
			}else {
				return new ErrorResult("Mernis Kontrolü Başarısız");

			}
		
	}

}
