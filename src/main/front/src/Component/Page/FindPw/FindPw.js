import { useEffect } from 'react';
import './FindPw.css'
import { PageTracker } from '../../../api/PageTracker';

const FindPw = () => {

    //페이지 트랙커
    useEffect(() => {
        PageTracker(window.location.pathname);
    }, [])

    return (
        <>

            <div className='findpw-body'>
                <div className='findpw-container'>
                    <div className='findpw-logo'>
                        비밀번호 찾기
                    </div>
                    <div className='findpw-logo-comment'>회원 정보에 등록한 이메일 인증</div>
                    <div className='findpw-box'>
                        <div className='same-comment'>
                            회원정보에 등록한 아이디와 입력한 이메일이 같아야, 인증번호를 받을 수 있습니다.
                        </div>
                        <form className='findpw-form-box'>
                            <div className='findpw-input'>
                                <div className='input-id'>
                                    <label htmlFor='findpw-userid'>아이디</label>
                                    <input type="text" id="findpw-userid" name="findpw-userid" />
                                </div>
                                <div className='input-email'>
                                    <label htmlFor='findpw-useremail'>이메일</label>
                                    <input type="text" id="findpw-useremail" name="findpw-useremail" />
                                    <button>인증번호 받기</button>
                                </div>
                            </div>
                        </form>
                        <div className='verifi-number'>

                            <input type='text' id="verifi-number" name='verifi-number' placeholder="인증번호 6자리 숫자 입력" />
                        </div>
                    </div>
                    <div className='btn-check'>
                        <button> 확인 </button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default FindPw;