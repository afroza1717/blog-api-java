import { useState, useEffect } from "react";
import Signout from "../../Authentication/Signin/Signout";

const Navbar = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [username, setUsername] = useState(" ");
    // console.log(username);

    useEffect(() => {
        // Check if the token is available in localStorage
        const token = localStorage.getItem("token");
        console.log(token);

        const storedUsername = localStorage.getItem("username");
        console.log(storedUsername);

        if (token) {
            setIsAuthenticated(true);
            if (storedUsername) {
                setUsername(storedUsername);
            }
        }
    }, []);

    const handleLogout = () => {
        // Clear user data and token from local storage
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        setIsAuthenticated(false); // Update authentication state
        setUsername(""); // Clear username on logout
    };

    return (
        <div className="md:px-16">
            <div className="navbar bg-base-200">
                <div className="navbar-start">
                    <div className="dropdown">
                        <div
                            tabIndex={0}
                            role="button"
                            className="btn btn-ghost lg:hidden"
                        >
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                className="h-5 w-5"
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                            >
                                <path
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    strokeWidth="2"
                                    d="M4 6h16M4 12h8m-8 6h16"
                                />
                            </svg>
                        </div>
                        <ul
                            tabIndex={0}
                            className="menu menu-sm dropdown-content bg-base-100 rounded-box z-[1] mt-3 w-52 p-2 shadow"
                        >
                            {isAuthenticated && (
                                <ul className="menu menu-horizontal px-1">
                                    {/* <li>
                                        <a>Dashboard</a>
                                    </li> */}
                                    <li>
                                        <a href="">Blog</a>
                                    </li>
                                    <li>
                                        <a>{username}</a>
                                    </li>
                                   
                                </ul>
                            )}
                        </ul>
                    </div>
                    <a href="/" className=" text-2xl font-bold">
                        <span className="text-[#EE5836]">Bite</span>
                        <span className="text-[#2149C8]">Share</span>
                    </a>
                </div>
                <div className="navbar-center hidden lg:flex">
                    <ul className="menu menu-horizontal px-1 font-semibold">
                        {isAuthenticated && (
                            <ul className="menu menu-horizontal px-1">
                                {/* <li>
                                    <a href="/dashboard">Dashboard</a>
                                </li> */}
                                <li>
                                    <a href="/blog">Blog</a>
                                </li>
                              
                            </ul>
                        )}
                    </ul>
                </div>
                <div className="navbar-end">
                    {isAuthenticated ? (
                        <div className="flex items-center space-x-4 font-semibold">
                            <p className="hidden md:block">{username}</p>
                            <Signout onLogout={handleLogout} />
                        </div>
                    ) : (
                        <div className="menu menu-horizontal px-1">
                            <a
                                href="/signin"
                                className="btn bg-[#EE5836] text-white px-10 hover:bg-[#e29a8a]"
                            >
                                Sign In
                            </a>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Navbar;
