import {useState} from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import configFile from "../../../config.json";

const Signout = ()=>{
 // How is working useState
    // The first variable stores the current state or value,
    // and the second variable is a function that updates this state continuously.
    const [userData, setUserData] = useState(null); // New state to store user data
    const navigate = useNavigate(); // Initialize useNavigate
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token')); // Track authentication state based on token presence
    // Handle Logout
    const handleLogout = async () => {
        const token = localStorage.getItem('token');
        console.log("Token for logout:", token);

        try {
            const response = await axios.post(configFile.apiUrl + '/logout/', {}, {
                headers: {
                    Authorization: `Token ${token}`
                }
            });
            console.log(response.data, 'line 26');
            // Clear user data and token from local storage
            setUserData(null);
            localStorage.removeItem('token');
            toast.success(response.data.message);
            setIsAuthenticated(false); // Set authentication state to false
            // navigate(response.data.redirect); // Redirect to sign-in page
            navigate('/signin')
            // console.log('Last line 34')
        } catch (error) {
            // Capture validation errors from backend and set them in state
            if (error.response) {
                console.error('Response data:', error.response.data);
                console.error('Response status:', error.response.status);
                console.error('Response headers:', error.response.headers);
            } else if (error.request) {
                console.error('Request was made but no response:', error.request);
            } else {
                console.error('Error setting up request:', error.message);
            }
            console.error("Logout error:", error);
            alert('There was an error logging out.');
        }
    };

    return(
        <div>
            {/* <h1>Signout</h1> */}
            <ToastContainer 
                position="top-center"  // Set the toast position to top-center
                autoClose={1000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
            />
            <div className="form-control">
                <button
                    onClick={handleLogout}
                    className="bg-[#EE5836] text-white hover:bg-[#ce7c6a] py-2 px-4 rounded-lg "
                >
                    Logout
                </button>
            </div>
        </div>
    );
};

export default Signout