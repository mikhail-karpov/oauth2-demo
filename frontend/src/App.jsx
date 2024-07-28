import { useEffect, useState } from 'react';
import { BrowserRouter, Navigate, Outlet, Routes, Route } from 'react-router-dom'
import axios from 'axios'

import Home from './components/Home';
import SignIn from './components/SignIn';
import NotFound from './components/NotFound';
import useAuth from './hooks/useAuth';
import Secret from './components/Secret';
import Loader from './components/Loader';
import SignOut from './components/SignOut';

function ProtectedRouter() {
  const { auth } = useAuth();

  return auth
    ? <Outlet />
    : <Navigate to={"/sign-in"} />
}

function App() {
  const [isLoading, setLoading] = useState(true);
  const { setAuth } = useAuth();

  useEffect(() => {
    async function getAuth() {
      try {
        const respone = await axios.get("http://localhost:8080/api/v1/user", {
          withCredentials: true
        });
        if (respone.status !== 302) {
          setAuth(respone.data);
        } else {
          throw new Error('Unauthorized');
        }
      } catch (error) {
        console.error(error.message);
      } finally {
        setLoading(false);
      }
    }
    getAuth();
  }, []);

  if (isLoading) {
    return <Loader />
  }

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/sign-in' element={<SignIn />} />
        <Route path='/sign-out' element={<SignOut />} />

        <Route element={<ProtectedRouter />}>
          <Route path='/secret' element={<Secret />} />
        </Route>

        <Route path='*' element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
