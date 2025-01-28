import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ childern }) => {
    const isAuthenticated = localStorage.getItem('token'); // Check if the user is authenticated
    return isAuthenticated ? childern : <Navigate to="/signin" />;
};

export default ProtectedRoute;
