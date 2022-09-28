import React, {useState} from 'react';
import '../../../css/common.css'
import missionImageExample from '../../../images/missionExample.jpeg';

const SmallSuccessCard = (props) => {
    const [successContent, setSuccessContent] = useState("success Content");
    // const [missionImage, setMissionImage] = useState(props.image);

    return <>
        <div className={'smallSuccessView'}>
            <div className={'smallSuccessImageMask'}>
                <img className={'smallSuccessImage'}
                     src={missionImageExample}
                     alt={null}/>
            </div>

            <div className={'smallSuccessCardContent'}>
                {successContent}
            </div>
        </div>
    </>
}

export default SmallSuccessCard;