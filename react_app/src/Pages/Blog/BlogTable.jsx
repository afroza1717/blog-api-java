import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import configFile from '../../config.json';


const BlogTable = () => {
    // Set initial state of data as an empty array to avoid undefined issues
    const [data, setData] = useState([]);
    const navigate = useNavigate();
    const {id} = useParams();

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

    // Delete the blog by its ID
    const deleteHandle = async (id) => {
        const token = localStorage.getItem('token');
        console.log(token, 'line 22')
        try {
            const response = await axios.delete(configFile.apiUrl + `/delete/${id}/`, {
                headers: {
                    Authorization: `Token ${token}`,
                }
            });
            console.log(response.data, 'line 30');
            toast.success('Blog deleted successfully!');
            // Refresh the table data after deletion
            dataHandle();

        } catch (error) {
            console.error("Error deleting blog", error);
            toast.error('Error deleting blog!');
        }
    };

    // Use useEffect to fetch data when the component loads
    useEffect(() => {
        dataHandle(); // Fetch the data when the component mounts
    }, []);

    return (
        <div className="max-w-screen-xl mx-auto mt-5 my-5">
            <div className="flex justify-end md:mr-3 mr-4">
                <Link to="add">
                    <button className="btn btn-outline btn-primary ">
                        Write a blog
                    </button>
                </Link>
            </div>

            <div className="overflow-x-auto mb-5">
                <table className="table">
                    {/* Table Head */}
                    <thead>
                        <tr className="text-base">
                            <th>S/L</th>
                            <th>Image</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Paragraph</th>
                            <th>Is Published</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    {/* Table Body */}
                    <tbody>
                        {data.length > 0 ? (
                            data.map((blog, index) => (
                                <tr key={blog._id}>
                                    {console.log(blog.title, 'line 55')}
                                    <td>{index + 1}</td>
                                    <td>
                                        <div className="flex items-center gap-3">
                                            <div className="avatar">
                                                <div className="mask mask-squircle h-12 w-12">
                                                    <img
                                                        src={configFile.apiUrl + `${blog.image}`} // Use blog image from API
                                                        alt="Blog Image"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td>{blog.title?.substring(0, 20)}</td> {/* Blog title */}
                                    <td>{blog.author}</td> {/* Blog author */}
                                    <td>{blog.paragraph?.substring(0, 20)}...</td> {/* Short paragraph */}
                                    <td>{blog.is_publish ? 'Published' : 'Draft'}</td> {/* Publish status */}
                                    <td className="items-center">
                                        <Link to={`/blog/editblog/${blog.id}`}>
                                            <button className="btn btn-warning btn-xs mb-1 mr-1">
                                                Edit
                                            </button>
                                        </Link>
                                        <Link to={`singleblog/${blog.id}`}>
                                            <button className="btn btn-accent btn-xs mb-1 mr-1">
                                                View
                                            </button>
                                        </Link>
                                        <Link>
                                            <button className="btn btn-error btn-xs mb-1 mr-1" 
                                                    onClick={() => deleteHandle(blog.id)}>
                                                Delete
                                            </button>
                                        </Link>

                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="7" className="text-center">
                                    No blogs available.
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>

            <Link to="viewallblog">
                <button className="bg-[#EE5836] text-white hover:bg-[#ce7c6a] py-2 px-4 rounded-lg flex mx-auto">
                    View All Blog
                </button>
            </Link>
        </div>
    );
};

export default BlogTable;
