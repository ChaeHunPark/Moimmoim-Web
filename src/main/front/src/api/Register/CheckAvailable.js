import axios from "axios";


export const checkIdAvailable = async (formikFirst, values, setSection) => {
    try {
        const response = await axios.post('/api/check-id', { id: values.id });
        // 중복 여부에 따라 처리
        if (response.data) {
            setSection(2);
        } else {
            console.log(response)
            // 중복된 아이디일 경우 처리
            formikFirst.setFieldError('id', '이 아이디는 이미 사용 중입니다.');
            alert('이 아이디는 이미 사용 중입니다.')
        }
    } catch (error) {
        console.error('아이디 중복 여부 확인 중 오류 발생:', error);
    }
}

export const checkNicknameAvailable = async (formikSecond, values) => {
    try {
        const response = await axios.post('/api/check-nickname', { nickname: values.nickname });
        // 중복 여부에 따라 처리
        if (response.data) {
            return true;
        } else {
            formikSecond.setFieldError('nickname', '이 닉네임은 이미 사용 중입니다.');
            alert('이 닉네임는 이미 사용 중입니다.')
            return false;
        }
    } catch (error) {
        console.error('닉네임 여부 확인 중 오류 발생:', error);
    }
}