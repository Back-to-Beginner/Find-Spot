import React from 'react';

const PasswordInput = ({value, setValue}) => {
    return <>
        <div className={'passwordBox'}>
            <input
                type={'password'}
                className={'passwordTextInput'}
                value={value}
                onChange={(e) => {setValue(e.target.value)}}
                placeholder='Password'>
            </input>
        </div>
    </>
}

export default PasswordInput;