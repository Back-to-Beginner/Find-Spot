import React, {useEffect, useState} from "react";
import Header from "../../components/header/Header";
import SmallMissionCard from "../../components/cards/missionCard/SmallMissionCard";
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import YellowButton from "../../components/button/YelloButton";
import DetailView from "../../components/cards/detailView/DetailView";
import axios from "axios";

const MainPage = () => {
    const [detailView, setDetailView] = useState(false);
    const [mission, setMission] = useState([]);
    const [success, setSuccess] = useState([]);
    const missionList = [];
    const setDetailViewStyle = () => {
        return detailView ? {visibility: 'visible'} : {visibility: 'hidden'}
    }

    useEffect(() => {
        axios({
            method: 'get',
            url: '/posts/types/m'
        }).then(res => {
            if (res.data.data[0]) {
                // res.data.data.map(m => {
                //     missionList.push(m)
                // });
                // setMission(missionList.pop);
                sessionStorage.setItem("missionId", res.data.data[0].id);
                setMission(res.data.data);
                getSuccess();
            }
        })
    }, [])

    const getSuccess = () => {
        axios({
            method: 'get',
            url: `/posts/missions/${sessionStorage.getItem("missionId")}/successes`
        }).then(res => {
            res.data.data && setSuccess(res.data.data[0]);
            console.log(res.data.data[0].id)
            console.log(success.id)
        })
    }

    const getNextMission = () => {
        const tempMission = missionList.pop;
        setMission(tempMission);
        missionList.push(tempMission);
        getSuccess()
    }

    return <>
        <Header/>
        <div className={'detailViewLayout'} style={setDetailViewStyle()}>
            <DetailView/>
            <div className="mainBoxPosition">
                <div style={{paddingTop: '50px'}} onClick={() => setDetailView(false)}>
                    <YellowButton buttonName={'Challenge!'}/>
                </div>
                <div style={{padding: '10px'}} onClick={() => setDetailView(false)}>
                    <div className='findSuccess'>
                        Close
                    </div>
                </div>
            </div>
        </div>
        <div className="mainPageWave">
            <div className='mainPageTitle'>
                New Success
            </div>
            <div className="cardViewLocation">
                <div className="previousArrow">
                    &#5176;
                </div>
                <div style={{padding: '10px'}} onClick={() => setDetailView(true)}>
                    <SmallMissionCard/>
                </div>
                <div style={{padding: '10px'}}>
                    <SmallSuccessCard data={success}/>
                </div>
                <div className="postArrow"
                    // onClick={() => getNextMission()}
                >
                    &#5171;
                </div>
            </div>

            <div className="mainBoxPosition">
                <div style={{paddingTop: '50px'}}>
                    <YellowButton buttonName={'Challenge this Mission'}/>
                </div>
                <div style={{padding: '10px'}}>

                    <div className='findOtherSpot'>
                        Find other Spot
                    </div>
                </div>
            </div>

        </div>
    </>
};

export default MainPage;