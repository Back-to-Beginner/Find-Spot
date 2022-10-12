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
            <ul>
                <li>
                    <a href="#">
                        <figure>
                            <div className={'smallMissionImageMask'}>
                                <img className={'smallMissionImage'}
                                    src={imagePath}
                                    alt={null} />
                            </div>
                            <figcaption>자세히보기</figcaption>
                        </figure>
                    </a>
                </li>
            </ul>
        </div>
    </>
}

export default SmallMissionCard;