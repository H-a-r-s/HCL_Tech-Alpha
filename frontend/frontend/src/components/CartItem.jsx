import { useCart } from '../context/CartContext';

function CartItem({ item }) {
  const { updateQuantity, removeFromCart } = useCart();

  return (
    <article className="card">
      <div className="card-header">
        <h3 className="card-title">{item.name}</h3>
        <span className="card-price">
          ₹{(item.price * item.quantity).toFixed(2)}
        </span>
      </div>
      <p className="card-meta muted">Unit price: ₹{item.price.toFixed(2)}</p>
      <div className="stack-row">
        <label className="input-label">
          Qty:
          <input
            type="number"
            min="1"
            className="input-field"
            style={{ width: '70px', marginLeft: '0.5rem' }}
            value={item.quantity}
            onChange={e => updateQuantity(item.id, Number(e.target.value) || 1)}
          />
        </label>
        <button
          className="btn btn-secondary"
          onClick={() => removeFromCart(item.id)}
        >
          Remove
        </button>
      </div>
    </article>
  );
}

export default CartItem;

