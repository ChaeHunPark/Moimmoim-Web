import * as yup from 'yup';

export const validationSchemaFirst = yup.object({
    id: yup.string().min(5, '아이디는 5글자 이상이어야 합니다.').matches(/^[a-zA-Z0-9]+$/, '아이디에는 한글이 포함될 수 없습니다.').required('아이디를 입력하세요.'),
    password: yup
        .string()
        .matches(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/, '비밀번호는 최소 8자 이상이어야 하며, 영문, 숫자, 특수문자를 모두 포함해야 합니다.')
        .required('비밀번호를 입력하세요.'),
    confirmPassword: yup.string().oneOf([yup.ref('password'), null], '비밀번호가 일치하지 않습니다.').required('비밀번호를 다시 입력하세요.'),
    email: yup.string().email('유효하지 않은 이메일 형식입니다.').required('이메일을 입력하세요.'),
    phone: yup.string().matches(/^(\d{3}-\d{3}-\d{4}|\d{3}-\d{4}-\d{4})$/, '올바른 전화번호 형식이 아닙니다.').required('전화번호를 입력하세요.'),
});

export const validationSchemaSecond = yup.object({
    name: yup.string().min(2, '이름은 2글자 이상이어야 합니다.').max(30, '이름은 30글자 이하이어야 합니다.').required('이름을 입력하세요.'),
    birth: yup.string().matches(/^(19|20)\d\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/, '유효하지 않은 생일 형식입니다.').required('생일을 입력하세요.'),
    nickname: yup.string().min(3, '닉네임은 3글자 이상이어야 합니다.').required('닉네임을 입력하세요.'),
    gender: yup.string().required('성별을 선택하세요.'),
    question: yup.string().required('질문을 선택하세요.'),
    answer: yup.string().required('답변을 입력하세요.'),
    // 다른 필드에 대한 유효성 검사 추가
});