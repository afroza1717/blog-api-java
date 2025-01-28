import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import configFile from '../../config.json';

const ViewAllBlog = () => {
    const [data, setData] = useState([]);

    // Function to fetch data from the API
    const dataHandle = async () => {
        try {
            const response = await axios.get(configFile.apiUrl + '/blog_list/');
            console.log(response.data, 'line 10 fetched data');
            setData(response.data); // Save the API response in the state
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    // Use useEffect to fetch data when the component loads
    useEffect(() => {
        dataHandle(); // Fetch the data when the component mounts
    }, []);

    return (
        <div className="mb-10 max-w-screen-xl mx-auto">
            <div className="py-10">
                <h1 className="text-center mt-5 mb-5 text-2xl lg:text-4xl md:text-2xl font-semibold">
                    View All Blog
                </h1>

                {/* Ensure the data is mapped properly */}
                {data.length > 0 ? (
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 px-3">
                        {data.map((blog, index) => (
                            <div key={blog.id} className="card card-compact bg-base-100 w-full shadow-xl">
                                <figure>
                                    <img
                                        src={`http://127.0.0.1:8000/${blog.image}`} // Display blog image
                                        alt="Blog Image"
                                        className="w-full h-32 md:h-48 lg:h-64 object-cover"
                                    />
                                </figure>
                                <div className="card-body">
                                    <h2 className="card-title">{blog.title}</h2>
                                    <p>{blog.paragraph.substring(0, 100)}...</p> {/* Show part of the paragraph */}
                                    <div className="card-actions justify-end">
                                        <Link to={`/blog/singleblog/${blog.id}`}>
                                            <button className="bg-[#EE5836] text-white hover:bg-[#ce7c6a] py-2 px-4 rounded-lg flex mx-auto">
                                                Details
                                            </button>
                                        </Link>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                ) : (
                    <div className="text-center">
                        <p>No blogs available.</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default ViewAllBlog;
