import { useContext } from "react"
import AuthorizationContext from "../context/AuthorizationContext"

const useAuthorizationContext = () => {
    return useContext(AuthorizationContext)
};

export default useAuthorizationContext;