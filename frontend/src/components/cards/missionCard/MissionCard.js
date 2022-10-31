import React, {useEffect, useState} from 'react';
import '../../../css/common.css'

const MissionCard = (props) => {
    const [imageSrc, setImageSrc] = useState(sessionStorage.getItem("imageSrc"));
    const [content, setContent] = useState(sessionStorage.getItem("content"));

    return <>
        <div className={'missionView'}>
            <div className={'missionCardTitle'}>
                {content}
            </div>
            <div className={'missionImageMask'}>
                <img className={'missionImage'}
                     src={imageSrc}
                     alt={null}/>
            </div>
        </div>
    </>
}

export default MissionCard;