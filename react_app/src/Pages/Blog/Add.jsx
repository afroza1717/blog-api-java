import { useForm } from "react-hook-form";
import { useState } from "react";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";
import configFile from "../../config.json";

const Add = () => {
    // How is working useState
    // The first variable stores the current state or value, 
    // and the second variable is a function that updates this state continuously.
    const [formData, setFormData] = useState({
        title: '',
        author: '',
        image: null,  // Initialize as null for file input
        paragraph: '',
        is_publish: '',
        paragraphLabel: '',
        isPublishLabel: ''
    });

    const { title, author, image, paragraph, is_publish } = formData;
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token'));
    const [errors, setErrors] = useState({}); // Add this state to store backend errors

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleImageChange = (e) => {
        console.log("Selected file:", e.target.files[0]);
        setFormData({
            ...formData,
            image: e.target.files[0], // Handle file input correctly
        });
     console.log("Selected file After:", e.target.files[0]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const token = localStorage.getItem('token');
        console.log("Token in Add blog", token);

        if (token == null) {
              navigate("/signin");
        } else {

        const blogData = new FormData();
        blogData.append('title', title);
        blogData.append('author', author);
        blogData.append('image', image); // Append the file
        console.log("Selected file After IMG:", image);

        blogData.append('paragraph', paragraph);
        blogData.append('is_publish', is_publish);

        try {
            const response = await axios.post(configFile.apiUrl + "/create_blog", blogData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `Token ${token}`,
                }
            });
            console.log(response.data, 'line 33 blog');
            toast.success(response.data.message);

            // Clear form and errors upon successful submission
            setFormData({
                title: '',
                author: '',
                image: null,
                paragraph: '',
                is_publish: '',
            });
            setErrors({}); // Clear errors

        } catch (error) {
            if (error.response) {
                const errors = error.response.data;               
                for (let key in errors) {
                    if (errors.hasOwnProperty(key)) {
                        const messages = errors[key];
                        if (Array.isArray(messages)) {
                            messages.forEach(msg => {
                                toast.error(`${key}: ${msg}`);
                            });
                        } else {
                            toast.error(`${key}: ${messages}`);
                        }
                    }
                }
                console.error('Response data:', error.response.data);
            } else if (error.request) {
                console.error('Request was made but no response:', error.request);
            } else {
                console.error('Error setting up request:', error.message);
            }
        }
      }
    };

    return (
        <div className="hero bg-[#F9FBFD] min-h-screen w-full">
            <div className="hero-content">
                <div className="card bg-base-100 w-full max-w-lg shrink-0 shadow-xl">
                    <form
                        className="card-body"
                        encType="multipart/form-data" onSubmit={handleSubmit} noValidate
                    >
                        <h1 className="text-3xl font-bold mb-4 text-[#2149C8] text-center">
                            Write Your Blog
                        </h1>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">
                                    Title*
                                </span>
                            </label>
                            <input
                                type="text"
                                name="title"
                                value={title}
                                onChange={handleChange}
                                placeholder="Title"
                                className="input input-bordered"
                                required
                            />
                            {errors.title && (
                                <span className="text-red-500">
                                    Title is required
                                </span>
                            )}
                        </div>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">
                                    Author*
                                </span>
                            </label>
                            <input
                                type="text"
                                name="author"
                                value={author}
                                onChange={handleChange}
                                placeholder="Author"
                                className="input input-bordered"
                                required
                            />
                            {errors.title && (
                                <span className="text-red-500">
                                    {errors.title[0]} {/* Display the error message */}
                                </span>
                            )}
                        </div>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">
                                    Published*
                                </span>
                            </label>
                            <select
                                className="select select-bordered w-full max-w-sm"
                                name="is_publish"
                                value={is_publish}
                                onChange={handleChange}
                                required
                            >
                                <option value="" disabled selected>
                                    Select option
                                </option>
                                <option value="Publish">Publish</option>
                                <option value="Draft">Draft</option>
                            </select>
                            {errors.option && (
                                <span className="text-red-500">
                                    {/* {errors.option.message} */}
                                    This field is required
                                </span>
                            )}
                        </div>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">
                                    Paragraph*
                                </span>
                            </label>

                            <textarea
                                name="paragraph"
                                value={paragraph}
                                onChange={handleChange}
                                placeholder="Write your message.."
                                className="textarea textarea-bordered"
                                required
                            ></textarea>
                            {errors.paragraph && (
                                <span className="text-red-500">
                                    Paragraph is required
                                </span>
                            )}
                        </div>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">
                                    Image Upload*
                                </span>
                            </label>

                            <input
                                type="file"
                                name="image"
                                accept="image/jpeg,image/png,image/gif"
                                onChange={(e) => {handleImageChange(e)}}
                                placeholder="Image"
                                className="input input-bordered"
                                required
                            />
                            {errors.image && (
                                <span className="text-red-500">
                                    Image is required
                                </span>
                            )}
                        </div>

                        <div className="form-control mt-6">
                            <button className="btn bg-[#EE5836] text-white hover:bg-[#e29a8a]">
                                Create Blog
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Add;
