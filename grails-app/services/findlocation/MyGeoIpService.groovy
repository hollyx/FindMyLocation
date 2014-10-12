package findlocation

import grails.transaction.Transactional
import grails.util.BuildSettingsHolder
import javax.annotation.PostConstruct

import org.springframework.beans.factory.InitializingBean

import com.maxmind.geoip.LookupService
/**
import com.maxmind.geoip.RegionName
import com.maxmind.geoip.TimeZone
*/


import org.springframework.context.* 

/**
 * Service for determining a geographical location based on an IP. 
 * 
 * @author Radu Andrei Tanasa
 */
public class MyGeoIpService implements InitializingBean, ApplicationContextAware {

	ApplicationContext applicationContext
	
	def grailsApplication
	def grailsSettings
	def servletContext
	BuildSettingsHolder buildSettingsHolder
	def lookupService
	String FILE_LOCATION
	
	def getLocation(def ip) {
		 def location = lookupService.getLocation(ip);
/*
		 if (location) {
			 location.regionName = RegionName.regionNameByCode(location.countryCode, location.region)
			 location.timezone = TimeZone.timeZoneByCountryAndRegion(location.countryCode, location.region)
		 }
		 */
		 return location
	}
	
	@PostConstruct
	def init() {
		def dataResource = getFileLocation();
		this.lookupService = new LookupService(dataResource, 2);
	}
	
	void afterPropertiesSet() {
	}
	
    private String getFileLocation()
	{
		if(FILE_LOCATION == null)
		{
			String s1 = servletContext.getRealPath("/") + "WEB-INF/GeoLiteCity.dat"
			/*
			String datFile = grailsApplication.config.geoip.data.resource
			StringBuffer buffer = new StringBuffer()
			buildSettingsHolder = new BuildSettingsHolder()
			buffer.append(buildSettingsHolder.settings.baseDir.toString())
			buffer.append('/web-app/' + grailsApplication.config.geoip.data.resource)
			log.info("Using GeoIP database from: " + buffer.toString())
			//FILE_LOCATION = buffer.toString()
			 */
			FILE_LOCATION = s1
		}
		
		return FILE_LOCATION
	}

		
}