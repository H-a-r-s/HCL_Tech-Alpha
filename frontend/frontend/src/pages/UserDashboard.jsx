import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useCart } from '../context/CartContext';

function UserDashboard() {
  const { user } = useAuth();
  const { items, totals } = useCart();

  const itemCount = items.reduce((sum, item) => sum + item.quantity, 0);

  return (
    <section className="page dashboard-page">
      <div className="page-header">
        <div>
          <h1 className="page-title">Dashboard</h1>
          <p className="page-subtitle">Quick overview of your account.</p>
        </div>
      </div>

      <div className="dashboard-grid">
        <article className="card dashboard-card">
          <h3 className="card-title">Profile</h3>
          <p className="card-meta">Signed in as:</p>
          <p className="highlight">{user?.email}</p>
          <p className="card-meta" style={{ marginTop: '0.5rem' }}>
            Rating:
            <span className="highlight" style={{ marginLeft: '0.35rem' }}>
              ★★★★☆
            </span>
          </p>
        </article>

        <article className="card dashboard-card">
          <h3 className="card-title">Cart snapshot</h3>
          <p className="card-meta">
            {itemCount === 0
              ? 'Your cart is empty.'
              : `${itemCount} item(s) worth ₹${totals.total.toFixed(2)}`}
          </p>
          <Link to="/cart" className="btn btn-secondary">
            Go to Cart
          </Link>
        </article>

        <article className="card dashboard-card">
          <h3 className="card-title">Orders</h3>
          <p className="card-meta">View your previous purchases.</p>
          <Link to="/orders" className="btn btn-secondary">
            View Orders
          </Link>
        </article>
      </div>
    </section>
  );
}

export default UserDashboard;

