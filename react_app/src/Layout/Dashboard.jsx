import { Link, Outlet } from "react-router-dom";
import { FaHome } from "react-icons/fa";
import { MdFileCopy } from "react-icons/md";
import { MdProductionQuantityLimits } from "react-icons/md";
import { IoPeople } from "react-icons/io5";
import { HiDocumentReport } from "react-icons/hi";
import { BiSolidCopy } from "react-icons/bi";
import { useEffect, useState } from "react";
import Signout from "../Pages/Authentication/Signin/Signout";

const Dashboard = () => {
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
        <div>
            {/* Dashboard Navbar */}

            <div className="navbar bg-[#101135] md:px-10 ">
                <div className="flex-1">
                    <a href="" className=" text-2xl font-bold">
                        <span className="text-[#EE5836]">Bite</span>
                        <span className="text-[#2149C8]">Share</span>
                    </a>
                </div>
                <div className="flex-none">
                    <div className="form-control">
                        <input
                            type="text"
                            placeholder="Search"
                            className="input input-bordered w-24 md:w-auto"
                        />
                    </div>
                    <div className="dropdown dropdown-end">
                        <div
                            tabIndex={0}
                            role="button"
                            className="btn btn-ghost btn-circle avatar"
                        >
                            <div className="w-10 rounded-full">
                                <img
                                    alt="Tailwind CSS Navbar component"
                                    src="https://img.daisyui.com/images/stock/photo-1534528741775-53994a69daeb.webp"
                                />
                            </div>
                        </div>
                        <ul
                            tabIndex={0}
                            className=" menu-sm dropdown-content bg-base-100 rounded-box z-[1] mt-3 w-52 p-2 shadow"
                        >
                            {isAuthenticated && (
                                <li>
                                    <a className="justify-between text-lg font-semibold">
                                        {username}
                                    </a>
                                </li>
                            )}

                            <li>
                                <a href="" className="justify-between hover:text-[#EE5836] ">Profile</a>
                            </li>
                            <li>
                                <a href="" className="justify-between hover:text-[#EE5836] ">Settings</a>
                            </li>
                            <li>
                                {isAuthenticated ? (
                                    <Signout onLogout={handleLogout} />
                                ) : (
                                    <a
                                        href="/signin"
                                        className="btn bg-[#EE5836] text-white px-10 hover:bg-[#e29a8a] rounded"
                                    >
                                        Sign In
                                    </a>
                                )}
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            {/* Dashboard Navbar end  */}

            <div className="drawer lg:drawer-open">
                <input
                    id="my-drawer-2"
                    type="checkbox"
                    className="drawer-toggle"
                />
                <div className="drawer-content flex flex-col items-center justify-center">
                    <Outlet></Outlet>

                    <label
                        htmlFor="my-drawer-2"
                        className="btn btn-primary drawer-button lg:hidden"
                    >
                        Open Sidebar
                    </label>
                </div>
                <div className="drawer-side">
                    <label
                        htmlFor="my-drawer-2"
                        aria-label="close sidebar"
                        className="drawer-overlay"
                    ></label>
                    <ul className="menu bg-[#101135] text-white min-h-full w-80 p-4 text-base">
                        {/* Sidebar content here */}

                        <li className="text-red-200 hover:text-white">
                            <Link to="home" className="mt-3 flex items-center">
                                <FaHome className="mr-1" />
                                Dashboard
                            </Link>
                        </li>
                        <li className="text-red-200 hover:text-white">
                            <a href="" className=" flex items-center">
                                <MdFileCopy className="mr-1" />
                                Orders
                            </a>
                        </li>
                        <li className="text-red-200 hover:text-white">
                            <a href="" className=" flex items-center">
                                <MdProductionQuantityLimits className="mr-1" />
                                Products
                            </a>
                        </li>
                        <li className="text-red-200 hover:text-white">
                            <a href="" className=" flex items-center">
                                <IoPeople className="mr-1" />
                                Customers
                            </a>
                        </li>
                        <li className="text-red-200 hover:text-white">
                            <a href="" className=" flex items-center">
                                <HiDocumentReport className="mr-1" />
                                Reports
                            </a>
                        </li>
                        <li className="text-red-200 hover:text-white">
                            <a href="" className=" flex items-center">
                                <BiSolidCopy className="mr-1" />
                                Integrations
                            </a>
                        </li>

                        <li>
                            <Link to="user">User</Link>
                        </li>
                        <hr className="text-[#EE5836]" />
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;
