import React from "react";
import LoginBox from "../../components/box/LoginBox";
import tempLogo from "../../images/missionExample.jpeg"


const LoginPage = () => {
  return <>
    <div>
      <div className="topWave" />
      <div className="bottomWave" />
      <img className="logo" src="tempLogo"></img>
      <div className="findText">
        <span className='yellowText'>
          F
        </span>
        IND
      </div>
      <div className="spotText">
        SP
        <span className='yellowText'>
          O
        </span>
        T
      </div>
      <div className="loginBoxPosition"><LoginBox buttonName={'LOGIN'} /></div>
    </div>
  </>
};

export default LoginPage;