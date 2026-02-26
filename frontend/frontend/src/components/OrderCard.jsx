function OrderCard({ order }) {
  // Backend order: { id, userId, totalAmount, status, createdAt, items }
  // OrderItem: { id, productId, quantity, priceAtPurchase }
  const total = order.totalAmount ?? order.total ?? 0;
  const date = order.createdAt
    ? new Date(order.createdAt).toLocaleDateString()
    : order.date || '-';
  const items = order.items || [];

  return (
    <article className="card">
      <div className="card-header">
        <h3 className="card-title">Order #{order.id}</h3>
        <span className="card-price">₹{Number(total).toFixed(2)}</span>
      </div>
      <p className="card-meta">
        Placed on {date} • {items.length} items
      </p>
      <div className="stack">
        {items.map(item => {
          const price = item.priceAtPurchase ?? item.price ?? 0;
          const qty = item.quantity ?? 1;
          const displayName = item.name ?? `Product #${item.productId ?? item.id}`;
          return (
            <div key={item.id} className="stack-row">
              <span className="muted">
                {displayName} × {qty} @ ₹{Number(price).toFixed(2)}
              </span>
              <span className="card-price">
                Amount: ₹{(price * qty).toFixed(2)}
              </span>
            </div>
          );
        })}
      </div>
    </article>
  );
}

export default OrderCard;

