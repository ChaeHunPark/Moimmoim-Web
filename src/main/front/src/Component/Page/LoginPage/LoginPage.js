import { Link } from 'react-router-dom';
import './LoginPage.css'

const LoginPage = () =>{
    
    return(<>
    <div className='login-body'>
    <div class="login-container">
        <form class="login-form">
        <div className='login-logo'>
            <Link to={'/'}>MoimMoim</Link>
        </div>
            <input type="text" id="username" name="username" required placeholder='ID'/>
            <input type="password" id="password" name="password" required placeholder='Password'/>
            <div></div>
            <button type="submit">Login</button>

            <div className='find-container'>
                <Link to={'/findid'}><nav>아이디 찾기</nav></Link>
                &nbsp;&nbsp;
                <span>|</span>
                &nbsp;&nbsp;
                <Link to={'/findpw'}><nav>비밀번호 찾기</nav></Link>
                &nbsp;&nbsp;
                <span>|</span>
                &nbsp;&nbsp;
                <Link to={'/registeruser'}><nav>회원가입</nav></Link>
            </div>

            <div className='copy-right'>
            <span>MoimMoim </span>
            Copyright © MoimMoim Corp. All Rights Reserved.
            </div>
        </form>
    </div>
        </div>
    </>)
}

export default LoginPage;