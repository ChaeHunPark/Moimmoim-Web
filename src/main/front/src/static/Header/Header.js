import { useEffect, useState } from 'react';
import './Header.css'
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';


const Header = () =>{

    const [loginState, setLoginState] = useState('');
    const token = localStorage.getItem("token");
    
    const checkLoginStatus = () => {
        if (token) {
            setLoginState("로그아웃");
        } else {
            setLoginState("로그인 / 회원가입");
        }
    }



    useEffect(()=>{
        checkLoginStatus();
    },[])


    const handleLogout = () => {
        // 로컬 스토리지에서 토큰 삭제
        localStorage.removeItem("token");
    }

    return(<>       

                <header className="header-container">

                    <div className="left-container">
                        <div className="logo-and-category">
                            <div className="logo-box">
                                MoimMoim
                            </div>
                            <ul className="category-box">
                                <li><a href="#">홈</a></li>
                                <li><a href="#">커뮤니티</a></li>
                                <li><a>온모임</a></li>
                                <li><a>오프모임</a></li>
                            </ul>
                        </div>
                    </div>

                    <div className="right-container">
                        <div className="login-and-password-box">
                            <div className="login-box">
                            <Link to={'/login'} onClick={handleLogout}>{loginState}</Link> 
                            </div>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <div className="password-box">
                                <a href="#"> 비밀번호 찾기</a>
                            </div>
                        </div>

                        <div className="notice-and-inquiry-box">
                            <div className="notice-box">
                                <a href="#">공지</a>
                            </div>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <div className="inquiry-box">
                                <a href="#">문의</a>
                            </div>
                        </div>

                    </div>

                </header>

        
        </>)
}

export default Header;