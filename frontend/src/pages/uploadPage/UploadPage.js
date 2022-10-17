import React, {useEffect, useLayoutEffect, useState} from "react";
import Header from "../../components/header/Header";
import YellowButton from "../../components/button/YelloButton";
import axios from "axios";
import MissionCard from "../../components/cards/missionCard/MissionCard";
import UploadCard from "../../components/cards/uploadCard/UploadCard";
import {Link, Navigate} from "react-router-dom";

const UploadPage = () => {
    const [mission, setMission] = useState([]);
    const [success, setSuccess] = useState([]);

    useLayoutEffect(() => {
        axios({
            method: 'get',
            url: '/posts/types/m'
        }).then(res => {
            if (res.data.data[0]) {
                sessionStorage.setItem("missionId", res.data.data[0].id);
                setMission(res.data.data);
                getSuccess();
            }
        })
    }, []);

    const getSuccess = () => {
        axios({
            method: 'get',
            url: `/posts/parent/${sessionStorage.getItem("missionId")}/child/s`
        }).then(res => {
            console.log(res.data.data[0])
            res.data.data && setSuccess(res.data.data[0]);
        })
    }

    return (<>
        <Header/>
        <div className="mainPageWave">
            <div className='mainPageTitle'>
                Upload You're New Success
            </div>

            <div className={'detailViewLocation'}>
                <MissionCard data={mission[0]}/>
                <UploadCard data={success}/>
            </div>

            <div className="mainBoxPosition">
                <div style={{paddingTop: '50px'}}>
                    <YellowButton buttonName={'Upload !!'}/>
                </div>
                <div style={{padding: '10px'}}>
                    <Link>
                        <div className='findOtherSpot' onClick={() => window.history.back()}>
                            cancel
                        </div>
                    </Link>
                </div>
            </div>
        </div>
    </>)
};

export default UploadPage;