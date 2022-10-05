import React from 'react';

const PasswordConfirmInput = ({value, setValue}) => {

    return <>
    <div className={'passwordconfirmBox'}>
          <input
              type={'password'}
              className={'passwordConfirmTextInput'}
              value={value}
              onChange={(e) => {setValue(e.target.value)}}
              placeholder='Password Confirm'></input>
    </div>
    </>

}

export default PasswordConfirmInput;