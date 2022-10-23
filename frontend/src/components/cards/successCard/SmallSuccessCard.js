import React, {useEffect, useState} from 'react';
import '../../../css/common.css'
import { Link } from 'react-router-dom';
import DetailView from '../detailView/DetailView';

const SmallSuccessCard = (props) => {
    const [content, setContent] = useState("");
    const [userName, setUserName] = useState("");
    const [imagePath, setImagePath] = useState("");

    useEffect(() => {
        if (props.data) {
            setContent(props.data.content);
            setUserName(props.data.userName);
            setImagePath(props.data.imagePath);
        }
    });

    return <>
        <div className={'smallSuccessView'}>
            <div className={'smallSuccessImageMask'}>
                <a><Link to={'/successdetailview'}>
                    <figure>
                        <img className={'smallSuccessImage'}
                             src={imagePath}
                             alt={null}/>
                        <figcaption>자세히 보기</figcaption>
                    </figure>
                </Link></a>
            </div>
            <div className={'smallSuccessCardContent'}>
                @{userName} {content}
            </div>
        </div>
    </>
}

export default SmallSuccessCard;