import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

function Login() {
  const { login, loading, error } = useAuth();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async e => {
    e.preventDefault();
    const ok = await login(email, password);
    if (ok) navigate('/products');
  };

  return (
    <section className="page">
      <div className="page-header">
        <h1 className="page-title">Login</h1>
        <p className="page-subtitle">Access your account and orders.</p>
      </div>

      <form className="card form-card" onSubmit={handleSubmit}>
        <div className="input-group">
          <label className="input-label" htmlFor="email">
            Email
          </label>
          <input
            id="email"
            type="email"
            className="input-field"
            value={email}
            onChange={e => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="input-group">
          <label className="input-label" htmlFor="password">
            Password
          </label>
          <input
            id="password"
            type="password"
            className="input-field"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
          />
        </div>

        {error && <div className="danger" style={{ marginBottom: '0.75rem' }}>{error}</div>}

        <button className="btn btn-primary" type="submit" disabled={loading}>
          {loading ? 'Logging in…' : 'Login'}
        </button>

        <div className="form-footer">
          New here? <Link to="/register">Create an account</Link>
        </div>
      </form>
    </section>
  );
}

export default Login;

