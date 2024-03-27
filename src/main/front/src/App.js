import { BrowserRouter, Routes, Route,} from 'react-router-dom';
import MainPage from './Page/MainPage/MainPage'
import LoginPage from './Page/LoginPage/LoginPage';
import FindIdPage from './Page/FindIdPage/FindIdPage';
import FindPwPage from './Page/FindPwPage/FindPwPage';
import RegisterPage from './Page/RegisterPage/RegisterPage';
import ForbiddenPage from './Page/ForbiddenPage/ForbiddenPage';
import CreatePostPage from './Page/BoradPage/CreateBoardPage/CreateBoardPage';
import FreeBoardListPage from './Page/BoradPage/FreeBoardPage/FreeBoardListPage';
import { Auth } from './hoc/Auth';



const AuthenticatedMainPage = Auth(MainPage);


function App() {



  return (  

      <BrowserRouter initialEntries={['/main']}>
        <Routes>
          <Route path="/main" element={<AuthenticatedMainPage/>}/>
          <Route path="/login" element={<LoginPage/>}></Route>
          <Route path='/findid' element={<FindIdPage/>}></Route>
          <Route path='/findpw' element={<FindPwPage/>}></Route>
          <Route path='/registeruser' element={<RegisterPage/>}/>
          <Route path='/createpost' element={<CreatePostPage/>}/>
          <Route path="/forbidden" element={<ForbiddenPage/>} />
          <Route path="freeboard" element={<FreeBoardListPage/>}/>
        </Routes>
      </BrowserRouter>

  );
}

export default App;
