import React, {useState} from 'react';
import searchlogo from "../../images/magnifying_glass_icon.png";

const SearchOpenButton = (props) => {
    const [searchWord, setSearchWord] = useState();

    const goResult = () => {
        searchWord && window.location.replace(`/result/${searchWord}`)
    }

    const onChange = (e) => {
        setSearchWord(e.target.value)
    }

    return <>
        <div className={'searchOpen'}>
            <input className={'searchForm'} placeholder='Enter Search Word' value={searchWord} onChange={onChange}></input>
            <img className={'searchIcon'} src={searchlogo} onClick={goResult}/>
        </div>
    </>
}

export default SearchOpenButton;