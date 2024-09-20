import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import HomePage from './pages/HomePage';
import { AuthorizationProvider } from './context/AuthorizationContext';
import PrivateRoute from './misc/PrivateRoute'

function App() {
  
  return (
    <>
      <AuthorizationProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element = { <LoginPage/> }></Route>
            <Route path="/logout" element = { <LoginPage/> }></Route>
            <Route path="/home" element = { <PrivateRoute Component={HomePage} /> }></Route>
          </Routes>
        </BrowserRouter>
      </AuthorizationProvider>
    </>  
  );
}

export default App;
