import React, {useEffect, useState} from "react";
import Header from '../../components/header/Header'
import SearchOpenButton from "../../components/button/SearchOpenButton";
import SmallSuccessCard from "../../components/cards/successCard/SmallSuccessCard";
import axios from "axios";
import {useParams} from "react-router-dom";
import SmallMissionCard from "../../components/cards/missionCard/SmallMissionCard";
import SmallProfileCard from "../../components/cards/profileCard/SmallProfileCard";

const ResultPage = (props) => {
    const [searchWord, setSearchWord] = useState("");
    const [searchType, setSearchType] = useState("s");
    const [result, setResult] = useState([]);
    const parm = useParams()

    useEffect(() => {
        setSearchWord(parm.searchWord);
        axios({
            url: `/posts/search/s`,
            method: 'get',
            params: {'word': parm.searchWord}
        }).then(res => {
            res.data.data && setResult(res.data.data);
        })
    }, []);

    const search = (e) => {
        setSearchType(e.target.value);

        axios({
            url: `/posts/search/${e.target.value}`,
            method: 'get',
            params: {'word': searchWord}
        }).then(res => {
            res.data.data && setResult(res.data.data);
        })
    }

    return (<>
        <Header/>
        <div className="resultPageWave">
            <div style={{backgroundColor: '#ffe03e', padding: '10px', borderRadius: '999px'}}>
                <SearchOpenButton/>
            </div>
            <br/>
            <form onChange={search}>
                <input type={"radio"} name={'searchWord'} value={'s'} defaultChecked={true}/> 성공&nbsp;&nbsp;
                <input type={"radio"} name={'searchWord'} value={'m'}/> 미션&nbsp;&nbsp;
                <input type={"radio"} name={'searchWord'} value={'u'}/> 사용자
            </form>
            <div className="resultPageTitle">
                "{searchWord}" Search Result
            </div>
            <div className="resultPageServeTitle">
                {result.length} results
            </div>
            {
                searchType === 's' &&
                <div className={'cardGrid'}>
                    {result.map(card =>
                        <div style={{padding: '5px'}}>
                            <SmallSuccessCard data={card}/>
                        </div>
                    )}
                </div>
            }
            {
                searchType === 'm' &&
                <div className={'cardGrid'}>
                    {result.map(card =>
                        <div style={{padding: '5px'}}>
                            <SmallMissionCard data={card}/>
                        </div>
                    )}
                </div>
            }
            {
                searchType === 'u' &&
                <div className={'cardGrid'}>
                    {result.map(card =>
                        <div style={{padding: '5px'}}>
                            <SmallProfileCard data={card}/>
                        </div>
                    )}
                </div>
            }
        </div>
    </>)
}

export default ResultPage;