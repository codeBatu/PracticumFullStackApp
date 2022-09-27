import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import NavBar from './component/Navbar/Navbar'
import Login from './component/login/login'

function App() {
  return (
    <>
      <BrowserRouter>
        <NavBar></NavBar>
        <Routes>
          <Route path="/login" element={<Login />} />
        </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
