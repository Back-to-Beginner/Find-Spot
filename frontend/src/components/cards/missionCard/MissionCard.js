import React, {useState} from 'react';
import '../../../css/common.css';
import missionImageExample from '../../../images/missionExample.jpeg';

const MissionCard = (props) => {
    const [missionName, setMissionName] = useState("mission Title");
    // const [missionImage, setMissionImage] = useState(props.image);

    return <>
        <div className={'missionView'}>
            <div className={'missionCardTitle'}
                 style={{textAlign: "center", marginLeft: "auto", marginRight: "auto", width: "300px"}}>
                {missionName}
            </div>
            <div className={'missionImageMask'}>
                <img className={'missionImage'}
                     src={missionImageExample}
                     alt={null}/>
            </div>
        </div>
    </>
}

export default MissionCard;