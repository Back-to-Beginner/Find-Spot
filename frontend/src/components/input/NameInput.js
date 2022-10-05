import React from 'react';

const NameInput = ({value, setValue}) => {
    return <>
        <div className={'emailBox'}>
            <input
                type={'text'}
                className={'emailTextInput'}
                value={value}
                onChange={(e) => {setValue(e.target.value)}}
                placeholder='Name'></input>
        </div>
    </>
}

export default NameInput;