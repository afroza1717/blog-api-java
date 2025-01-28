import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import "react-toastify/dist/ReactToastify.css";
import configFile from "../../../config.json";


export default function Signup() {
    const navigate = useNavigate();
    const {
        register,
        handleSubmit,
        watch,
        formState: { errors },
    } = useForm();

    const onSubmit = async (data) => {
        const { password, confirm_password } = data;

        if (password !== confirm_password) {
            toast.error("Passwords do not match!");
            return;
        }

        try {
            console.log(data, "request data");

            const response = await axios.post(
                configFile.apiUrl + "/register",
                data
            );
            console.log(response.data, "line 32");
            console.log(response.data.stripeUrl, "Stripe payment URL");

            toast.success(response.data.message);
            setTimeout(() => {
                if (data.userType == "GUEST") {
                     navigate("/signin");
                } else {
                    window.location.href = response.data.stripeUrl; // Navigate to the Stripe checkout page
                }

                //navigate(response.data.stripeUrl);
            }, 1000);
        } catch (error) {
            if (error.response) {
                toast.error(
                    `Error: ${
                        error.response.data.detail || "Something went wrong"
                    }`
                );
            } else if (error.request) {
                toast.error("No response from the server. Please try again.");
            } else {
                toast.error("Error setting up request.");
            }
        }
    };

    return (
        <div>
            <ToastContainer
                position="top-center"
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
                    <div className="lg:text-left w-[35%] lg:mt-[-450px]">
                        <div className="py-6 text-[#161C2D]">
                            <p className="py-4">How It Works</p>
                            <p className="py-4">
                                The system expects you to put the same email
                                address that you have used to create the
                                account.
                            </p>
                            <p className="py-4">
                                If you do not receive an email from
                                accounts@resumenalyzer.com within a few minutes,
                                please check your spam folder.
                            </p>
                            <p className="py-4">
                                If you have any issue receiving the intended
                                email from us, please do not hesitate to reach
                                out to the Customer Support Team at
                                support@resumenalyzer.com
                            </p>
                        </div>
                    </div>

                    <div className="card bg-base-100 max-w-full max-w-sm shrink-0 shadow-xl py-8">
                        <  form
                            className="card-body"
                            onSubmit={handleSubmit(onSubmit)}
                        >
                            <h1 className="text-3xl font-bold mb-4 text-[#2149C8]">
                                Sign Up!
                            </h1>

                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold text-base">
                                        Username*
                                    </span>
                                </label>
                                <input
                                    type="text"
                                    placeholder="Username"
                                    name="username"
                                    className="input input-bordered"
                                    {...register("username", {
                                        required: true,
                                    })}
                                />
                                {errors.username && (
                                    <span className="text-red-500">
                                        User name is required
                                    </span>
                                )}
                            </div>

                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold text-base">
                                        First Name*
                                    </span>
                                </label>
                                <input
                                    type="text"
                                    name="first_name"
                                    placeholder="First Name"
                                    className="input input-bordered"
                                    {...register("first_name", {
                                        required: true,
                                    })}
                                />
                                {errors.first_name && (
                                    <span className="text-red-500">
                                        First name is required
                                    </span>
                                )}
                            </div>

                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold text-base">
                                        Last Name*
                                    </span>
                                </label>
                                <input
                                    type="text"
                                    name="last_name"
                                    placeholder="Last Name"
                                    className="input input-bordered"
                                    {...register("last_name", {
                                        required: true,
                                    })}
                                />
                                {errors.last_name && (
                                    <span className="text-red-500">
                                        Last name is required
                                    </span>
                                )}
                            </div>

                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold text-base">
                                        Email Address*
                                    </span>
                                </label>
                                <input
                                    type="email"
                                    name="email"
                                    placeholder="Email Address"
                                    className="input input-bordered"
                                    {...register("email", { required: true })}
                                />
                                {errors.email && (
                                    <span className="text-red-500">
                                        Email is required
                                    </span>
                                )}
                            </div>
                            <p className="text-xs text-[#869AB8]">
                                We never share your email address with any 3rd
                                party company
                            </p>

                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold">
                                        Password*
                                    </span>
                                </label>
                                <input
                                    type="password"
                                    placeholder="Password"
                                    className="input input-bordered"
                                    {...register("password", {
                                        required: true,
                                        minLength: 8,
                                    })}
                                />
                                {errors.password && (
                                    <span className="text-red-500">
                                        Password must be at least 8 characters
                                        long
                                    </span>
                                )}
                            </div>

                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold">
                                        Password Confirmation*
                                    </span>
                                </label>
                                <input
                                    type="password"
                                    name="confirm_password"
                                    placeholder="Password Confirmation"
                                    className="input input-bordered"
                                    {...register("confirm_password", {
                                        required: true,
                                    })}
                                />
                                {errors.confirm_password && (
                                    <span className="text-red-500">
                                        This field is required
                                    </span>
                                )}
                                {watch("confirm_password") &&
                                    watch("password") !==
                                        watch("confirm_password") && (
                                        <span className="text-red-500">
                                            Passwords do not match
                                        </span>
                                    )}{" "}
                            </div>
                            {/* <p className="text-xs text-[#869AB8] mb-4">Enter the same password as above</p> */}

                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold">
                                        Want to be the member?
                                    </span>
                                </label>
                                
                                <label className="mt-2" htmlFor="inline-radio">Guest
                                    <input id="inline-radio" type="radio" value="GUEST" name="userType"
                                        {...register("userType", { required: true })}/>
                                </label>

                                <label className="mt-2" htmlFor="inline-radio">Regular User(5$)
                                    <input id="inline-radio" type="radio" value="REGULAR" name="userType"
                                         {...register("userType", { required: true })}/>
                                </label>

                                <label className="mt-2" htmlFor="inline-radio">Premium User(10$)
                                    <input id="inline-radio" type="radio" value="PREMIUM" name="userType"
                                        {...register("userType", { required: true })}/>
                                </label>

                            

                            </div>
                            

                            <div className="form-control mt-6">
                                <button className="btn bg-[#EE5836] text-white hover:bg-[#e29a8a]">
                                    Signup
                                </button>
                            </div>

                            <div className="py-5">
                                <p className="mb-3 text-[#869AB8]">
                                    Already have an account?{" "}
                                    <a
                                        href="/signin"
                                        className="text-[#2149C8]"
                                    >
                                        Sign In
                                    </a>
                                </p>
                                <p className="text-[#869AB8]">
                                    Need to reset your password?{" "}
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
}
