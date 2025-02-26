import { useEffect, useRef } from 'react';
import './App.css';

function App() {
    const mapContainer = useRef(null);

    useEffect(() => {
        if(mapContainer.current) {
            var options = {
                center: new window.kakao.maps.LatLng(33.450701, 126.570667),
                level: 3
            };

            new window.kakao.maps.Map(mapContainer.current, options);
        }
        // const initializeMap = () => {
        //     if (mapContainer.current && window.kakao && window.kakao.maps) {
        //         var options = {
        //             center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        //             level: 3
        //         };

        //         new window.kakao.maps.Map(mapContainer.current, options);
        //     }
        // };

        // if (window.kakao && window.kakao.maps) {
        //     initializeMap();
        // } else {
        //     const script = document.createElement('script');
        //     script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=9cf5433065437fe9116df22537b5bf1e&autoload=false`;
        //     script.async = true;
        //     document.head.appendChild(script);

        //     script.onload = () => {
        //         window.kakao.maps.load(initializeMap);
        //     };
        // }
    }, []);

    return (
    <div className="App">
        <div className='box' ref={mapContainer}></div>
    </div>
    );
}

export default App;
