import React from 'react';

const EmailInput = ({value, setValue}) => {
    return <>
        <div className={'emailBox'}>
            <input
                type={'email'}
                className={'emailTextInput'}
                value={value}
                onChange={(e) => {setValue(e.target.value)}}
                placeholder='Email'></input>
        </div>
    </>
}

export default EmailInput;