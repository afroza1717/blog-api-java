# Dockerfile for React Frontend

# Use the official Node.js image from the Docker Hub
FROM node:18

# Set the working directory in the container
WORKDIR /app

# Install dependencies
COPY package.json package-lock.json /app/
RUN npm install

# Copy the React project files into the container
COPY . /app/

# Build the React application
RUN npm run build

# Use a lightweight server to serve the React build
FROM nginx:alpine

# Copy the build files from the previous stage
COPY --from=0 /app/build /usr/share/nginx/html

# Expose port 80 for the web server
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]