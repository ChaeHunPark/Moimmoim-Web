    import * as Validation from './ValidationStorage';

    // 유효성 검사
    export const ValidationFirst = (formData, checkedUniqueId, setChangeNextForm, setErrors, errors) => {
        
        let vaild = false;

        const newErrors = { ...errors };

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
        } else if (!Validation.passwordRegex.test(formData.password) && formData.password.length < 8) {
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
        } else if (!Validation.emailRegex.test(formData.email)) {
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
        } else if (!Validation.koreanPhoneNumberRegex.test(formData.phone)) {
            vaild = false;
            newErrors.phone = '전화번호가 올바르지 않습니다.';
        } else {
            vaild = true;
            newErrors.phone = '';
        }


        if (vaild) {
            setChangeNextForm(true);
        }else{
            setChangeNextForm(false);
        }

        setErrors(newErrors)
    }