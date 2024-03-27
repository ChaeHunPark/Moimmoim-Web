import { useEffect } from "react";

const DaumMap = ({ onDataChange }) => {

    const initMap = () => {
        const mapContainer = document.getElementById('map'); // 지도를 표시할 div
        const mapOption = {
            center: new window.daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

        //지도를 미리 생성
        const map = new window.daum.maps.Map(mapContainer, mapOption);
        //주소-좌표 변환 객체를 생성
        const geocoder = new window.daum.maps.services.Geocoder();
        //마커를 미리 생성
        const marker = new window.daum.maps.Marker({
            position: new window.daum.maps.LatLng(37.537187, 127.005476),
            map: map
        });

        const execDaumPostcode = () => {
            new window.daum.Postcode({
                oncomplete: (data) => {
                    const addr = data.address; // 최종 주소 변수

                    // 주소 정보를 해당 필드에 넣는다.
                    document.getElementById("address").value = addr;

                    // 주소로 상세 정보를 검색
                    geocoder.addressSearch(data.address, (results, status) => {
                        // 정상적으로 검색이 완료됐으면
                        if (status === window.daum.maps.services.Status.OK) {

                            const result = results[0]; //첫번째 결과의 값을 활용

                            // 해당 주소에 대한 좌표를 받아서
                            const coords = new window.daum.maps.LatLng(result.y, result.x);
                            // 지도를 보여준다.
                            mapContainer.style.display = "block";
                            map.relayout();
                            // 지도 중심을 변경한다.
                            map.setCenter(coords);
                            // 마커를 결과값으로 받은 위치로 옮긴다.
                            marker.setPosition(coords);

                            // 위도, 경도 정보를 상위 컴포넌트로 넘긴다.
                            onDataChange({ latitude: result.y, longitude: result.x, address: addr });
                        }
                    });
                }
            }).open();
        };

        document.getElementById('search_button').addEventListener('click', execDaumPostcode);
    };

    useEffect(() => {
        initMap();
    }, []);

    return (
        <div>
            <input type="text" id="address" placeholder="주소" />
            <input type="button" id="search_button" value="주소 검색" /><br />
            <div id="map" style={{ width: '400px', height: '400px', marginTop: '10px', display: 'none' }}></div>

            <div>
                {/* <h2>위도: {latitude}</h2>
                <h2>경도: {longitude}</h2>
                <h2>주소: {address}</h2> */}
            </div>
        </div>
    );
}

export default DaumMap;