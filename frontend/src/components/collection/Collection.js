import React, {useState} from 'react';
import SmallMissionCard from "../cards/missionCard/SmallMissionCard";
import axios from "axios";

const Collection = (props) => {

    const getImage = (id) => {
        // eslint-disable-next-line react-hooks/rules-of-hooks
        const [path, setPath] = useState()
        axios({
            url: `/images/post/${id}`,
            method: 'get',
        }).then(res => {
            setPath(res.data.data[0].path);
        });
        return path;
    }

    return <>
        <div className="collectionTitle">{props.data.name}</div>
        <div className="collectionDescribe">{props.data.description}</div>
        <div className={'schoolCardGrid'}>
            {props.data.postList.map(card =>
                <div style={{padding: '10px'}}>
                    {console.log(getImage(card.id))}
                    <SmallMissionCard data={
                        {
                            'id': card.id,
                            'imagePath': getImage(card.id),
                            'content': card.content
                        }}/>
                </div>
            )}
        </div>
    </>

}

export default Collection;