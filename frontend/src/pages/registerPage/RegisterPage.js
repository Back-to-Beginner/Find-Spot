import React from "react";
import RegisterBox from "../../components/box/RegisterBox";
import tempLogo from "../../images/missionExample.jpeg"


const RegisterPage = () => {
  return <div style={{backgroundColor: 'white'}}>
    <div>
      <div className="topWave" />
      <div className="bottomWave" />
      <div className="loginLayout">
        <div className="loginLeft">
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
        </div>
        <div className="RegisterBoxPosition">
          <RegisterBox />
        </div>
      </div>
    </div>
  </div>
};

export default RegisterPage;