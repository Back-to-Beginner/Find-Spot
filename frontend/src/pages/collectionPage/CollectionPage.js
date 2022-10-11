import React, {useState} from 'react';
import School from "../../components/trip/School";
import Anyang from "../../components/trip/Anyang";
import Header from "../../components/header/Header";

const CollectionPage = () => {
    const [successCount, setSuccessCount] = useState(0);
    return <>
        <Header />
        <div className="collectionPageWave">
            <div className="collectionPageTitle">
                Collection Page
            </div>
            <div className="schoolTitle">1. 안양대학교(Anyang University)</div>
            <div className="schoolServeTitle">안양대학교는 안양시 만안구에 위치한 4년제 대학교이다.<br />
                사진의 예시는 "대신관", "수리관", "수봉관", "비전관", "아리관", "도서관" 총 6개의 장소이다.<br />
                Find Spot and Complete Missions!
            </div>
            <div className="colloectionSuccess">
                        <span>{successCount} Success</span>
                    </div>
            <div className='collectionLocation'>
                <School />
            </div>
            <div className="schoolTitle">2. 안양 8경(Anyang 8 Views)</div>
            <div className="schoolServeTitle">안양 내에는 다양한 관광지가 존재한다.<br />
                사진의 예시는 "@@@", "@@@", "@@@", "@@@", "@@@", "@@@", "@@@", "@@@" 총 8개의 장소이다.<br />
                Find Spot and Complete Missions!
            </div>
            <div className="colloectionSuccess">
                        <span>{successCount} Success</span>
                    </div>
            <div className='collectionLocation'>
                <Anyang />
            </div>
        </div>
    </>
}

export default CollectionPage;