import React, {useEffect, useState} from 'react';
import '../../../css/common.css'
import {Link} from 'react-router-dom';

const SmallSuccessCard = (props) => {
    const [data, setData] = useState({});

    useEffect(() => {
        props.data && setData(props.data);
    },)

    const setStorage = () => {
        sessionStorage.setItem("imageSrc", data.imagePath);
        sessionStorage.setItem("content", data.content);
        sessionStorage.setItem("uploader", data.userName);
        sessionStorage.setItem("postId", data.id);
    }

    return <>
        <div className={'smallSuccessView'}>
            <div className={'smallSuccessImageMask'}>
                <Link to={'/detail/s'} onClick={setStorage}>
                    <figure>
                        <img className={'smallSuccessImage'}
                             src={data.imagePath}
                             alt={null}/>
                        <figcaption>자세히 보기</figcaption>
                    </figure>
                </Link>
            </div>
            <div className={'smallSuccessCardContent'}>
                @{data.userName} {data.content}
            </div>
        </div>
    </>
}

export default SmallSuccessCard;