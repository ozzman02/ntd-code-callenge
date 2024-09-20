/* eslint-disable react/prop-types */
import { Navigate } from 'react-router-dom';
import useAuthorizationContext from '../hooks/UseAuthorizationContext';

const PrivateRoute = ({ Component }) => {

    const { isUserAuthenticated } = useAuthorizationContext();

    return isUserAuthenticated() ? <Component/> : <Navigate to="/" />;
        
};

export default PrivateRoute;