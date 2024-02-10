import { useState } from 'react';
import './RegisterPage.css'
import { Link } from 'react-router-dom';
import axios from 'axios';

const RegisterPage = () => {


    const [changeNextForm, setChangeNextForm] = useState(false);
    const [checkedUniqueId, setcheckedUniqueId] = useState({
        state: false,
        massage: '',
    });

    // form 데이터
    const [formData, setFormData] = useState({
        id: '',
        password: '',
        confirmPassword: '',
        email: '',
        phone: '',
        name: '',
        birth: '',
        gender: '',
        nickname: '',
        find_question: '',
        find_answers: '',
    });
    const [resData, setResData] = useState('');

    // validation
    const [errors, setErrors] = useState({
        id: '',
        password: '',
        confirmPassword: '',
        email: '',
        phone: '',
        name: '',
        birth: '',
        gender: '',
        nickname: '',
        find_question: '',
        find_answers: '',
    });


    // 유효성 검사
    const vaildateForm = (e) => {
        let vaild = false;

        const newErrors = { ...errors };
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z\d!@#$%^&*()_+]{8,}$/;
        const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
        const koreanPhoneNumberRegex = /^\d{3}-\d{3,4}-\d{4}$/;

        // 아이디


        if (formData.id === '') {
            vaild = false;
            newErrors.id = '아이디를 입력하세요.';
        } else if (formData.id.length <= 5) {
            vaild = false;
            newErrors.id = '아이디는 5자 이상이어야 합니다.';
        } else if (!checkedUniqueId.state) {
            vaild = false;
            newErrors.id = '아이디를 다시 확인해주세요.'
        } else {
            vaild = true;
            newErrors.id = '';
        }

        // 비밀번호
        if (formData.password === '') {
            vaild = false;
            newErrors.password = '비밀번호를 입력하세요.';
        } else if (!passwordRegex.test(formData.password) && formData.password.length < 8) {
            vaild = false;
            newErrors.password = '비밀번호는 최소 8자 이상이어야 하며, 대문자, 소문자, 숫자, 특수문자를 모두 포함해야 합니다.';
        } else {
            vaild = true;
            newErrors.password = '';
        }

        // 비밀번호 확인
        if (formData.confirmPassword === '') {
            vaild = false;
            newErrors.confirmPassword = '비밀번호를 입력하세요.';
        } else if (formData.password !== formData.confirmPassword) {
            vaild = false;
            newErrors.confirmPassword = '비밀번호가 동일하지 않습니다.';
        } else {
            vaild = true;
            newErrors.confirmPassword = '';
        }

        //이메일 유효성 검사
        if (formData.email === '') {
            vaild = false;
            newErrors.email = '이메일을 입력하세요.';
        } else if (!emailRegex.test(formData.email)) {
            vaild = false;
            newErrors.email = '유효하지 않는 이메일입니다.';
        } else {
            vaild = true;
            newErrors.email = '';
        }

        //휴대폰 유효성 검사
        if (formData.phone === '') {
            vaild = false;
            newErrors.phone = '전화번호를 입력하세요.';
        } else if (!koreanPhoneNumberRegex.test(formData.phone)) {
            vaild = false;
            newErrors.phone = '전화번호가 올바르지 않습니다.';
        } else {
            vaild = true;
            newErrors.phone = '';
        }


        if (vaild) {
            setChangeNextForm(true);
        }

        setErrors(newErrors)
    }


    // 휴대폰 번호 하이픈 자동 입력
    const handlePhoneNumberChange = (e) => {
        const inputPhoneNumber = e.target.value;

        // 입력된 숫자에서 숫자만 추출하여 이어붙이기
        const numericPhoneNumber = inputPhoneNumber.replace(/\D/g, '');

        // 전화번호 형식에 맞게 하이픈 추가
        let formattedPhoneNumber = '';
        if (numericPhoneNumber.length <= 3) {
            formattedPhoneNumber = numericPhoneNumber;
        } else if (numericPhoneNumber.length <= 7) {
            formattedPhoneNumber = `${numericPhoneNumber.slice(0, 3)}-${numericPhoneNumber.slice(3)}`;
        } else {
            formattedPhoneNumber = `${numericPhoneNumber.slice(0, 3)}-${numericPhoneNumber.slice(3, 7)}-${numericPhoneNumber.slice(7, 11)}`;
        }

        // 상태 업데이트
        setFormData({ ...formData, [e.currentTarget.name]: formattedPhoneNumber, })
    };


    // 폼 이전으로
    const handlePreviForm = (e) => {
        setChangeNextForm(false);
    };



    // 아이디 중복체크
    const isIdUnique = () => {
        const id = formData.id
        if (id.length >= 5) {
            //@RequstParam = {params: {}}
            axios.get('/api/is_id_unique', { params: { id: id } })
                .then((res) => {
                    if (res.data) {
                        setcheckedUniqueId(true);
                        setErrors({
                            ...errors,
                            id : "사용가능한 아이디 입니다.",
                        });
                    }else{
                        setErrors({
                            ...errors,
                            id : "중복된 아이디 이거나 사용 불가능한 아이디 입니다.",
                        });
                    }
                })
                .catch((e) => {
                    console.log(e);
                });
        }
    };





    // 폼 제출
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('제출된 폼 데이터:', formData);
    };


    const handleInputChange = (e) => {
        // 입력 필드 값 변경 시 상태 업데이트
        setFormData({
            ...formData,
            [e.currentTarget.name]: e.currentTarget.value,
        });

    };


    

    const Register = () => {
        axios.post('/api/register', formData)
            .then((response) => {
                console.log(formData)
                setResData(response.data);
                alert(response.data);
                console.log(response.data)
            })
            .catch((err) => {
                console.log(err)
                alert(err)
            })
    };


    return (
        <>
            <div className="register-body">
                <div className='register-container'>
                    <div className='register-logo'>회원가입</div>

                    {changeNextForm === false ?

                        <form onSubmit={handleSubmit}>
                            <div className='register-form'>
                                <div className='input-id'>
                                    <label htmlFor='id'>아이디</label> <input type='text' id='id' name='id' value={formData.id} onChange={handleInputChange}></input> <button onClick={isIdUnique} type='button'>중복확인</button>
                                    <div>
                                        {checkedUniqueId.massage}
                                        {errors.id}
                                    </div>
                                </div>
                                <div className='input-pw'>
                                    <label htmlFor='pw'>비밀번호</label> <input type='password' id='password' name='password' value={formData.password} onChange={handleInputChange}></input>
                                    <div>{errors.password}</div>
                                </div>
                                <div className='input-confirm-pw'>
                                    <label htmlFor='confirm-pw'>비밀번호 확인</label> <input type='password' id='confirmPassword' name='confirmPassword' value={formData.confirmPassword} onChange={handleInputChange}></input>
                                    <div>{errors.confirmPassword}</div>
                                </div>
                                <div className='input-email'>
                                    <label htmlFor='email'>이메일 주소</label> <input type='text' id='email' name='email' value={formData.email} onChange={handleInputChange}></input>
                                    <div>{errors.email}</div>
                                </div>
                                <div className='input-phone'>
                                    <label htmlFor='phone'>휴대전화번호</label> <input type='text' id='phone' name='phone' value={formData.phone} onChange={handlePhoneNumberChange}></input>
                                    <div>{errors.phone}</div>
                                </div>
                            </div>
                            <div className='btn-next'>
                                <button type='button' onClick={vaildateForm}>다 음</button>
                            </div>
                        </form>
                        : <>
                            <form>
                                <div className='register-form'>
                                    <div className='input-id'>
                                        <label htmlFor='name'>이름</label> <input type='text' id='name' name='name' value={formData.name} onChange={handleInputChange}></input>
                                    </div>

                                    <div className='input-birth'>
                                        <label htmlFor='birth'>생년월일</label> <input type='text' id='birth' name='birth' value={formData.birth} onChange={handleInputChange}></input>
                                    </div>

                                    <div className='input-gender'>
                                        <span>성별</span>
                                        <label htmlFor='male'>남자</label>
                                        <input type="radio" id="gender" name="gender" value={"m"} checked={formData.gender === 'm'} onChange={handleInputChange} />
                                        <label htmlFor='female'>여자</label>
                                        <input type="radio" id="gender" name="gender" value={"f"} checked={formData.gender === 'f'} onChange={handleInputChange} />
                                        <label htmlFor='not-select'>선택안함</label>
                                        <input type="radio" id="gender" name="gender" value={"n"} checked={formData.gender === 'n'} onChange={handleInputChange} />
                                    </div>

                                    <div className='input-nikename'>
                                        <label htmlFor='nickname'>닉네임</label> <input type='text' id='nickname' name='nickname' value={formData.nickName} onChange={handleInputChange}></input>
                                        <button>중복확인</button>
                                    </div>
                                </div>
                            </form>
                            <form className='register-form2'>
                                <div className='find-pw-question'>
                                    <span>비밀번호 찾기 질문 / 답변</span>
                                    <select name="find_question" onChange={handleInputChange}>
                                        <option >질문을 선택해주세요.</option>
                                        <option >다시 태어나면 되고 싶은 것은?</option>
                                        <option >자신의 인생 좌우명은?</option>
                                        <option >자신의 보물 제1호는?</option>
                                        <option >인상 깊게 읽은 책 이름은?</option>
                                        <option >내가 좋아하는 캐릭터는?</option>
                                    </select>
                                </div>
                                <div className='find-pw-answers'>
                                    <input type='text' id='find_answers' name='find_answers' placeholder='답변을 입력하세요.' value={formData.findAnswers} onChange={handleInputChange} />
                                </div>
                                <button onClick={handlePreviForm}>이전으로</button>
                                <Link to={'#'}><button type='submit' onClick={Register} >제출하기</button></Link>
                            </form>
                        </>
                    }
                </div>
            </div>
        </>
    );
};

export default RegisterPage;