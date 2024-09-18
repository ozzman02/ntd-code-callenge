/* eslint-disable react/prop-types */
import { createContext, useEffect, useState } from "react";

const AuthorizationContext = createContext();

function AuthorizationProvider({ children }) {

    const [user, setUser] = useState(null);

    const getAuthorizationHeader = () => {
        if (user && user.token) {
            return { Authorization: 'Bearer ' + user.token };
        } else {
            return {};
        } 
    };

    const getUser = () => {
        return JSON.parse(localStorage.getItem('user'));
    };

    const isUserAuthenticated = () => {
        return localStorage.getItem('user') !== null;
    };

    const userLogin = user => {
        localStorage.setItem('user', JSON.stringify(user));
        setUser(user);
    };

    const userLogout = () => {
        localStorage.removeItem('user');
        setUser(null);
    };

    const contextValue = {
        getAuthorizationHeader,
        user,
        getUser,
        isUserAuthenticated,
        userLogin,
        userLogout
    };

    useEffect(() => {
        const storedUser = JSON.parse(localStorage.getItem('user'));
        setUser(storedUser);
    }, []);

    return (
        <AuthorizationContext.Provider value={contextValue}>
            {children}
        </AuthorizationContext.Provider>
    );
} 

export { AuthorizationProvider };

export default AuthorizationContext;