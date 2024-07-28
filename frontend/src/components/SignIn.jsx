import { Navigate } from "react-router-dom";
import useAuth from "../hooks/useAuth"

export default function SignIn() {
    const { auth } = useAuth();

    if (auth) {
        return <Navigate to={"/"} replace />
    }

    return (
        <section>
            <h1>Sign In</h1>
            <div>
                <a href="http://localhost:8080/oauth2/authorization/google">Sign In with Google</a>
            </div>
        </section>
    )
}