import React from 'react';
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import LoginPage from './loginPage/LoginPage';
import MainPage from './mainPage/MainPage';
import ProfilePage from './profilePage/ProfilePage';
import RegisterPage from './registerPage/RegisterPage';
import ResultPage from './resultPage/ResultPage';
import CollectionPage from './collectionPage/CollectionPage';

const RouterPage = () => {

    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path={'/login'}
                           element={sessionStorage.getItem('id') ? <Navigate replace to='/'/> : <LoginPage/>}/>
                    <Route path={'/register'}
                           element={sessionStorage.getItem('id') ? <Navigate replace to='/'/> : <RegisterPage/>}/>
                    <Route path={'/user/:id'}
                           element={<ProfilePage/>}/>
                    <Route path={'/'} element={<MainPage/>}/>
                    <Route path={'/result'} element={<ResultPage/>}/>
                    <Route path={'/collection'} element={<CollectionPage/>}/>
                    <Route path={'*'} element={<Navigate replace to={'/'}/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    )
}

export default RouterPage;