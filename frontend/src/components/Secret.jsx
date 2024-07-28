import useAuth from "../hooks/useAuth"

export default function Secret() {
    const { auth } = useAuth();

    return (
        <section>
            <h1>Secret Page</h1>
            <p>You're logged in as {auth.username}</p>
        </section>
    )
}