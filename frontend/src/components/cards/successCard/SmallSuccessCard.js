import React, {useEffect, useState} from 'react';
import '../../../css/common.css'

const SmallSuccessCard = (props) => {
    const [content, setContent] = useState("");
    const [imagePath, setImagePath] = useState("");

    useEffect(() => {
        if (props.data) {
            setContent(props.data.content);
            setImagePath(props.data.imagePath);
        }
    });

    return <>
        <div className={'smallSuccessView'}>
            <div className={'smallSuccessImageMask'}>
                <a>
                    <figure>
                        <img className={'smallSuccessImage'}
                             src={imagePath}
                             alt={null}/>
                        <figcaption>자세히 보기</figcaption>
                    </figure>
                </a>
            </div>
            <div className={'smallSuccessCardContent'}>
                {content}
            </div>
        </div>
    </>
}

export default SmallSuccessCard;