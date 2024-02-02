import './Header.css'
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';


const Header = () =>{

    return(<>       

                <header class="header-container">

                    <div class="left-container">
                        <div class="logo-and-category">
                            <div class="logo-box">
                                MoimMoim
                            </div>
                            <ul class="category-box">
                                <li><a href="#">홈</a></li>
                                <li><a href="#">커뮤니티</a></li>
                                <li><a>온모임</a></li>
                                <li><a>오프모임</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="right-container">
                        <div class="login-and-password-box">
                            <div class="login-box">
                            <Link to={'/login'}>로그인 / 회원가입</Link> 
                            </div>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <div class="password-box">
                                <a href="#"> 비밀번호 찾기</a>
                            </div>
                        </div>

                        <div class="notice-and-inquiry-box">
                            <div class="notice-box">
                                <a href="#">공지</a>
                            </div>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <div class="inquiry-box">
                                <a href="#">문의</a>
                            </div>
                        </div>

                    </div>

                </header>

        
        </>)
}

export default Header;