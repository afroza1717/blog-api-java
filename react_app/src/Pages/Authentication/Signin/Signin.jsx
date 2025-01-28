import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom"; // For redirecting
import Signout from "./Signout";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import configFile from "../../../config.json";


const Signin = () => {
    const [formData, setFormData] = useState({
        username: "",
        password: "",
    });

    // How is working useState
    // The first variable stores the current state or value,
    // and the second variable is a function that updates this state continuously.
    const [userData, setUserData] = useState(null); // New state to store user data
    const [errors, setErrors] = useState({}); // to capture validation errors from the backend
    const [showPassword, setShowPassword] = useState(false);
    const { username, password } = formData;

    // Update state with input values
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };
    const navigate = useNavigate(); // Initialize useNavigate

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(
                configFile.apiUrl + "/login",
                formData
            );
            console.log('line 40',response.data); // Handle successful login, e.g., saving token
            const userNameApi = response.data.data.username;
            console.log(userNameApi);

            setUserData(response.data);
            console.log("token", response.data.token);
            localStorage.setItem("token", response.data.token);
            localStorage.setItem("username",userNameApi);
            toast(response.data.message);
            setTimeout(() => {
                navigate("/blog"); // Redirect to home page
            }, 1000); // Delay redirection to allow the toast to display
        } catch (error) {
            // Capture validation errors from backend and set them in state
            if (error.response) {
                console.error("Response data:", error.response.data);
                console.error("Response status:", error.response.status);
                console.error("Response headers:", error.response.headers);
            } else if (error.request) {
                console.error(
                    "Request was made but no response:",
                    error.request
                );
            } else {
                console.error("Error setting up request:", error.message);
            }

            toast("There was an error with your login.");
        }
    };

    return (
        <div>
            <ToastContainer
                position="top-center" // Set the toast position to top-center
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
            />
            <div className="hero bg-[#F9FBFD] min-h-screen">
                <div className="hero-content flex-col lg:flex-row-reverse gap-20">
                    <div className="lg:text-left w-[35%] lg:mt-[-300px]">
                        <div className="py-6 text-[#161C2D]">
                            <p className="py-4">How It Works </p>
                            <p className="py-4">
                                The system expects you to put the same email
                                address that you have used to create the
                                account.
                            </p>
                            <p>
                                If you encounter any difficulties receiving the
                                designated email from us, kindly do not hesitate
                                to contact our Customer Support Team at
                                support@resumenalyzer.com
                            </p>
                        </div>
                    </div>
                    <div className="card bg-base-100 w-full max-w-sm shrink-0 shadow-xl py-8">
                        <form
                            className="card-body"
                            onSubmit={handleSubmit}
                            noValidate
                        >
                            <h1 className="text-3xl font-bold mb-4 text-[#2149C8]">
                                Sign In!
                            </h1>
                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold">
                                        Username*
                                    </span>
                                </label>
                                <input
                                    type="text"
                                    name="username"
                                    placeholder="Username"
                                    onChange={handleChange}
                                    value={username}
                                    className={`input input-bordered mb-4 ${
                                        errors.username ? "input-error" : ""
                                    }`}
                                    required
                                />
                                {/* Display backend validation error */}
                                {errors.username && (
                                    <span className="text-red-500 text-sm">
                                        {errors.username[0]}
                                    </span>
                                )}
                            </div>
                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold">
                                        Password*
                                    </span>
                                </label>
                                <input
                                    type={showPassword ? "text" : "password"}
                                    name="password"
                                    placeholder="Password"
                                    onChange={handleChange}
                                    value={password}
                                    className={`input input-bordered ${
                                        errors.password ? "input-error" : ""
                                    } mb-4`}
                                    required
                                />
                                {/* Display backend validation error */}
                                {errors.password && (
                                    <span className="text-red-500 text-sm">
                                        {errors.password[0]}
                                    </span>
                                )}

                                <div className="flex items-center">
                                    <label className="flex items-center cursor-pointer">
                                        <input
                                            type="checkbox"
                                            className="checkbox checkbox-sm mr-3"
                                            onChange={() =>
                                                setShowPassword(!showPassword)
                                            }
                                        />
                                        <span>Show Password</span>
                                    </label>
                                </div>
                            </div>
                            <div className="form-control mt-6">
                                <input
                                    type="submit"
                                    className="btn bg-[#EE5836] text-white hover:bg-[#e29a8a]"
                                    value="Sign In"
                                />
                            </div>

                            {/* <Signout></Signout> */}

                            <div className="py-5">
                                <p className="mb-3 text-[#869AB8]">
                                    Don't have an account yet?{" "}
                                    <a
                                        href="/signup"
                                        className="text-[#2149C8]"
                                    >
                                        Sign Up
                                    </a>
                                </p>
                                <p className="text-[#869AB8]">
                                    Need to reset password?{" "}
                                    <a href="" className="text-[#2149C8]">
                                        Start Now
                                    </a>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Signin;

// const Signin = () => {

//     const [formData, setFormData] = useState({
//             username: '',
//             password: ''
//         }
//     )

//     const [errors, showErrors] = useState({}); // get validation error from backend
//     const [showPassword, setShowPassword] = useState(false);

//     const {username, password} = formData

//     // Set data
//     const handleChange = (e) => {
//         setFormData({
//             ...formData,
//             [e.target.name] : e.target.value
//         });
//     };

//     const handleSubmit = async (e) => {
//         e.preventDefault();

//         try{
//             const response = await axios.post('http://127.0.0.1:8000/login/', formData);
//             console.log(response.data, 'line 31'); // Handle successful login, e.g., saving token
//             alert('Login successfully.');
//         }catch(error){
//             // will show the error in details
//             if (error.response) {
//                 console.error('Response data:', error.response.data);
//                 console.error('Response status:', error.response.status);
//                 console.error('Response headers:', error.response.headers);
//             } else if (error.request) {
//                 console.error('Request was made but no response:', error.request);
//             } else {
//                 console.error('Error setting up request:', error.message);
//             }

//             alert("There was an error with your login.");

//         }
//     };

//     return (
//         <div>
//             <div className="hero bg-[#F9FBFD] min-h-screen">
//                 <div className="hero-content flex-col lg:flex-row-reverse gap-20">
//                     <div className=" lg:text-left w-[35%] lg:mt-[-300px]">

//                         <div className="py-6 text-[#161C2D]">
//                             <p className="py-4">How It Works </p>
//                             <p className="py-4">The system expects you to put the same
//                             email address that you have used to create the
//                             account. </p>
//                             If you encounter any difficulties receiving
//                             the designated email from us, kindly do not hesitate
//                             to contact our Customer Support Team at
//                             support@resumenalyzer.com
//                         </div>
//                     </div>
//                     <div className="card bg-base-100 w-full max-w-sm shrink-0 shadow-xl py-8 ">
//                         <form className="card-body" onSubmit={handleSubmit} noValidate>
//                             <h1 className="text-3xl font-bold mb-4 text-[#2149C8]">Sign In!</h1>
//                             <div className="form-control">
//                                 <label className="label">
//                                     <span className="label-text font-bold">
//                                         Username*
//                                     </span>
//                                 </label>
//                                 <input
//                                     type="text"
//                                     name="username"
//                                     placeholder="Username"
//                                     onChange={handleChange}
//                                     value={username}
//                                     className={`input input-bordered md-4 ${errors.username ? 'input-error' : ''}`}
//                                     required
//                                 />
//                                 {/* Display backend validation error */}
//                                 {errors.username && (
//                                     <span className="text-red-500 text-sm">{errors.username}</span>
//                                 )}
//                             </div>
//                             <div className="form-control">
//                                 <label className="label">
//                                     <span className="label-text font-bold">
//                                         Password*
//                                     </span>
//                                 </label>
//                                 <input
//                                     type="password"
//                                     name="password"
//                                     placeholder="Password"
//                                     onChange={handleChange}
//                                     value={password}
//                                     className={`input input-bordered ${errors.password ? 'input-error' : ''} mb-4`}
//                                     required
//                                 />

//                                 {/* Display backend validation error */}
//                                 {errors.password && (
//                                     <span className="text-red-500 text-sm">{errors.password}</span>
//                                 )}

//                                 <div className="flex items-center">
//                                     <label className="flex items-center cursor-pointer">
//                                         <input
//                                             type="checkbox"
//                                             className="checkbox checkbox-sm mr-3"
//                                             onChange={()=>setShowPassword(!showPassword)}
//                                         />
//                                         <span>Show Password</span>
//                                     </label>
//                                 </div>
//                             </div>
//                             <div className="form-control mt-6">
//                                 <button className="btn bg-[#EE5836] text-white  hover:bg-[#e29a8a]">
//                                     Signin
//                                 </button>
//                             </div>
//                             <div className="py-5">
//                                 <p className="mb-3 text-[#869AB8]">Don't have an account yet ? <a href='/signup' className="text-[#2149C8]">Sign Up</a></p>
//                                 <p className="text-[#869AB8]">Need to reset password? <a href='' className="text-[#2149C8]">Start Now</a></p>
//                             </div>
//                         </form>
//                     </div>
//                 </div>
//             </div>
//         </div>
//     );
// };

// export default Signin;
