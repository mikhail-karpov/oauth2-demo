import { useEffect } from "react";
import { Navigate } from "react-router-dom";

import useAuth from "../hooks/useAuth"
import Loader from "./Loader";
import axios from "axios";

export default function SignOut() {
    const { auth, setAuth } = useAuth();

    useEffect(() => {
        async function logout() {
            try {
                const response = await axios.post("http://localhost:8080/logout");
            } catch (error) {
                console.error(error.message);
            } finally {
                setAuth(null)
            }
        }
        logout();
    }, []);

    return auth ? <Loader /> : <Navigate to="/" replace />
}