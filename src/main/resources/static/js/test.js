$(function() {

     var mapOptions = {
          center: new naver.maps.LatLng(37.49918743412937, 127.03455133162683),
          // center: new naver.maps.LatLng(99.19694482, 218.33569082),
          zoom: 17, // 줌 설정, 수치가 클수록 지도 확대
          zoomControl: true
     };


     var map = new naver.maps.Map('map', mapOptions);
   });