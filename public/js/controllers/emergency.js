var map;
var infowindow;
console.log("trying to init gmap before init");

function initialize() {
    var pyrmont = new google.maps.LatLng(37.773972, -122.431297);

    map = new google.maps.Map(document.getElementById('gmap_canvas'), {
        center: pyrmont,
        zoom: 8
    });

    console.log("trying to init gmap");

    var request = {
        location: pyrmont,
        radius: 1000,
        bounds: map.getBounds(),
        keyword: ['veterinarians']
    };
    infowindow = new google.maps.InfoWindow();
    var service = new google.maps.places.PlacesService(map);
    service.nearbySearch(request, callback);
}

function callback(results, status) {
    if (status == google.maps.places.PlacesServiceStatus.OK) {
        for (var i = 0; i < results.length; i++) {
            createMarker(results[i]);
        }
    }
}

function createMarker(place) {
    var placeLoc = place.geometry.location;
    var marker = new google.maps.Marker({
        map: map,
        position: place.geometry.location
    });

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.setContent(place.name);
        infowindow.open(map, this);
    });
}

google.maps.event.addDomListener(window, 'load', initialize);
