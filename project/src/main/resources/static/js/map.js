function initMap() {

    let planetarium = {
        lat: 50.431782,
        lng: 30.516382
    };

    let map = new google.maps.Map(
        document.getElementById('map'), {
            zoom: 20,
            center: planetarium
        });

    let contentString = '<div id="content">' +
        '<div id="siteNotice">' +
        '</div>' +
        '<h1 id="firstHeading" class="firstHeading">Request №12</h1>' +
        '<div id="bodyContent">' +
        '<p><b>Lost Phone</b></p>' +
        '<p>Lost phone near planetarium </p>' +
        '<p>Author: test@test.com</p>' +
        '<p>Attr: Black Phone, without screen</p>' +
        '<p>(Posted 29.02.2018).</p>' +
        '</div>' +
        '</div>';

    let infowindow = new google.maps.InfoWindow({
        content: contentString
    });


    let marker = new google.maps.Marker({
        position: planetarium,
        map: map,
        title: "Planetarium",
        icon: '../images/dolma.png',
    });

    marker.addListener('click', function () {
        infowindow.open(map, marker);
    });

}

$('.ui.sidebar').sidebar({
    context: $('.bottom.segment')
}).sidebar('attach events', '.menu .item');