import { useEffect, useState } from 'react';
import OrderCard from '../components/OrderCard';
import { useAuth } from '../context/AuthContext';
import * as orderService from '../services/orderService';

function OrderHistory() {
  const { user } = useAuth();
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const userId = user?.id ?? (import.meta.env.VITE_USER_ID ? Number(import.meta.env.VITE_USER_ID) : null);

  useEffect(() => {
    if (!userId) {
      setOrders([]);
      setError('User ID is required to load orders.');
      return;
    }
    setError(null);
    (async () => {
      setLoading(true);
      try {
        const res = await orderService.getOrdersByUser(userId);
        setOrders(res.data || []);
      } catch (e) {
        setOrders([]);
        setError('Could not load orders.');
      } finally {
        setLoading(false);
      }
    })();
  }, [userId]);

  return (
    <section className="page">
      <div className="page-header">
        <div>
          <h1 className="page-title">Order History</h1>
          <p className="page-subtitle">Track your previous purchases.</p>
        </div>
      </div>

      {error && <div className="danger" style={{ marginBottom: '1rem' }}>{error}</div>}
      {loading && <div className="empty-state">Loading orders…</div>}

      {!loading && !error && orders.length === 0 && (
        <div className="empty-state">You have no orders yet.</div>
      )}

      <div className="card-grid">
        {orders.map(order => (
          <OrderCard key={order.id} order={order} />
        ))}
      </div>
    </section>
  );
}

export default OrderHistory;

