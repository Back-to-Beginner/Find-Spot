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
        sessionStorage.setItem("parentPostId", data.parentPostId);
    }

    return <>
        <div className={'smallSuccessView'}>
            <div className={'smallSuccessImageMask'}>
                <Link to={'/detail/s'} onClick={setStorage}>
                    <figure>
                        <img className={'smallSuccessImage'}
                             src={data.imagePath}
                             alt={'아직 성공하지 못한 미션입니다.'}/>
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