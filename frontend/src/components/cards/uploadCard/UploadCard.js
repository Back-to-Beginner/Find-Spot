import React, {useRef, useState} from 'react';
import '../../../css/common.css'
import fileupload from '../../../images/fileupload.png';
import information from '../../../images/information.png';
import approved from '../../../images/approved.png';
import cancel from '../../../images/cancel.png';

const UploadCard = (props) => {
    const [imageSrc, setImageSrc] = useState('');
    const [approve, setApprove] = useState(false);

    const getUploadImage = () => {
        return (<>
            <div className={'fileupload'}>
                <img className={'fileuploadImage'} src={fileupload}/>
                <div className={'fileuploadText'}>Select Image</div>
            </div>
        </>);
    }

    const getApproveImage = () => {
        return approve ? approved : cancel;
    }

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
        const reader = new FileReader();
        reader.readAsDataURL(fileBlob);
        return new Promise((resolve) => {
            reader.onload = () => {
                setImageSrc(reader.result);
                resolve();
            };
        });
    };

    return <>
        <div className={'uploadView'}>
            <div className={'uploadImageMask'} onClick={handleClick}>
                <div className={'information'}>
                    <img className={'informationImage'} src={information}/>
                    <span className={'informationTooltipText'}>미션 사진과&nbsp;
                        <span style={{fontWeight: 'bold', textDecoration: 'underline'}}>같은 장소</span>
                        에서&nbsp;
                        <span style={{fontWeight: 'bold', textDecoration: 'underline'}}>같은 구도</span>
                        로 촬영한 <br/> 사진만 업로드 할 수 있습니다.</span>
                </div>

                <div className={'approved'}>
                    <img className={'approvedImage'} src={getApproveImage()}/>
                    <span className={'approveTooltipText'} style={getApproveTextColor()}>{getApproveText()}</span>
                </div>

                <input className={'uploadInput'} type={"file"} accept={"image/png, image/jpg, image/jpeg"}
                       onChange={(event) => {
                           encodeFileToBase64(event.target.files[0]);
                       }}
                       ref={imageInput}/>

                {imageSrc ? <img className={'successImage'}
                                 src={imageSrc}
                                 alt={null}/> : getUploadImage()}
            </div>

            <div className={'uploadContent'}>
                <textarea readOnly={!approve} className={'uploadContentTextarea'} />
                <span className={'approveContentTooltipText'} style={getApproveTextColor()}>{getApproveContentText()}</span>
            </div>
        </div>
    </>
}

export default UploadCard;