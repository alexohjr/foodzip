let lat = 37.49918743412937;
let lng = 127.03455133162683;

let addMarker;

$(function () {

  naverMapInit();

});


function naverMapInit() {

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function (position) {

      console.log(position);
      lat = position.coords.latitude;
      lng = position.coords.longitude;
      console.log("lat :: " + lat);
      console.log("lng :: " + lng);
    }, function (error) {
      console.error(error);
    });
  }


  var mapOptions = {
    center: new naver.maps.LatLng(lat, lng),
    // center: new naver.maps.LatLng(99.19694482, 218.33569082),
    zoom: 17, // 줌 설정, 수치가 클수록 지도 확대
    zoomControl: true,
    zoomControlOptions: {
      position: naver.maps.Position.TOP_RIGHT
    }
  };

  var map = new naver.maps.Map('map', mapOptions);

  addMarker = new naver.maps.Marker({
    map: map
  });

  naver.maps.Event.addListener(map, 'click', function(e) {

    if($('#v-pills-profile-tab').hasClass('active')) {
      addMarker.setMap(map);
      

      console.log('e :: ' + e.coord);

      console.log('e.coord.lat :: ' + e.coord.lat());
      console.log('e.coord.lng :: ' + e.coord.lng());

      $('.lat-text').text(e.coord.lat());
      $('.lng-text').text(e.coord.lng());

      addMarker.setPosition(e.coord);
    }
  });

  

}

