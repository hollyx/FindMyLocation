
package myip

class StartController {
    
    def geoIpService
    def index() {
        def defaultLocation = [latitude: 40.7141, longitude: 74.0064, countryName: "United States", countryCode: "US"]
        def message = "78.153.217.226"
        def message2 = "3.255.255.255"
        def message3 = request.remoteAddr
        def ans = geoIpService.getLocation(message)
        if ( ans == null) {
            ans = defaultLocation
        }
        
        def country = ans.countryName
        [country:country]
    }
}
