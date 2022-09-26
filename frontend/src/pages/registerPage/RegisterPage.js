import React from "react";
import RegisterBox from "../../components/box/RegisterBox";
import tempLogo from "../../images/missionExample.jpeg"


const RegisterPage = () => {
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
      <div className="RegisterBoxPosition">
        <RegisterBox />
      </div>
    </div>
  </>
};

export default RegisterPage;