import React, {useEffect, useState} from "react";
import Header from '../../components/header/Header'
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import CommentCard from "../../components/cards/commentCard/CommentCard";

const GroupPage = (props) => {
    const parm = useParams();
    const navigate = useNavigate();


    const [group, setGroup] = useState({});
    const [groupList, setGroupList] = useState([]);
    const [name, setName] = useState("");
    const [info, setInfo] = useState("");

    useEffect(() => {

        axios({
            method: "get",
            url: `/groups/${parm.id}`,
        }).then(r => {
            r.data.data && setGroup(r.data.data);
        }).catch(r => {
            navigate('/group');
        });
    }, []);

    return (<>
        <Header/>
        <div className={'profilePage'}>
            <div className={'registerformLocation'}>
                <span className={'mainPageTitle'} style={{paddingTop: '50px'}}>Group</span>
                {group && (<>
                    <input value={'Leave'} type={'button'}
                           style={{width: '50px', marginTop: '10px', fontSize: '24px'}}
                           onClick={() => {
                               window.location.href = '/group'
                           }}
                    />
                    <div className={'profilePageHeader'}>
                        <div className={'profile'}>
                                <span className={'description'}>
                                    @{group.name} : {group.info}
                                </span>
                        </div>
                        <div className={'profileSub'}>
                            {/*<span>{success.length} Success</span>*/}
                            {/*<span>{group.users.length} User</span>*/}
                        </div>
                    </div>
                    {/*Group Success*/}
                    {/*<div className={'cardGrid'}>*/}
                    {/*    {success.map(card =>*/}
                    {/*        <div style={{padding: '5px'}}>*/}
                    {/*            <SmallProfileCard data={card}/>*/}
                    {/*        </div>*/}
                    {/*    )}*/}
                    {/*</div>*/}
                    <h1>Chat</h1>
                    <div style={{padding: '5px'}}>
                        <CommentCard/>
                    </div>
                </>)}
            </div>
        </div>
    </>)
}

export default GroupPage;