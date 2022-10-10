import React, { useState } from 'react';
import '../../../css/common.css'

const SmallSuccessCard = (props) => {
    const [successContent, setSuccessContent] = useState(props.content);
    const [imageSrc, setImageSrc] = useState(props.image);

    const getImage = () => {
        return imageSrc ? imageSrc : null;
    }

    return <>
        <div className={'smallSuccessView'}>
            <ul>
                <li>
                    <a href="#">
                        <figure>
                            <div className={'smallSuccessImageMask'}>
                                <img className={'smallSuccessImage'}
                                    src={getImage()}
                                    alt={null} />
                            </div>
                            <figcaption>자세히보기</figcaption>
                        </figure>
                    </a>
                </li>
            </ul>
            <div className={'smallSuccessCardContent'}>
                {successContent}
            </div>
        </div>
    </>
}

export default SmallSuccessCard;