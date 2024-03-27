import { BrowserRouter, Routes, Route,} from 'react-router-dom';
import MainPage from './Component/Page/MainPage/MainPage';
import LoginPage from './Component/Page/LoginPage/LoginPage';
import FindId from './Component/Page/FindId/FindId';
import FindPw from './Component/Page/FindPw/FindPw';
import RegisterPage from './Component/Page/RegisterPage/RegisterPage';
import { Auth } from './hoc/Auth';
import ForbiddenPage from './Component/Page/ForbiddenPage/ForbiddenPage';
import { CreatePost } from './Component/Page/BoradPage/CreatePost';
import DaumAddress from './Component/Page/BoradPage/DaumAddress';



const AuthenticatedMainPage = Auth(MainPage);


function App() {



  return (  

      <BrowserRouter initialEntries={['/main']}>
        <Routes>
          <Route path="/main" element={<AuthenticatedMainPage/>}></Route>
          <Route path="/login" element={<LoginPage />}></Route>
          <Route path='/findid' element={<FindId />}></Route>
          <Route path='/findpw' element={<FindPw />}></Route>
          <Route path='/registeruser' element={<RegisterPage />}></Route>
          <Route path='/createpost' element={<CreatePost/>}/>
          <Route path="/address" element={<DaumAddress/>} />
          <Route path="/forbidden" element={<ForbiddenPage/>} />

        </Routes>
      </BrowserRouter>

  );
}

export default App;
