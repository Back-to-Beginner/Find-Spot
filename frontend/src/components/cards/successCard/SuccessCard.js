import React, { useState } from 'react';
import '../../../css/common.css'
import missionImageExample from '../../../images/missionExample.jpeg';

const SuccessCard = (props) => {
    const [successContent, setSuccessContent] = useState("success Content");
    // const [missionImage, setMissionImage] = useState(props.image);

    return <>

        <div className={'successView'}>

            <div className={'successImageMask'}>
                <img className={'successImage'}
                    src={missionImageExample}
                    alt={null} />
            </div>
            <div className={'successCardContent'}>
                {successContent}
            </div>
        </div>
    </>
}

export default SuccessCard;