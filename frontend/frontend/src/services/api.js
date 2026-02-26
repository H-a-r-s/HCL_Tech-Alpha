import axios from 'axios';

// Base URL: API Gateway running on localhost:8080

const api = axios.create({
    baseURL: 'http://localhost:8080'
});

api.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

api.interceptors.response.use(
    res => res,
    err => {
        const status = err.response?.status;

        if (status === 401) {
            // Token expired or invalid
            localStorage.removeItem('token');
            localStorage.removeItem('user');

            if (!window.location.pathname.includes('/login')) {
                window.location.href = '/login';
            }

        } else if (status === 403) {
            // User doesn't have permission
            console.warn('Access forbidden');
        }

        return Promise.reject(err);
    }
);

export default api;