import React, {useEffect, useState} from 'react';
import '../../../css/common.css'
import axios from "axios";

const SmallSuccessCard = (props) => {
    const [imageSrc, setImageSrc] = useState();

    useEffect(() => {
        axios({
            method: 'get',
            url: `/images/post/${props.data.id}`
        }).then(res => {
            console.log(res.data.data);
            res.data.data[0] && setImageSrc(res.data.data[0].path);
        });
    });

    return <>
        <div className={'smallSuccessView'}>
            <div className={'smallSuccessImageMask'}>
                <img className={'smallSuccessImage'}
                     src={imageSrc}
                     alt={null}/>
            </div>

            <div className={'smallSuccessCardContent'}>
                {props.data.content}
            </div>
        </div>
    </>
}

export default SmallSuccessCard;