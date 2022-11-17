import React, {useEffect, useState} from 'react';
import Header from "../../components/header/Header";
import axios from "axios";
import Collection from "../../components/collection/Collection";

const CollectionPage = () => {

    const [collections, setCollections] = useState([]);

    useEffect(() => {
        axios({
            url: '/collections',
            method: 'get',
        }).then(res => {
            res.data.data && setCollections(res.data.data);
        })
    }, []);

    return <>
        <Header/>
        <div className='mainPageWave'>
            <div className='mainPageTitle'>
                ✅ Collection
            </div>
            {collections.map(c => <Collection data={c}/>)}
        </div>
    </>
}

export default CollectionPage;