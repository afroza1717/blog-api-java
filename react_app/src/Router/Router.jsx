import { createBrowserRouter } from "react-router-dom";
import Main from "../Layout/Main";
import Home from "../Pages/Home/Home/Home";
import Signin from "../Pages/Authentication/Signin/Signin";
import Signup from "../Pages/Authentication/Signup/Signup";
import Signout from "../Pages/Authentication/Signin/Signout";
import ProtectedRoute from "./ProtectedRoute";
import Dashboard from "../Layout/Dashboard";
import User from "../Pages/Dashboard/User";
import DashboardLanding from "../Pages/Dashboard/DashboardChart/DashboardLanding";
import BlogTable from "../Pages/Blog/BlogTable";
import Add from "../Pages/Blog/Add";
import ViewAllBlog from "../Pages/Blog/ViewAllBlog";
import SingleBlog from "../Pages/Blog/SingleBlog";
import EditBlog from "../Pages/Blog/EditBlog";
// import Blog from "../Pages/Home/Blog/Blog";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <Main></Main>,
        children: [
            {
                path: "/",
                element: <ProtectedRoute element={<Home />}></ProtectedRoute>,
            },
            {
                path: "/signin",
                element: <Signin></Signin>,
            },
            {
                path: "/signup",
                element: <Signup></Signup>,
            },
            {
                path: "/logout",
                element: <Signout></Signout>,
            },
            {
                path: "/blog",
                element: <BlogTable></BlogTable>,
            },
        ],
    },
    {
        path: "/blog",
        element: <Main></Main>,
        children: [
            {
                path: "add",
                element: <Add></Add>,
            },
            {
                path:'viewallblog',
                element:<ViewAllBlog></ViewAllBlog>
            },
            {
                path:'singleblog/:id',
                element:<SingleBlog></SingleBlog>
            },
            {
                path:'editblog/:id',
                element:<EditBlog></EditBlog>
            }

        ],
    },
    {
        path: "/dashboard",
        element: <Dashboard></Dashboard>,
        children: [
            {
                path: "user",
                element: <User></User>,
            },
            {
                path: "home",
                element: <DashboardLanding></DashboardLanding>,
            },
        ],
    },
]);
