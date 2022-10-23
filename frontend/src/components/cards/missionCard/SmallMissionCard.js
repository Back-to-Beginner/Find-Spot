import React, {useEffect, useState} from 'react';
import '../../../css/common.css'
import { Link } from 'react-router-dom';
import DetailView from '../detailView/DetailView';

const SmallMissionCard = (props) => {
    const [content, setContent] = useState("");
    const [userName, setUserName] = useState("");
    const [imagePath, setImagePath] = useState("");

    useEffect(() => {
        if (props.data) {
            setContent(props.data.content);
            setUserName(props.data.userName);
            setImagePath(props.data.imagePath);
        }
    },)

    return <>

        <div className={'smallMissionView'}>
            <div className={'smallMissionCardTitle'}>
                {content}
            </div>
                       
            <div className={'smallMissionImageMask'}>
                <a>
                 <Link to={'/detailview'}>
                    <figure>
                        <img className={'smallMissionImage'}
                             src={imagePath}
                             alt={null}/>
                        <figcaption>자세히 보기</figcaption>
                    </figure>
                </a>
                </Link>
            </div>
        </div>
    </>
}

export default SmallMissionCard;