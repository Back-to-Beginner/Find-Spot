import React, {useState} from "react";
import Header from "../../components/header/Header";
import SmallMissionCard from "../../components/cards/missionCard/SmallMissionCard";
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import YellowButton from "../../components/button/YelloButton";
import DetailView from "../../components/cards/detailView/DetailView";

const MainPage = () => {
    const [detailView, setDetailView] = useState(false);

    const setDetailViewStyle = () => {
        return detailView ? {visibility: 'visible'} : {visibility: 'hidden'}
    }

    return <>
        <Header />
        <div className={'detailViewLayout'} style={setDetailViewStyle()}>
            <DetailView />
            <div className="mainBoxPosition">
                <div style={{ paddingTop: '50px' }} onClick={() => setDetailView(false)}>
                    <YellowButton buttonName={'Challenge!'} />
                </div>
                <div style={{ padding: '10px' }} onClick={() => setDetailView(false)}>
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
                <div style={{ padding: '10px' }} onClick={() => setDetailView(true)}>
                    <SmallMissionCard />
                </div>
                <div style={{ padding: '10px' }}>
                    <SmallSuccessCard image={''} content={''} />
                </div>
                <div className="postArrow">
                    &#5171;
                </div>
            </div>

            <div className="mainBoxPosition">
                <div style={{ paddingTop: '50px' }}>
                    <YellowButton buttonName={'Challenge this Mission'} />
                </div>
                <div style={{ padding: '10px' }}>

                    <div className='findOtherSpot'>
                        Find other Spot
                    </div>
                </div>
            </div>

        </div>
    </>
};

export default MainPage;