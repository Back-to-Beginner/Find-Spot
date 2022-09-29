import React from "react";
import Header from "../../components/header/Header";
import SmallMissionCard from "../../components/cards/missionCard/SmallMissionCard";
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import YellowButton from "../../components/button/YelloButton";

const MainPage = () => {

    return <>
        <Header />


        <div className="mainPageWave">
            <div className='mainPageTitle'>
                New Success
            </div>
            <div className="cardViewLocation">
                <div className="previousArrow">
                    &#5176;
                </div>
                <div style={{ padding: '10px' }}>
                    <SmallMissionCard />
                    </div>
                <div style={{ padding: '10px' }}>
                    <SmallSuccessCard image={''} content={''}/>
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