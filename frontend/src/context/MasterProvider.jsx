import { createContext } from "react";
import { AuthProvider } from "./AuthProvider";

const MasterContext = createContext({});

export const MasterProvider = ({ children }) => {
    return (
        <MasterContext.Provider value={{}}>
            <AuthProvider>
                {children}
            </AuthProvider>
        </MasterContext.Provider>
    )
}

export default MasterContext;