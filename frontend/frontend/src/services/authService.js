// Auth API aligned with backend (via Gateway 8080):
// POST /api/auth/register -> "User registered successfully"
// POST /api/auth/login -> { token, role } (store token, email from form)

import api from './api';

export function loginUser({ email, password }) {
  return api.post('/api/auth/login', { email, password });
}

export function registerUser({ email, password }) {
  return api.post('/api/auth/register', { email, password });
}

