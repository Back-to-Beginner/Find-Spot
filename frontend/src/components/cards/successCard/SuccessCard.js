import React, { useState } from 'react';
import '../../../css/common.css'
import missionImageExample from '../../../images/missionExample.jpeg';

const SuccessCard = (props) => {
    const [imageSrc, setImageSrc] = useState(sessionStorage.getItem("imageSrc"));
    const [content, setContent] = useState(sessionStorage.getItem("content"));
    const [uploader, serUploader] = useState(sessionStorage.getItem("uploader"));

    return <>

        <div className={'successView'}>

            <div className={'successImageMask'}>
                <img className={'successImage'}
                    src={imageSrc}
                    alt={null} />
            </div>
            <div className={'successCardContent'}>
                @{uploader} {content}
            </div>
        </div>
    </>
}

export default SuccessCard;