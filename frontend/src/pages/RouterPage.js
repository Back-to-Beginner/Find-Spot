import React, { Component, useState } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './loginPage/LoginPage';
import MainPage from './mainPage/MainPage';
import ProfilePage from './profilePage/ProfilePage';
import RegisterPage from './registerPage/RegisterPage';
const RouterPage = () => {

    const [isLogin, setIsLogin] = useState(false);

    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path={'/login'} element={isLogin ? <Navigate replace to='/'/> : <LoginPage />}/>
                    <Route path={'/'} element={<MainPage />} />
                    <Route path={'/user/*'} element={<ProfilePage />}/>
                    <Route path={'/login'} element={<LoginPage />}/>
                    <Route path={'/register'} element={isLogin ? <Navigate replace to='/'/> : <RegisterPage />}/>
                    <Route path={'*'} element={<Navigate replace to={'/'}/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default RouterPage;