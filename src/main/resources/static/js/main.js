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

  naver.maps.Event.addListener(map, 'click', function (e) {

    if ($('#v-pills-profile-tab').hasClass('active')) {


//     naver.maps.Service.reverseGeocode({
//
//       coords: e.coord,
//       // location: new naver.maps.LatLng(e.coord.lat(), e.coord.lng()),
//     }, function (status, response) {
//       if (status !== naver.maps.Service.Status.OK) {
//         return alert('Something wrong!');
//       }
//
//       var result = response.result, // 검색 결과의 컨테이너
//         items = result.items; // 검색 결과의 배열
//
//       console.log("items :: ", items);
//
//       // do Something
//     });
//
//     return;


      addMarker.setMap(map);

      $('.lat-text').text(e.coord.lat());
      $('.lng-text').text(e.coord.lng());

      addMarker.setPosition(e.coord);

      //      var coord = e.coord.lat() + "," + e.coord.lng();

      var coords = e.coord.lng() + "," + e.coord.lat();
      var coord2 = "37.499187,127.034551";

      console.log("coords :: " + coords);

      $.ajax({
        type: "GET",
        url: "main/add2",
        data: {
          "coords": coords
        },
        success: function (res) {
          console.log(res);

          var area1 = res.results[1].region.area1.name;
          var area2 = res.results[1].region.area2.name;
          var area3 = res.results[1].region.area3.name;
          var area4 = res.results[1].region.area4.name;

          var area = area1 + " " + area2 + " " + area3 + " " + area4;


          console.log("area :: " + area);
        }
      });
    }
  });



}

