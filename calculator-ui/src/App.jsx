import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import HomePage from './pages/HomePage';

function App() {
  

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element = { <LoginPage/> }></Route>
        <Route path="/home" element = { <HomePage /> }></Route>
      </Routes>
    </BrowserRouter>  
  );
}

export default App
