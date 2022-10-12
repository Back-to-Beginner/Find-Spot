import React, {useState} from 'react';
import '../../../css/common.css'
import missionImageExample from '../../../images/missionExample.jpeg';

const SmallMissionCard = (props) => {
    const [missionName, setMissionName] = useState("mission Title");
    // const [missionImage, setMissionImage] = useState(props.image);

    return <>
        <div className={'smallMissionView'}>

            <div className={'smallMissionCardTitle'}>
                {missionName}
            </div>
            <div className={'smallMissionImageMask'}>
                <a href="#">
                    <figure>
                        <img className={'smallMissionImage'}
                             src={missionImageExample}
                             alt={null}/>
                        <figcaption>자세히 보기</figcaption>
                    </figure>
                </a>
            </div>
        </div>
    </>
}

export default SmallMissionCard;