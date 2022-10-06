import React, {useRef, useState} from 'react';
import '../../../css/common.css'
import fileupload from '../../../images/fileupload.png';
import information from '../../../images/information.png';
import approved from '../../../images/approved.png';
import cancel from '../../../images/cancel.png';

const ProfileUploadCard = (props) => {
    const [image, setImage] = useState();

    const getUploadImage = () => {
        return (<>
            <div className={'fileupload'}>
                <img className={'fileuploadImage'} src={fileupload}/>
                <div className={'fileuploadText'}>Select Image</div>
            </div>
        </>);
    }

    const imageInput = useRef();

    const handleClick = () => {
        imageInput.current.click();
    }

    const encodeFileToBase64 = (fileBlob) => {
        props.setImageSrc(fileBlob);
        const reader = new FileReader();
        reader.readAsDataURL(fileBlob);
        return new Promise((resolve) => {
            reader.onload = () => {
                setImage(reader.result);
                resolve();
            };
        });
    };

    return <>
        <div className={'uploadView'}>

            <div className={'uploadImageMask'} onClick={handleClick}>
                <input className={'uploadInput'} type={"file"} accept={"image/png, image/jpg, image/jpeg"}
                       onChange={(event) => {
                           encodeFileToBase64(event.target.files[0]);
                       }}
                       ref={imageInput}/>

                {image ? <img className={'successImage'}
                                 src={image}
                                 alt={null}/> : getUploadImage()}
            </div>

            <div className={'uploadContent'}>
                <textarea className={'uploadContentTextarea'} value={props.content}/>
            </div>
        </div>
    </>
}

export default ProfileUploadCard;