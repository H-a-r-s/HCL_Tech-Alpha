import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useCart } from '../context/CartContext';

function ProductCard({ product }) {
  const { isAuthenticated } = useAuth();
  const { addToCart } = useCart();
  const navigate = useNavigate();

  const handleAddToCart = () => {
    if (!isAuthenticated) {
      navigate('/login');
      return;
    }
    addToCart(product, 1);
  };

  return (
    <article className="card">
      <div className="card-header">
        <h3 className="card-title">{product.name}</h3>
        <span className="card-price">₹{product.price.toFixed(2)}</span>
      </div>
      <p className="card-meta muted">{product.description}</p>
      <div className="stack-row">
        <button
          className="btn btn-primary"
          onClick={handleAddToCart}
        >
          {isAuthenticated ? 'Add to Cart' : 'Login to add to cart'}
        </button>
      </div>
    </article>
  );
}

export default ProductCard;

