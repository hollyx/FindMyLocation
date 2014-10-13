package findlocation

import grails.transaction.Transactional
import grails.util.BuildSettingsHolder

@Transactional
class MyLookupService {
	
	com.maxmind.geoip.LookupService localLookupService
	String FILE_LOCATION
	 
	def grailsApplication
 
	public com.maxmind.geoip.LookupService getLookupService() {
		if(localLookupService == null) 
		{
			try 
			{
				localLookupService = new com.maxmind.geoip.LookupService(getFileLocation(), 2)
			}
			catch (e) 
			{
				log.error(e.message, e)
			}
		}
		localLookupService
	}

	private String getFileLocation()
	{
		if(FILE_LOCATION == null)
		{
			StringBuffer buffer = new StringBuffer()
			buffer.append(BuildSettingsHolder.settings.baseDir.toString())
			buffer.append('/web-app/' + grailsApplication.config.geoip.data.resource)
			log.info("Using GeoIP database from: " + buffer.toString())
			FILE_LOCATION = buffer.toString()
		}
	 
		return FILE_LOCATION
	}
}