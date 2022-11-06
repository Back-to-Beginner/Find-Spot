import React, {useRef, useState} from 'react';
import '../../../css/common.css'
import fileupload from '../../../images/fileupload.png';
import information from '../../../images/information.png';
import loading from "../../../images/loading.gif";
import axios from "axios";

const UploadCard = (props) => {
    const [imageSrc, setImageSrc] = useState('');
    const [approve, setApprove] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    const getUploadImage = () => {
        return (<>
            <div className={'fileupload'}>
                <img className={'fileuploadImage'} src={fileupload}/>
                <div className={'fileuploadText'}>Select Image</div>
            </div>
        </>);
    }

    // const getApproveImage = () => {
    //     return approve ? approved : cancel;
    // }

    const getApproveText = () => {
        return approve ? '성공하였습니다! 게시글을 작성해 주세요!' : '조금 더 비슷한 사진으로 시도해 주세요';
    }

    const getApproveContentText = () => {
        return approve ? '작성 후 아래 업로드 버튼을 클릭해 주세요' : '먼저 미션 사진과 비슷한 사진을 올려 주세요';
    }

    const getApproveTextColor = () => {
        return approve ? {background: 'green'} : {background: 'red'};
    }

    const imageInput = useRef();

    const handleClick = () => {
        imageInput.current.click();
    }

    const encodeFileToBase64 = (fileBlob) => {
        setIsLoading(true)
        const reader = new FileReader();
        reader.readAsDataURL(fileBlob);

        const form = new FormData();
        form.append('images', fileBlob);
        axios({
            header: {'content-type': 'multipart/form-data'}, method: 'post', url: "/images/upload", data: form
        }).then(res => {
            props.imageSrc(res.data.data);
            axios({
                method: 'get',
                url: '/images/compare',
                params: {
                    'challengeUrl': res.data.data,
                    "missionId": sessionStorage.getItem('postId'),
                    "slice": 'PHA'
                }
            }).then(res => {
                if (res.data.data === true) {
                    props.approve(true);
                    setApprove(true);
                } else {
                    props.approve(false);
                    setApprove(false)
                }
                console.log(res.data.data);
                setIsLoading(false)
            })
        })

        return new Promise((resolve) => {
            reader.onload = () => {
                setImageSrc(reader.result);
                resolve();
            };
        });
    };

    return <>
        <div className={'uploadView'}>
            <div className={'information'}>
                <img className={'informationImage'} src={information}/>
                <span className={'informationTooltipText'}>⚠️ 미션 사진과&nbsp;
                    <span style={{fontWeight: 'bold', textDecoration: 'underline'}}>같은 장소</span>
                        에서&nbsp;
                    <span style={{fontWeight: 'bold', textDecoration: 'underline'}}>같은 구도</span>
                        로 촬영한 <br/> 사진만 업로드 할 수 있습니다.
                </span>
            </div>

            <div className={'uploadImageMask'} onClick={handleClick}>
                <input className={'uploadInput'}
                       type={"file"}
                       accept={"image/png, image/jpg, image/jpeg"}
                       onChange={(event) => {
                           encodeFileToBase64(event.target.files[0]);
                       }}
                       ref={imageInput}/>

                {imageSrc ? <img className={'successImage'}
                                 src={imageSrc}
                                 alt={null}/> : getUploadImage()}
            </div>
            {isLoading ? (
                <div className={"background"} style={{alignItems: 'center', padding: '20px'}}>
                    <img src={loading} alt="로딩 중" style={{width: "80px"}}/>
                </div>
            ) : (
                approve ? (
                    <div className={'uploadContent'}>
                    <textarea
                        className={'uploadContentTextarea'}
                        onChange={event => props.content(event.target.value)}
                        placeholder={"✅ 미션에 성공 하였습니다! 여기에 게시글을 작성 해주세요"}
                    />
                        <span className={'approveContentTooltipText'} style={getApproveTextColor()}>
                    {getApproveContentText()}
                </span>
                    </div>
                ) : (
                    imageSrc && <div className={"detailViewLocation"}>
                        <div style={{padding: '20px'}}>
                            <div>구도 : ❌️</div>
                        </div>
                        <div style={{padding: '20px'}}>
                            <div>색상 : ❌</div>
                        </div>
                        <div>{getApproveText()}</div>
                    </div>))}
        </div>
    </>
}

export default UploadCard;