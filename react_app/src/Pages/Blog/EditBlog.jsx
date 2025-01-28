import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"; // Import useParams to get the blog ID from URL
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom"; // For redirecting
import configFile from '../../config.json';

const EditBlog = () => {
    const { id } = useParams(); // Get the blog ID from URL
    console.log(id, 'line 9')
    const [formData, setFormData] = useState({
        title: '',
        author: '',
        image: null,
        paragraph: '',
        is_publish: '',
    });

    const { title, author, image, paragraph, is_publish } = formData;
    const [errors, setErrors] = useState({});
    const navigate = useNavigate(); // Initialize useNavigate

    const token = localStorage.getItem('token');
    console.log("token in fetch blog", token);

    if (token == null) {
        navigate("/signin");
    }

    else {
        //alert("hi");
        // Fetch blog data when the component mounts (edit mode)
        useEffect(() => {
            const fetchBlog = async () => {
                try {
                    const response = await axios.get(configFile.apiUrl + `/compelete_update/${id}/`, {
                        headers: {
                            'Authorization': `Token ${token}`,
                        }
                    });

                    const blogData = response.data;
                    setFormData({
                        title: blogData.title || '',
                        author: blogData.author || '',
                        image: blogData.image || null,
                        paragraph: blogData.paragraph || '',
                        is_publish: blogData.is_publish || ''
                    });
                } catch (error) {
                    console.error("Error fetching blog data", error);
                }
            };

            if (id) {
                fetchBlog(); // Fetch data only in edit mode
            }
        }, [id]);


    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleImageChange = (e) => {
        setFormData({
            ...formData,
            image: e.target.files[0], // Handle file input correctly
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const token = localStorage.getItem('token');
        console.log("token", token);
        const blogData = new FormData();
        blogData.append('title', title);
        blogData.append('author', author);
        blogData.append('image', image); // Append the file if updated
        blogData.append('paragraph', paragraph);
        blogData.append('is_publish', is_publish);

        try {
            const response = await axios.put(configFile.apiUrl + `/partial_update/${id}/`, blogData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    Authorization: `Token ${token}`,
                }
            });
            toast.success(response.data.message);
            setTimeout(() => {
                navigate("/blog"); // Redirect to home page
            }, 1000);
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
    };

    return (
        <div className="hero bg-[#F9FBFD] min-h-screen w-full">
            <div className="hero-content">
                <div className="card bg-base-100 w-full max-w-lg shrink-0 shadow-xl">
                    <form
                        className="card-body"
                        encType="multipart/form-data"
                        onSubmit={handleSubmit}
                        noValidate
                    >
                        <h1 className="text-3xl font-bold mb-4 text-[#2149C8] text-center">
                            {id ? 'Edit Your Blog' : 'Write Your Blog'}
                        </h1>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">Title*</span>
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
                                <span className="text-red-500">Title is required</span>
                            )}
                        </div>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">Author*</span>
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
                            {errors.author && (
                                <span className="text-red-500">Author is required</span>
                            )}
                        </div>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">Published*</span>
                            </label>
                            <select
                                className="select select-bordered w-full max-w-sm"
                                name="is_publish"
                                value={is_publish}
                                onChange={handleChange}
                                required
                            >
                                <option value="" disabled>Select option</option>
                                <option value="Publish">Publish</option>
                                <option value="Draft">Draft</option>
                            </select>
                            {errors.is_publish && (
                                <span className="text-red-500">This field is required</span>
                            )}
                        </div>

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">Paragraph*</span>
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
                                <span className="text-red-500">Paragraph is required</span>
                            )}
                        </div>

                        {image && typeof image === 'string' && (
                            <div className="form-control">
                                <label className="label">
                                    <span className="label-text font-bold text-base">Current Image</span>
                                </label>
                                <img src={`http://127.0.0.1:8000${image}`} alt="Current blog image" className="mb-4 w-24 h-24 object-cover" />
                            </div>
                        )}

                        <div className="form-control">
                            <label className="label">
                                <span className="label-text font-bold text-base">Image Upload*</span>
                            </label>
                            <input
                                type="file"
                                name="image"
                                accept="image/jpeg,image/png,image/gif"
                                onChange={handleImageChange}
                                placeholder="Image"
                                className="input input-bordered"
                                required
                            />
                            {errors.image && (
                                <span className="text-red-500">Image is required</span>
                            )}
                        </div>

                        <div className="form-control mt-6">
                            <button className="btn bg-[#EE5836] text-white hover:bg-[#e29a8a]">
                                {id ? 'Update Blog' : 'Create Blog'}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <ToastContainer />
        </div>
    );
  }
};

export default EditBlog;
