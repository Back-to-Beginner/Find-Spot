import React, {useEffect, useState} from 'react';
import '../../../css/common.css'
import {Link} from 'react-router-dom';

const SmallMissionCard = (props) => {
    const [data, setData] = useState({});

    useEffect(() => {
        props.data && setData(props.data);
    },)

    const setStorage = () => {
        sessionStorage.setItem("imageSrc", data.imagePath);
        sessionStorage.setItem("content", data.content);
        sessionStorage.setItem("postId", data.id);
    }

    return <>
        <div className={'smallMissionView'}>
            <div className={'smallMissionCardTitle'}>
                {data.content}
            </div>
            <div className={'smallMissionImageMask'}>
                <Link to={`/detail/m`} onClick={setStorage}>
                    <figure>
                        <img className={'smallMissionImage'}
                             src={data.imagePath}
                             alt={null}/>
                        <figcaption>자세히 보기</figcaption>
                    </figure>
                </Link>
            </div>
        </div>
    </>
}

export default SmallMissionCard;