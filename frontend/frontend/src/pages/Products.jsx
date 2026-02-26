import { useEffect, useState } from 'react';
import ProductCard from '../components/ProductCard';
import * as productService from '../services/productService';

function Products() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    (async () => {
      setLoading(true);
      setError(null);
      try {
        const res = await productService.getProducts();
        setProducts(res.data || []);
      } catch (e) {
        setProducts([]);
        setError('Could not load products from API.');
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  return (
    <section className="page">
      <div className="page-header">
        <div>
          <h1 className="page-title">Products</h1>
          <p className="page-subtitle">
            Browse items and add them to your cart.
          </p>
        </div>
      </div>

      {loading && <div className="empty-state">Loading products…</div>}
      {error && <div className="muted">{error}</div>}

      {!loading && products.length === 0 && (
        <div className="empty-state">No products available yet.</div>
      )}

      <div className="card-grid">
        {products.map(product => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </section>
  );
}

export default Products;

