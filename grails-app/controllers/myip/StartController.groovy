
package myip

class StartController {
    
    def geoIpService
    def myLookupService
    def index() {
        def defaultLocation = [latitude: 40.7141, longitude: 74.0064, countryName: "United States", countryCode: "US"]
        def message = "78.153.217.226"
        def message2 = "3.255.255.255"
        def message3 = request.remoteAddr
        
        def service = myLookupService.getLookupService()
        def ans2 = service.getLocation(message);
        render message + " => " + ans2.countryCode + " - " + ans2.countryName
        

    }
}
