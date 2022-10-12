import React, {useEffect, useState} from 'react';
import '../../../css/common.css'

const SmallMissionCard = (props) => {
    const [content, setContent] = useState("");
    const [imagePath, setImagePath] = useState("");

    useEffect(() => {
        if (props.data) {
            setContent(props.data.content);
            setImagePath(props.data.imagePath);
        }
    },)

    return <>
        <div className={'smallMissionView'}>
            <div className={'smallMissionCardTitle'}>
                {content}
            </div>
            <div className={'smallMissionImageMask'}>
                <a href="#">
                    <figure>
                        <img className={'smallMissionImage'}
                             src={imagePath}
                             alt={null}/>
                        <figcaption>자세히 보기</figcaption>
                    </figure>
                </a>
            </div>
        </div>
    </>
}

export default SmallMissionCard;