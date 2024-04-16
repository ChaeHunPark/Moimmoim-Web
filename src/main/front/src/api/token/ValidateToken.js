import axios from "axios";


export const ValidateToken = async () => {
    
    const token = localStorage.getItem('token');  // 사용자의 인증 토큰
    
    try {
        // 서버로 GET 요청 보내기
        const response = await axios.get('/api/validate-token', {
            headers: {
                'Authorization': `Bearer ${token}`,  // Bearer 토큰을 헤더에 추가
            },
        });
        console.log("토큰있음")
        return true;
        // 성공적으로 데이터를 받은 경우

    } catch (error) {
        // 요청이 실패한 경우
        console.error('서버에서 데이터를 가져오는 데 실패했습니다:', error);
        return false;
    }
};
