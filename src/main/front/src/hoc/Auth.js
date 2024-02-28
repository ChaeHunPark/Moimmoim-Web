import {useNavigate } from "react-router-dom";
import { ValidateToken } from "../api/token/ValidateToken";
import { useEffect } from "react";


export const Auth = (WrappedComponent) => {
    const AuthComponent = (props) => {
      const navigate = useNavigate();
  
      useEffect(() => {
        const checkAuthentication = async () => {
          // 실제 인증 체크 로직으로 대체하세요
          const isAuthenticated = await ValidateToken();
  
          // 인증되지 않은 경우 로그인 페이지로 리다이렉트
          if (!isAuthenticated) {
            navigate('/forbidden');
          }
          console.log(isAuthenticated === true);
        };
  
        checkAuthentication();
  
      }, [navigate]);
  
      // 인증된 경우 래핑된 컴포넌트 렌더링
      return <WrappedComponent {...props} />;
    };
  
    return AuthComponent;
  };



