import { Link } from 'react-router-dom';

function OrderConfirmation() {
  return (
    <section className="page">
      <div className="page-header">
        <div>
          <h1 className="page-title">Order placed</h1>
          <p className="page-subtitle">
            Thank you for your purchase. A confirmation email with your order ID,
            status and total amount has been sent to your registered address.
          </p>
        </div>
      </div>

      <div className="card form-card">
        <p className="muted" style={{ marginBottom: '1rem' }}>
          Once the backend notification service is wired, this page will display the
          actual order details returned from the order service and mirror what was
          emailed to you.
        </p>
        <div className="stack-row">
          <Link to="/products" className="btn btn-secondary">
            Continue shopping
          </Link>
          <Link to="/orders" className="btn btn-primary">
            View orders
          </Link>
        </div>
      </div>
    </section>
  );
}

export default OrderConfirmation;

