package tr.gov.nvi.tckimlik.WS;

import java.rmi.RemoteException;
import java.time.ZoneId;

public class Main {

	public static void main(String[] args) {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		boolean result = true;
		
	
			try {
				result = kpsPublicSoapProxy.TCKimlikNoDogrula(
						Long.parseLong("35761811114"),
						"Merve Melis".toUpperCase(),
						"Şimşek".toUpperCase(), 
						1993
						//"".toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()
						);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(result);
	}

}
