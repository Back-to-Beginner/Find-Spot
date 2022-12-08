import React, {useEffect, useState} from "react";
import Header from '../../components/header/Header'
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import CommentCard from "../../components/cards/commentCard/CommentCard";
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";

const GroupPage = (props) => {
    const parm = useParams();
    const navigate = useNavigate();
    const [group, setGroup] = useState({});
    const [users, setUsers] = useState([]);
    const [success, setSuccess] = useState([]);

    useEffect(() => {
        axios({
            method: "get",
            url: `/groups/${parm.id}`,
        }).then(r => {
            r.data.data && setGroup(r.data.data);
            r.data.data && setUsers(r.data.data.users);
        }).catch(r => {
            sessionStorage.setItem("groupId", null);
            navigate('/group');
        }).then(r => axios({
            method: "get",
            url: `/posts/types/s/groups/${parm.id}`,
        }).then(r => {
            r.data.data[0] && setSuccess(r.data.data);
        }));
    }, []);

    const quitGroup = () => {
        axios({
            method: "patch",
            url: `/users/${sessionStorage.getItem('id')}/group-quit`
        }).then(r => {
            sessionStorage.setItem("groupId", null);
        }).then(r => {
            window.location.href = '/group'
        });
    }

    return (<>
        <Header/>
        <div className={'profilePage'}>
            <div className={'registerformLocation'}>
                <span className={'mainPageTitle'} style={{paddingTop: '50px'}}>@{group.name}</span>
                {group && (<>
                    <div className={'profilePageHeader'}>
                        <div className={'profile'}>
                                <span className={'description'} style={{width: "400px"}}>
                                    {group.info}
                                </span>
                        </div>
                        <div className={'profileSub'}>
                            <span>{users.length} User</span>
                            <span>total {success.length} Success</span>
                        </div>
                    </div>
                    <h1>Group Success</h1>
                    <div className={'cardGrid'}>
                        {success.map(card =>
                            <div style={{padding: '5px'}}>
                                <SmallSuccessCard data={card}/>
                            </div>
                        )}
                    </div>
                    <h1 style={{paddingTop: "30px"}}>Chat</h1>
                    <div style={{padding: '5px'}}>
                        <CommentCard/>
                    </div>
                    <input value={'Leave'} type={'button'}
                           style={{width: '100px', marginTop: '10px', fontSize: '24px'}}
                           onClick={() => quitGroup()}
                    />
                    <br/>
                </>)}
            </div>
        </div>
    </>)
}

export default GroupPage;