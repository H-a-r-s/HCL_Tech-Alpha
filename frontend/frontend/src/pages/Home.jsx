import { useEffect, useMemo, useState } from 'react';
import ProductCard from '../components/ProductCard';
import * as productService from '../services/productService';

function Home() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [search, setSearch] = useState('');
  const [activeCategory, setActiveCategory] = useState('All');

  useEffect(() => {
    (async () => {
      try {
        const [prodRes, catRes] = await Promise.all([
          productService.getProducts(),
          productService.getCategories()
        ]);
        setProducts(prodRes.data || []);
        const cats = catRes.data || [];
        setCategories(['All', ...cats.map(c => c.name || c)]);
      } catch {
        setProducts([]);
        setCategories(['All']);
      }
    })();
  }, []);

  const filteredProducts = useMemo(() => {
    return products.filter(p => {
      const matchesSearch =
        !search ||
        (p.name || '').toLowerCase().includes(search.toLowerCase()) ||
        (p.description || '').toLowerCase().includes(search.toLowerCase());
      const matchesCategory =
        activeCategory === 'All' || p.categoryName === activeCategory;
      return matchesSearch && matchesCategory;
    });
  }, [products, search, activeCategory]);

  return (
    <section className="page">
      <header className="page-header">
        <div>
          <h1 className="page-title">Browse our menu</h1>
          <p className="page-subtitle">
            Search and filter by category.
          </p>
        </div>
      </header>

      <div className="card" style={{ marginBottom: '1rem' }}>
        <div className="filters-row">
          <div className="input-group" style={{ flex: 2 }}>
            <label className="input-label" htmlFor="search">
              Search products
            </label>
            <input
              id="search"
              type="text"
              className="input-field"
              placeholder="Type pizza, drink, bread…"
              value={search}
              onChange={e => setSearch(e.target.value)}
            />
          </div>

          <div className="input-group" style={{ flex: 1 }}>
            <label className="input-label">Category</label>
            <div className="pill-row">
              {categories.map(cat => (
                <button
                  key={cat}
                  type="button"
                  className={`pill pill-filter ${
                    activeCategory === cat ? 'pill-active' : ''
                  }`}
                  onClick={() => setActiveCategory(cat)}
                >
                  {cat}
                </button>
              ))}
            </div>
          </div>
        </div>
      </div>

      {filteredProducts.length === 0 && (
        <div className="empty-state">No products match your filters.</div>
      )}

      <div className="card-grid">
        {filteredProducts.map(product => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </section>
  );
}

export default Home;

