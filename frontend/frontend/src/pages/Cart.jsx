import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import CartItem from '../components/CartItem';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import * as orderService from '../services/orderService';

function Cart() {
  const { items, totals, clearCart } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [checkoutError, setCheckoutError] = useState(null);

  const handleCheckout = async () => {
    if (!items.length) return;
    setCheckoutError(null);
    const userId = user?.id ?? (import.meta.env.VITE_USER_ID ? Number(import.meta.env.VITE_USER_ID) : null);
    if (!userId) {
      setCheckoutError('User ID is required for orders. The auth service does not currently return user ID.');
      return;
    }
    try {
      await orderService.placeOrder({
        userId,
        items: items.map(({ id, quantity }) => ({ productId: id, quantity }))
      });
      clearCart();
      navigate('/order-confirmation');
    } catch (e) {
      setCheckoutError(e.response?.data?.message || e.response?.data || 'Failed to place order');
    }
  };

  return (
    <section className="page">
      <div className="page-header">
        <div>
          <h1 className="page-title">Your Cart</h1>
          <p className="page-subtitle">
            Review items before placing your order.
          </p>
        </div>
      </div>

      {items.length === 0 && (
        <div className="empty-state">Your cart is empty.</div>
      )}

      <div className="card-grid">
        {items.map(item => (
          <CartItem key={item.id} item={item} />
        ))}
      </div>

      {items.length > 0 && (
        <div className="cart-summary">
          <div className="stack">
            <div className="stack-row">
              <span className="muted">Subtotal</span>
              <span>₹{totals.subtotal.toFixed(2)}</span>
            </div>
            <div className="stack-row">
              <span className="muted">Tax (18%)</span>
              <span>₹{totals.tax.toFixed(2)}</span>
            </div>
            <div className="stack-row">
              <span className="highlight">Total</span>
              <span className="highlight">₹{totals.total.toFixed(2)}</span>
            </div>
            <div className="input-group" style={{ marginTop: '0.75rem' }}>
              <label className="input-label" htmlFor="coupon">
                Coupon code (optional)
              </label>
              <input
                id="coupon"
                type="text"
                className="input-field"
                placeholder="Enter coupon code"
              />
            </div>
          </div>

          {checkoutError && (
            <div className="danger" style={{ marginBottom: '0.75rem' }}>{checkoutError}</div>
          )}
          <button className="btn btn-primary" onClick={handleCheckout}>
            Place Order
          </button>
        </div>
      )}
    </section>
  );
}

export default Cart;

