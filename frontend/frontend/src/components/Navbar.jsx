import { NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useCart } from '../context/CartContext';

function Navbar() {
  const { isAuthenticated, user, logout } = useAuth();
  const { items } = useCart();
  const navigate = useNavigate();

  const cartCount = items.reduce((sum, item) => sum + item.quantity, 0);

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <header className="navbar">
      <div className="navbar-logo">Retail Shop</div>

      <nav className="navbar-links">
        <NavLink
          to="/"
          className={({ isActive }) =>
            `navbar-link ${isActive ? 'navbar-link-active' : ''}`
          }
        >
          Home
        </NavLink>
        <NavLink
          to="/cart"
          className={({ isActive }) =>
            `navbar-link ${isActive ? 'navbar-link-active' : ''}`
          }
        >
          Cart ({cartCount})
        </NavLink>
        <NavLink
          to="/orders"
          className={({ isActive }) =>
            `navbar-link ${isActive ? 'navbar-link-active' : ''}`
          }
        >
          Orders
        </NavLink>
      </nav>

      <div className="navbar-auth">
        {isAuthenticated ? (
          <>
            <NavLink
              to="/dashboard"
              className={({ isActive }) =>
                `navbar-link ${isActive ? 'navbar-link-active' : ''}`
              }
            >
              Dashboard
            </NavLink>
            <span className="pill">{user?.email ?? 'User'}</span>
            <button className="btn btn-ghost" onClick={handleLogout}>
              Logout
            </button>
          </>
        ) : (
          <>
            <NavLink to="/login" className="btn btn-secondary">
              Login
            </NavLink>
            <NavLink to="/register" className="btn btn-primary">
              Register
            </NavLink>
          </>
        )}
      </div>
    </header>
  );
}

export default Navbar;

