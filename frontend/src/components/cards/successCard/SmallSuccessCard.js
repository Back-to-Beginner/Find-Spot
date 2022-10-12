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
            <ul>
                <li>
                    <a href="#">
                        <figure>
                            <div className={'smallSuccessImageMask'}>
                                <img className={'smallSuccessImage'}
                                     src={imagePath}
                                     alt={null}/>
                            </div>
                            <figcaption>자세히보기</figcaption>
                        </figure>
                    </a>
                </li>
            </ul>
            <div className={'smallSuccessCardContent'}>
                {content}
            </div>
        </div>
    </>
}

export default SmallSuccessCard;