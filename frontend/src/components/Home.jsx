import { Link } from "react-router-dom";
import useAuth from "../hooks/useAuth";

export default function Home() {
    const { auth } = useAuth();

    return (
        <section>
            <h1>Home</h1>
            {auth
                ? <Link to={"/sign-out"}>Sign Out</Link>
                : <Link to={"/sign-in"}>Sign In</Link>
            }
        </section>
    )
}